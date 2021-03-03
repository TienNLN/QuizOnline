/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.user_question_answer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tiennln.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class User_Question_AnswerDAO implements Serializable {

    public boolean createUserQuestionAnswer(int quizID, int questionID,
            String choice, boolean isCorrect)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO User_Question_Answer (quizID, questionID,"
                        + " choice, isCorrect) "
                        + "VALUES (?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                stm.setInt(2, questionID);
                stm.setString(3, choice);
                stm.setBoolean(4, isCorrect);

                int executeUpdate = stm.executeUpdate();
                if (executeUpdate > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAnswer(int quizID, int questionID,
            String choice, boolean isCorrect)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE User_Question_Answer "
                        + "SET choice = ?, isCorrect = ? "
                        + "WHERE quizID = ? AND questionID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, choice);
                stm.setBoolean(2, isCorrect);
                stm.setInt(3, quizID);
                stm.setInt(4, questionID);

                int executeUpdate = stm.executeUpdate();
                if (executeUpdate > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int countAnswer(boolean isCorrect, int quizID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = "SELECT COUNT(choice) "
                        + "FROM User_Question_Answer "
                        + "WHERE isCorrect=? "
                        + "AND quizID=?";
                pst = cn.prepareStatement(sqlString);
                pst.setBoolean(1, isCorrect);
                pst.setInt(2, quizID);
                
                result = pst.executeQuery();
                
                if(result.next()){
                    return result.getInt(1);
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

        return -1;
    }
}
