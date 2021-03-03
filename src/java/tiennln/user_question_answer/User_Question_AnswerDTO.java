/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.user_question_answer;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class User_Question_AnswerDTO implements Serializable {

    private int quizID;
    private int questionID;
    private String choice;
    private boolean isCorrect;

    public User_Question_AnswerDTO() {
    }

    public User_Question_AnswerDTO(int quizID, int questionID, String choice, boolean isCorrect) {
        this.quizID = quizID;
        this.questionID = questionID;
        this.choice = choice;
        this.isCorrect = isCorrect;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.quizID;
        hash = 37 * hash + this.questionID;
        hash = 37 * hash + Objects.hashCode(this.choice);
        hash = 37 * hash + (this.isCorrect ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User_Question_AnswerDTO other = (User_Question_AnswerDTO) obj;
        if (this.quizID != other.quizID) {
            return false;
        }
        if (this.questionID != other.questionID) {
            return false;
        }
        if (this.isCorrect != other.isCorrect) {
            return false;
        }
        if (!Objects.equals(this.choice, other.choice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserQuestionAnswerDTO{" + "quizID=" + quizID + ", questionID=" + questionID + ", choice=" + choice + ", isCorrect=" + isCorrect + '}';
    }
}
