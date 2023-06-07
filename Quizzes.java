import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quizzes {
    private JFrame frame = new JFrame("Projectile Motion Quiz");
    private int pageOn = 0;
    JPanel[] quizList = new JPanel[3];

    public Quizzes() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lesson 1:
        JPanel lesson1 = lesson1();
        quizList[0] = lesson1;
        JPanel lesson2 = lesson2();
        quizList[1] = lesson2;
        JPanel lesson3 = lesson3();
        quizList[2] = lesson3;

        
        for(int i = 0; i < quizList.length; i++)
        {
            JPanel completePage = quizList[i];

            JPanel buttons = new JPanel(new FlowLayout());
            Font button = new Font("Serif", Font.PLAIN, 20);;
            JButton back = new JButton("Back");
            back.addActionListener(new ActionListener() {@Override
                public void actionPerformed(ActionEvent e) {
                    if(pageOn != 0)
                    {
                        pageOn -= 1;
                        System.out.println("notsus");
                        newScreen();
                    }
                }
            });
            back.setFont(button);


            JButton next = new JButton("Next");
            next.setFont(button);
            next.addActionListener(new ActionListener() {@Override
                public void actionPerformed(ActionEvent f) {
                    if(pageOn < quizList.length-1)
                    {
                        pageOn += 1;
                        System.out.println("mesus");
                        newScreen();
                    }
                }
            });
            buttons.add(back); buttons.add(next);
            completePage.add(buttons, BorderLayout.SOUTH);

            quizList[i] = completePage;
        }

        frame.add(quizList[0]);
        frame.setVisible(true);
    }   

    public void newScreen()
    {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(quizList[pageOn]);
        frame.setVisible(true);
    }

    public JPanel lesson1() {
        String title = "question 1";
        String[] text = new String[5];
        text[0] = "if the impostor is sus";
        text[1] = "does that truly make me sus?";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz.getPanel();
    }

    public JPanel lesson2() {
        String title = "2";
        String[] text = new String[5];
        text[0] = "if the impostor is sus";
        text[1] = "does that truly make me sus?";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz.getPanel();
    }
    public JPanel lesson3() {
        String title = "3";
        String[] text = new String[5];
        text[0] = "if the impostor is sus";
        text[1] = "does that truly make me sus?";
        QuizPage quiz = new QuizPage(frame, title, text);

        return quiz.getPanel();
    }
}