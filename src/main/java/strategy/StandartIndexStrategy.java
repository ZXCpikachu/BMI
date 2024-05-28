package strategy;

public class StandartIndexStrategy implements BMICalculatorStrategy {
    @Override
    public double calculateBMI(double weight, double height) {
        return weight / (height/100*height/100);
    }
}