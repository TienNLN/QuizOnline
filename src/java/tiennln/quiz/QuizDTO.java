/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.quiz;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class QuizDTO implements Serializable {

    private int quizID;
    private String email;
    private String dateTakeQuiz;
    private String timeTakeQuiz;
    private float point;
    private String subjectID;

    public QuizDTO() {
    }

    public QuizDTO(int quizID, String email, String dateTakeQuiz, String timeTakeQuiz, float point, String subjectID) {
        this.quizID = quizID;
        this.email = email;
        this.dateTakeQuiz = dateTakeQuiz;
        this.timeTakeQuiz = timeTakeQuiz;
        this.point = point;
        this.subjectID = subjectID;
    }

    /**
     * @return the quizID
     */
    public int getQuizID() {
        return quizID;
    }

    /**
     * @param quizID the quizID to set
     */
    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dateTakeQuiz
     */
    public String getDateTakeQuiz() {
        return dateTakeQuiz;
    }

    /**
     * @param dateTakeQuiz the dateTakeQuiz to set
     */
    public void setDateTakeQuiz(String dateTakeQuiz) {
        this.dateTakeQuiz = dateTakeQuiz;
    }

    /**
     * @return the timeTakeQuiz
     */
    public String getTimeTakeQuiz() {
        return timeTakeQuiz;
    }

    /**
     * @param timeTakeQuiz the timeTakeQuiz to set
     */
    public void setTimeTakeQuiz(String timeTakeQuiz) {
        this.timeTakeQuiz = timeTakeQuiz;
    }

    /**
     * @return the point
     */
    public float getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(float point) {
        this.point = point;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

}
