package model;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Индикатор, отображаемый в виде горизонтального контейнера (HBox).
 */
public class Indicator {
    private final HBox panel = new HBox();

    /**
     * Добавляет указанный панельный элемент к индикатору.
     *
     * @param pane панельный элемент для добавления
     */
    public void add(Pane pane) {
        panel.getChildren().add(pane);
    }

    /**
     * Удаляет указанный панельный элемент из индикатора.
     *
     * @param pane панельный элемент для удаления
     */
    public void remove(Pane pane) {
        pane.getChildren().remove(panel);
    }

    /**
     * Отображает индикатор на заданной панели.
     *
     * @param pane панель, на которой отображается индикатор
     */
    public void show(Pane pane) {
        pane.getChildren().add(panel);
    }
}
