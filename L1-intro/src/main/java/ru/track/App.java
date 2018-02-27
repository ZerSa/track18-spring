package ru.track;

/**
 * TASK:
 * POST request to  https://guarded-mesa-31536.herokuapp.com/track
 * fields: name,github,email
 *
 * LIB: http://unirest.io/java.html
 *
 *
 */
public class App {

    public static final String URL = "http://guarded-mesa-31536.herokuapp.com/track";
    public static final String FIELD_NAME = "Zendrikova Sasha";
    public static final String FIELD_GITHUB = "https://github.com/ZerSa/track18-spring";
    public static final String FIELD_EMAIL = "sasha-zendrikova@yandex.ru";

    public static void main(String[] args) throws Exception {
        HttpResponse<JsonNode> r = Unirest.post(URL)
                .header("accept", "application/json")
                .field("name", FIELD_NAME)
                .field("github", FIELD_GITHUB)
                .field("email", FIELD_EMAIL)
                .asJson();

        boolean success = r.getBody().getObject().get("success");
        System.out.println(success);

       // 1) Use Unirest.post(URL)
        // 2) Get response .asJson()
        // 3) Get json body and JsonObject
        // 4) Get field "success" from JsonObject
        //boolean success = false;
    }

}
