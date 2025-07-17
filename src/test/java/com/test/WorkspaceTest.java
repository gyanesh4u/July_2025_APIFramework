package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoint.WorkspaceEndpoint;
import api.pojo.Workspace;
import api.pojo.WorkspaceRoute;
import io.restassured.response.Response;

public class WorkspaceTest {

	
	Workspace workspace;
	WorkspaceRoute wr;
	
	@BeforeClass
	public void generateTestData() {
		
		workspace=new Workspace();
		workspace.setName("gyanesh");
		workspace.setType("team");
		workspace.setDescription("api framework");
		
		wr=new WorkspaceRoute();
		wr.setWorkspace(workspace);
	}
	@Test(priority = 1)
	public void testCreateWorkSpace() {
		
		Response response = WorkspaceEndpoint.createWorkspace(wr);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		String id = response.jsonPath().getString("workspace.id");

		
		this.workspace.setId(id);
	}
	
	@Test(priority = 2)
	public void testGetAllWorkspace() {
		Response response = WorkspaceEndpoint.getAll_Workspace();
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testGetSingleWorkspace() {
		Response response=WorkspaceEndpoint.getsingle_Workspace(this.workspace.getId());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testDeleteWorkspace() {
		Response response=WorkspaceEndpoint.deletesingle_Workspace(this.workspace.getId());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}
