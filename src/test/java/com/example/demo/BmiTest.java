package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import strategy.StandartIndexStrategy;

public class BmiTest {
    private String filePath = "src/main/resources/bmi_status.xlsx";

    @Test
    void testGetM() {
        Bmi bmi = new Bmi(70, 170);
        assertEquals(70, bmi.getM());
    }

    @Test
    void testSetM() {
        Bmi bmi = new Bmi();
        bmi.setM(70);
        assertEquals(70, bmi.getM());
    }

    @Test
    void testGetH() {
        Bmi bmi = new Bmi(70, 170);
        assertEquals(170, bmi.getH());
    }

    @Test
    void testSetH() {
        Bmi bmi = new Bmi();
        bmi.setH(170);
        assertEquals(170, bmi.getH());
    }

    @Test
    void testCalc() {
        Bmi bmi = new Bmi(70, 170);
        bmi.setCalculatorStrategy(new StandartIndexStrategy());
        double result = bmi.calc();
        assertEquals(24.22, result, 0.01);
    }

    @Test
    void testToString() {
        Bmi bmi = new Bmi(70, 170);
        bmi.setCalculatorStrategy(new StandartIndexStrategy());
        String result = bmi.toString();
        assertEquals("ИМТ 24,2\n", result);
    }
    @Test
    void testStatus() {
        Bmi bmi = new Bmi(70, 170);
        bmi.setCalculatorStrategy(new StandartIndexStrategy());
        String status = bmi.getStatus(filePath, "Standart");
        assertEquals("Норма", status);
    }

    @Test
    void testStatusColor() {
        Bmi bmi = new Bmi(70, 170);
        bmi.setCalculatorStrategy(new StandartIndexStrategy());
        assertEquals("0x008000ff", bmi.getStatusColor(filePath, "Standart").toString());
    }
}
