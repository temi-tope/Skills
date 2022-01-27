package com.hospital.controller;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hospital.patient.PatientService;
import com.hospital.patient.data.PatientRequestData;
import com.hospital.patient.data.PatientResponseData;
import com.hospital.staff.StaffService;
import com.hospital.staff.data.StaffRequestData;
import com.hospital.staff.data.StaffResponseData;
import com.hospital.staff.data.UuidResponseData;
import com.hospital.utilities.Utilities;

@Path("/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class Controller {

    // Patient Registration Endpoint
    @POST
    @Path("/registration/patients")
    public String addPatient(String body) {
	Utilities util = new Utilities();

	// convert JSON request to object
	PatientRequestData patient = (PatientRequestData) util.jsonToObject(body, PatientRequestData.class);

	// call the patient's login service
	PatientService patientService = new PatientService();
	PatientResponseData response = patientService.addPatient(patient);

	// return response
	return util.objectToJson(response);
    }

    // Staff Registration Endpoint
    @POST
    @Path("/registration/staff")
    public String addStaff(String body) {
	Utilities util = new Utilities();

	// convert JSON request to object
	StaffRequestData staffRequestData = (StaffRequestData) util.jsonToObject(body, StaffRequestData.class);

	// call the patient's login service
	StaffService staffService = new StaffService();
	StaffResponseData response = staffService.addStaffToDB(staffRequestData);

	// return response
	return util.objectToJson(response);
    }

    // Update Staff name only
    @POST
    @Path("/update/staff")
    public String updateStaff(String body) {

	Utilities util = new Utilities();

	// convert JSON request to object
	StaffRequestData staffRequestData = (StaffRequestData) util.jsonToObject(body, StaffRequestData.class);

	// call the patient's login service
	StaffService staffService = new StaffService();
	StaffResponseData response = staffService.updateStaff(staffRequestData);

	// convert object to JSON string and return
	return util.objectToJson(response);
    }

    // Get Staff UUID
    @GET
    @Path("/uuid/{staffid}")
    public String getUuid(@PathParam("staffid") int body) {

	Utilities util = new Utilities();

	// call the complain service
	StaffService staffService = new StaffService();
	UuidResponseData response = staffService.getUuid(body);

	// return response
	return util.objectToJson(response);
    }

    @GET
    @Path("/patient/{uuid}/{age}")
    public String getPatient(@PathParam("uuid") String uuid, @PathParam("age") int age) {

	Utilities util = new Utilities();

	// call the patient's login service
	PatientService patientService = new PatientService();
	PatientResponseData response = patientService.getPatient(age, uuid);

	// return response
	return util.objectToJson(response);
    }

    @GET
    @Path("/patientcsv/{uuid}/{id}")
    public String getPatientCSV(@PathParam("uuid") String uuid, @PathParam("id") int id) {

	Utilities util = new Utilities();

	// call the patient's login service
	PatientService patientService = new PatientService();
	PatientResponseData response = patientService.getCsv(id, uuid);

	// return response
	return util.objectToJson(response);
    }

    @DELETE
    @Path("/visits/{uuid}/{start}/{end}")
    public String deleteVisit(@PathParam("start") String start, @PathParam("end") String end,
	    @PathParam("uuid") String uuid) {
	Utilities util = new Utilities();

	// call the patient's login service
	PatientService patientService = new PatientService();
	PatientResponseData response = patientService.deleteVisit(Date.valueOf(start), Date.valueOf(end), uuid);

	// return response
	return util.objectToJson(response);
    }

}
