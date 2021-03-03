/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import tiennln.question.QuestionDAO;
import tiennln.question.QuestionDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(SearchServlet.class);

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

        String txtSearch = request.getParameter("txtSearch");
        String txtSubject = request.getParameter("txtSubject");
        String txtQuestionStatus = request.getParameter("txtQuestionStatus");
        String txtPageNumber = request.getParameter("pageNumber");

        String txtQuestionIDEmpty = request.getParameter("txtQuestionIDEmpty");

        List<QuestionDTO> listQuiz = null;

        boolean questionStatus = false;

        String url = "adminPage";

        try {
            HttpSession session = request.getSession();

            if (txtQuestionStatus.trim().equalsIgnoreCase("usingQuestion")) {
                questionStatus = true;
            }

            if (txtQuestionIDEmpty != null) {
                request.setAttribute("QUESTION_ID_EMPTY", txtQuestionIDEmpty);
            }

            QuestionDAO quizDAO = new QuestionDAO();

            int totalPageNumber = quizDAO.totalNumberOfPage(txtSearch, txtSubject, questionStatus);
            session.setAttribute("NUMBER_OF_PAGE_USER", totalPageNumber);

            if (txtPageNumber == null) {
                listQuiz = quizDAO.searchQuestion(txtSearch, txtSubject, questionStatus, 1);

            } else {
                int pageNumber = Integer.parseInt(txtPageNumber.trim());
                listQuiz = quizDAO.searchQuestion(txtSearch, txtSubject, questionStatus, pageNumber);
            }

            if (!listQuiz.isEmpty()) {
                request.setAttribute("SEARCH_VALUE", listQuiz);
            } else {
                request.setAttribute("NO_SEARCH_RESULT", "No Search Matched !");
            }

            request.setAttribute("SEARCH_TEXT_VALUE", txtSearch);
            request.setAttribute("SEARCH_SUBJECT_VALUE", txtSubject);
            request.setAttribute("SEARCH_STATUS_VALUE", txtQuestionStatus);

        } catch (NamingException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } finally {
            if (url.equals("errorPage")) {
                response.sendRedirect(url);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
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
