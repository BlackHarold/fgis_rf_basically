package ru.bluewhale.fgis.service;

import com.google.gson.JsonObject;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bluewhale.fgis.content.ContentElement;
import ru.bluewhale.fgis.extractor.ListProcessor;
import ru.bluewhale.fgis.mapper.ObjectMapper;
import ru.bluewhale.fgis.mapper.SimpleWriter;

import java.util.List;

@Component
public class ProcessorServiceImpl implements ProcessorService {
    @Autowired
    ListProcessor processor;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    SimpleWriter writer;

    public List<ContentElement> getElements(Document doc, String className) {
        JsonObject object = new JsonObject();
        String h1, h2;
        h1 = doc.getElementsByTag("h1").text();

        if (!h1.isBlank()) {
            object.addProperty("h1",h1);
            if (h1.contains("Таблица")) {
                className = "td";
            }

        }

        h2 = doc.getElementsByTag("h2").text();
        if (!h2.isBlank()) {
            object.addProperty("h2", h2);
        }

        List<ContentElement> list = processor.getListOfElements(doc, className);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                object.addProperty("link " + i,list.get(i).getLink());
                object.addProperty("text " + i,list.get(i).getText());
            }

        }

        System.out.println(object);
        writer.writeToFile(object.toString());
        return list;
    }
}
