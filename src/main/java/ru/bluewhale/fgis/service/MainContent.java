package ru.bluewhale.fgis.service;

import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.bluewhale.fgis.configuration.PropertyConfig;
import ru.bluewhale.fgis.content.ContentElement;
import ru.bluewhale.fgis.extractor.DocumentExtractorImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("MainContent")
public class MainContent {

    private String FGIS_RF_URL;
    private String volume;
    private String mainClass;

    final
    DocumentExtractorImpl extractor;

    final
    ProcessorServiceImpl processorService;

    public MainContent(@Autowired PropertyConfig config, DocumentExtractorImpl extractor, ProcessorServiceImpl processorService) {
        FGIS_RF_URL = config.getFgisUrl();
        volume = config.getVolume();
        mainClass = config.getMainClass();
        this.extractor = extractor;
        this.processorService = processorService;
    }

    public List<ContentElement> getContent() {

        Document mainDoc = null;
        try {
            mainDoc = extractor.getDocument(FGIS_RF_URL);
        } catch (HttpStatusException e) {
            e.printStackTrace();
        }

        List<ContentElement> list = null;
        if (mainDoc != null) {
            List<ContentElement> mainList = processorService.getElements(mainDoc, mainClass);
            list = recurseToDeep(1, mainList);

        }

        /*List<Bean> objectsList*/
        return Optional.ofNullable(list).orElse(Collections.emptyList());
    }

    public List<ContentElement> recurseToDeep(int deepLevel, List<ContentElement> list) {
        List<ContentElement> contentElementList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Document doc = null;
            try {
                doc = extractor.getDocument(FGIS_RF_URL + list.get(i).getLink());
            } catch (HttpStatusException e) {
                System.out.println("HttpStatusException: " +
                        e.getUrl() +
                        " status was: " + e.getStatusCode() +
                        " message: " + e.getMessage()
                );
                e.printStackTrace();
            }
            List<ContentElement> rawList = processorService.getElements(doc, volume);
            if (rawList != null) {
                contentElementList.addAll(rawList);
            }
        }

        if (!contentElementList.isEmpty()) {
            recurseToDeep(deepLevel++, contentElementList);
        }

        return contentElementList;
    }
}
