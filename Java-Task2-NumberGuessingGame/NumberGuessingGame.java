import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        int score = 0;
        int round = 0;

        System.out.println("=================================");
        System.out.println("      NUMBER GUESSING GAME");
        System.out.println("=================================");

        while (playAgain) {

            round++;

            System.out.println("\n--- Round " + round + " ---");
            int secretNumber = random.nextInt(100) + 1;

            int attempts = 0;
            int maxAttempts = 7;
            boolean won = false;

            while (attempts < maxAttempts) {

                System.out.println(
                    "\nAttempt " + (attempts + 1) + " of " + maxAttempts
                );

                System.out.print("Enter your guess (1-100): ");
                int guess = scanner.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println(
                        "Please enter a number between 1 and 100."
                    );
                    continue;
                }

                attempts++;

                if (guess > secretNumber) {

                    System.out.println("Too High!");

                } else if (guess < secretNumber) {

                    System.out.println("Too Low!");

                } else {

                    System.out.println("Correct!");
                    System.out.println(
                        "You guessed it in " + attempts + " attempts!"
                    );

                    System.out.println(
                        "Round " + round +
                        " - guessed in " + attempts + " attempts"
                    );

                    score++;
                    won = true;

                    break;
                }
            }

            if (!won && attempts == maxAttempts) {

                System.out.println("\nYou Lost!");
                System.out.println(
                    "The number was: " + secretNumber
                );

                System.out.println(
                    "Round " + round + " - Lost"
                );
            }

            System.out.print("\nPlay again? (y/n): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("n")) {

                playAgain = false;

            } else if (!answer.equalsIgnoreCase("y")) {

                System.out.println("Invalid choice. Game ended.");
                playAgain = false;
            }
        }

        System.out.println("\n=================================");
        System.out.println("          FINAL SCORE");
        System.out.println("=================================");

        System.out.println("Total Rounds: " + round);
        System.out.println("Rounds Won: " + score);
        System.out.println("Rounds Lost: " + (round - score));

        System.out.println("\nThank you for playing!");

        scanner.close();
    }
}