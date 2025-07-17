package api.endpoint;
import static io.restassured.RestAssured.*;
import api.pojo.WorkspaceRoute;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class WorkspaceEndpoint {

	
	public static Response createWorkspace(WorkspaceRoute wr) {
		
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.header("X-API-Key",Routes.api_key)
		.body(wr).when()
		.post(Routes.post_workspace);
		return response;
	}
	public static Response getAll_Workspace() {
		Response response = given().accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.header("X-API-Key",Routes.api_key)
		.when().get(Routes.get_all_workspace);
		return response;
	}
	public static Response getsingle_Workspace(String workspace_id) {
		Response response = given().accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.header("X-API-Key",Routes.api_key)
		.pathParam("workspace_id", workspace_id)
		.when().get(Routes.get_single_workspace);
		return response;
	}
	public static Response deletesingle_Workspace(String workspace_id) {
		Response response = given().accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.header("X-API-Key",Routes.api_key)
		.pathParam("workspace_id", workspace_id)
		.when().delete(Routes.delete_workspace);
		return response;
	}
}
