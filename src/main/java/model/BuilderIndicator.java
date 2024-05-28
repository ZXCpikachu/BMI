package model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Реализация строителя для создания индикатора.
 */
public class BuilderIndicator implements Builder {
    private Indicator indicator = new Indicator();
    private Pane pane = new Pane();
    private double rectangleWidth;
    private double rectangleHeight;
    private double arrowPosition;

    /**
     * Создает прямоугольник для индикатора.
     *
     * @param width  ширина прямоугольника
     * @param height высота прямоугольника
     * @param color  цвет прямоугольника
     */
    @Override
    public void lineRectangle(double width, double height, Color color) {
        Rectangle rect = new Rectangle();
        rect.setWidth(width);
        rectangleWidth = width;
        rect.setHeight(height);
        rectangleHeight = height;
        rect.setFill(color);
        rect.setStroke(Color.BLACK);
        pane.getChildren().add(rect);
    }

    /**
     * Создает стрелку для индикатора.
     *
     * @param arrowWidth    ширина стрелки
     * @param arrowHeight   высота стрелки
     */
    @Override
    public void arrow(double arrowWidth, double arrowHeight, double bmi) {
        double arrowCenterX = rectangleWidth / 2 + (bmi - 18.5) * (rectangleWidth / 2 - arrowWidth) / (35 - 18.5);
        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(
                arrowCenterX - arrowWidth / 2, rectangleHeight,
                arrowCenterX + arrowWidth / 2, rectangleHeight,
                arrowCenterX, rectangleHeight - arrowHeight
        );
        arrow.setFill(Color.BLACK);
        pane.getChildren().add(arrow);
    }

    /**
     * Устанавливает текстовое сообщение для индикатора.
     *
     * @param message текстовое сообщение
     * @param label   компонент Label для отображения сообщения
     */
    @Override
    public void setMessage(String message, Label label) {
        label.setText(message);
    }

    /**
     * Строит индикатор.
     *
     * @return индикатор
     */
    @Override
    public Indicator Build() {
        indicator.add(pane);
        return indicator;
    }

    /**
     * Удаляет панель с индикатором.
     */
    public void remove() {
        indicator.remove(pane);
    }

    /**
     * Рассчитывает ширину прямоугольника в зависимости от ширины панели.
     *
     * @param paneWidth ширина панели
     * @return ширина прямоугольника
     */
    public double calculateRectangleWidth(double paneWidth) {
        return paneWidth * 0.75;
    }

    /**
     * Рассчитывает высоту прямоугольника в зависимости от высоты панели.
     *
     * @param paneHeight высота панели
     * @return высота прямоугольника
     */
    public double calculateRectangleHeight(double paneHeight) {
        return paneHeight * 0.92;
    }

    /**
     * Рассчитывает ширину стрелки в зависимости от ширины панели.
     *
     * @param paneWidth ширина панели
     * @return ширина стрелки
     */
    public double calculateArrowWidth(double paneWidth) {
        return paneWidth * 0.1;
    }

    /**
     * Рассчитывает высоту стрелки в зависимости от высоты панели.
     *
     * @param paneHeight высота панели
     * @return высота стрелки
     */
    public double calculateArrowHeight(double paneHeight) {
        return paneHeight * 0.3;
    }
}
