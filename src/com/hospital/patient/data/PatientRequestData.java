package com.hospital.patient.data;

import java.sql.Date;

public class PatientRequestData {
    private Integer id;

    private String name;

    private int age;

    private Date date;

    public void setId(int idd) {
	this.id = idd;
    }

    public void setName(String namee) {
	this.name = namee;
    }

    public Integer getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    /**
     * @return the age
     */
    public int getAge() {
	return age;
    }

    /**
     * @param age
     *            the age to set
     */
    public void setAge(int age) {
	this.age = age;
    }

    /**
     * @return the date
     */
    public Date getDate() {
	return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
	this.date = date;
    }
}
