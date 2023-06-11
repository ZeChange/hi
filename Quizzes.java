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
    QuizPage[] quizList = new QuizPage[5];
    JPanel[] quizPanel = new JPanel[5];
    Random random = new Random();

    public Quizzes() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<String> questions = loadQuestionsFromFile("questions.txt");

        // Lesson 1:
        QuizPage lesson1 = lesson1(questions.get(0));
        quizList[0] = lesson1;
        QuizPage lesson2 = lesson2(questions.get(1));
        quizList[1] = lesson2;
        QuizPage lesson3 = lesson3(questions.get(2));
        quizList[2] = lesson3;
        QuizPage lesson4 = lesson4(questions.get(3));
        quizList[3] = lesson4;
        QuizPage lesson5 = lesson5(questions.get(4));
        quizList[4] = lesson5;

        for (int i = 0; i < quizList.length; i++) {
            JPanel completePage = quizList[i].getPanel();

            JPanel buttons = new JPanel(new FlowLayout());
            Font button = new Font("Serif", Font.PLAIN, 20);
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

            JButton next = new JButton("Next");
            next.setFont(button);
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                    if (pageOn < quizList.length - 1 && (quizList[pageOn].finished() || quizList[pageOn].getTries() >= 5)) {
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

    public void newScreen() {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(quizPanel[pageOn]);
        frame.setVisible(true);
    }

    public QuizPage lesson1(String question) {
        String title = "Question 1";
        String[] text = new String[5];
        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(101);

        double horizontalVelocity = initialVelocity * Math.cos(Math.toRadians(angle));
        double verticalVelocity = initialVelocity * Math.sin(Math.toRadians(angle));
        
        text[0] = question;
        text[0] = text[0].replace("_", Integer.toString(angle));
        text[0] = text[0].replace("*", Integer.toString(initialVelocity));

        QuizPage quiz = new QuizPage(frame, title, text);

        System.out.println("Question 1 - Horizontal Velocity: " + horizontalVelocity);
        System.out.println("Question 1 - Vertical Velocity: " + verticalVelocity);

        return quiz;
    }

    public QuizPage lesson2(String question) {
        String title = "Question 2";
        String[] text = new String[5];

        int height = random.nextInt(101) + 10;
        int initialVelocity = random.nextInt(81) + 20;

        double time = Math.sqrt(2 * height / 9.8);
        double horizontalDistance = initialVelocity * time;
        
        text[0] = question;
        text[0] = text[0].replace("_", Integer.toString(height));
        text[0] = text[0].replace("*", Integer.toString(initialVelocity));

        QuizPage quiz = new QuizPage(frame, title, text);

        System.out.println("Question 2 - Distance: " + horizontalDistance);

        return quiz;
    }

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
}
