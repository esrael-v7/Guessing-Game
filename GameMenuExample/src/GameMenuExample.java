import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GameMenuExample {
    private static final String RECORD_FILE = "user_records.txt";
    private Map<String, Integer> userRecords = new HashMap<>();
    private String currentUser = "Guest";
    private int bestScore;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameMenuExample().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Load the records from file
        loadUserRecords();

        // Create the main frame
        JFrame frame = new JFrame("Guessing Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Add "Game" menu
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> startNewGame(frame));
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> System.exit(0));
        gameMenu.add(newGameItem);
        gameMenu.add(quitItem);
        menuBar.add(gameMenu);

        // Add "Help" menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showMessage("About", "This is a number guessing game where you guess a number between 1 and 100."));
        JMenuItem howToPlayItem = new JMenuItem("How to Play");
        howToPlayItem.addActionListener(e -> showMessage("How to Play", "Enter a number between 1 and 100. The game will tell you if you're too high or too low."));
        JMenuItem faqItem = new JMenuItem("FAQ");
        faqItem.addActionListener(e -> showMessage("FAQ", "Q: How is the score calculated?\nA: The fewer guesses, the higher the score."));
        helpMenu.add(aboutItem);
        helpMenu.add(howToPlayItem);
        helpMenu.add(faqItem);
        menuBar.add(helpMenu);

        // Add "Best Score" menu
        JMenu bestScoreMenu = new JMenu("Best Score");
        JMenuItem viewBestScoreItem = new JMenuItem("View Best Score");
        viewBestScoreItem.addActionListener(e -> showMessage("Best Score", "Player: " + currentUser + "\nBest Score: " + bestScore));
        bestScoreMenu.add(viewBestScoreItem);
        menuBar.add(bestScoreMenu);

        // Add player info
        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel playerLabel = new JLabel("Player: ");
        JTextField playerField = new JTextField(currentUser, 15);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            currentUser = playerField.getText().trim();
            bestScore = userRecords.getOrDefault(currentUser, 0);
            updateTitle(frame);
        });
        playerPanel.add(playerLabel);
        playerPanel.add(playerField);
        playerPanel.add(loginButton);
        frame.add(playerPanel, BorderLayout.NORTH);

        // Add menu bar to frame
        frame.setJMenuBar(menuBar);
        updateTitle(frame);

        // Show the frame
        frame.setVisible(true);
    }

    private void startNewGame(JFrame frame) {
        int numberToGuess = (int) (Math.random() * 100) + 1;
        int attempts = 0;
        while (true) {
            String guess = JOptionPane.showInputDialog(frame, "Guess the number (1-100):", "New Game", JOptionPane.QUESTION_MESSAGE);
            if (guess == null) {
                JOptionPane.showMessageDialog(frame, "Game canceled.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            attempts++;
            int guessedNumber;
            try {
                guessedNumber = Integer.parseInt(guess);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (guessedNumber < numberToGuess) {
                JOptionPane.showMessageDialog(frame, "Too low!", "Hint", JOptionPane.INFORMATION_MESSAGE);
            } else if (guessedNumber > numberToGuess) {
                JOptionPane.showMessageDialog(frame, "Too high!", "Hint", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int score = Math.max(100 - attempts, 0);
                JOptionPane.showMessageDialog(frame, "Correct! You guessed it in " + attempts + " attempts. Your score: " + score, "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                updateUserRecord(currentUser, score);
                updateTitle(frame);
                return;
            }
        }
    }

    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTitle(JFrame frame) {
        frame.setTitle("Guessing Number Game - Player: " + currentUser + " | Best Score: " + bestScore);
    }

    private void loadUserRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userRecords.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("No records found, starting fresh.");
        }
    }

    private void updateUserRecord(String username, int score) {
        bestScore = Math.max(score, userRecords.getOrDefault(username, 0));
        userRecords.put(username, bestScore);
        saveUserRecords();
    }

    private void saveUserRecords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_FILE))) {
            for (Map.Entry<String, Integer> entry : userRecords.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}