import classesForJson.Resources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Consumer {
    public static void main(String[] args) throws JsonProcessingException {
        //GET SIMPLE REST JSON
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://reqres.in/api/users?page=2";
//        String s = restTemplate.getForObject(url, String.class);
//        System.out.println(s);




        //POST SIMPLE
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> jsonToSend = new HashMap<>();
//        jsonToSend.put("name","Test NAme");
//        jsonToSend.put("job","Test JOb");
//        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
//        String url = "https://reqres.in/api/users";
//        String s = restTemplate.postForObject(url, request, String.class);
//        System.out.println(s);





        //HOW TO PARSE JSON
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://reqres.in/api/unknown";
//        String response = restTemplate.getForObject(url, String.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(response);
//        System.out.println("Should be fushia rose: "+jsonNode.get("data").get(1).get("name"));



        

        //MADE CLASSES FOR JSON (IN classesForJson)
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://reqres.in/api/unknown";
//        Resources resources = restTemplate.getForObject(url, Resources.class);
//
//        System.out.println(resources.getData().get(4).getName());

    }
}
