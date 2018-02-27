package ru.track;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import org.apache.commons.lang3.StringUtils;

public class App2 {

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

        System.out.println(r.getBody().getObject().get("success"));

    }
}
