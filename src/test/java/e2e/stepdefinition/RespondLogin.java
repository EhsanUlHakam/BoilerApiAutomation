package e2e.stepdefinition;

import e2e.model.RespondLoginModel;
import e2e.payload.RespondLoginCreatePayload;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import utils.BaseClass.BaseClass;
import utils.ConfigUtil.ConfigUtil;

import java.io.IOException;
import java.util.Properties;

public class RespondLogin {
    final String accessToken = "accessToken";
    static Properties myISConfigProp;
    public static String BASE_URI = setConfig().getProperty("baseURI");
    public static String LOGIN_ENDPOINT = setConfig().getProperty("endpointLogin");
    BaseClass baseClass = new BaseClass();
    SoftAssert softAssert = new SoftAssert();
    Assertion hardAssert = new Assertion();

    public static Properties setConfig() {
        return myISConfigProp = ConfigUtil.getConfig("config");
    }

    @When("I hit the post request of respond.io login api with following {string} and {string}")
    public void iHitThePostRequestOfRetailLoginApi(String email, String password) throws IOException {
        RespondLoginModel payloadRequest = RespondLoginCreatePayload.createPayload(email, password);
        Response response = baseClass.apiPostWithZeroHeaders(LOGIN_ENDPOINT, BASE_URI, payloadRequest);
        String access_token = response.jsonPath().getString("idToken");
        baseClass.createOrUpdateFile(access_token, accessToken);
    }
}
