package com.hospital.utilities;

public class BaseResponseData
{
	private String responseCode;

	private String responseMessage;

	public BaseResponseData(String responseCode, String responseMessage)
	{
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public BaseResponseData()
	{
	};

	public String getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(String responseCode)
	{
		this.responseCode = responseCode;
	}

	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

}
