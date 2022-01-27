package com.hospital.staff.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.hospital.staff.data.StaffRequestData;
import com.hospital.staff.data.StaffResponseData;
import com.hospital.utilities.Dbcon;
import com.hospital.utilities.ResponseCodeConstant;

public class StaffDB {
    public boolean staffUpdate(StaffRequestData staffRequestData) {
	boolean response = false;

	String uuid = staffRequestData.getUuid();
	String name = staffRequestData.getName();

	Dbcon con = new Dbcon();
	Connection connect = null;
	try {
	    PreparedStatement stmt;
	    connect = con.Dbconnect();

	    String query = "select id from staff where uuid = ?";
	    stmt = connect.prepareStatement(query);
	    stmt.setString(1, uuid);

	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
		String q1 = "UPDATE staff set name = ? WHERE uuid = ?";
		stmt = connect.prepareStatement(q1);
		stmt.setString(1, name);
		stmt.setString(2, uuid);
		stmt.executeUpdate();

		response = true;

	    }
	} catch (Exception e1) {
	    e1.printStackTrace();
	} finally {
	    try {
		connect.close();
	    } catch (SQLException e) {
	    }
	}
	return response;
    }

    public boolean isSaveToDB(StaffRequestData staffRequestData) {
	boolean response = false;

	Dbcon con = new Dbcon();
	Connection connect = null;

	try {
	    connect = con.Dbconnect();
	    Calendar calendar = Calendar.getInstance();
	    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	    UUID uuid = UUID.randomUUID();
	    String uuidAsString = uuid.toString();

	    String name = staffRequestData.getName();

	    String query = " insert into staff (name, uuid, reg_date)" + " values ( ?, ?, ?)";

	    PreparedStatement preparedStmt = connect.prepareStatement(query);

	    preparedStmt.setString(1, name);
	    preparedStmt.setString(2, uuidAsString);
	    preparedStmt.setString(3, startDate + "");

	    if (preparedStmt.executeUpdate() > 0) {
		response = true;
	    }
	    connect.close();
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	return response;
    }

    public StaffResponseData getStaff(String uuId) {

	StaffResponseData staffResponseData = new StaffResponseData();
	staffResponseData.setResponseCode(ResponseCodeConstant.FAILURE);
	staffResponseData.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	Dbcon con = new Dbcon();
	Connection connect = null;

	try {
	    PreparedStatement stmt;
	    connect = con.Dbconnect();

	    String query = "select id from staff where uuid = ?";
	    stmt = connect.prepareStatement(query);
	    stmt.setString(1, uuId);

	    ResultSet rs = stmt.executeQuery();

	    List<StaffRequestData> Stafflst = new ArrayList<StaffRequestData>();

	    if (rs.next()) {
		int staffId = rs.getInt("id");

		String query1 = "select * from staff where id = ?";
		stmt = connect.prepareStatement(query1);
		stmt.setInt(1, staffId);

		ResultSet rs1 = stmt.executeQuery();
		if (rs1.next()) {
		    StaffRequestData staff = new StaffRequestData();
		    staff.setId(rs1.getInt("id"));
		    staff.setName(rs1.getString("name"));

		    Stafflst.add(staff);

		    staffResponseData.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
		    staffResponseData.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);

		}
		staffResponseData.setStaff(Stafflst);
	    }
	} catch (Exception e1) {
	    e1.printStackTrace();
	} finally {
	    try {
		connect.close();
	    } catch (SQLException e) {
	    }
	}
	return staffResponseData;
    }

    public String getUUID(int staffid) {
	Dbcon con = new Dbcon();
	Connection connect = null;

	String uuid = "";
	try {
	    connect = con.Dbconnect();
	    String query = " select uuid from staff where id = ?";

	    PreparedStatement stmt = connect.prepareStatement(query);
	    stmt.setInt(1, staffid);

	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
		uuid = rs.getString("uuid");
	    }
	} catch (Exception e1) {
	    e1.printStackTrace();
	} finally {
	    try {
		connect.close();
	    } catch (SQLException e) {
	    }
	}

	return uuid;
    }

}
