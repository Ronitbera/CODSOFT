import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex; // Index of the correct answer in the options list

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = new ArrayList<>(options); // Create a new list to avoid modification issues
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return Collections.unmodifiableList(options); // Return an unmodifiable list
    }

    public boolean isCorrect(int chosenOptionIndex) {
        return chosenOptionIndex == correctAnswerIndex;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionText).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}