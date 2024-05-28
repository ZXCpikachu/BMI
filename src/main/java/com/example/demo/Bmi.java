    package com.example.demo;
    import javafx.fxml.FXML;
    import javafx.scene.control.ComboBox;
    import javafx.scene.paint.Color;
    import org.apache.poi.ss.usermodel.*;
    import strategy.BMICalculatorStrategy;
    import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
    import java.io.IOException;
    import java.io.FileInputStream;
    import java.text.DecimalFormat;
    import java.text.ParseException;
    import java.util.Iterator;
    /**
     * Класс для вычисления индекса массы тела (ИМТ) и определения статуса на основе данных из Excel-файла.
     */
    public class Bmi {
        private double m;
        private double h;
        private BMICalculatorStrategy calculatorStrategy;
        /**
         * Конструктор без параметров.
         */
        public Bmi() {
            this.m = 0;
            this.h = 0;
        }
        /**
         * Конструктор с параметрами веса и роста.
         *
         * @param m Вес
         * @param h Рост
         */
        public Bmi(double m, double h) {
            this.m = m;
            this.h = h;
        }
        /**
         * Получение значения веса.
         *
         * @return Значение веса
         */
        public double getM() {
            return m;
        }

        /**
         * Установка значения веса.
         *
         * @param m Новое значение веса
         */
        public void setM(double m) {
            this.m = m;
        }
        /**
         * Получение значения роста.
         *
         * @return Значение роста
         */
        public double getH() {
            return h;
        }
        /**
         * Установка значения роста.
         *
         * @param h Новое значение роста
         */
        public void setH(double h) {
            this.h = h;
        }
        /**
         * Установка стратегии расчета ИМТ.
         *
         * @param calculatorStrategy Стратегия расчета ИМТ
         */
        public void setCalculatorStrategy(BMICalculatorStrategy calculatorStrategy) {
            this.calculatorStrategy = calculatorStrategy;
        }
        /**
         * Вычисление ИМТ.
         *
         * @return Значение ИМТ
         */
        public double calc() {
            if (calculatorStrategy == null) {
                throw new IllegalStateException("Calculator strategy is not set");
            }
            return calculatorStrategy.calculateBMI(m, h);
        }
        /**
         * Получение статуса на основе данных из Excel-файла.
         *
         * @param filePath          Путь к Excel-файлу
         * @param selectedStrategy  Выбранная стратегия
         * @return Статус ИМТ
         */
        public String getStatus(String filePath, String selectedStrategy) {
            double bmiValue = calc();
            System.out.println("Calculated BMI value: " + bmiValue);
            try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath))) {
                Sheet sheet = workbook.getSheet(selectedStrategy);
                if (sheet == null) {
                    System.out.println("Sheet not found for selected strategy: " + selectedStrategy);
                    return "Статус не определен";
                }
                Iterator<Row> rowIterator = sheet.iterator();
                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (row.getLastCellNum() < 4) {
                        continue;
                    }

                    String indexType = row.getCell(0).getStringCellValue();
                    String status = row.getCell(1).getStringCellValue();
                    Cell lowerBoundCell = row.getCell(2);
                    Cell upperBoundCell = row.getCell(3);
                    if (lowerBoundCell.getCellType() == CellType.NUMERIC
                            && upperBoundCell.getCellType() == CellType.NUMERIC) {
                        double lowerBound = lowerBoundCell.getNumericCellValue();
                        double upperBound = upperBoundCell.getNumericCellValue();
                        if (selectedStrategy.equals(indexType) && bmiValue >= lowerBound && bmiValue < upperBound) {
                            System.out.println("Status found: " + status);
                            return status;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Status not found...");
            return "Статус не определен";
        }

        /**
         * Получение цвета статуса.
         *
         * @param filePath          Путь к Excel-файлу
         * @param selectedStrategy  Выбранная стратегия
         * @return Цвет статуса
         */
        public Color getStatusColor(String filePath, String selectedStrategy) {
            String status = getStatus(filePath, selectedStrategy);

            switch (status) {
                case "Дефицит":
                    return Color.RED;
                case "Норма":
                    return Color.GREEN;
                case "Избыточный вес":
                    return Color.YELLOW;
                case "Ожирение":
                    return Color.ORANGE;
                default:
                    return Color.GRAY;
            }
        }
        /**
         * Переопределение метода toString для вывода значения ИМТ в виде строки.
         *
         * @return Строковое представление ИМТ
         */
        @Override
        public String toString() {
            String formatBmi = String.format("%.1f", calc());
            return "ИМТ " + formatBmi + "";
        }
    }