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
import tiennln.question_choice.Question_ChoiceDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddNewQuestionChoiceServlet", urlPatterns = {"/AddNewQuestionChoiceServlet"})
public class AddNewQuestionChoiceServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AddNewQuestionChoiceServlet.class);

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

        String txtChoiceContent_1 = request.getParameter("txtChoiceContent_1");
        String txtResult_1 = request.getParameter("txtResult_1");

        String txtChoiceContent_2 = request.getParameter("txtChoiceContent_2");
        String txtResult_2 = request.getParameter("txtResult_2");

        String txtChoiceContent_3 = request.getParameter("txtChoiceContent_3");
        String txtResult_3 = request.getParameter("txtResult_3");

        String txtChoiceContent_4 = request.getParameter("txtChoiceContent_4");
        String txtResult_4 = request.getParameter("txtResult_4");

        boolean result_1 = false;
        boolean result_2 = false;
        boolean result_3 = false;
        boolean result_4 = false;

        boolean errorFound = false;

        String url = "addNewQuestionChoicePage";

        try {

            if (txtChoiceContent_1.equalsIgnoreCase(txtChoiceContent_2)) {
                request.setAttribute("DUPLICATE_CHOICE_CONTENT", "Choice content cannot be duplicated !");
                errorFound = true;
            }
            if (txtChoiceContent_1.equalsIgnoreCase(txtChoiceContent_3)) {
                request.setAttribute("DUPLICATE_CHOICE_CONTENT", "Choice content cannot be duplicated !");
                errorFound = true;
            }
            if (txtChoiceContent_1.equalsIgnoreCase(txtChoiceContent_4)) {
                request.setAttribute("DUPLICATE_CHOICE_CONTENT", "Choice content cannot be duplicated !");
                errorFound = true;
            }

            if (txtChoiceContent_2.equalsIgnoreCase(txtChoiceContent_3)) {
                request.setAttribute("DUPLICATE_CHOICE_CONTENT", "Choice content cannot be duplicated !");
                errorFound = true;
            }
            if (txtChoiceContent_2.equalsIgnoreCase(txtChoiceContent_4)) {
                request.setAttribute("DUPLICATE_CHOICE_CONTENT", "Choice content cannot be duplicated !");
                errorFound = true;
            }

            if (txtChoiceContent_3.equalsIgnoreCase(txtChoiceContent_4)) {
                request.setAttribute("DUPLICATE_CHOICE_CONTENT", "Choice content cannot be duplicated !");
                errorFound = true;
            }

            HttpSession session = request.getSession();
            session.setAttribute("QUESTION_ID_ON_TARGET", txtQuestionID.trim());

            if (txtResult_1.equals("true")) {
                result_1 = true;
            }
            if (txtResult_2.equals("true")) {
                result_2 = true;
            }
            if (txtResult_3.equals("true")) {
                result_3 = true;
            }
            if (txtResult_4.equals("true")) {
                result_4 = true;
            }

            List<Boolean> listResult = new ArrayList<>();
            listResult.add(result_1);
            listResult.add(result_2);
            listResult.add(result_3);
            listResult.add(result_4);

            int countResultTrue = 0;
            for (Boolean resultTemp : listResult) {
                if (resultTemp) {
                    countResultTrue += 1;
                }
            }

            if (countResultTrue > 1) {
                request.setAttribute("TRUE_CHOICE_MORE_THAN_1", "1 Question just can have 1 True Choice !");
                errorFound = true;
            }

            if (txtChoiceContent_1.trim().isEmpty()) {
                request.setAttribute("CHOICE_CONTENT_1_EMPTY", "Choice content cannot be empty !");
                errorFound = true;
            }
            if (txtChoiceContent_2.trim().isEmpty()) {
                request.setAttribute("CHOICE_CONTENT_2_EMPTY", "Choice content cannot be empty !");
                errorFound = true;
            }
            if (txtChoiceContent_3.trim().isEmpty()) {
                request.setAttribute("CHOICE_CONTENT_3_EMPTY", "Choice content cannot be empty !");
                errorFound = true;
            }
            if (txtChoiceContent_4.trim().isEmpty()) {
                request.setAttribute("CHOICE_CONTENT_4_EMPTY", "Choice content cannot be empty !");
                errorFound = true;
            }

            if (!errorFound) {
                Question_ChoiceDAO question_ChoiceDAO = new Question_ChoiceDAO();
                boolean added = question_ChoiceDAO.addNewQuestionChoice(Integer.parseInt(txtQuestionID),
                        result_1, txtChoiceContent_1,
                        result_2, txtChoiceContent_2,
                        result_3, txtChoiceContent_3,
                        result_4, txtChoiceContent_4);

                if (!added) {
                    request.setAttribute("ADD_QUESTION_CHOICE_FAILED", "Add failed, please try again !");
                    errorFound = true;
                } else {
                    QuestionDAO questionDAO = new QuestionDAO();
                    boolean updatedStatus = questionDAO.setStatusQuestion(Integer.parseInt(txtQuestionID), true);

                    if (updatedStatus) {
                        url = "adminPage";
                    } else {
                        request.setAttribute("ADD_QUESTION_CHOICE_FAILED", "Add failed, please try again !");
                        errorFound = true;
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
            if (errorFound) {
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
