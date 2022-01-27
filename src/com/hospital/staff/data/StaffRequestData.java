package com.hospital.staff.data;

public class StaffRequestData {
    private int id;

    private String name;

    private String reg_date;

    private String uuid;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the reg_date
     */
    public String getReg_date() {
	return reg_date;
    }

    /**
     * @param reg_date
     *            the reg_date to set
     */
    public void setReg_date(String reg_date) {
	this.reg_date = reg_date;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
	return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
	this.uuid = uuid;
    }

}
