import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game!");
        Scanner s = new Scanner(System.in);
        boolean play = true;
        int score = 0;
        int round = 1;
        
        while (play) {
            System.out.println("\nRound " + round);
            int targetNumber = randomNumber(1, 100);
            int attempts = 0;
            boolean correctGuess = false;

            while (!correctGuess  && attempts < 5) {
                System.out.print("Guess a number between 1 and 100: ");
                int guess = s.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score += calculateScore(attempts);
                    correctGuess  = true;
                } else if (guess > targetNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!correctGuess ) {
                System.out.println("Sorry, you have used all your attempts. The number was: " + targetNumber);
            }

            System.out.println("Your score: " + score);
            round++;

            System.out.print("Do you want to play again? (Y/N): ");
            String playAgain = s.next();

            if (!playAgain.equalsIgnoreCase("Y")) {
                play = false;
            }
        }
        System.out.println("Total score: "+ score);
        System.out.println("Thank you for playing the Number Guessing Game!");
    }
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }


    public static int calculateScore(int attempts) {
        if (attempts == 1) {
            return 10;
        } else if (attempts == 2) {
            return 8;
        } else if (attempts == 3) {
            return 6;
        } else if (attempts == 4) {
            return 4;
        } else if (attempts == 5){
            return 2;
        }
        else {
            return 1;
        }
    }
}