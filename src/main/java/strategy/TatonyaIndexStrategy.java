package strategy;
/**
 * Реализация стратегии расчета стандартного индекса массы тела (ИМТ).
 */
public class TatonyaIndexStrategy implements BMICalculatorStrategy {
    /**
     * Рассчитывает стандартный индекс массы тела (ИМТ).
     *
     * @param weight вес в килограммах
     * @param height рост в сантиметрах
     * @return стандартный индекс массы тела (ИМТ)
     */
    @Override
    public double calculateBMI(double weight, double height) {
        return height - (100 + (height - 100) / 20);
    }
}