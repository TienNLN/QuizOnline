/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tiennln.user.UserDAO;
import tiennln.util.EncodeUtil;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(SignUpServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String txtEmail = request.getParameter("txtEmail");
        String txtPassword = request.getParameter("txtPassword");
        String txtConfirmPassword = request.getParameter("txtConfirmPassword");
        String txtName = request.getParameter("txtName");

        boolean errorFound = false;
        String url = null;

        try {
            if (txtEmail.trim().isEmpty()) {
                request.setAttribute("EMAIL_EMPTY_ERROR", "Email cannot be empty !");
                errorFound = true;
            }

            if (txtPassword.trim().isEmpty()) {
                request.setAttribute("PASSWORD_EMPTY_ERROR", "Password cannot be empty !");
                errorFound = true;
            } else {
                if (txtConfirmPassword.trim().isEmpty()) {
                    request.setAttribute("CONFIRM_PASSWORD_EMPTY_ERROR", "Confirm password cannot be empty !");
                    errorFound = true;
                } else {
                    if (!txtConfirmPassword.equals(txtPassword)) {
                        request.setAttribute("PASSWORD_CONFIRM_NOT_MATCH_ERROR", "Password and Confirm Password must match !");
                        errorFound = true;
                    }
                }
            }

            if (txtName.trim().isEmpty()) {
                request.setAttribute("NAME_EMPTY_ERROR", "Name cannot be empty !");
                errorFound = true;
            }

            if (errorFound) {
                url = "signUpFailPage";
            } else {
                String encodePassword = EncodeUtil.sha256Encode(txtPassword);

                UserDAO dao = new UserDAO();
                boolean userAdded = dao.addNewUser(txtEmail, encodePassword, txtName);
                if (userAdded) {
                    url = "loginPage";
                } else {
                    request.setAttribute("SIGN_UP_FAIL_ERROR", "Sign Up Fail");
                    errorFound = true;
                    url = "signUpFailPage";
                }
            }
        } catch (NamingException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } finally {
            if (url.equalsIgnoreCase("signUpFailPage")) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
