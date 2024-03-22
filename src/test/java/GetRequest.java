import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class GetRequest {



    public static void main(String[] args) {
        // 1. İstek yapılacak URL
        String url = "https://demoqa.com/BookStore/v1/Books";


        // 2. İstek başlıklarını belirleyin (opsiyonel)
        String contentType = ContentType.JSON.toString();

        Map<String, Object> header = new HashMap<>();
        header.put("Accept", "application/json");
//        String RequestBody = "{\n" +
//                " \"record_offset\": 0,\n" +
//                " \"record_size\": 10,\n" + "}";
        // 3. GET isteğini gönderin
        Response response = RestAssured.given()
                .contentType(contentType)
                .headers(header)
                .when().log().all()
                .get(url);


        // 4. Yanıtı işleyin ve doğrulamalar yapın
        response.then().log().all()
                .statusCode(200);
        assertThat(response.body().jsonPath().getString("books[0].title")).isEqualTo("Git Pocket Guide");
        assertThat(response.body().jsonPath().getString("books[1].isbn")).isEqualTo("9781449331818");


    }
}