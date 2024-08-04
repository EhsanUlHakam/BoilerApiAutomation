package utils.BaseClass;

import com.opencsv.CSVWriter;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.Assertion;
import utils.ConfigUtil.ConfigUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Map;

public class BaseClass {
    public static Logger log = LogManager.getLogger(BaseClass.class.getName());
    static Map<String, Object> dataConfig;

    static {
        try {
            dataConfig = ConfigUtil.getYaml();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Assertion hardassert = new Assertion();
    String dbURL = dataConfig.get("dbURL").toString();
    String user = dataConfig.get("dbUser").toString();
    String pass = dataConfig.get("dbPass").toString();

    public Response apiGetWithOneHeaders(String baseURL, String endpoint, String header1) {
        Response response = SerenityRest.given()
                .headers("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .contentType("application/json")
                .when()
                .get(baseURL + endpoint);

        return response;
    }

    public Response apiGetWithTwoHeadersAppType(String baseURL, String endpoint, String token, String apptype) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", apptype)
                .contentType("application/json")
                .when()
                .get(baseURL + endpoint);

        return response;
    }

    public Response apiPATCHWithTwoHeaderAppTypeOnlyPathParam(
            String endpoint, String baseURL, String header1, String header2, String WareHouseHubProductID) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .pathParam("WareHouseHubProductID", WareHouseHubProductID)
                .contentType("application/json")
                .log()
                .all()
                .when()
                .patch(baseURL + endpoint);

        return response;
    }

    public Response apiPATCHWithTwoHeaderAppTypePathParam(
            String endpoint, String baseURL, Object object, String header1, String header2, String HubProductID) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .pathParam("HubProductID", HubProductID)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .patch(baseURL + endpoint);

        return response;
    }

    public Response apiPUTWithTwoHeaderAppType(
            String endpoint, String baseURL, Object object, String header1, String header2) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .log()
                .all()
                .put(baseURL + endpoint);

