package com.example.demo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import strategy.StandartIndexStrategy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    final private String BOT_TOKEN = "";
    final private String BOT_NAME = "SemenovTask16_bot";

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();

            if ("/start".equals(text)) {
                sendResponse(chatId, "Привет! Я бот для расчета ИМТ. Пожалуйста, отправьте свой вес и рост для расчета.");
            } else if ("/help".equals(text)) {
                sendResponse(chatId, "Это бот для расчета ИМТ. Отправьте свой вес и рост для расчета.");
            } else if ("Вычислить ИМТ".equals(text)) {
                sendResponse(chatId, "Пожалуйста, отправьте свой вес и рост для расчета ИМТ в формате: вес рост.");
            } else {
                String[] parts = text.split(" ");
                if (parts.length == 2) {
                    try {
                        double weight = Double.parseDouble(parts[0]);
                        double height = Double.parseDouble(parts[1]);
                        double bmi = calculateBMI(weight, height);
                        sendResponse(chatId, "Ваш ИМТ: " + bmi);
                    } catch (NumberFormatException e) {
                        sendResponse(chatId, "Прости, я не могу распознать введенные данные. Пожалуйста, отправьте вес и рост в формате: вес рост.");
                    }
                } else {
                    sendResponse(chatId, "Прости, я не могу распознать введенные данные. Пожалуйста, отправьте вес и рост в формате: вес рост.");
                }
            }
        }
    }

    private double calculateBMI(double weight, double height) {
        Bmi bmiCalculator = new Bmi(weight, height);
        bmiCalculator.setCalculatorStrategy(new StandartIndexStrategy());
        return bmiCalculator.calc();
    }

    private ReplyKeyboardMarkup createKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Вычислить ИМТ");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private void sendResponse(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        ReplyKeyboardMarkup keyboardMarkup = createKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
