/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennln.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws NamingException, SQLException {

        // 1. Get current Context
        Context currentContext = new InitialContext();

        // 2. Get Server Context
        Context serverContext = (Context) currentContext.lookup("java:comp/env");

        // 3. Get Datasource
        DataSource dataSource = (DataSource) serverContext.lookup("QuizOnlineDS");

        // 4. Make Connection
        Connection cn = dataSource.getConnection();
        
        return cn;
    }
}
