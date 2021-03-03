/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tiennln.question_choice.Question_ChoiceDAO;
import tiennln.question_choice.Question_ChoiceDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewQuestionInformationServlet", urlPatterns = {"/ViewQuestionInformationServlet"})
public class ViewQuestionInformationServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(ViewQuestionInformationServlet.class);

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

        String questionID = request.getParameter("questionID");

        String txtQuestionChoiceIDEmpty = request.getParameter("txtQuestionChoiceIDEmpty");

        String url = "adminPage";

        Map<Integer, Question_ChoiceDTO> mapChoiceIDResult = null;

        try {
            if (txtQuestionChoiceIDEmpty != null) {
                request.setAttribute("QUESTION_CHOICE_ID_EMPTY", txtQuestionChoiceIDEmpty);
            }

            request.setAttribute("VIEWING_INFO_QUESTION_ID", questionID);

            HttpSession session = request.getSession();

            Question_ChoiceDAO question_ChoiceDAO = new Question_ChoiceDAO();

            List<Question_ChoiceDTO> listQuestionChoice = question_ChoiceDAO.listAllQuestionChoice(Integer.parseInt(questionID));

            if (request.getAttribute("PRE_UPDATE") != null) {
                mapChoiceIDResult = (HashMap<Integer, Question_ChoiceDTO>) session.getAttribute("MAP_CHOICE_ID_RESULT");

                for (Question_ChoiceDTO question_ChoiceDTO : listQuestionChoice) {
                    question_ChoiceDTO.setIsCorrect(mapChoiceIDResult.get(question_ChoiceDTO.getChoiceID()).isIsCorrect());
                    question_ChoiceDTO.setChoice(mapChoiceIDResult.get(question_ChoiceDTO.getChoiceID()).getChoice());
                }
            } else {
                mapChoiceIDResult = new HashMap<>();

                for (Question_ChoiceDTO question_ChoiceDTO : listQuestionChoice) {
                    mapChoiceIDResult.put(question_ChoiceDTO.getChoiceID(), question_ChoiceDTO);
                }
            }

            if (!listQuestionChoice.isEmpty()) {
                request.setAttribute("LIST_QUESTION_CHOICE", listQuestionChoice);
                session.setAttribute("MAP_CHOICE_ID_RESULT", mapChoiceIDResult);
            } else {
                request.setAttribute("NO_QUESTION_CHOICE", "This Question has had no choice yet !");
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
