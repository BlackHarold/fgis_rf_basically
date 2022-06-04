package ru.bluewhale.fgis.mapper;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    private static Gson gson;

    private ObjectMapper() {
        gson = new Gson();
    }

    public String objectToJson(Object o) {
        System.out.println(gson.toJson(o));
        return gson.toJson(o);
    }
}
