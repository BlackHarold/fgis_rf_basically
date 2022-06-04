package ru.bluewhale.fgis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.bluewhale.fgis.service.MainContent;

@ComponentScan
public class SpringApplication {
    public static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = new AnnotationConfigApplicationContext(SpringApplication.class);

        MainContent mainContent = ctx.getBean("MainContent", MainContent.class);
        /*List<ContentElement> content = */mainContent.getContent();

        byte[] buffer = new byte[64 * 1024];
        int read;
    }

}
