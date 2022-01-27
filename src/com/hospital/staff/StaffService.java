package com.hospital.staff;

import com.hospital.staff.data.StaffRequestData;
import com.hospital.staff.data.StaffResponseData;
import com.hospital.staff.data.UuidResponseData;
import com.hospital.staff.database.StaffDB;
import com.hospital.utilities.ResponseCodeConstant;

public class StaffService {

    public StaffResponseData addStaffToDB(StaffRequestData staffRequestData) {
	StaffResponseData response = new StaffResponseData();
	response.setResponseCode(ResponseCodeConstant.FAILURE);
	response.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	if (new StaffDB().isSaveToDB(staffRequestData)) {
	    response.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
	    response.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
	}
	return response;
    }

    public StaffResponseData getStaff(String sessionId) {
	StaffDB database = new StaffDB();
	StaffResponseData staffResponseData = database.getStaff(sessionId);
	return staffResponseData;
    }

    public UuidResponseData getUuid(int staffid) {
	UuidResponseData response = new UuidResponseData();
	response.setResponseCode(ResponseCodeConstant.FAILURE);
	response.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	StaffDB database = new StaffDB();
	String uuid = database.getUUID(staffid);
	if (!uuid.isEmpty()) {
	    response.setUuId(uuid);
	    response.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
	    response.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
	}

	return response;
    }

    public StaffResponseData updateStaff(StaffRequestData staffRequestData) {
	StaffResponseData response = new StaffResponseData();
	response.setResponseCode(ResponseCodeConstant.FAILURE);
	response.setResponseMessage(ResponseCodeConstant.FAILURE_MESSAGE);

	if (new StaffDB().staffUpdate(staffRequestData)) {
	    response.setResponseCode(ResponseCodeConstant.SUCCESSFUL);
	    response.setResponseMessage(ResponseCodeConstant.SUCCESSFUL_MESSAGE);
	}
	return response;
    }
}
