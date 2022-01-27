package com.hospital.staff.data;

import java.util.List;

import com.hospital.utilities.BaseResponseData;

public class StaffResponseData extends BaseResponseData {
    public StaffResponseData() {
    };

    public StaffResponseData(String responseCode, String responseMessage) {
	super(responseCode, responseMessage);
    }

    private List<StaffRequestData> staff = null;

    public List<StaffRequestData> getStaff() {
	return staff;
    }

    public void setStaff(List<StaffRequestData> staffs) {
	this.staff = staffs;
    }

}
