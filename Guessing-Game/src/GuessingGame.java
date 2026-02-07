import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuessingGame extends JFrame {
    private int randomNumber;
    private int maxGuesses = 10;
    private int attempts = 0;
    private String username;
    private static Map<String, Integer> bestScores = new HashMap<>();

    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptLabel;
    private JButton guessButton;
    private JButton retryButton;
    private JButton startButton;
    private JPanel gamePanel;
    private JPanel welcomePanel;
    private JMenuBar menuBar;
    private JMenuItem newGameItem;
    private JMenuItem quitItem;
    private JMenuItem helpItem;
    private JMenuItem howToPlayItem;
    private JMenuItem faqItem;
    private JMenuItem bestScoresItem;
    private Image backgroundImage;

    public GuessingGame(String username) {
        this.username = username;

        // Load background image
        backgroundImage = new ImageIcon("C:\\Users\\hudso\\OneDrive\\Desktop\\guessing game12\\Guessing-Game\\src\\resources\\guessing.jpg.jpg").getImage(); // Replace with your image path

        // Set application icon
        setIconImage(new ImageIcon("path/to/your/icon.png").getImage()); // Replace with your icon path

        // Generate random number
        randomNumber = new Random().nextInt(1000) + 1;

        // Setup GUI
        setTitle("Guess the Number Game - Developed by Guessing Masters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        setSize(700, 500);

        // Menu bar setup
        menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        newGameItem = new JMenuItem("New Game");
        quitItem = new JMenuItem("Quit");
        newGameItem.addActionListener(e -> startNewGame());
        quitItem.addActionListener(e -> System.exit(0));
        gameMenu.add(newGameItem);
        gameMenu.add(quitItem);

        JMenu helpMenu = new JMenu("Help");
        helpItem = new JMenuItem("About");
        helpItem.addActionListener(e -> showHelp());
        howToPlayItem = new JMenuItem("How to Play");
        howToPlayItem.addActionListener(e -> showHowToPlay());
        faqItem = new JMenuItem("FAQ");
        faqItem.addActionListener(e -> showFAQ());
        helpMenu.add(helpItem);
        helpMenu.add(howToPlayItem);
        helpMenu.add(faqItem);

        JMenu scoreMenu = new JMenu("Scores");
        bestScoresItem = new JMenuItem("Best Scores");
        bestScoresItem.addActionListener(e -> showBestScoresFrame());
        scoreMenu.add(bestScoresItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        menuBar.add(scoreMenu);
        setJMenuBar(menuBar);

        // Welcome Panel
        welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        welcomePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel welcomeLabel = new JLabel("Welcome to the Guessing Game!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        welcomePanel.add(welcomeLabel, gbc);

        JLabel instructionLabel = new JLabel("Ready to begin? Press Start to start the fun!");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setForeground(Color.YELLOW);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        welcomePanel.add(instructionLabel, gbc);

        startButton = createStyledButton("Start", Color.GREEN, new Dimension(150, 50));
        startButton.addActionListener(e -> showGamePanel());
        gbc.gridx = 0;
        gbc.gridy = 2;
        welcomePanel.add(startButton, gbc);

        add(welcomePanel);

        // Game Panel
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        gamePanel.setLayout(new GridBagLayout());

        JLabel instructionsLabel = new JLabel("Guess a number between 1 and 1000:");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gamePanel.add(instructionsLabel, gbc);

        // Centered input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.setOpaque(false);
        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(250, 40));
        guessField.setFont(new Font("Arial", Font.BOLD, 20));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gamePanel.add(inputPanel, gbc);
        inputPanel.add(guessField);

        feedbackLabel = new JLabel("Enter your guess above.");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setForeground(Color.YELLOW);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gamePanel.add(feedbackLabel, gbc);

        attemptLabel = new JLabel("Attempts left: " + (maxGuesses - attempts));
        attemptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptLabel.setForeground(Color.WHITE);
        attemptLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gamePanel.add(attemptLabel, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        guessButton = createStyledButton("Submit", Color.BLUE, new Dimension(150, 50));
        guessButton.addActionListener(new GuessHandler());
        buttonPanel.add(guessButton);

        retryButton = createStyledButton("Retry", Color.RED, new Dimension(150, 50));
        retryButton.setEnabled(false);
        retryButton.addActionListener(e -> retryGame());
        buttonPanel.add(retryButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gamePanel.add(buttonPanel, gbc);

        add(gamePanel);
        gamePanel.setVisible(false);

        // Add ComponentListener for resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });

        setVisible(true);
    }

    private void resizeComponents() {
        int width = getWidth();
        int height = getHeight();

        // Resize all components dynamically
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component child : panel.getComponents()) {
                    if (child instanceof JLabel) {
                        child.setFont(new Font("Arial", Font.BOLD, Math.max(width / 50, 20)));
                    } else if (child instanceof JTextField) {
                        child.setFont(new Font("Arial", Font.BOLD, Math.max(width / 50, 20)));
                        child.setPreferredSize(new Dimension(width / 3, height / 15));
                    } else if (child instanceof JButton) {
                        child.setFont(new Font("Arial", Font.BOLD, Math.max(width / 50, 20)));
                        child.setPreferredSize(new Dimension(width / 8, height / 15));
                    }
                }
                panel.revalidate();
                panel.repaint();
            }
        }
    }

    private JButton createStyledButton(String text, Color color, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(size);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
                button.setForeground(Color.WHITE);
            }
        });
        return button;
    }

    private void showGamePanel() {
        welcomePanel.setVisible(false);
        gamePanel.setVisible(true);
    }

    private void retryGame() {
        attempts = 0;
        feedbackLabel.setText("Enter your guess above.");
        attemptLabel.setText("Attempts left: " + maxGuesses);
        guessField.setText("");
        guessButton.setEnabled(true);
        retryButton.setEnabled(false);
    }

    private void startNewGame() {
        randomNumber = new Random().nextInt(1000) + 1;
        retryGame();
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(this, "Developed by Guessing Masters\nGuess the number between 1 and 1000.", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showHowToPlay() {
        JOptionPane.showMessageDialog(this, "1. Press Start to begin the game.\n2. Guess a number between 1 and 1000.\n3. You'll get feedback on whether your guess is too high or too low.\n4. You have 10 attempts to guess the correct number.\nGood luck!", "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showFAQ() {
        JOptionPane.showMessageDialog(this, "Q: How many attempts do I get?\nA: You get 10 attempts to guess the number.\n\nQ: What happens if I run out of attempts?\nA: You can click Retry to try again.\n\nQ: Can I start a new game anytime?\nA: Yes, use the New Game option in the Game menu.", "FAQ", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showBestScoresFrame() {
        JFrame scoresFrame = new JFrame("Best Scores");
        scoresFrame.setSize(400, 300);
        scoresFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Username", "Score"};
        String[][] data = new String[bestScores.size()][2];
        int index = 0;
        for (Map.Entry<String, Integer> entry : bestScores.entrySet()) {
            data[index][0] = entry.getKey();
            data[index][1] = String.valueOf(entry.getValue());
            index++;
        }

        JTable scoresTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scoresFrame.add(scrollPane, BorderLayout.CENTER);

        scoresFrame.setVisible(true);
    }

    private String getHint() {
        if (attempts == 5) {
            String numberStr = String.valueOf(randomNumber);
            if (numberStr.length() == 2) {
                return "Hint: " + numberStr.charAt(0) + "*";
            } else if (numberStr.length() == 3) {
                return "Hint: " + numberStr.charAt(0) + numberStr.charAt(1) + "*";
            }
        }
        return "";
    }

    private class GuessHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = guessField.getText();
            try {
                int guess = Integer.parseInt(input);
                attempts++;
                if (guess == randomNumber) {
                    String message;
                    if (attempts == 1) {
                        message = "Excellent!! You guessed it on the first try!";
                    } else if (attempts == 2) {
                        message = "Very Good!!! You guessed it in two attempts!";
                    } else if (attempts == 3) {
                        message = "Good!! You guessed it in three attempts!";
                    } else if (attempts == 4) {
                        message = "Satisfactory. You guessed it in four attempts.";
                    } else {
                        message = "Nice! You guessed it in " + attempts + " attempts.";
                    }
                    JOptionPane.showMessageDialog(null, message);
                    updateBestScore();
                    startNewGame();
                } else {
                    feedbackLabel.setText(guess < randomNumber ? "Too low! Try a higher number." : "Too high! Try a lower number.");
                    if (attempts == 5) {
                        feedbackLabel.setText(feedbackLabel.getText() + " " + getHint());
                    }
                }
                if (attempts >= maxGuesses) {
                    JOptionPane.showMessageDialog(null, "You've run out of attempts! Click Retry to try again.");
                    guessButton.setEnabled(false);
                    retryButton.setEnabled(true);
                }
                attemptLabel.setText("Attempts left: " + (maxGuesses - attempts));
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    private void updateBestScore() {
        if (!bestScores.containsKey(username) || attempts < bestScores.get(username)) {
            bestScores.put(username, attempts);
        }
    }
    
   

    public static void main(String[] args) {
        
    SwingUtilities.invokeLater(() -> {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(700, 500);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\hudso\\OneDrive\\Desktop\\guess.jpg"); // Replace with your image path
        Image backgroundImage = backgroundIcon.getImage();

        JPanel loginPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        GridBagLayout gridBagLayout = new GridBagLayout();
        loginPanel.setLayout(gridBagLayout);

        // Create components
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        // Set initial styles
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        userLabel.setFont(labelFont);
        userLabel.setForeground(Color.WHITE);
        passLabel.setFont(labelFont);
        passLabel.setForeground(Color.WHITE);

        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        // Add resize listener to adjust font size dynamically
        loginFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = loginFrame.getWidth();
                int frameHeight = loginFrame.getHeight();

                // Dynamically calculate font size based on frame size
                int newFontSize = Math.min(frameWidth / 20, frameHeight / 25);
                Font resizedFont = new Font("Arial", Font.BOLD, newFontSize);

                userLabel.setFont(resizedFont);
                passLabel.setFont(resizedFont);
                loginButton.setFont(new Font("Arial", Font.BOLD, newFontSize - 2));

                userField.setFont(new Font("Arial", Font.PLAIN, newFontSize - 4));
                passField.setFont(new Font("Arial", Font.PLAIN, newFontSize - 4));
            }
        });

        // Add login action
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (!username.isEmpty() && !password.isEmpty()) {
                loginFrame.dispose();
                new GuessingGame(username);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    });
}}