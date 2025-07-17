package com.test;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoint.WorkspaceEndpoint;
import api.pojo.Workspace;
import api.pojo.WorkspaceRoute;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class WorkspaceTest {

	
	Workspace workspace;
	WorkspaceRoute wr;
	public static Logger logger;
	@BeforeClass
	public void generateTestData() {
		
		workspace=new Workspace();
		workspace.setName("gyanesh");
		workspace.setType("team");
		workspace.setDescription("api framework");
		
		wr=new WorkspaceRoute();
		wr.setWorkspace(workspace);
		logger = LogManager.getLogger("RestAssuredAutomationFramework_test");
	}
	@Test(priority = 1)
	public void testCreateWorkSpace() {
		
		Response response = WorkspaceEndpoint.createWorkspace(wr);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		String id = response.jsonPath().getString("workspace.id");

		
		this.workspace.setId(id);
		logger.info("Created new workspace");
	}
	
	@Test(priority = 2)
	public void testGetAllWorkspace() {
		Response response = WorkspaceEndpoint.getAll_Workspace();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Fetched all workspace");
		
	}
	
	@Test(priority = 3)
	public void testGetSingleWorkspace() {
		Response response=WorkspaceEndpoint.getsingle_Workspace(this.workspace.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteWorkspace() {
		Response response=WorkspaceEndpoint.deletesingle_Workspace(this.workspace.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Deleted workspace");
	}
	

}
