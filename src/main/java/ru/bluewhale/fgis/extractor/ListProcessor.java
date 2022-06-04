package ru.bluewhale.fgis.extractor;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.bluewhale.fgis.content.ContentElement;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class ListProcessor implements Processor {

    private List<ContentElement> contentElementList;

    public List<ContentElement> getListOfElements(Document document, String elementClass) {
        Elements li;
        if (elementClass != null) {
            if (elementClass.equals("td")) {
                li = document.getElementsByTag(elementClass);
            } else {
                li = document.getElementsByClass(elementClass).select("li");
            }
        } else {
            li = document.getElementsByTag("li");
        }

        if (!elementClass.equals("td")) {
            contentElementList = li.stream()
                    .filter(rawComponent -> rawComponent.getElementsByTag("a") != null && rawComponent.getElementsByTag("a").size() > 0)
                    .filter(rawComponent -> !rawComponent.getElementsByTag("a").get(0).attr("href").equals("/"))
                    .map(filteredComponent -> new ContentElement(filteredComponent.getElementsByTag("a")
                            .get(0)
                            .attr("href"),
                            filteredComponent.text()
                    ))
                    .collect(Collectors.toList());
        } else {
            li.stream()
                    .filter(rawComponent -> rawComponent != null && rawComponent.getElementsByTag("td").size() > 0)
                    .map(filteredComponent -> {
                        Elements elements = filteredComponent.getElementsByTag("td");
                        StringJoiner stringJoiner = new StringJoiner(", ");
                        for (int i = 0; i < elements.size(); i++) {
                            stringJoiner.add(elements.get(i).text());
                        }

                        return new ContentElement(null, stringJoiner.toString());
                    }).collect(Collectors.toList());
        }

        return Optional.ofNullable(contentElementList).orElse(Collections.EMPTY_LIST);
    }

    public void cleanProcessor() {
        contentElementList = null;
    }
}
