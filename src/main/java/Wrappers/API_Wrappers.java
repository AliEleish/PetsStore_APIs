package Wrappers;

import DataReaders.ReadPropertiesFile;
import Enums.Endpoints;
import io.restassured.parsing.Parser;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API_Wrappers {

    public static <T> T getPetByStatus(String baseurlFilepath, Endpoints endpoint, Map<String,String> queryParams
            , Map<String,String> headers ,Class<T> responseModel){
        ReadPropertiesFile propertyFileReader = new ReadPropertiesFile(baseurlFilepath);
       return given().headers(headers).queryParams(queryParams).expect().defaultParser(Parser.JSON).when()
                .get(propertyFileReader.getPropertyValue("BaseURL").concat(endpoint.getUrl())).as(responseModel);
    }

    public static <T> T restPostNewPet(String baseurlFilePath, Map<String,String> headers, Object apiPayload
            , Class<T> responseModel ){
        ReadPropertiesFile propertyFileReader = new ReadPropertiesFile(baseurlFilePath);
       return given().headers(headers).body(apiPayload).expect().defaultParser(Parser.JSON).when()
                .post(propertyFileReader.getPropertyValue("BaseURL")).as(responseModel);
    }

    public static <T> T restDeletePet(String endpoint, String pathParamKey, int pathParamValue, Map<String,String> headers
            , Class<T> responseModel){
       return given().headers(headers).pathParam(pathParamKey,pathParamValue).expect().defaultParser(Parser.JSON).when()
                .delete(endpoint).as(responseModel);
    }

    public static <T> T restPut(String baseurlFilePath, Map<String,String> headers, Object apiPayload
            , Class<T> responseModel ){
        ReadPropertiesFile propertyFileReader = new ReadPropertiesFile(baseurlFilePath);
        return given().headers(headers).body(apiPayload).expect().defaultParser(Parser.JSON).when()
                .post(propertyFileReader.getPropertyValue("BaseURL")).as(responseModel);
    }
}
