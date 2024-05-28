    package strategy;
    /**
     * Реализация стратегии расчета индекса массы тела (ИМТ) по методике Брока.
     */
    public class BrocaIndexStrategy implements BMICalculatorStrategy {
        /**
         * Рассчитывает индекс массы тела (ИМТ) по методике Брока.
         *
         * @param weight вес в килограммах
         * @param height рост в сантиметрах
         * @return индекс массы тела (ИМТ) по методике Брока
         */
        @Override
        public double calculateBMI(double weight, double height) {
            return ((height - 100) * 0.9);
        }
    }