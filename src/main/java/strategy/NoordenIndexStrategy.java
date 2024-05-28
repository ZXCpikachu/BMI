package strategy;
/**
 * Реализация стратегии расчета индекса массы тела (ИМТ) по методике Ноордена.
 */
public class NoordenIndexStrategy implements BMICalculatorStrategy {

    /**
     * Рассчитывает индекс массы тела (ИМТ) по методике Ноордена.
     *
     * @param weight вес в килограммах
     * @param height рост в метрах
     * @return индекс массы тела (ИМТ) по методике Ноордена
     */
    @Override
    public double calculateBMI(double weight, double height) {
        return height * 0.42;
    }
}