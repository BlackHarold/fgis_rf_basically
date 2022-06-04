package ru.bluewhale.fgis.extractor;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


@Component
public class DocumentExtractorImpl implements Extractor {

    public Document getDocument(String url) throws HttpStatusException {
        Connection connection;

        Document document = null;
        connection = Jsoup.connect(url);
        connection.timeout(5000);
        try {
            Connection.Response response = connection.execute();
            if (response.statusCode() == 200) {
                document = connection.get();
            } else {
                Thread.sleep(1000);
                //repeat request
                getDocument(url);
            }

        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception: " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(document).orElse(new Document("null"));
    }
}
