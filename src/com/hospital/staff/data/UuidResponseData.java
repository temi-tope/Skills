package com.hospital.staff.data;

import com.hospital.utilities.BaseResponseData;

public class UuidResponseData extends BaseResponseData {
    private String uuId;

    public UuidResponseData() {
    };

    public UuidResponseData(String responseCode, String responseMessage) {
	super(responseCode, responseMessage);
    }

    public String getUuId() {
	return uuId;
    }

    public void setUuId(String uuid) {
	this.uuId = uuid;
    }

}
