
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineExaminationSystem extends JFrame {

    private String username = "riya";
    private String password = "java123";
    private String displayName = "Riya";

    private final List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private int[] selectedAnswers;
    private int timeRemaining = 300;
    private Timer timer;

    private JLabel timerLabel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color PRIMARY = new Color(40, 70, 120);

    public OnlineExaminationSystem() {
        setTitle("Online Examination System");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    OnlineExaminationSystem.this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    stopTimer();
                    dispose();
                }
            }
        });

        loadQuestions();
        showLoginScreen();
    }

    private void loadQuestions() {
        questions.add(new Question(
            "Which keyword is used to create an object in Java?",
            new String[]{"class", "new", "this", "static"}, 1));

        questions.add(new Question(
            "Which data type stores true or false?",
            new String[]{"int", "char", "boolean", "double"}, 2));

        questions.add(new Question(
            "Which method is the entry point of a Java program?",
            new String[]{"start()", "run()", "main()", "init()"}, 2));

        questions.add(new Question(
            "Which keyword is used for class inheritance?",
            new String[]{"implements", "extends", "inherits", "super"}, 1));

        questions.add(new Question(
            "Which collection does not allow duplicates?",
            new String[]{"List", "ArrayList", "Set", "Vector"}, 2));

        questions.add(new Question(
            "Which symbol ends a Java statement?",
            new String[]{".", ":", ";", ","}, 2));

        questions.add(new Question(
            "Which package contains Swing components?",
            new String[]{"java.io", "java.net",
                          "javax.swing", "java.sql"}, 2));

        questions.add(new Question(
            "Which loop executes at least once?",
            new String[]{"for", "while", "do-while", "if"}, 2));

        questions.add(new Question(
            "Which operator checks equality?",
            new String[]{"=", "==", "!=", "++"}, 1));

        questions.add(new Question(
            "Which keyword is used to define a class?",
            new String[]{"method", "object", "class", "void"}, 2));

        selectedAnswers = new int[questions.size()];
        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }
    }

    private void showLoginScreen() {
        stopTimer();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
            "Online Examination System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(PRIMARY);

        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String enteredUser = userField.getText().trim();
            String enteredPassword =
                new String(passField.getPassword());

            if (enteredUser.equals(username)
                    && enteredPassword.equals(password)) {
                showProfileScreen();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Invalid username or password.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        passField.addActionListener(e -> loginButton.doClick());

        setContentPane(panel);
        revalidate();
        repaint();
    }

    private void showProfileScreen() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Student Profile");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(PRIMARY);

        JTextField nameField = new JTextField(displayName, 20);
        JPasswordField passwordField =
            new JPasswordField(password, 20);

        JButton updateButton = new JButton("Update Profile");
        JButton startButton = new JButton("Start Exam");
        JButton logoutButton = new JButton("Logout");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Display Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        gbc.gridy++;
        panel.add(startButton, gbc);

        gbc.gridy++;
        panel.add(logoutButton, gbc);

        updateButton.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newPassword =
                new String(passwordField.getPassword());

            if (newName.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Name and password cannot be empty.");
                return;
            }

            displayName = newName;
            password = newPassword;

            JOptionPane.showMessageDialog(
                this, "Profile updated successfully!");
        });

        startButton.addActionListener(e -> startExam());
        logoutButton.addActionListener(e -> showLoginScreen());

        setContentPane(panel);
        revalidate();
        repaint();
    }

    private void startExam() {
        currentQuestion = 0;
        timeRemaining = 300;

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND);

        JLabel welcomeLabel = new JLabel("Welcome, " + displayName);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        timerLabel = new JLabel("Time: 05:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setForeground(Color.RED);

        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.EAST);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(
            new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 17));
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createVerticalStrut(25));

        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBackground(Color.WHITE);
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionGroup.add(options[i]);
            questionPanel.add(options[i]);
            questionPanel.add(Box.createVerticalStrut(12));
        }

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 12, 10));
        buttonPanel.setBackground(BACKGROUND);

        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton submitButton = new JButton("Submit");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(logoutButton);

        previousButton.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentQuestion > 0) {
                currentQuestion--;
                displayQuestion();
            }
        });

        nextButton.addActionListener(e -> {
            saveCurrentAnswer();
            if (currentQuestion < questions.size() - 1) {
                currentQuestion++;
                displayQuestion();
            }
        });

        submitButton.addActionListener(e -> {
            saveCurrentAnswer();
            int result = JOptionPane.showConfirmDialog(
                this, "Are you sure you want to submit?",
                "Confirm Submission", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                submitExam();
            }
        });

        logoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                this, "Are you sure you want to quit the exam?",
                "Confirm Exit", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                stopTimer();
                showLoginScreen();
            }
        });

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        displayQuestion();
        startTimer();

        revalidate();
        repaint();
    }

    private void displayQuestion() {
        Question q = questions.get(currentQuestion);

        questionLabel.setText(
            "<html>Question " + (currentQuestion + 1)
            + " of " + questions.size()
            + "<br><br>" + q.question + "</html>");

        optionGroup.clearSelection();

        for (int i = 0; i < 4; i++) {
            options[i].setText(q.options[i]);
        }

        if (selectedAnswers[currentQuestion] != -1) {
            options[selectedAnswers[currentQuestion]]
                .setSelected(true);
        }
    }

    private void saveCurrentAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                selectedAnswers[currentQuestion] = i;
                return;
            }
        }
        selectedAnswers[currentQuestion] = -1;
    }

    private void startTimer() {
        stopTimer();

        timer = new Timer(1000, e -> {
            timeRemaining--;
            updateTimerLabel();

            if (timeRemaining <= 0) {
                stopTimer();
                saveCurrentAnswer();

                JOptionPane.showMessageDialog(
                    this, "Time is up! Exam submitted automatically.");

                submitExam();
            }
        });

        timer.start();
    }

    private void updateTimerLabel() {
        timerLabel.setText(String.format(
            "Time: %02d:%02d",
            timeRemaining / 60, timeRemaining % 60));
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void submitExam() {
        stopTimer();

        int correctAnswers = 0;
        int answered = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (selectedAnswers[i] != -1) {
                answered++;
            }

            if (selectedAnswers[i] == questions.get(i).correctAnswer) {
                correctAnswers++;
            }
        }

        showResultScreen(answered, correctAnswers,
                         300 - timeRemaining);
    }

    private void showResultScreen(
            int answered, int correctAnswers, int timeTaken) {

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(
            BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(BACKGROUND);

        JLabel title = new JLabel(
            "Examination Result", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(PRIMARY);

        int incorrect = answered - correctAnswers;
        int unanswered = questions.size() - answered;
        double percentage =
            correctAnswers * 100.0 / questions.size();

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 18));
        resultArea.setBackground(BACKGROUND);

        resultArea.setText(
            "Student: " + displayName + "\n\n"
            + "Score: " + correctAnswers
            + " / " + questions.size() + "\n\n"
            + String.format("Percentage: %.2f%%\n\n", percentage)
            + "Correct Answers: " + correctAnswers + "\n"
            + "Incorrect Answers: " + incorrect + "\n"
            + "Unanswered: " + unanswered + "\n\n"
            + "Time Taken: "
            + String.format("%02d:%02d",
                timeTaken / 60, timeTaken % 60));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> showLoginScreen());

        panel.add(title, BorderLayout.NORTH);
        panel.add(resultArea, BorderLayout.CENTER);
        panel.add(logoutButton, BorderLayout.SOUTH);

        setContentPane(panel);
        revalidate();
        repaint();
    }

    private static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OnlineExaminationSystem exam =
                new OnlineExaminationSystem();
            exam.setVisible(true);
        });
    }
}
