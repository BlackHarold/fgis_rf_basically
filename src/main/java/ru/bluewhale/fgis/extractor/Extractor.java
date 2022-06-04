package ru.bluewhale.fgis.extractor;

import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;

public interface Extractor {

    @Async("threadPoolTaskExecutor")
    Document getDocument(String url) throws HttpStatusException;
}
