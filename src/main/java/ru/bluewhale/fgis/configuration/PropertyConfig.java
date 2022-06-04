package ru.bluewhale.fgis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class PropertyConfig {

    @Value("${fgis.url}")
    private String fgisUrl;

    @Value("${fgis.volume.class}")
    private String volume;

    @Value("${fgis.main.item}")
    private String mainClass;

    public String getFgisUrl() {
        return fgisUrl;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getVolume() {
        return volume;
    }
}
