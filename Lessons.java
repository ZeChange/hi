import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lessons {
    private JFrame frame = new JFrame("Projectile Motion Lesson");
    private int pageOn = 0;
    JPanel[] lessonList = new JPanel[3];

    public Lessons() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lesson 1:
        JPanel lesson1 = lesson1();
        lessonList[0] = lesson1;
        JPanel lesson2 = lesson2();
        lessonList[1] = lesson2;
        JPanel lesson3 = lesson3();
        lessonList[2] = lesson3;

        
        for(int i = 0; i < lessonList.length; i++)
        {
            JPanel completePage = lessonList[i];

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
                    if(pageOn < lessonList.length-1)
                    {
                        pageOn += 1;
                        System.out.println("mesus");
                        newScreen();
                    }
                }
            });
            buttons.add(back); buttons.add(next);
            completePage.add(buttons, BorderLayout.SOUTH);

            lessonList[i] = completePage;
        }

        frame.add(lessonList[0]);
        frame.setVisible(true);
    }   

    public void newScreen()
    {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(lessonList[pageOn]);
        frame.setVisible(true);
    }

    public JPanel lesson1() {
        String title = "The Sus";
        String[] text = new String[5];
        text[0] = "i love among us";
        text[1] = "does that truly make me sus?";
        text[2] = "who cares to make a fuss";
        text[3] = "being an impostor is a plus";
        text[4] = "therefore, i love among us";
        ImageIcon picture = new ImageIcon("Red.png");
        LessonPage lesson1 = new LessonPage(title, text, picture);

        return lesson1.getPanel();
    }

    public JPanel lesson2() {
        String title = "2";
        String[] text = new String[5];
        text[0] = "i love among us 222";
        text[1] = "does that truly make me sus? 222";
        text[2] = "who cares to make a fuss 222";
        text[3] = "being an impostor is a plus 222";
        text[4] = "therefore, i love among us 222";
        ImageIcon picture = new ImageIcon("Red.png");
        LessonPage lesson1 = new LessonPage(title, text, picture);

        return lesson1.getPanel();
    }
    public JPanel lesson3() {
        String title = "3";
        String[] text = new String[5];
        text[0] = "i love among us 333";
        text[1] = "does that truly make me sus? 333";
        text[2] = "who cares to make a fuss 333";
        text[3] = "being an impostor is a plus 333";
        text[4] = "therefore, i love among us 333";
        ImageIcon picture = new ImageIcon("Red.png");
        LessonPage lesson1 = new LessonPage(title, text, picture);

        return lesson1.getPanel();
    }
}
