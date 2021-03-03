/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.quiz;

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
public class QuizDAO implements Serializable {

    public List<QuizDTO> listAllQuizHistory(String email)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<QuizDTO> listQuizHistory = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                listQuizHistory = new ArrayList<>();

                String sqlString = "SELECT * "
                        + "FROM Quiz "
                        + "WHERE email=?";
                pst = cn.prepareStatement(sqlString);
                pst.setString(1, email);

                result = pst.executeQuery();

                while (result.next()) {
                    int resultQuizID = result.getInt("quizID");
                    String resultDateTakeQuiz = result.getTimestamp("dateTakeQuiz").toString();
                    String resultTimeTakeQuiz = result.getTime("timeTakeQuiz").toString();
                    float resultPoint = result.getFloat("point");
                    String resultSubjectID = result.getString("subjectID");

                    QuizDTO dto = new QuizDTO(resultQuizID, email, resultDateTakeQuiz, resultTimeTakeQuiz, resultPoint, resultSubjectID);
                    listQuizHistory.add(dto);
                }

                return listQuizHistory;
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

    public List<QuizDTO> searchQuizHistory(String email, List<String> listSubjectID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<QuizDTO> listQuizHistory = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                listQuizHistory = new ArrayList<>();

                String sqlString = "SELECT * "
                        + "FROM Quiz "
                        + "WHERE email=? "
                        + "AND subjectID=?";
                
                for (String tempSubjectID : listSubjectID) {
                    pst = cn.prepareStatement(sqlString);
                    pst.setString(1, email);
                    pst.setString(2, tempSubjectID);

                    result = pst.executeQuery();

                    while (result.next()) {
                        int resultQuizID = result.getInt("quizID");
                        String resultDateTakeQuiz = result.getTimestamp("dateTakeQuiz").toString();
                        String resultTimeTakeQuiz = result.getTime("timeTakeQuiz").toString();
                        float resultPoint = result.getFloat("point");
                        String resultSubjectID = result.getString("subjectID");

                        QuizDTO dto = new QuizDTO(resultQuizID, email, resultDateTakeQuiz, resultTimeTakeQuiz, resultPoint, resultSubjectID);
                        listQuizHistory.add(dto);
                    }
                }

                return listQuizHistory;
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
    
    public int getQuizAmount()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Quiz";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }//end while rs is not null
            }//end if con is not null
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
        return 0;
    }

    public boolean createNewQuiz(int quizID, String email, String dateTakeQuiz,
            String timeTakeQuiz, double point, int questionAmount, String subjectID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Quiz (quizID, email, dateTakeQuiz, "
                        + "timeTakeQuiz, point, questionAmount, subjectID) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setInt(1, quizID);
                stm.setString(2, email);
                stm.setString(3, dateTakeQuiz);
                stm.setString(4, timeTakeQuiz);
                stm.setDouble(5, point);
                stm.setInt(6, questionAmount);
                stm.setString(7, subjectID);

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

    public boolean updatePointPlus(int quizID, double point)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Quiz "
                        + "SET point = point + ? "
                        + "WHERE quizID = ?";

                stm = con.prepareStatement(sql);

                stm.setDouble(1, point);
                stm.setInt(2, quizID);

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
    
    public boolean updatePointMinus(int quizID, double point)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Quiz "
                        + "SET point = point - ? "
                        + "WHERE quizID = ?";

                stm = con.prepareStatement(sql);

                stm.setDouble(1, point);
                stm.setInt(2, quizID);

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
}
