package com.example.demo;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

public class MyMRCBot extends TelegramWebhookBot {
    private String webHook; // домен - адрес связывающий сервер телеги и локальную машину
    private String botname;
    private String botToken;

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }

    public String getBotname() {
        return botname;
    }

    public void setBotname(String botname) {
        this.botname = botname;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        long chat_Id = update.getMessage().getChatId();
        String msg = update.getMessage().getText();
        if(update.getMessage() != null && update.getMessage().hasText()){
            try { // все инструкции, которые согуть вызвать исключения
            response(chat_Id,msg);
            } catch (Exception e ){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botname;
    }

    @Override
    public String getBotPath() {
        return null;
    }

    public  void response(long chat_id, String msg) throws  Exception{
        switch (msg){
            case "/file": {
                String filePath = "src/main/resources/static/subbota_znamenatel_21.11.2020_2.pdf";
                File file = new File(filePath);

                SendDocument document = new SendDocument();
                document.setDocument(file);
                document.setChatId(chat_id);
                execute(document);
            }
            default:{
                execute(new SendMessage(chat_id, "хочешь расписание? /file"));
            }
        }
    }
}
