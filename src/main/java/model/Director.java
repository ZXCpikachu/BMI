package model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Директор, который управляет построением индикатора.
 */
public class Director {
    BuilderIndicator builder = new BuilderIndicator();

    /**
     * Строит индикатор с заданными параметрами.
     *
     * @param color    цвет индикатора
     * @param centerX  координата X центра стрелки
     * @param message  текстовое сообщение
     * @param label    компонент Label для отображения сообщения
     * @param paneWidth  ширина панели
     * @param paneHeight высота панели
     * @return построенный индикатор
     */
    public Indicator Constr(Color color, double centerX, String message, Label label, double paneWidth, double paneHeight,double bmi) {
        double rectWidth = builder.calculateRectangleWidth(paneWidth);
        double rectHeight = builder.calculateRectangleHeight(paneHeight);
        double arrowWidth = builder.calculateArrowWidth(paneWidth);
        double arrowHeight = builder.calculateArrowHeight(paneHeight);

        builder.lineRectangle(rectWidth, rectHeight, color);
        builder.arrow(arrowWidth, arrowHeight, bmi);
        builder.setMessage(message, label);

        return builder.Build();
    }

    /**
     * Удаляет индикатор.
     */
    public void hidden() {
        builder.remove();
    }
}
