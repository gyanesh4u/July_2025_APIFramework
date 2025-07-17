package api.endpoint;

public class Routes {
	/* declaring url for all API calls */

	public static String baseURL = "https://api.getpostman.com";

	public static String get_all_workspace = baseURL + "/workspaces";
	public static String get_single_workspace = baseURL + "/workspaces/{workspace_id}";
	public static String post_workspace = baseURL + "/workspaces";
	public static String put_workspce = baseURL + "/workspaces/{workspace_id}";
	public static String delete_workspace = baseURL + "/workspaces/{workspace_id}";
	public static String api_key = "PMAK-6854d6ed1b081f0001390b50-05fc0ff96b0d51e95e6dcbdff9a17a0ada";

}
