/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tiennln.question.QuestionDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(UpdateQuestionServlet.class);

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

        String txtQuestionID = request.getParameter("txtQuestionID");
        String txtQuestion = request.getParameter("txtQuestion");
        String txtSubjectID = request.getParameter("txtSubjectID");
        String txtStatus = request.getParameter("txtStatus");

        String searchTextValue = request.getParameter("searchTextValue");
        String searchSubjectValue = request.getParameter("searchSubjectValue");
        String searchStatusValue = request.getParameter("searchStatusValue");

        boolean status = false;
        String url = "search?"
                + "txtSearch=" + searchTextValue
                + "&txtSubject=" + searchSubjectValue
                + "&txtQuestionStatus=" + searchStatusValue;

        boolean errorFound = false;

        try {
            if (txtStatus.trim().equals("Using")) {
                status = true;
            }

            if (txtQuestion.trim().isEmpty()) {
                url += "&txtQuestionIDEmpty=" + txtQuestionID;
                errorFound = true;
            }

            if (!errorFound) {
                QuestionDAO questionDAO = new QuestionDAO();

                boolean updated = questionDAO.updateQuestion(Integer.parseInt(txtQuestionID), txtQuestion, txtSubjectID, status);
                if (!updated) {
                    url = "errorPage";
                }
            }

        } catch (NamingException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } finally {
            response.sendRedirect(url);
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
