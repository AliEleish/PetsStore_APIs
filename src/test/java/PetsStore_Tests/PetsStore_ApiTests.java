package PetsStore_Tests;

import Enums.Endpoints;
import POJO.RequestModels.PostAndPutPetApiPayload;
import POJO.ResponseModels.DeletePetApiResponse;
import POJO.ResponseModels.GetPetByStatusApiResponse;
import Wrappers.API_Wrappers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetsStore_ApiTests {


    @Test
    public static void findActiveStatusPets(){
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("status","available");
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
       GetPetByStatusApiResponse[] activePetsResponseList = API_Wrappers
               .getPetByStatus("src/main/java/Properties/BaseURL.properties", Endpoints.findPetsByStatus,queryParams
                       ,headers, GetPetByStatusApiResponse[].class);

       for(int i = 0 ; i < activePetsResponseList.length ; i++){
           try{
               Assert.assertTrue(activePetsResponseList[i].getId() != 0);
               Assert.assertFalse(activePetsResponseList[i].getName().isEmpty());
               Assert.assertEquals(activePetsResponseList[i].getStatus(), "available");
           }catch(Exception e){

           }
       }
      }

      @Test
    public static void addNewPet(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
          PostAndPutPetApiPayload payload = new PostAndPutPetApiPayload();
          payload.setId(505050);
          payload.setName("Katie");
          payload.setStatus("sold");
          PostAndPutPetApiPayload.Category category = new PostAndPutPetApiPayload.Category();
          category.setId(5);
          category.setName("Cat");
          payload.setCategory(category);
          List<String> photoURLs = new ArrayList<>();
          photoURLs.add("cat.png");
          photoURLs.add("blackCat.Jpg");
          payload.setPhotoUrls(photoURLs);
          PostAndPutPetApiPayload.Tag tag = new PostAndPutPetApiPayload.Tag();
          tag.setId(15);
          tag.setName("zoo");
          List<PostAndPutPetApiPayload.Tag> tagsList = new ArrayList<>();
          tagsList.add(tag);
          payload.setTags(tagsList);
          GetPetByStatusApiResponse postNewPetResponse = API_Wrappers
                  .restPostNewPet("src/main/java/Properties/BaseURL.properties",headers,payload
                          ,GetPetByStatusApiResponse.class);
          Assert.assertEquals(postNewPetResponse.getId(), payload.getId());
          Assert.assertEquals(postNewPetResponse.getName(),payload.getName());
          Assert.assertEquals(postNewPetResponse.getStatus(),payload.getStatus());
      }

      @Test
    public void deletePetFromStore(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("api_key", "api_key");
        int pathParamValue = 505050;
        try{
          DeletePetApiResponse deletedPetResponse = API_Wrappers.restDeletePet("https://petstore.swagger.io/v2/pet/{petId}"
                  ,"petId",pathParamValue,headers, DeletePetApiResponse.class);
          Assert.assertEquals(deletedPetResponse.getCode(),200);
          Assert.assertFalse(deletedPetResponse.getType().isEmpty());
          Assert.assertEquals(Integer.parseInt(deletedPetResponse.getMessage()),pathParamValue);
        }
        catch(RuntimeException e){
           throw new RuntimeException("Please enter a valid petId to delete a pet from the store, you can do this by changing the " +
                   "pathParamValue in restDeletePet() method");
        }
      }

      @Test
    public void UpdatePetData(){
        Map<String,String> headers = new HashMap<>();
          headers.put("Content-Type", "application/json");
          PostAndPutPetApiPayload payload = new PostAndPutPetApiPayload();
          payload.setId(505050);
          payload.setName("Katie");
          payload.setStatus("sold");
          PostAndPutPetApiPayload.Category category = new PostAndPutPetApiPayload.Category();
          category.setId(5);
          category.setName("Cat");
          payload.setCategory(category);
          List<String> photoURLs = new ArrayList<>();
          photoURLs.add("cat.png");
          photoURLs.add("blackCat.Jpg");
          payload.setPhotoUrls(photoURLs);
          PostAndPutPetApiPayload.Tag tag = new PostAndPutPetApiPayload.Tag();
          tag.setId(15);
          tag.setName("zoo");
          List<PostAndPutPetApiPayload.Tag> tagsList = new ArrayList<>();
          tagsList.add(tag);
          payload.setTags(tagsList);
          GetPetByStatusApiResponse postNewPetResponse = API_Wrappers
                  .restPut("src/main/java/Properties/BaseURL.properties",headers,payload
                          ,GetPetByStatusApiResponse.class);
          Assert.assertEquals(postNewPetResponse.getId(), payload.getId());
          Assert.assertEquals(postNewPetResponse.getName(),payload.getName());
          Assert.assertEquals(postNewPetResponse.getStatus(),payload.getStatus());
      }

}
