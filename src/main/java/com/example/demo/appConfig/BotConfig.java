package com.example.demo.appConfig;


import com.example.demo.MyMRCBot;
import com.example.demo.MymrcbotApplication;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot") // чтобы вынести в отдельный файл
public class BotConfig {
    private String webHook; // домен - адрес связывающий сервер телеги и локальную машину
    private String botname;
    private String botToken;

    @Bean
    public MyMRCBot myMRCBot(){
        MyMRCBot bot = new MyMRCBot();
        bot.setBotname(botname);
        bot.setWebHook(webHook);
        bot.setBotToken(botToken);
        return bot;
    }
}
