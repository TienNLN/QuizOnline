/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "UpdateQuestionChoiceServlet", urlPatterns = {"/UpdateQuestionChoiceServlet"})
public class UpdateQuestionChoiceServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(UpdateQuestionChoiceServlet.class);

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

        String txtQuestionChoiceID = request.getParameter("txtQuestionChoiceID");
        String txtQuestionResult = request.getParameter("txtQuestionResult");
        String txtQuestionChoiceContent = request.getParameter("txtQuestionChoiceContent");
        String txtQuestionID = request.getParameter("txtQuestionID");
        String txtOldQuestionResult = request.getParameter("txtOldQuestionResult");

        boolean result = false;

        boolean oldResult = false;

        boolean preUpdate = false;

        String url = "viewQuestionInfo?"
                + "questionID=" + txtQuestionID;

        Map<Integer, Question_ChoiceDTO> mapChoiceIDResult = null;

        boolean errorFound = false;

        try {
            if (txtQuestionResult.trim().equals("true")) {
                result = true;
            }

            if (txtOldQuestionResult.trim().equals("true")) {
                oldResult = true;
            }

            if (txtQuestionChoiceContent.trim().isEmpty()) {
                url += "&txtQuestionChoiceIDEmpty=" + txtQuestionChoiceID;
                errorFound = true;
            }

            if (!errorFound) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    mapChoiceIDResult = (HashMap<Integer, Question_ChoiceDTO>) session.getAttribute("MAP_CHOICE_ID_RESULT");
                    if (oldResult != result) {
                        if (oldResult) {
                            boolean trueChoiceExist = false;
                            for (Integer temp : mapChoiceIDResult.keySet()) {
                                if (mapChoiceIDResult.get(temp).isIsCorrect() && mapChoiceIDResult.get(temp).getChoiceID() != Integer.parseInt(txtQuestionChoiceID)) {
                                    trueChoiceExist = true;
                                }
                            }

                            if (!trueChoiceExist) {
                                Question_ChoiceDTO tempDTO = mapChoiceIDResult.get(Integer.parseInt(txtQuestionChoiceID));
                                tempDTO.setIsCorrect(result);
                                tempDTO.setChoice(txtQuestionChoiceContent);
                                mapChoiceIDResult.put(Integer.parseInt(txtQuestionChoiceID), tempDTO);

                                session.setAttribute("MAP_CHOICE_ID_RESULT", mapChoiceIDResult);
                                request.setAttribute("PRE_UPDATE", "PRE_UPDATE");
                                preUpdate = true;
                                return;
                            } else {
                                mapChoiceIDResult.get(Integer.parseInt(txtQuestionChoiceID)).setIsCorrect(result);
                            }
                        } else {
                            boolean trueChoiceExist = false;
                            for (Integer temp : mapChoiceIDResult.keySet()) {
                                if (mapChoiceIDResult.get(temp).isIsCorrect() && mapChoiceIDResult.get(temp).getChoiceID() != Integer.parseInt(txtQuestionChoiceID)) {
                                    trueChoiceExist = true;
                                }
                            }
                            if (trueChoiceExist) {
                                Question_ChoiceDTO tempDTO = mapChoiceIDResult.get(Integer.parseInt(txtQuestionChoiceID));
                                tempDTO.setIsCorrect(result);
                                tempDTO.setChoice(txtQuestionChoiceContent);
                                mapChoiceIDResult.put(Integer.parseInt(txtQuestionChoiceID), tempDTO);

                                session.setAttribute("MAP_CHOICE_ID_RESULT", mapChoiceIDResult);
                                request.setAttribute("PRE_UPDATE", "PRE_UPDATE");
                                preUpdate = true;
                                return;
                            } else {
                                mapChoiceIDResult.get(Integer.parseInt(txtQuestionChoiceID)).setIsCorrect(result);
                            }
                        }
                    }
                }

                Question_ChoiceDAO question_ChoiceDAO = new Question_ChoiceDAO();

                List<Question_ChoiceDTO> listQuestionChoice = new ArrayList<>();

                for (Integer temp : mapChoiceIDResult.keySet()) {
                    listQuestionChoice.add(mapChoiceIDResult.get(temp));
                }

                for (Question_ChoiceDTO question_ChoiceDTO : listQuestionChoice) {
                    if (question_ChoiceDTO.getChoiceID() == Integer.parseInt(txtQuestionChoiceID)) {
                        question_ChoiceDTO.setChoice(txtQuestionChoiceContent);
                    }
                }

                boolean updated = question_ChoiceDAO.updateQuestionChoice(listQuestionChoice);

                if (updated) {
                    session.removeAttribute("MAP_CHOICE_ID_RESULT");
                } else {
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
            if(url.equals("errorPage")){
                response.sendRedirect(url);
            }
            if (preUpdate) {
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
