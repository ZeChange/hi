import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.text.DecimalFormat;

public class Quizzes {
    private JFrame frame = new JFrame("Projectile Motion Quiz");
    private int pageOn = 0;
    QuizPage[] quizList = new QuizPage[2];
    JPanel[] quizPanel = new JPanel[2];
    Random random = new Random();

    public static final int VELOCITY = 1;
    public static final int HEIGHT = 2;
    public static final int ANGLE = 3;
    public static final int MC = 0;

    public Quizzes() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<String> questions = loadQuestionsFromFile("questions.txt");

        // Lesson 1:
        QuizPage lesson1 = lesson1(questions.get(0));
        quizList[0] = lesson1;
        QuizPage lesson2 = lesson2(questions.get(1));
        quizList[1] = lesson2;

        for (int i = 0; i < quizList.length; i++) {
            JPanel completePage = quizList[i].getPanel();

            JPanel buttons = new JPanel(new FlowLayout());
            Font button = new Font("Serif", Font.PLAIN, 20);
            
            // Back button
            JButton back = new JButton("Back");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pageOn != 0) {
                        pageOn -= 1;
                        newScreen();
                    }
                }
            });
            back.setFont(button);

            // Next button
            JButton next = new JButton("Next");
            next.setFont(button);
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                    if (quizList[pageOn].finished() || quizList[pageOn].getTries() >= 5) {
                        if (quizList[pageOn].getTries() >= 5 && !quizList[pageOn].finished()) {
                            int ans = JOptionPane.showConfirmDialog(null, "You have not completed the simulation. Would you like to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);

                            if (ans == JOptionPane.YES_OPTION) {
                                pageOn += 1;
                                newScreen();
                            }
                        } else {
                            pageOn += 1;
                            newScreen();
                        }
                    } else if(pageOn < quizList.length - 1) {
                        // TODO: Handle special case if needed
                    } else {
                        JOptionPane.showMessageDialog(null, "Please complete the simulation first, or try 5 times.", "Alert!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            buttons.add(back);
            buttons.add(next);
            completePage.add(buttons, BorderLayout.SOUTH);

            quizPanel[i] = completePage;
        }

        frame.add(quizPanel[0]);
        frame.setVisible(true);
    }

    // Loads questions from a file
    private List<String> loadQuestionsFromFile(String fileName) {
        List<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                questions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return questions;
    }

    // Creates a new screen for the current quiz page
    public void newScreen() {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(quizPanel[pageOn]);
        frame.setVisible(true);
    }

    // Generates QuizPage for Lesson 1
    public QuizPage lesson1(String question) {
        // Solve for initial height
        String title = "Question 1";
        String[] text = new String[5];
        double angle = random.nextDouble(30) + 10;
        double initialVelocity = random.nextDouble(3) + 6;
        
        double[] simValues = new double[4];
        simValues[0] = initialVelocity/10.0;
        simValues[1] = angle;
        simValues[2] = 40.0;
        simValues[3] = 0.1;
        
        text[0] = question;
        text[0] = text[0].replace("_", Double.toString(angle));
        text[0] = text[0].replace("*", Double.toString(initialVelocity));

        QuizPage quiz = new QuizPage(frame, title, text, HEIGHT, simValues);

        return quiz;
    }
    
    // Generates QuizPage for Lesson 2
    public QuizPage lesson2(String question) {
        // Solve for initial velocity
        String title = "Question 2";
        String[] text = new String[5];
        double angle = random.nextDouble(40) + 15;
        double initialHeight = random.nextDouble(5) + 0;
        
        double[] simValues = new double[4];
        simValues[0] = initialHeight/2.0;
        simValues[1] = angle;
        simValues[2] = 300.0;
        simValues[3] = 0.5;
        
        text[0] = question;
        text[0] = text[0].replace("_", Double.toString(angle));
        text[0] = text[0].replace("*", Double.toString(initialHeight));

        QuizPage quiz = new QuizPage(frame, title, text, VELOCITY, simValues);

        return quiz;
    }
/* 
    public QuizPage lesson3(String question) {
        String title = "Question 3";
        String[] text = new String[5];

        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(81) + 20;

        double maxHeight = (Math.pow(initialVelocity, 2) * Math.pow(Math.sin(Math.toRadians(angle)), 2)) / (2 * 9.8); 
        
        text[0] = question;
        text[0] = text[0].replace("_", String.valueOf(angle));
        text[0] = text[0].replace("*", String.valueOf(initialVelocity));

        QuizPage quiz = new QuizPage(frame, title, text);

        System.out.println("Question 3 - Maximum Height: " + maxHeight);

        return quiz;
    }

    public QuizPage lesson4(String question) {
        String title = "Question 4";
        String[] text = new String[5];

        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(81) + 20;

        double timeToMaxHeight = (initialVelocity * Math.sin(Math.toRadians(angle))) / 9.8;
        
        text[0] = question;
        text[0] = text[0].replace("_", String.valueOf(angle));
        text[0] = text[0].replace("*", String.valueOf(initialVelocity));

        QuizPage quiz = new QuizPage(frame, title, text);

        System.out.println("Question 4 - Time to max height: " + timeToMaxHeight);

        return quiz;
    }

    public QuizPage lesson5(String question) {
        String title = "Question 5";
        String[] text = new String[5];

        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(21) + 10;
        int distance = random.nextInt(41) + 10;

        double totalTime = (2 * initialVelocity * Math.sin(Math.toRadians(angle))) / 9.8; // Formula for total time of flight

        text[0] = question;
        text[0] = text[0].replace("_", String.valueOf(angle));
        text[0] = text[0].replace("*", String.valueOf(initialVelocity));
        text[0] = text[0].replace("@", String.valueOf(distance));

        QuizPage quiz = new QuizPage(frame, title, text);

        System.out.println("Question 5 - Time: " + totalTime);

        return quiz;
    }
    */
}