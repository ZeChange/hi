import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
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

        // Lesson 1:
        QuizPage lesson1 = lesson1();
        quizList[0] = lesson1;
        QuizPage lesson2 = lesson2();
        quizList[1] = lesson2;
        QuizPage lesson3 = lesson3();
        quizList[2] = lesson3;
        QuizPage lesson4 = lesson4();
        quizList[3] = lesson4;
        QuizPage lesson5 = lesson5();
        quizList[4] = lesson5;

        
        for(int i = 0; i < quizList.length; i++)
        {
            JPanel completePage = quizList[i].getPanel();

            JPanel buttons = new JPanel(new FlowLayout());
            Font button = new Font("Serif", Font.PLAIN, 20);;
            JButton back = new JButton("Back");
            back.addActionListener(new ActionListener() {@Override
                public void actionPerformed(ActionEvent e) {
                    if(pageOn != 0)
                    {
                        pageOn -= 1;
                        newScreen();
                    }
                }
            });
            back.setFont(button);

            JButton next = new JButton("Next");
            next.setFont(button);
            next.addActionListener(new ActionListener() {@Override
                public void actionPerformed(ActionEvent f) {
                    if(pageOn < quizList.length-1 && (quizList[pageOn].finished() || quizList[pageOn].getTries() >= 5))
                    {
                        if(quizList[pageOn].getTries() >= 5 && !quizList[pageOn].finished())
                        {
                            int ans = JOptionPane.showConfirmDialog(null, "You have not completed the simulation. Would you like to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);

                            if (ans == JOptionPane.YES_OPTION)
                            {
                                pageOn += 1;
                                newScreen();
                            }
                        }
                        else{
                            pageOn += 1;
                            newScreen();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog( null, "Please complete the simulation first, or try 5 times.", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                    }
                }
            });
            buttons.add(back); buttons.add(next);
            completePage.add(buttons, BorderLayout.SOUTH);

            quizPanel[i] = completePage;
        }

        frame.add(quizPanel[0]);
        frame.setVisible(true);
    }   

    public void newScreen()
    {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(quizPanel[pageOn]);
        frame.setVisible(true);
    }

    public QuizPage lesson1() {
        String title = "Question 1";
        String[] text = new String[5];
        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(101);

        double horizontalVelocity = initialVelocity * Math.cos(Math.toRadians(angle));
        double verticalVelocity = initialVelocity * Math.sin(Math.toRadians(angle));

        // answer
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedHorizontalVelocity = decimalFormat.format(horizontalVelocity);
        String formattedVerticalVelocity = decimalFormat.format(verticalVelocity);
        System.out.println("Question1: " + formattedHorizontalVelocity);
        System.out.println("Question 1: " + formattedVerticalVelocity);

        
        text[0] = "A soccer ball is kicked at an angle of " + angle + " degrees with the ground.";
        text[1] = "If its initial velocity is " + initialVelocity + " m/s, calculate the horizontal and vertical components of its velocity.";
        
        // answer
        // text[2] = "Horizontal velocity: " + formattedHorizontalVelocity + " m/s";
        // text[3] = "Vertical velocity: " + formattedVerticalVelocity + " m/s";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }

    public QuizPage lesson2() {
        String title = "Question 2";
        String[] text = new String[5];

        Random random = new Random();
        int height = random.nextInt(101) + 10;
        int initialVelocity = random.nextInt(81) + 20;

        // Calculate the horizontal distance traveled
        double time = 2 * height / 9.8; // Time of flight
        double horizontalDistance = initialVelocity * time;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedHorizontalDistance = decimalFormat.format(horizontalDistance);
        System.out.println("Question 2:" + formattedHorizontalDistance);

        text[0] = "A baseball is thrown horizontally from a height of " + height + " meters with an initial velocity of " + initialVelocity + " m/s.";
        text[1] = "How far does it travel horizontally before hitting the ground?";

        // answer
        // text[2] = "Horizontal distance: " + formattedHorizontalDistance + " meters";

        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }
    public QuizPage lesson3() {
        String title = "Question 3";
        String[] text = new String[3];

        // Randomize the angle and initial velocity
        Random random = new Random();
        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(81) + 20;

        // Calculate the maximum height reached
        double maxHeight = (Math.pow(initialVelocity, 2) * Math.pow(Math.sin(Math.toRadians(angle)), 2)) / (2 * 9.8); // Formula for maximum height

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedMaxHeight = decimalFormat.format(maxHeight);
        System.out.println("Question 3: " + formattedMaxHeight);

        // Construct the question text
        text[0] = "A cannonball is launched at an angle of " + angle + " degrees above the horizontal with an initial velocity of " + initialVelocity + " m/s.";
        text[1] = "Determine the maximum height reached by the cannonball.";
        // text[2] = "Maximum height: " + formattedMaxHeight + " meters";

        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }

    public QuizPage lesson4() {
        String title = "Question 4";
        String[] text = new String[3];

        // Randomize the angle and initial velocity
        Random random = new Random();
        int angle = random.nextInt(31) + 30;
        int initialVelocity = random.nextInt(81) + 20;

        // Calculate the time to reach maximum height
        double timeToMaxHeight = (initialVelocity * Math.sin(Math.toRadians(angle))) / 9.8; // Formula for time to reach maximum height

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedTimeToMaxHeight = decimalFormat.format(timeToMaxHeight);
        System.out.println("Question 4: " + formattedTimeToMaxHeight);

        // Construct the question text
        text[0] = "A javelin is thrown with an initial velocity of " + initialVelocity + " m/s at an angle of " + angle + " degrees with the ground.";
        text[1] = "Calculate the time it takes for the javelin to reach its maximum height.";
        // text[2] = "Time to reach maximum height: " + formattedTimeToMaxHeight + " seconds";

        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }

    public QuizPage lesson5() {
        String title = "Question 5";
        String[] text = new String[3];

        // Randomize the angle and horizontal distance
        Random random = new Random();
        int angle = random.nextInt(31) + 30; // Random angle between 30 and 60 degrees
        int horizontalDistance = random.nextInt(61) + 20; // Random horizontal distance between 20 and 50 meters

        // Calculate the time of flight
        double initialVelocity = 10.0; // Fixed initial velocity
        double timeOfFlight = (2 * initialVelocity * Math.sin(Math.toRadians(angle))) / 9.8; // Formula for time of flight

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedTimeOfFlight = decimalFormat.format(timeOfFlight);
        System.out.println("Question 5: " + formattedTimeOfFlight);

        text[0] = "A basketball is thrown at an angle of " + angle + " degrees with the ground, and it lands " + horizontalDistance + " meters away.";
        text[1] = "If its initial velocity is " + initialVelocity + " m/s, calculate the time of flight.";
        // text[2] = "Time of flight: " + formattedTimeOfFlight + " seconds";

        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }
}