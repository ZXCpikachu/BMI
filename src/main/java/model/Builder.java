package model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Интерфейс для создания строителя индикатора.
 */
public interface Builder {
    /**
     * Создает прямоугольник заданного размера и цвета.
     *
     * @param width  Ширина прямоугольника
     * @param height Высота прямоугольника
     * @param color  Цвет прямоугольника
     */
    void lineRectangle(double width, double height, Color color);

    /**
     * Создает стрелку заданной ширины, высоты и положения по оси X.
     *
     * @param arrowWidth    Ширина стрелки
     * @param arrowHeight   Высота стрелки
     * @param arrowCenterX  Положение стрелки по оси X
     */
    void arrow(double arrowWidth, double arrowHeight, double arrowCenterX);

    /**
     * Устанавливает текст сообщения на метке.
     *
     * @param message Сообщение
     * @param label   Метка для отображения сообщения
     */
    void setMessage(String message, Label label);

    /**
     * Собирает индикатор.
     *
     * @return Собранный индикатор
     */
    Indicator Build();
}
