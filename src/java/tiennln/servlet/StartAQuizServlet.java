/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import tiennln.question.QuestionDAO;
import tiennln.question.QuestionDTO;
import tiennln.question_choice.Question_ChoiceDAO;
import tiennln.question_choice.Question_ChoiceDTO;
import tiennln.quiz.QuizDAO;
import tiennln.subject.SubjectDAO;
import tiennln.subject.SubjectDTO;
import tiennln.user.UserDTO;
import tiennln.user_question_answer.User_Question_AnswerDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StartAQuizServlet", urlPatterns = {"/StartAQuizServlet"})
public class StartAQuizServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(StartAQuizServlet.class);

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

        String subjectID = request.getParameter("SubjectID");
        String email = null;

        String url = "quizPage";

        try {

            HttpSession session = request.getSession();

            UserDTO lastUser = (UserDTO) session.getAttribute("LAST_USER");

            if (lastUser != null) {
                email = lastUser.getEmail();
            } else {
                url = "errorPage";
                return;
            }

            SubjectDAO subjectDAO = new SubjectDAO();
            QuestionDAO questionDAO = new QuestionDAO();

            if (questionDAO.countQuestionBySubjectID(subjectID) < subjectDAO.getSubjectByID(subjectID).getQuestionAmount()) {
                url = "errorPage";
                return;
            } else {
                //0. create quiz in table quiz
                ////0.1. get current time start
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String startTime = dtf.format(now);

                ////0.2. set defaut timeTakeQuiz, point, questionAmount
                subjectDAO = new SubjectDAO();
                SubjectDTO subjectByID = subjectDAO.getSubjectByID(subjectID);

                String timeTakeQuiz = subjectByID.getTimeTakeQuiz();
                int point = 0;
                int questionAmount = subjectByID.getQuestionAmount();

                ////0.3. set auto ID
                QuizDAO quizDAO = new QuizDAO();
                int quizID = quizDAO.getQuizAmount() + 1;
                boolean createNewQuiz = quizDAO.createNewQuiz(quizID, email, startTime,
                        timeTakeQuiz, point, questionAmount, subjectID);

                /////////////////////////////////
                if (createNewQuiz) {
                    //1. load random n question in database by subject ID

                    questionDAO = new QuestionDAO();
                    questionDAO.getRandomQuestionBySubject(subjectID, questionAmount);
                    List<QuestionDTO> listQuestion = questionDAO.getListQuestion();

                    //2. save question list to database at table user_question_answer
                    String choice = "NOT ANSWER";
                    boolean isCorrect = false;
                    User_Question_AnswerDAO userQuestionAnswerDAO
                            = new User_Question_AnswerDAO();
                    for (QuestionDTO questionDTO : listQuestion) {
                        userQuestionAnswerDAO.createUserQuestionAnswer(quizID,
                                questionDTO.getQuestionID(), choice, isCorrect);
                    }

                    //3. load first question with answer
                    Question_ChoiceDAO questionChoiceDAO = new Question_ChoiceDAO();
                    questionChoiceDAO.getChoiceByQuestion(listQuestion.get(0).getQuestionID());
                    List<Question_ChoiceDTO> listQuestionChoice = questionChoiceDAO.getListQuestionChoice();

                    //4. set time limit for quiz
                    String[] hourMin = subjectByID.getTimeTakeQuiz().split(":");
                    int hour = Integer.parseInt(hourMin[0]);
                    int mins = Integer.parseInt(hourMin[1]);
                    int timeTake = hour * 60 + mins;
                    LocalDateTime end = now.plusMinutes(timeTake);

                    //5. create a map for check question true or false to get point
                    Map<QuestionDTO, Boolean> questionCorrect = new HashMap<QuestionDTO, Boolean>();
                    for (QuestionDTO questionDTO : listQuestion) {
                        questionCorrect.put(questionDTO, false);
                    }

                    Map<QuestionDTO, String> questionAnswer = new HashMap<QuestionDTO, String>();
                    for (QuestionDTO questionDTO : listQuestion) {
                        questionAnswer.put(questionDTO, "NOT ANSWER");
                    }

                    //6. set attribute question and answer
                    session.setAttribute("CHECK_QUESTION", questionCorrect);
                    session.setAttribute("QUESTION_ANSWER", questionAnswer);
                    session.setAttribute("QUESTION_LIST", listQuestion);
                    session.setAttribute("QUIZID", quizID);
                    session.setAttribute("SUBJECT", subjectID);
                    session.setAttribute("AMOUNT", questionAmount);
                    session.setAttribute("TIME_END", dtf.format(end));
                    request.setAttribute("QUESTION", listQuestion.get(0));
                    request.setAttribute("ANSWER", listQuestionChoice);
                    request.setAttribute("Q_NUMBER", 1);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            url = "errorPage";
        } catch (NamingException ex) {
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
