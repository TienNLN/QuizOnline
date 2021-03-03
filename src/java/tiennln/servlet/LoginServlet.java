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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tiennln.subject.SubjectDAO;
import tiennln.subject.SubjectDTO;
import tiennln.user.UserDAO;
import tiennln.user.UserDTO;
import tiennln.util.EncodeUtil;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(LoginServlet.class);

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

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");

        String url = "errorPage";

        boolean errorFound = false;

        try {
            if (email.trim().isEmpty()) {
                request.setAttribute("EMAIL_EMPTY_ERROR", "Email cannot be empty !");
                errorFound = true;
            }

            if (password.trim().isEmpty()) {
                request.setAttribute("PASSWORD_EMPTY_ERROR", "Password cannot be empty !");
                errorFound = true;
            }

            if (!errorFound) {
                String encodePassword = EncodeUtil.sha256Encode(password);

                UserDAO dao = new UserDAO();

                UserDTO userLogin = dao.checkLogin(email, encodePassword);

                if (userLogin != null) {
                    HttpSession session = request.getSession();

                    session.setAttribute("LAST_USER", userLogin);

                    url = "adminPage";

                    SubjectDAO subjectDAO = new SubjectDAO();

                    List<SubjectDTO> listSubject = subjectDAO.listAllSubject();

                    session.setAttribute("LIST_SUBJECT", listSubject);
                }
            } else {
                url = "loginFailPage";
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
            if (url.equals("loginFailPage")) {
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
