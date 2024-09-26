import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestApiTest {

//    Integer id = new Random().nextInt(500000);

    @BeforeClass
    public void prepare() throws IOException {


        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/")
                .addHeader("api_key", System.getProperty("api.key"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.filters(new ResponseLoggingFilter());
    }

    @Test
    public void checkObjectSave() {
        Order order = new Order();
//        Integer id = new Random().nextInt(500000);
        Integer petId = new Random().nextInt(500000);
        order.setId(254169);
        order.setPetId(petId);
        order.setQuantity(4);
        order.setComplete(true);

        given()
                .body(order)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);

        Order actual =
                given()
                        .pathParam("orderId", System.getProperty("orderId"))
                        .when()
                        .get("/store/order/{orderId}")
                        .then()
                        .statusCode(200)
                        .extract().body()
                        .as(Order.class);

        Assert.assertEquals(actual.getId(), order.getId());

        //Провалидировал id
        Map map = given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(200)
                .extract().body()
                .as(Map.class);
        Assert.assertTrue(map.containsKey("id"), "Order не содержит статус id");
    }

    @Test
    public void tetDelete() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));

        given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);
        given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404);

    }

    @Test
    public void testInventory() {
        //Брем и создаем Map, так как коллекция принимает пару ключ(sold), значение(123)
        Map map = given()
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .extract().body()//берем тело ответа
                .as(Map.class);//пихаем его в map
        //валидируем наш ключ sold
        Assert.assertTrue(map.containsKey("sold"), "Inventory не содержит статус sold");
    }
}
