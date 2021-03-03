/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.subject;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class SubjectDTO implements Serializable {

    private String subjectID;
    private String subjectName;
    private boolean status;
    private String timeTakeQuiz;
    private int questionAmount;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, boolean status, String timeTakeQuiz, int questionAmount) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.status = status;
        this.timeTakeQuiz = timeTakeQuiz;
        this.questionAmount = questionAmount;
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

    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
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
     * @return the quizAmount
     */
    public int getQuestionAmount() {
        return questionAmount;
    }

    /**
     * @param quizAmount the quizAmount to set
     */
    public void setQuizAmount(int quizAmount) {
        this.questionAmount = quizAmount;
    }

    
}
