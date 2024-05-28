package strategy;
/**
 * Реализация стратегии расчета индекса массы тела (ИМТ) по методике Брейтмана.
 */
public class BreitmanIndexStrategy implements BMICalculatorStrategy {

    /**
     * Рассчитывает индекс массы тела (ИМТ) по методике Брейтмана.
     *
     * @param weight вес в килограммах
     * @param height рост в метрах
     * @return индекс массы тела (ИМТ) по методике Брейтмана
     */
    @Override
    public double calculateBMI(double weight, double height) {
        return height * 0.7 - 50;
    }
}