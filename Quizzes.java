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
    // List of pages, one for panel and one for QuizPage object
    QuizPage[] quizList = new QuizPage[5];
    JPanel[] quizPanel = new JPanel[5];
    Random random = new Random();

    // Sentinels for assigning a type of question to a lesson page
    // Works by adding different interactables to each question type (e.g., one for changing angle, one for changing height, one to input a guess, etc.)
    public static final int VELOCITY = 1;
    public static final int HEIGHT = 2;
    public static final int ANGLE = 3;
    public static final int MC = 4;
    public static final int SIMULATOR = 0;

    public Quizzes() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // List of all the questions in the text file
        List<String> questions = loadQuestionsFromFile("questions.txt");

        // Create pages for all the lessons
        QuizPage lesson1 = lesson1(questions.get(0));
        QuizPage lesson2 = lesson2(questions.get(1));
        QuizPage lesson3 = lesson3(questions.get(2));
        QuizPage lesson4 = lesson4(questions.get(3));
        QuizPage lesson5 = lesson5(questions.get(4));
        quizList[0] = lesson1;
        quizList[1] = lesson2;
        quizList[2] = lesson3;
        quizList[3] = lesson4;
        quizList[4] = lesson5;


        // Add the "Next" and "Back" buttons
        for (int i = 0; i < quizList.length; i++) {
            JPanel completePage = quizList[i].getPanel();

            // Back button
            JPanel buttons = new JPanel(new FlowLayout());
            Font button = new Font("Serif", Font.PLAIN, 20);
            JButton back = new JButton("Back");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Only go backwards if you are not on the first page
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
                    // If you have not finished or tried less than 3 times, continue checking
                    if (quizList[pageOn].finished() || quizList[pageOn].getTries() >= 3) {
                        // Ask user if they want to continue if they have not completed the sim but have tried 3 times (lost the point)
                        if (quizList[pageOn].getTries() >= 5 && !quizList[pageOn].finished()) {
                            int ans = JOptionPane.showConfirmDialog(null, "You have not completed the simulation. Would you like to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);

                            if (ans == JOptionPane.YES_OPTION) {
                                pageOn += 1;
                                newScreen();
                            }
                        }
                        // Next Page 
                        else {
                            pageOn += 1;
                            newScreen();
                        }
                    } 
                    // If criteria is not met, do not go to next page
                    else {
                         JOptionPane.showMessageDialog(null, "Please complete the simulation first, or try 3 times.", "Alert!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            buttons.add(back);
            buttons.add(next);
            completePage.add(buttons, BorderLayout.SOUTH);

            // Replace the incomplete page with the finished page in the JPanel list
            quizPanel[i] = completePage;
        }

        // Start on question 1
        frame.add(quizPanel[0]);
        frame.setVisible(true);
    }

    // Get the questions from the text file and make a list
    private List<String> loadQuestionsFromFile(String fileName) {
        List<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Put the line into the array list
            while ((line = br.readLine()) != null) {
                questions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return questions;
    }

    // Changing screens in the page
    public void newScreen() {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizList[pageOn].updateScore();
        quizPanel[pageOn] = quizList[pageOn].getPanel();
        frame.add(quizPanel[pageOn]);
        frame.setVisible(true);
    }

    // LESSON PAGES BELOW:
    //---------------------------------------------------
    public QuizPage lesson1(String question) {
        // Solve for initial height
        String title = "Question 1";
        // Text for the question
        String[] text = new String[5];
        // Randomize the question's given values with a range
        double angle = random.nextDouble(30) + 10;
        double initialVelocity = random.nextDouble(5) + 10;
        double answer = random.nextDouble(15) + 20;
        
        // Put the randomized values into a list for the QuizPage to read
        double[] simValues = new double[4];
        simValues[0] = initialVelocity;
        simValues[1] = angle;
        simValues[2] = answer;
        simValues[3] = 0.1;
        
        text[0] = question;
        // Replace the location symbols with the randomized values (rounded to fit the screen)
        // Operations for rounding decimal places
        text[0] = text[0].replace("*", Double.toString((double)((int)(angle*10000))/10000.0));
        text[0] = text[0].replace(">", Double.toString((double)((int)(initialVelocity*10000))/10000.0));
        text[0] = text[0].replace("@", Double.toString((double)((int)(answer*10000))/10000.0));

        QuizPage quiz = new QuizPage(frame, title, text, HEIGHT, simValues);

        return quiz;
    }
    
    public QuizPage lesson2(String question) {
        // Solve for angle
        String title = "Question 2";
        String[] text = new String[5];
        double angle = random.nextDouble(40) + 15;
        double initialHeight = random.nextDouble(5) + 0;
        double answer = random.nextDouble(25) + 170;
        
        double[] simValues = new double[4];
        simValues[0] = initialHeight;
        simValues[1] = angle;
        simValues[2] = answer;
        simValues[3] = 0.5;
        
        text[0] = question;
        text[0] = text[0].replace("*", Double.toString(angle));
        text[0] = text[0].replace("_", Double.toString(initialHeight));
        text[0] = text[0].replace("@", Double.toString(answer));

        QuizPage quiz = new QuizPage(frame, title, text, VELOCITY, simValues);

        return quiz;
    }

    public QuizPage lesson3(String question) {
        // Solve for initial velocity
        String title = "Question 3";
        String[] text = new String[5];
        double initialVelocity = random.nextDouble(10) + 139;
        double initialHeight = random.nextDouble(2) + 9;
        double answer = random.nextDouble(200) + 1000;
        
        double[] simValues = new double[4];
        simValues[0] = initialVelocity;
        simValues[1] = initialHeight;
        simValues[2] = answer;
        simValues[3] = 1.0;
        
        text[0] = question;
        text[0] = text[0].replace(">", Double.toString(initialVelocity));
        text[0] = text[0].replace("_", Double.toString(initialHeight));
        text[0] = text[0].replace("@", Double.toString(answer));

        QuizPage quiz = new QuizPage(frame, title, text, ANGLE, simValues);

        return quiz;
    }

    public QuizPage lesson4(String question) {
        // Solve for landing zone
        String title = "Question 4";
        String[] text = new String[5];
        double initialVelocity = random.nextDouble(75) + 100;
        double initialHeight = random.nextDouble(5) + 2;
        double angle = random.nextDouble(5) + 15;
        
        double[] simValues = new double[4];
        simValues[0] = initialVelocity;
        simValues[1] = initialHeight;
        simValues[2] = angle;
        simValues[3] = 0.75;
        
        text[0] = question;
        text[0] = text[0].replace(">", Double.toString(initialVelocity));
        text[0] = text[0].replace("_", Double.toString(initialHeight));
        text[0] = text[0].replace("*", Double.toString(angle));

        QuizPage quiz = new QuizPage(frame, title, text, MC, simValues);

        return quiz;
    }

    public QuizPage lesson5(String question) {
        // Solve for landing zone
        String title = "Question 5";
        String[] text = new String[5];
        double initialVelocity = random.nextDouble(6) + 20;
        double initialHeight = random.nextDouble(3) + 1;
        double angle = random.nextDouble(15) + 25;
        
        double[] simValues = new double[4];
        simValues[0] = initialVelocity;
        simValues[1] = initialHeight;
        simValues[2] = angle;
        simValues[3] = 0.15;
        
        text[0] = question;
        text[0] = text[0].replace(">", Double.toString(initialVelocity));
        text[0] = text[0].replace("_", Double.toString(initialHeight));
        text[0] = text[0].replace("*", Double.toString(angle));

        QuizPage quiz = new QuizPage(frame, title, text, MC, simValues);

        return quiz;
    }
    
}