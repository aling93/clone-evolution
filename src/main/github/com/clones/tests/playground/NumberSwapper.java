package github.com.clones.tests.playground;

public class NumberSwapper {
    private int firstNumber;
    private int secondNumber;

    public NumberSwapper(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }
    public int getSecondNumber() {
        return secondNumber;
    }

    public void swap() {
        int temp = 0;

        temp = firstNumber;
        firstNumber = secondNumber;
        secondNumber = temp;
    }
}
