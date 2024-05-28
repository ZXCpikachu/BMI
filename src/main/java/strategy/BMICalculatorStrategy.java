package strategy;
/**
 * Интерфейс стратегии расчета ИМТ.
 */
public interface BMICalculatorStrategy {
    /**
     * Рассчитывает индекс массы тела (ИМТ) на основе веса и роста.
     *
     * @param weight вес в килограммах
     * @param height рост в метрах
     * @return индекс массы тела (ИМТ)
     */
    double calculateBMI(double weight, double height);
}
