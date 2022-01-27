package com.hospital.staff;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.hospital.controller.Controller;
import com.hospital.patient.data.PatientResponseData;
import com.hospital.staff.data.UuidResponseData;

public class ControllerTest extends JerseyTest {

    @Override
    public Application configure() {
	enable(TestProperties.LOG_TRAFFIC);
	enable(TestProperties.DUMP_ENTITY);
	return new ResourceConfig(Controller.class);
    }

    public void testupdateStaff() {
	UuidResponseData response = (UuidResponseData) target("/update/staff").request();
	assertEquals("should return the uuid",  response.getUuId());
	assertNotNull("Should have a response", response.getResponseMessage());
	System.out.println(response.getUuId());
	System.out.println(response.getResponseMessage());
    }

    @Test
    public void testGetCsv() {
	PatientResponseData output = (PatientResponseData) target("/patient/{uuid}/{age}").request();
	assertEquals("Should return status 00", 00, output.getResponseCode());
	System.out.println(output.getResponseCode());
    }
}
