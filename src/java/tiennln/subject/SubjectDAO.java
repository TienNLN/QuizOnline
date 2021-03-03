/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.subject;

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
public class SubjectDAO implements Serializable {

    public List<SubjectDTO> listAllSubject()
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<SubjectDTO> listSubject = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {

                listSubject = new ArrayList<>();

                String sqlString = "SELECT * "
                        + "FROM [Subject]";
                pst = cn.prepareStatement(sqlString);

                result = pst.executeQuery();

                while (result.next()) {
                    String subjectID = result.getString("subjectID");
                    String subjectName = result.getString("subjectName");
                    boolean status = result.getBoolean("status");
                    String timeTakeQuiz = result.getTime("timeTakeQuiz").toString();
                    int questionAmount = result.getInt("questionAmount");

                    SubjectDTO dto = new SubjectDTO(subjectID, subjectName, status, timeTakeQuiz, questionAmount);

                    listSubject.add(dto);
                }
                return listSubject;
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

    public List<String> searchSubjectID(String subjectName)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        List<String> listSubjectID = null;

        try {
            cn = DBHelper.makeConnection();

            if (cn != null) {
                listSubjectID = new ArrayList<>();

                String sqlString = "SELECT subjectID "
                        + "FROM [Subject] "
                        + "WHERE subjectName like ?";
                pst = cn.prepareStatement(sqlString);
                pst.setString(1, "%" + subjectName + "%");

                result = pst.executeQuery();

                while (result.next()) {
                    String resultSubjectID = result.getString("subjectID");

                    listSubjectID.add(resultSubjectID);
                }

                return listSubjectID;
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

    private List<SubjectDTO> listSubject = null;

    public List<SubjectDTO> getListSubject() {
        return listSubject;
    }

    public void getAllSubject()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT subjectID, subjectName,"
                        + " status, timeTakeQuiz, questionAmount "
                        + "FROM Subject";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    String subjectName = rs.getString("subjectName");
                    boolean status = rs.getBoolean("status");
                    String timeTakeQuiz = rs.getString("timeTakeQuiz");
                    int questionAmount = rs.getInt("questionAmount");

                    SubjectDTO subjectDTO
                            = new SubjectDTO(subjectID, subjectName, status, timeTakeQuiz, questionAmount);

                    if (this.listSubject == null) {
                        this.listSubject = new ArrayList<>();
                    }

                    this.listSubject.add(subjectDTO);
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

    public SubjectDTO getSubjectByID(String subjectID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT subjectName,"
                        + " status, timeTakeQuiz, questionAmount "
                        + "FROM Subject "
                        + "WHERE subjectID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String subjectName = rs.getString("subjectName");
                    boolean status = rs.getBoolean("status");
                    String timeTakeQuiz = rs.getString("timeTakeQuiz");
                    int questionAmount = rs.getInt("questionAmount");

                    SubjectDTO subjectDTO
                            = new SubjectDTO(subjectID, subjectName, status, timeTakeQuiz, questionAmount);

                    return subjectDTO;
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
        return null;
    }
}
