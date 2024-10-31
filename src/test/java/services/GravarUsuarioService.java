package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.GravarModel;

import static io.restassured.RestAssured.given;

public class GravarUsuarioService {

    final GravarModel gravarModel = new GravarModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;
    String baseUrl = "http://localhost:8080";
    public void setFieldsRecord(String field, String value){
        switch (field){
            case "nome" -> gravarModel.setNome(value);
            case "telefone" -> gravarModel.setTelefone(value);
            case "email" -> gravarModel.setEmail(value);
            case "senha" -> gravarModel.setSenha(value);
            case "role" -> gravarModel.setRole(value);
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
}

public void createRecord(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(gravarModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
}

}
