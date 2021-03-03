/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.question_choice;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tiennln.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class Question_ChoiceDAO implements Serializable {

    public List<Question_ChoiceDTO> listAllQuestionChoice(int questionID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<Question_ChoiceDTO> listQuestionChoice = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                listQuestionChoice = new ArrayList<>();

                String sqlString = "SELECT * "
                        + "FROM Question_Choice "
                        + "WHERE questionID = ?";
                pst = cn.prepareStatement(sqlString);
                pst.setInt(1, questionID);

                result = pst.executeQuery();

                while (result.next()) {
                    int resultChoiceID = result.getInt("choiceID");
                    int resultQuestionID = result.getInt("questionID");
                    boolean resultIsCorrect = result.getBoolean("isCorrect");
                    String resultChoice = result.getString("choice");

                    Question_ChoiceDTO dto = new Question_ChoiceDTO(resultChoiceID, resultQuestionID, resultIsCorrect, resultChoice);
                    listQuestionChoice.add(dto);
                }
                return listQuestionChoice;
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return null;
    }

    public boolean addNewQuestionChoice(int questionID,
            boolean isCorrect_1, String choiceContent_1,
            boolean isCorrect_2, String choiceContent_2,
            boolean isCorrect_3, String choiceContent_3,
            boolean isCorrect_4, String choiceContent_4
    )
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                int choiceID = nextQuestionChoiceID();

                int choiceID_1 = choiceID;
                int choiceID_2 = choiceID + 1;
                int choiceID_3 = choiceID + 2;
                int choiceID_4 = choiceID + 3;

                String sqlString = "INSERT INTO Question_Choice "
                        + "VALUES(?, ?, ?, ?)";
                pst = cn.prepareStatement(sqlString);
                cn.setAutoCommit(false);

                int result = -1;

                for (int i = 1; i <= 4; i++) {
                    if (i == 1) {
                        pst.setInt(1, choiceID_1);
                        pst.setInt(2, questionID);
                        pst.setBoolean(3, isCorrect_1);
                        pst.setString(4, choiceContent_1);
                        result = pst.executeUpdate();
                    }
                    if (i == 2) {
                        pst.setInt(1, choiceID_2);
                        pst.setInt(2, questionID);
                        pst.setBoolean(3, isCorrect_2);
                        pst.setString(4, choiceContent_2);
                        result = pst.executeUpdate();
                    }
                    if (i == 3) {
                        pst.setInt(1, choiceID_3);
                        pst.setInt(2, questionID);
                        pst.setBoolean(3, isCorrect_3);
                        pst.setString(4, choiceContent_3);
                        result = pst.executeUpdate();
                    }
                    if (i == 4) {
                        pst.setInt(1, choiceID_4);
                        pst.setInt(2, questionID);
                        pst.setBoolean(3, isCorrect_4);
                        pst.setString(4, choiceContent_4);
                        result = pst.executeUpdate();
                    }
                }
                cn.commit();
                cn.setAutoCommit(true);

                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return false;
    }

    public int nextQuestionChoiceID()
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<Integer> listAllQuestionChoiceID = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                listAllQuestionChoiceID = new ArrayList<>();

                String sqlString = "SELECT choiceID "
                        + "FROM Question_Choice";
                pst = cn.prepareStatement(sqlString);

                result = pst.executeQuery();

                while (result.next()) {
                    listAllQuestionChoiceID.add(result.getInt("choiceID"));
                }

                if (!listAllQuestionChoiceID.isEmpty()) {
                    int latestQuestionChoiceID = 0;

                    for (Integer questionChoiceID : listAllQuestionChoiceID) {
                        if (questionChoiceID > latestQuestionChoiceID) {
                            latestQuestionChoiceID = questionChoiceID;
                        }
                    }

                    return latestQuestionChoiceID + 1;
                }
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return 1;
    }

    public boolean trueQuestionExist(int questionID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = "SELECT isCorrect "
                        + "FROM Question_Choice "
                        + "WHERE questionID=?";
                pst = cn.prepareStatement(sqlString);
                pst.setInt(1, questionID);

                result = pst.executeQuery();

                while (result.next()) {
                    if (result.getBoolean("isCorrect")) {
                        return true;
                    }
                }
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return false;
    }

    public boolean updateQuestionChoice(List<Question_ChoiceDTO> listQuestionChoice) //int choiceID, boolean isCorrect, String choiceContent
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = "UPDATE Question_Choice "
                        + "SET isCorrect=?,"
                        + "choice=? "
                        + "WHERE choiceID=?";
                pst = cn.prepareStatement(sqlString);
                cn.setAutoCommit(false);

                int result = -1;

                for (Question_ChoiceDTO question_ChoiceDTO : listQuestionChoice) {
                    pst.setBoolean(1, question_ChoiceDTO.isIsCorrect());
                    pst.setString(2, question_ChoiceDTO.getChoice());
                    pst.setInt(3, question_ChoiceDTO.getChoiceID());
                    result = pst.executeUpdate();
                }

                cn.commit();
                cn.setAutoCommit(true);

                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return false;
    }

    private List<Question_ChoiceDTO> listQuestionChoice;

    public List<Question_ChoiceDTO> getListQuestionChoice() {
        return listQuestionChoice;
    }

    public void getChoiceByQuestion(int questionID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT choiceID, isCorrect, choice "
                        + "FROM Question_Choice "
                        + "WHERE questionID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, questionID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int choiceID = rs.getInt("choiceID");
                    boolean isCorrect = rs.getBoolean("isCorrect");
                    String choice = rs.getString("choice");

                    Question_ChoiceDTO questionChoiceDTO
                            = new Question_ChoiceDTO(choiceID, questionID, isCorrect, choice);

                    if (this.listQuestionChoice == null) {
                        this.listQuestionChoice = new ArrayList<>();
                    }

                    this.listQuestionChoice.add(questionChoiceDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean checkChoice(String choice, int questionID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT isCorrect "
                        + "FROM Question_Choice "
                        + "WHERE questionID = ? AND choice = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, questionID);
                stm.setString(2, choice);

                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getBoolean("isCorrect");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