        return response;
    }

    public Response apiPUTWithTwoHeaderAppTypePathParam(
            String endpoint, String baseURL, Object object, String header1, String header2, String ProductID) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .pathParam("ProductID", ProductID)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .put(baseURL + endpoint);

        return response;
    }

    public Response apiPost(String endpoint, String baseURL, Object object, String Token) {
        Response response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .baseUri(baseURL)
                .auth()
                .preemptive()
                .basic("Authorization", "Bearer" + Token)
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(object)
                .when()
                .post(endpoint);

        return response;
    }

    public Response apiPost2(String endpoint, String baseURL, Object object, String Token) {
        Response response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + Token)
                .header("Content-Type", "application/json")
                .body(object)
                .when()
                .post(endpoint);

        return response;
    }

    public Response apiPost3(String endpoint, String baseURL, Object object, String Token) {
        Response response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType("application/json")
                .header("Authorization", "Bearer " + Token)
                .header("Content-Type", "application/json")
                .body(object)
                .when()
                .post(endpoint);

        return response;
    }

    public Response apiPostFormData(String endpoint, String baseURL, Object object, String header1, String header2) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostLoginWithAppType(String endpoint, String baseURL, Object object, String appType) {
        Response response = SerenityRest.given()
                .header("app-type", appType)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithNoAuthAppType(String endpoint, String baseURL, Object object, String apptype) {
        Response response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .header("app-type", apptype)
                .body(object)
                .when()
                .post(endpoint);

        return response;
    }

    public Response apiPostWithNoBodyAppType(String endpoint, String baseURL, String token, String apptype) {
        Response response = SerenityRest.given()
                .header("refreshToken", token)
                .header("app-type", apptype)
                .contentType("application/json")
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithOneHeader(String endpoint, String baseURL, Object object, String header1) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header1)
                .contentType("application/json")
                .body(object)
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithOneHeaderAppType(String endpoint, String baseURL, Object object, String header1) {
        Response response = SerenityRest.given()
                .header("app-type", header1)
                .contentType("application/json")
                .body(object)
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithTwoHeaderAppType(
            String endpoint, String baseURL, Object object, String token, String apptype) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", apptype)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithTwoHeaders(
            String endpoint, String baseURL, String header1, String header2, String userName, String password) {
        Response response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .baseUri(baseURL)
                .auth()
                .preemptive()
                .basic(userName, password)
                .header("BranchID", header1)
                .header("BankId", header2)
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when()
                .post(endpoint);

        return response;
    }

    public Response apiPostWithOneQueryParamsAppType(
            String endpoint, String baseURL, String token, String apptype, String keyOne, String valueOne) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", apptype)
                .contentType("application/json")
                .queryParam("" + keyOne + "", valueOne)
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiGetWithOnePathParamsAppType(
            String endpoint, String baseURL, String token, String apptype, String keyOne, String valueOne) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", apptype)
                .contentType("application/json")
                .pathParam("" + keyOne + "", valueOne)
                .log()
                .all()
                .when()
                .get(baseURL + endpoint);

        return response;
    }

    public Response apiGetWithOneQueryParamsAppType(
            String endpoint, String baseURL, String token, String apptype, String keyOne, String valueOne) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", apptype)
                .contentType("application/json")
                .queryParam("" + keyOne + "", valueOne)
                .log()
                .all()
                .when()
                .get(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithTwoQueryParamsAppType(
            String endpoint, String baseURL, String token, String apptype, Integer orderId, boolean mergeCart) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", apptype)
                .contentType("application/json")
                .queryParam("orderId", orderId)
                .queryParam("shouldMergeToExistingCart", mergeCart)
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithUploadFile(
            String endpoint, String baseURL, File Product_Hub_Binding, String header1, String header2) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .multiPart(Product_Hub_Binding)
                .log()
                .all()
                .when()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPostWithZeroHeaders(String endpoint, String baseURL, Object object) {
        Response response = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(object)
                .when()
                .log()
                .all()
                .post(baseURL + endpoint);

        return response;
    }

    public Response apiPutWithTwoHeadersandOnePathParam(
            String endpoint, String baseURL, Object object, String token, String appType, String tripId) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", appType)
                .pathParam("id", tripId)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .put(baseURL + endpoint);

        return response;
    }

    public Response apiPutWithTwoHeadersandTwoPathParam(
            String endpoint, String baseURL, Object object, String token, String appType, String id, boolean isActive) {
        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + token, "Authorization", "Bearer " + token)
                .header("app-type", appType)
                .pathParam("id", id)
                .pathParam("isActive", isActive)
                .contentType("application/json")
                .body(object)
                .log()
                .all()
                .when()
                .put(baseURL + endpoint);

        return response;
    }

    public int boolToInt(boolean tf) {
        int output;

        if (tf) {
            output = 1;
        } else {
            output = 0;
        }

        return output;
    }

    public void connectionClose(Connection Connection) throws SQLException {
        Connection.close();
    }

    public void createOrUpdateFile(String value, String filename) throws IOException {
        File myObj5 = new File("C:\\accessTokens\\" + filename + ".txt");

        if (myObj5.exists()) {
            myObj5.delete();
            myObj5.createNewFile();

            FileWriter myWriter = new FileWriter("C:\\accessTokens\\" + filename + ".txt");

            myWriter.write(value);
            myWriter.close();
            log.info("Successfully wrote to the file: " + filename);
        } else {
            myObj5.createNewFile();

            FileWriter myWriter = new FileWriter("C:\\accessTokens\\" + filename + ".txt");

            myWriter.write(value);
            myWriter.close();
            log.info("Successfully wrote to the file: " + filename);
        }
    }

    public void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // add data to csv
            writer.writeNext(new String[] {text});

            // closing writer connection
            writer.close();
            log.info("Successfully written: " + text + " To file: " + filePath);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void deleteFileAndAddHeader(String filePath) {
        try {
            Files.delete(Paths.get(filePath));
            log.info("File deleted successfully");
        } catch (NoSuchFileException x) {
            log.error("%s: no such" + " file or directory%n", filePath);
        } catch (DirectoryNotEmptyException x) {
            log.error("%s not empty%n", filePath);
        } catch (IOException x) {
            // File permission problems are caught here.
            log.error(x);
        }
    }

    public ResultSet sqlConnectionCreate(String QUERY) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;

        try {
            log.info(dbURL + " " + user + " " + pass);
            conn = DriverManager.getConnection(dbURL, user, pass);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(String.valueOf(QUERY));
        } catch (Exception e) {
            log.error(e);
        }

        return rs;
    }

    public int isNotNull(Object input) {
        int output;

        if (input == null) {
            output = 0;
        } else {
            output = 1;
        }

        return output;
    }

    public Response getRequestWithOneQueryParam(
            String baseURL, String endpoint, String header1, String header2, String name, String value) {
        log.info("Inside BaseClass Endpoint values --> " + baseURL + endpoint);

        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .header("app-type", header2)
                .param(name, value)
                .when()
                .log()
                .all()
                .get(baseURL + endpoint);

        return response;
    }

    public Response getRequestWithQueryParam(String baseURL, String endpoint, String header1, String promocode) {
        log.info("Inside BaseClass Endpoint values --> " + baseURL + endpoint);

        Response response = SerenityRest.given()
                .header("Authorization", "Bearer " + header1, "Authorization", "Bearer " + header1)
                .param("promoCode", promocode)
                .when()
                .log()
                .all()
                .get(baseURL + endpoint);

        return response;
    }
}
