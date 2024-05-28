package com.example.demo;

import javafx.scene.layout.HBox;
import strategy.BMICalculatorStrategy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import model.Director;
import model.Indicator;
import strategy.BernardIndexStrategy;
import strategy.BreitmanIndexStrategy;
import strategy.BrocaIndexStrategy;
import strategy.DavenportIndexStrategy;
import strategy.NoordenIndexStrategy;
import strategy.TatonyaIndexStrategy;
import strategy.StandartIndexStrategy;

/**
 * Контроллер для управления интерфейсом и расчета ИМТ.
 */
public class HelloController {

    @FXML
    public HBox panel;

    @FXML
    private TextField mass;

    @FXML
    private TextField rost;

    @FXML
    private Label messages;

    @FXML
    private ComboBox<String> Combo_box;


    private Bmi bmi = new Bmi();
    private String filePath = "src/main/resources/bmi_status.xlsx";
    /**
     * Инициализация методов при загрузке окна.
     */
    @FXML
    public void initialize() {
        Combo_box.getItems().addAll("Brock", "Breitman", "Berngard", "Davenport", "Noorden", "Tatonya", "Standart");
        Combo_box.setValue("Standart");
    }
    /**
     * Обработчик нажатия кнопки для расчета ИМТ.
     *
     * @param actionEvent Событие нажатия кнопки
     */
    @FXML
    public void ButtonClick(ActionEvent actionEvent) {
        panel.getChildren().clear();
        bmi.setM(Double.parseDouble(mass.getText()));
        bmi.setH(Double.parseDouble(rost.getText()));
        double parsedMass = Double.parseDouble(mass.getText());
        double parsedRost = Double.parseDouble(rost.getText());

        String formattedMass = String.format("%.1f", parsedMass);
        String formattedRost = String.format("%.1f", parsedRost);
        String selectedStrategy = Combo_box.getValue();
        BMICalculatorStrategy strategy = createStrategy(selectedStrategy);
        bmi.setCalculatorStrategy(strategy);
        String status = bmi.getStatus(filePath, selectedStrategy);
        String msg = "Рост:" +formattedRost +"см\n"
                + "Вес:"+formattedMass+ "кг\n"+
                String.valueOf(bmi)+ " " + status;
        Color color = bmi.getStatusColor(filePath, selectedStrategy);
        Indicator indicator = new Indicator();
        int arrow = (int) bmi.calc() ;
        System.out.println("Selected strategy before calling getStatus(): " + selectedStrategy);
        Director director = new Director();
        double paneWidth = panel.getWidth();
        double paneHeight = panel.getHeight();

        indicator = director.Constr(color, paneWidth / 2, msg, messages, paneWidth, paneHeight,bmi.calc());
        indicator.show(panel);
    }
    /**
     * Создание стратегии расчета ИМТ в зависимости от выбранного типа.
     *
     * @param strategyType Тип стратегии
     * @return Стратегия расчета ИМТ
     */
    private BMICalculatorStrategy createStrategy(String strategyType) {
        switch (strategyType) {
            case "Brock":
                return new BrocaIndexStrategy();
            case "Breitman":
                return new BreitmanIndexStrategy();
            case "Berngard":
                return new BernardIndexStrategy();
            case "Davenport":
                return new DavenportIndexStrategy();
            case "Noorden":
                return new NoordenIndexStrategy();
            case "Tatonya":
                return new TatonyaIndexStrategy();
            case "Standart":
                return new StandartIndexStrategy();
            default:
                throw new IllegalArgumentException("Invalid strategy type: " + strategyType);
        }
    }
}
