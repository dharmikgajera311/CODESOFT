import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = random.nextInt(100) + 1; 
            int attempts = 0;
            boolean hasGuessedCorrectly = false;
            int maxAttempts = 10;

            System.out.println("Guess the number between 1 and 100.");

            while (!hasGuessedCorrectly && attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    hasGuessedCorrectly = true;
                    score += maxAttempts - attempts + 1;
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                if (attempts == maxAttempts && !hasGuessedCorrectly) {
                    System.out.println("Sorry! You've reached the maximum number of attempts. The correct number was " + targetNumber + ".");
                }
            }

            System.out.println("Your current score is: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String userResponse = scanner.next();

            if (!userResponse.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }
}
