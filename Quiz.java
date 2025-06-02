import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;
    private int timeLeftInSeconds;
    private int quizDurationInSeconds;
    private boolean quizFinished;
    private boolean timerExpired;

    // Constructor to initialize quiz with questions and duration
    public Quiz(List<Question> questions, int quizDurationInSeconds) {
        this.questions = new ArrayList<>(questions);
        Collections.shuffle(this.questions); // Shuffle questions for variety
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.quizDurationInSeconds = quizDurationInSeconds;
        this.timeLeftInSeconds = quizDurationInSeconds;
        this.quizFinished = false;
        this.timerExpired = false;
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null; // No more questions
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size();
    }

    public void goToNextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        } else {
            quizFinished = true;
        }
    }

    public void answerQuestion(int chosenOptionIndex) {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (currentQuestion.isCorrect(chosenOptionIndex)) {
                score++;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public boolean isQuizFinished() {
        return quizFinished || timerExpired;
    }

    public int getTimeLeftInSeconds() {
        return timeLeftInSeconds;
    }

    public boolean isTimerExpired() {
        return timerExpired;
    }

    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeLeftInSeconds > 0 && !quizFinished) {
                    timeLeftInSeconds--;
                    // In a GUI application, you'd update a timer display here
                    // System.out.println("Time left: " + timeLeftInSeconds + " seconds"); // For console
                } else {
                    if (!quizFinished) { // Timer expired, and quiz wasn't already finished by answering all
                        timerExpired = true;
                        quizFinished = true;
                        System.out.println("\nTime's up! Quiz finished.");
                    }
                    timer.cancel(); // Stop the timer
                }
            }
        }, 0, 1000); // Delay 0, repeat every 1 second
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    // --- Console-based demonstration methods ---
    public void startConsoleQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quiz Started! You have " + quizDurationInSeconds + " seconds.");
        startTimer();

        while (!isQuizFinished() && hasNextQuestion() && !isTimerExpired()) {
            Question currentQuestion = getCurrentQuestion();
            System.out.println("\n--- Question " + (currentQuestionIndex + 1) + " ---");
            System.out.println("Time Left: " + timeLeftInSeconds + " seconds");
            System.out.println(currentQuestion);

            System.out.print("Enter your answer (1-" + currentQuestion.getOptions().size() + "): ");
            int chosenOption = -1;
            try {
                chosenOption = Integer.parseInt(scanner.nextLine());
                if (chosenOption < 1 || chosenOption > currentQuestion.getOptions().size()) {
                    System.out.println("Invalid option. Please enter a number within the range.");
                    continue; // Ask the same question again
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; // Ask the same question again
            }

            answerQuestion(chosenOption - 1); // Adjust for 0-based indexing
            goToNextQuestion();
        }

        stopTimer(); // Ensure timer is stopped
        System.out.println("\n--- Quiz Over! ---");
        System.out.println("Your final score: " + getScore() + " out of " + getTotalQuestions());
        scanner.close();
    }

    public static void main(String[] args) {
        // Create some sample questions
        List<Question> quizQuestions = new ArrayList<>();
        quizQuestions.add(new Question("What is the capital of France?",
                List.of("Berlin", "Madrid", "Paris", "Rome"), 2)); // Paris is at index 2
        quizQuestions.add(new Question("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Jupiter", "Venus"), 1));
        quizQuestions.add(new Question("What is 7 + 8?",
                List.of("12", "13", "14", "15"), 3));
        quizQuestions.add(new Question("Who painted the Mona Lisa?",
                List.of("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"), 2));
        quizQuestions.add(new Question("What is the largest ocean on Earth?",
                List.of("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"), 3));

        // Create a quiz instance with 60 seconds duration
        Quiz myQuiz = new Quiz(quizQuestions, 60); // 60 seconds duration
        myQuiz.startConsoleQuiz();
    }
}