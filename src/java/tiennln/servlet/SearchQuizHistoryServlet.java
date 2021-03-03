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
import tiennln.quiz.QuizDAO;
import tiennln.quiz.QuizDTO;
import tiennln.subject.SubjectDAO;
import tiennln.user.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchQuizHistoryServlet", urlPatterns = {"/SearchQuizHistoryServlet"})
public class SearchQuizHistoryServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(SearchQuizHistoryServlet.class);

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

        String txtSubjectName = request.getParameter("txtSubjectName");

        String url = "quizHistoryPage";

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO lastUser = (UserDTO) session.getAttribute("LAST_USER");
                if (lastUser != null) {
                    String email = lastUser.getEmail();

                    SubjectDAO subjectDAO = new SubjectDAO();

                    List<String> listSubjectID = subjectDAO.searchSubjectID(txtSubjectName);

                    if (!listSubjectID.isEmpty()) {
                        QuizDAO quizDAO = new QuizDAO();

                        List<QuizDTO> listQuizHistory = quizDAO.searchQuizHistory(email, listSubjectID);

                        request.setAttribute("LIST_QUIZ_HISTORY", listQuizHistory);
                    }
                }
            }

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
