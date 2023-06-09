import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quizzes {
    private JFrame frame = new JFrame("Projectile Motion Quiz");
    private int pageOn = 0;
    QuizPage[] quizList = new QuizPage[3];
    JPanel[] quizPanel = new JPanel[3];

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
        String title = "question 1";
        String[] text = new String[5];
        text[0] = "if the impostor is sus";
        text[1] = "does that truly make me sus?";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }

    public QuizPage lesson2() {
        String title = "2";
        String[] text = new String[5];
        text[0] = "if the impostor is sus";
        text[1] = "does that truly make me sus?";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }
    public QuizPage lesson3() {
        String title = "3";
        String[] text = new String[5];
        text[0] = "if the impostor is sus";
        text[1] = "does that truly make me sus?";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz;
    }
}