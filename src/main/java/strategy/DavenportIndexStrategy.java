package strategy;
/**
 * Реализация стратегии расчета индекса массы тела (ИМТ) по методике Дэвенпорта.
 */
public class DavenportIndexStrategy implements BMICalculatorStrategy {
    /**
     * Рассчитывает индекс массы тела (ИМТ) по методике Дэвенпорта.
     *
     * @param weight вес в килограммах
     * @param height рост в метрах
     * @return индекс массы тела (ИМТ) по методике Дэвенпорта
     */
    @Override
    public double calculateBMI(double weight, double height) {
        return ((weight*1000)/(Math.pow(height,2))) ;
    }
}