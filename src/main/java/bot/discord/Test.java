package bot.discord;

public class Test {

    public static void main(String[] args) {
        String test = "what is 8 + 8?";
        String firstNumber = test.substring(8,9);
        String secondNumber = test.substring(12,13);
        int sum = Integer.valueOf(firstNumber) + Integer.valueOf(secondNumber);
        System.out.println("The sum of " + firstNumber + " + " + secondNumber +
                " is: " + sum + ".");
    }
}
