package strategy;
/**
 * Стратегия расчета ИМТ согласно индексу Бернарда.
 */
public class BernardIndexStrategy implements BMICalculatorStrategy {
    /**
     * Рассчитывает ИМТ согласно индексу Бернарда.
     *
     * @param weight вес в килограммах
     * @param height рост в сантиметрах
     * @return индекс массы тела (ИМТ) согласно индексу Бернарда
     */
    @Override
    public double calculateBMI(double weight, double height) {
        double chestCircumference = 100;
        return height * chestCircumference / 240;
    }
}