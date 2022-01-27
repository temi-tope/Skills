package com.hospital.patient.database;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hospital.patient.data.PatientRequestData;
import com.hospital.patient.data.PatientResponseData;
import com.hospital.utilities.Dbcon;
import com.hospital.utilities.ResponseCodeConstant;

public class PatientDB {
    public boolean isSaveToDB(PatientRequestData patientRequestData) {
	boolean response = false;

	Dbcon con = new Dbcon();
	Connection connect = null;
	try {
	    connect = con.Dbconnect();
	    Calendar calendar = Calendar.getInstance();
	    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

	    String name = patientRequestData.getName();
	    int age = patientRequestData.getAge();

	    String query = " insert into patient (name, age)" + " values (?, ?)";

	    PreparedStatement preparedStmt = connect.prepareStatement(query);
	    preparedStmt.setString(1, name);
	    preparedStmt.setInt(2, age);

	    if (preparedStmt.executeUpdate() > 0) {
		response = true;
	    }
	    connect.close();
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	return response;
    }

    public boolean isSaveVisitToDB(PatientRequestData patientRequestData) {
	boolean response = false;

	Dbcon con = new Dbcon();
	Connection connect = null;
	try {
	    connect = con.Dbconnect();
	    Calendar calendar = Calendar.getInstance();
	    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

	    int id = patientRequestData.getId();

	    String query = " insert into visit (patient_id, date)" + " values (?, ?)";

	    PreparedStatement preparedStmt = connect.prepareStatement(query);
	    preparedStmt.setInt(1, id);
	    preparedStmt.setDate(2, startDate);

	    if (preparedStmt.executeUpdate() > 0) {
		response = true;
	    }
	    connect.close();
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	return response;
    }

    public PatientResponseData getPatient(int age, String uuid) {
	PatientResponseData patientResponseData = new PatientResponseData();
	patientResponseData.setResponseCode(ResponseCodeConstant.FAILURE);
	patientResponseData.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	Dbcon con = new Dbcon();
	Connection connect = null;

	try {
	    connect = con.Dbconnect();
	    PreparedStatement stmt;

	    String query = " select uuid from staff where uuid =? ";
	    stmt = connect.prepareStatement(query);
	    stmt.setString(1, uuid);

	    ResultSet rs1 = stmt.executeQuery();

	    List<PatientRequestData> Patientlst = new ArrayList<PatientRequestData>();

	    if (rs1.next()) {
		String query1 = "select a.id, a.name, a.age, max(b.date) AS last_visit_date from patient a inner join visit b on a.id=b.patient_id where a.age <= ? GROUP BY b.patient_id";
		stmt = connect.prepareStatement(query1);
		stmt.setInt(1, age);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
		    PatientRequestData patient = new PatientRequestData();
		    patient.setId(rs.getInt("id"));
		    patient.setName(rs.getString("name"));
		    patient.setAge(rs.getInt("age"));
		    patient.setDate(rs.getDate("last_visit_date"));

		    Patientlst.add(patient);

		    patientResponseData.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
		    patientResponseData.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);

		    patientResponseData.setUsers(Patientlst);

		}
	    }
	} catch (Exception e1) {
	    e1.printStackTrace();
	} finally {
	    try {
		connect.close();
	    } catch (SQLException e) {
	    }
	}

	return patientResponseData;

    }

    private static final String CSV_SEPARATOR = ",";

    public PatientResponseData getCSV(int id, String uuid) {
	PatientResponseData patientResponseData = new PatientResponseData();
	patientResponseData.setResponseCode(ResponseCodeConstant.FAILURE);
	patientResponseData.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	Dbcon con = new Dbcon();
	Connection connect = null;

	try {
	    connect = con.Dbconnect();
	    PreparedStatement stmt;

	    String query = " select uuid from staff where uuid =? ";
	    stmt = connect.prepareStatement(query);
	    stmt.setString(1, uuid);

	    ResultSet rs1 = stmt.executeQuery();

	    List<PatientRequestData> Patientlst = new ArrayList<PatientRequestData>();

	    if (rs1.next()) {
		String query1 = "select * from patient where id = ?";
		stmt = connect.prepareStatement(query1);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		BufferedWriter bw = new BufferedWriter(
			new OutputStreamWriter(new FileOutputStream("patient.csv"), "UTF-8"));

		if (rs.next()) {
		    StringBuffer oneLine = new StringBuffer();
		    oneLine.append(rs.getInt("id"));
		    oneLine.append(CSV_SEPARATOR);
		    oneLine.append(rs.getString("name"));
		    oneLine.append(CSV_SEPARATOR);
		    oneLine.append(rs.getInt("age"));
		    oneLine.append(CSV_SEPARATOR);
		    bw.write(oneLine.toString());

		    patientResponseData.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
		    patientResponseData.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
		}
		bw.flush();
		bw.close();
	    }
	} catch (Exception e1) {
	    e1.printStackTrace();
	} finally {
	    try {
		connect.close();
	    } catch (SQLException e) {
	    }
	}

	return patientResponseData;

    }

    public boolean deleteVisits(Date start, Date end, String uuid) {
	boolean response = false;

	Dbcon con = new Dbcon();
	Connection connect = null;
	try {
	    connect = con.Dbconnect();
	    PreparedStatement stmt;
	    String query = " select uuid from staff where uuid =? ";
	    stmt = connect.prepareStatement(query);
	    stmt.setString(1, uuid);

	    ResultSet rs1 = stmt.executeQuery();

	    List<PatientRequestData> Patientlst = new ArrayList<PatientRequestData>();

	    if (rs1.next()) {

		String query1 = " DELETE FROM visit WHERE date BETWEEN ? and ?";

		stmt = connect.prepareStatement(query1);
		stmt.setDate(1, start);
		stmt.setDate(2, end);

		if (stmt.executeUpdate() > 0) {
		    response = true;
		}
		connect.close();
	    }
	} catch (Exception e1) {
	    e1.printStackTrace();
	}
	return response;
    }
}
