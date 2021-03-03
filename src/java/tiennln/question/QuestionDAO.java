/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import tiennln.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class QuestionDAO implements Serializable {

    public List<QuestionDTO> searchQuestion(String question, String subjectID, boolean questionStatus, int pageNumber)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;
        List<QuestionDTO> listQuiz = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = null;
                listQuiz = new ArrayList<>();

                if (!question.isEmpty()) { // có question, có questionStatus
                    if (!subjectID.isEmpty()) { // có question, có questionStatus, có subjectID
                        sqlString = "DECLARE @PageNumber AS INT "
                                + "DECLARE @RowsOfPage AS INT "
                                + "SET @PageNumber=? "
                                + "SET @RowsOfPage=5 "
                                + "SELECT * "
                                + "FROM Question "
                                + "WHERE question like ? "
                                + "AND subjectID = ? "
                                + "AND status = ? "
                                + "ORDER BY questionID "
                                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS "
                                + "FETCH NEXT @RowsOfPage ROWS ONLY";
                        pst = cn.prepareStatement(sqlString);
                        pst.setInt(1, pageNumber);
                        pst.setString(2, "%" + question + "%");
                        pst.setString(3, subjectID);
                        pst.setBoolean(4, questionStatus);
                    } else { // có question, có questionStatus, ko có subjectID
                        sqlString = "DECLARE @PageNumber AS INT "
                                + "DECLARE @RowsOfPage AS INT "
                                + "SET @PageNumber=? "
                                + "SET @RowsOfPage=5 "
                                + "SELECT * "
                                + "FROM Question "
                                + "WHERE question like ? "
                                + "AND status = ? "
                                + "ORDER BY questionID "
                                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS "
                                + "FETCH NEXT @RowsOfPage ROWS ONLY";
                        pst = cn.prepareStatement(sqlString);
                        pst.setInt(1, pageNumber);
                        pst.setString(2, "%" + question + "%");
                        pst.setBoolean(3, questionStatus);
                    }
                } else { // ko có question, có questionStatus và có subjectID
                    if (!subjectID.isEmpty()) { // ko có question, có questionStatus và ko có subjectID
                        sqlString = "DECLARE @PageNumber AS INT "
                                + "DECLARE @RowsOfPage AS INT "
                                + "SET @PageNumber=? "
                                + "SET @RowsOfPage=5 "
                                + "SELECT * "
                                + "FROM Question "
                                + "WHERE subjectID = ? "
                                + "AND status = ? "
                                + "ORDER BY questionID "
                                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS "
                                + "FETCH NEXT @RowsOfPage ROWS ONLY";
                        pst = cn.prepareStatement(sqlString);
                        pst.setInt(1, pageNumber);
                        pst.setString(2, subjectID);
                        pst.setBoolean(3, questionStatus);
                    } else {
                        sqlString = "DECLARE @PageNumber AS INT "
                                + "DECLARE @RowsOfPage AS INT "
                                + "SET @PageNumber=? "
                                + "SET @RowsOfPage=5 "
                                + "SELECT * "
                                + "FROM Question "
                                + "WHERE status = ? "
                                + "ORDER BY questionID "
                                + "OFFSET (@PageNumber-1)*@RowsOfPage ROWS "
                                + "FETCH NEXT @RowsOfPage ROWS ONLY";
                        pst = cn.prepareStatement(sqlString);
                        pst.setInt(1, pageNumber);
                        pst.setBoolean(2, questionStatus);
                    }
                }

                result = pst.executeQuery();

                while (result.next()) {
                    int resultQuestionID = Integer.parseInt(result.getString("questionID"));
                    String resultQuestion = result.getString("question");
                    Timestamp resultCreateDate = result.getTimestamp("createDate");
                    String resultSubjectID = result.getString("subjectID");
                    boolean resultStatus = result.getBoolean("status");

                    QuestionDTO dto = new QuestionDTO(resultQuestionID, resultQuestion, resultCreateDate.toString(), resultSubjectID, resultStatus);
                    listQuiz.add(dto);
                }

                return listQuiz;
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

    public boolean deleteQuestion(int questionID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = "UPDATE Question "
                        + "SET status = 0 "
                        + "WHERE questionID = ?";
                pst = cn.prepareStatement(sqlString);
                pst.setInt(1, questionID);

                int result = pst.executeUpdate();
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

    public boolean updateQuestion(int questionID, String question, String subjectID, boolean status)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = "UPDATE Question "
                        + "SET question=?,"
                        + "subjectID=?,"
                        + "status=? "
                        + "WHERE questionID=?";
                pst = cn.prepareStatement(sqlString);
                pst.setString(1, question);
                pst.setString(2, subjectID);
                pst.setBoolean(3, status);
                pst.setInt(4, questionID);

                int result = pst.executeUpdate();
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

    public boolean addNewQuestion(String question, String subjectID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                int questionID = nextQuestionID();

                Date currentDate = new Date();
                long currentDateTime = currentDate.getTime();

                Timestamp time = new Timestamp(currentDateTime);

                String sqlString = "INSERT INTO Question "
                        + "VALUES(?, ?, ?, ?, 0)";
                pst = cn.prepareStatement(sqlString);
                pst.setInt(1, questionID);
                pst.setString(2, question);
                pst.setTimestamp(3, time);
                pst.setString(4, subjectID);

                int result = pst.executeUpdate();

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

    public int nextQuestionID()
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<Integer> listAllQuestionID = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                listAllQuestionID = new ArrayList<>();

                String sqlString = "SELECT questionID "
                        + "FROM Question";
                pst = cn.prepareStatement(sqlString);

                result = pst.executeQuery();

                while (result.next()) {
                    listAllQuestionID.add(result.getInt("questionID"));
                }

                if (!listAllQuestionID.isEmpty()) {
                    int latestQuestionID = 0;

                    for (Integer questionID : listAllQuestionID) {
                        if (questionID > latestQuestionID) {
                            latestQuestionID = questionID;
                        }
                    }

                    return latestQuestionID + 1;
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

    public int totalNumberOfPage(String question, String subjectID, boolean questionStatus)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = null;

                if (!question.isEmpty()) { // có question, có questionStatus
                    if (!subjectID.isEmpty()) { // có question, có questionStatus, có subjectID
                        sqlString = "SELECT COUNT(questionID) "
                                + "FROM Question "
                                + "WHERE question like ? "
                                + "AND subjectID = ? "
                                + "AND status = ? ";
                        pst = cn.prepareStatement(sqlString);
                        pst.setString(1, "%" + question + "%");
                        pst.setString(2, subjectID);
                        pst.setBoolean(3, questionStatus);
                    } else { // có question, có questionStatus, ko có subjectID
                        sqlString = "SELECT COUNT(questionID) "
                                + "FROM Question "
                                + "WHERE question like ? "
                                + "AND status = ? ";
                        pst = cn.prepareStatement(sqlString);
                        pst.setString(1, "%" + question + "%");
                        pst.setBoolean(2, questionStatus);
                    }
                } else { // ko có question, có questionStatus và có subjectID
                    if (!subjectID.isEmpty()) { // ko có question, có questionStatus và ko có subjectID
                        sqlString = "SELECT COUNT(questionID) "
                                + "FROM Question "
                                + "WHERE subjectID = ? "
                                + "AND status = ? ";
                        pst = cn.prepareStatement(sqlString);
                        pst.setString(1, subjectID);
                        pst.setBoolean(2, questionStatus);
                    } else {
                        sqlString = "SELECT COUNT(questionID) "
                                + "FROM Question "
                                + "WHERE status = ? ";
                        pst = cn.prepareStatement(sqlString);
                        pst.setBoolean(1, questionStatus);
                    }
                }

                result = pst.executeQuery();

                while (result.next()) {
                    int numberOfPage = result.getInt(1);

                    if (numberOfPage < 5) {
                        return 1;
                    } else {
                        if (numberOfPage % 5 != 0) {
                            return (numberOfPage / 5) + 1;
                        } else {
                            return numberOfPage / 5;
                        }
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

        return -1;
    }

    private List<QuestionDTO> listQuestion;

    public List<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    public void getRandomQuestionBySubject(String subjectIDK, int questionAmount)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT top (?) questionID, question, createDate, subjectID "
                        + "FROM Question "
                        + "WHERE subjectID = ? AND status = 1 "
                        + "ORDER BY NEWID()";

                stm = con.prepareStatement(sql);
                stm.setInt(1, questionAmount);
                stm.setString(2, subjectIDK);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int questionID = rs.getInt("questionID");
                    String question = rs.getString("question");
                    String createDate = rs.getString("createDate");

                    QuestionDTO questionDTO
                            = new QuestionDTO(questionID, question, createDate, subjectIDK, true);

                    if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }

                    this.listQuestion.add(questionDTO);
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

    public int countQuestionBySubjectID(String subjectID)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            cn = DBHelper.makeConnection();
            
            if(cn != null){
                String sqlString = "SELECT COUNT(questionID) "
                        + "FROM Question "
                        + "WHERE subjectID=?";
                pst = cn.prepareStatement(sqlString);
                pst.setString(1, subjectID);
                
                result = pst.executeQuery();
                if(result.next()){
                    int totalQuestionOfThisSubject = result.getInt(1);
                    return totalQuestionOfThisSubject;
                }
                
            }
        } finally {
            if(result != null){
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
    
    public boolean setStatusQuestion(int questionID, boolean status)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                String sqlString = "UPDATE Question "
                        + "SET status=? "
                        + "WHERE questionID=?";
                pst = cn.prepareStatement(sqlString);
                pst.setBoolean(1, status);
                pst.setInt(2, questionID);

                int result = pst.executeUpdate();
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
}
