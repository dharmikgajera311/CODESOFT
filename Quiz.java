import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    char correctAnswer;

    public Question(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    private Question[] questions;
    private int score;
    private Scanner scanner;
    private int currentQuestionIndex;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
        this.scanner = new Scanner(System.in);
        this.currentQuestionIndex = 0;
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            askQuestion(questions[currentQuestionIndex]);
        }
        displayResults();
    }

    private void askQuestion(Question question) {
        System.out.println(question.question);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((char) ('A' + i) + ": " + question.options[i]);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                handleAnswerSubmission(' ');
            }
        };

        timer.schedule(task, 10000); // 10 seconds timer

        System.out.print("Enter your answer: ");
        String answer = scanner.nextLine().toUpperCase();
        timer.cancel();

        if (answer.length() > 0) {
            handleAnswerSubmission(answer.charAt(0));
        } else {
            handleAnswerSubmission(' '); // Handle empty input
        }
    }

    private void handleAnswerSubmission(char answer) {
        Question currentQuestion = questions[currentQuestionIndex];
        if (answer == currentQuestion.correctAnswer) {
            score++;
        }
        System.out.println("Correct Answer: " + currentQuestion.correctAnswer + "\n");
    }

    private void displayResults() {
        System.out.println("Quiz Over!");
        System.out.println("Your score: " + score + "/" + questions.length);
    }

    public static void main(String[] args) {
        Question[] questions = new Question[] {
            new Question("What is the capital of France?", new String[] { "Paris", "London", "Berlin", "Madrid" }, 'A'),
            new Question("What is 5 + 7?", new String[] { "10", "11", "12", "13" }, 'C'),
            new Question("Which planet is known as the Red Planet?", new String[] { "Earth", "Mars", "Jupiter", "Saturn" }, 'B'),
            new Question("Who wrote 'To Kill a Mockingbird'?", new String[] { "Harper Lee", "J.K. Rowling", "Ernest Hemingway", "Mark Twain" }, 'A')
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
