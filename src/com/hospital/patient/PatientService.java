package com.hospital.patient;

import java.sql.Date;

import com.hospital.patient.data.PatientRequestData;
import com.hospital.patient.data.PatientResponseData;
import com.hospital.patient.database.PatientDB;
import com.hospital.utilities.ResponseCodeConstant;

public class PatientService {

    public PatientResponseData addPatient(PatientRequestData patientRequestData) {
	PatientResponseData response = new PatientResponseData();
	response.setResponseCode(ResponseCodeConstant.FAILURE);
	response.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	if (new PatientDB().isSaveToDB(patientRequestData)) {
	    response.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
	    response.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
	}
	return response;
    }

    public PatientResponseData addVisit(PatientRequestData patientRequestData) {
	PatientResponseData response = new PatientResponseData();
	response.setResponseCode(ResponseCodeConstant.FAILURE);
	response.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	if (new PatientDB().isSaveVisitToDB(patientRequestData)) {
	    response.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
	    response.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
	}
	return response;
    }

    public PatientResponseData getPatient(int age, String uuid) {
	PatientDB database = new PatientDB();
	PatientResponseData patientResponseData = database.getPatient(age, uuid);
	return patientResponseData;
    }

    public PatientResponseData getCsv(int id, String uuid) {
	PatientDB database = new PatientDB();
	PatientResponseData patientResponseData = database.getCSV(id, uuid);
	return patientResponseData;
    }

    public PatientResponseData deleteVisit(Date start, Date end, String uuid) {
	PatientResponseData response = new PatientResponseData();
	response.setResponseCode(ResponseCodeConstant.FAILURE);
	response.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	if (new PatientDB().deleteVisits(start, end, uuid)) {
	    response.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
	    response.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
	}
	return response;
    }

}
