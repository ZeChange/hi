import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        JFrame frame = new JFrame("Projectile Motion Menu");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Font and buttons
        Font button = new Font("Serif", Font.PLAIN, 20);
        JButton quiz = new JButton("Quiz");
        quiz.setFont(button);

        JButton lesson = new JButton("Lesson");
        lesson.setFont(button);

        // Centered Title
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Projectile Motion Lesson");
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        topPanel.add(title, BorderLayout.CENTER);

        // Copyright text
        JLabel copyright = new JLabel("© Danielle Lechange and Firepower1122, 2023");
        copyright.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel panel = new JPanel(new BorderLayout(0, 50));
        panel.add(topPanel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2, 1));
        JPanel centerBottom = new JPanel(new GridLayout(3, 1));
        JPanel fill0a = new JPanel();
        JPanel fill0b = new JPanel();
        JPanel centerBottomA = new JPanel(new GridLayout(1, 3, 250, 0));
        JPanel fill1 = new JPanel();
        centerBottomA.add(lesson);
        centerBottomA.add(fill1);
        centerBottomA.add(quiz);
        centerBottom.add(fill0a);
        centerBottom.add(fill0b);
        centerBottom.add(centerBottomA);
        center.add(centerBottom);
        panel.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout());
        south.add(copyright);
        panel.add(south, BorderLayout.SOUTH);

        JPanel fill2 = new JPanel();
        fill2.setPreferredSize(new Dimension(200, 0));
        panel.add(fill2, BorderLayout.EAST);
        JPanel fill3 = new JPanel();
        fill3.setPreferredSize(new Dimension(200, 0));
        panel.add(fill3, BorderLayout.WEST);

        frame.add(panel);
        frame.setVisible(true);

        // Action listeners for buttons
        quiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Quizzes quizzes = new Quizzes();
                frame.dispose();
            }
        });

        lesson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Lessons lessons = new Lessons();
                frame.dispose();
            }
        });
    }
}