/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.question_choice;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Question_ChoiceDTO implements Serializable {

    private int choiceID;
    private int questionID;
    private boolean isCorrect;
    private String choice;

    public Question_ChoiceDTO() {
    }

    public Question_ChoiceDTO(int choiceID, int questionID, boolean isCorrect, String choice) {
        this.choiceID = choiceID;
        this.questionID = questionID;
        this.isCorrect = isCorrect;
        this.choice = choice;
    }

    /**
     * @return the choiceID
     */
    public int getChoiceID() {
        return choiceID;
    }

    /**
     * @param choiceID the choiceID to set
     */
    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    /**
     * @return the questionID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the isCorrect
     */
    public boolean isIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    /**
     * @return the choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * @param choice the choice to set
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

}
