package com.hospital.patient.data;

import java.util.List;

import com.hospital.utilities.BaseResponseData;

public class PatientResponseData extends BaseResponseData
{
	public PatientResponseData()
	{
	};

	public PatientResponseData(String responseCode, String responseMessage)
	{
		super(responseCode, responseMessage);
	}

	private List<PatientRequestData> patient = null;

	public List<PatientRequestData> getUsers()
	{
		return patient;
	}

	public void setUsers(List<PatientRequestData> patients)
	{
		this.patient = patients;
	}

}
