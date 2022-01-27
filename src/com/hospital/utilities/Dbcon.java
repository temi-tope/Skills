package com.hospital.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbcon {

    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String pass = "root";
    String dburl = "jdbc:mysql://localhost/hospital";

    public Connection Dbconnect() throws Exception {
	Class.forName(driver);
	Connection con = DriverManager.getConnection(dburl, user, pass);
	return con;
    }
}
