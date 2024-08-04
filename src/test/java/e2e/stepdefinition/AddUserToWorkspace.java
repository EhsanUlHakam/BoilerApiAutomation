package e2e.stepdefinition;

import e2e.model.AddUserToWorkspaceModel;
import e2e.payload.AddUserToWorkspaceCreatePayload;
import e2e.stepdefinition.B2C.B2CPlaceOrderAPI;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.BaseClass.BaseClass;
import utils.ConfigUtil.ConfigUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class AddUserToWorkspace {
    public static Logger log = LogManager.getLogger(B2CPlaceOrderAPI.class.getName());
    static Properties myISConfigProp;
    public static String BASE_URI = setConfig().getProperty("baseURI");
    public static String ADD_USER_TO_WORKSPACE_ENDPOINT = setConfig().getProperty("endpointAddUserToWorkspace");
    public static String accessTokenFile = setConfig().getProperty("ACCESSTOKENFILE");

    public static Properties setConfig() {
        return myISConfigProp = ConfigUtil.getConfig("config");
    }

    @When("I hit the post request to add user to workspace using {string}")
    public void i_hit_the_post_request_to_add_user_to_workspace(String email) throws FileNotFoundException {
        BaseClass baseClass = new BaseClass();
        File myObj = new File(accessTokenFile);
        Scanner myReader = new Scanner(myObj);
        String accessToken = myReader.nextLine();

        AddUserToWorkspaceModel payloadRequest = AddUserToWorkspaceCreatePayload.createPayload(email);
        Response response = baseClass.apiPost3(ADD_USER_TO_WORKSPACE_ENDPOINT, BASE_URI, payloadRequest, accessToken);
        log.debug(response.prettyPrint());
    }
}
