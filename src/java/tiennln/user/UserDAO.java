/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.user;

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
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String email, String password)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sqlString = "SELECT * "
                        + "FROM [User] "
                        + "WHERE email=? "
                        + "And password=? "
                        + "And status=1";
                pst = cn.prepareStatement(sqlString);
                pst.setString(1, email);
                pst.setString(2, password);

                result = pst.executeQuery();

                if (result.next()) {
                    String name = result.getString("name");
                    String role = result.getString("role");

                    UserDTO dto = new UserDTO(email, name, password, role, true);
                    return dto;
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

        return null;
    }

    public boolean addNewUser(String email, String password, String name)
            throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBHelper.makeConnection();
            
            if(cn !=null){
                String sqlString = "INSERT INTO [User]"
                        + "(email, name, password, role, status) "
                        + "VALUES(?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sqlString);
                pst.setString(1, email);
                pst.setString(2, name);
                pst.setString(3, password);
                pst.setString(4, "student");
                pst.setBoolean(5, true);
                
                int result = pst.executeUpdate();
                
                if(result > 0){
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
