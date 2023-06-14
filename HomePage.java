import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        // Create the main frame for the home page
        JFrame frame = new JFrame("Projectile Motion Menu");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define font and buttons
        Font button = new Font("Serif", Font.PLAIN, 20);
        JButton quiz = new JButton("Quiz");
        quiz.setFont(button);

        JButton lesson = new JButton("Lesson");
        lesson.setFont(button);

        // Create a centered title panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Projectile Motion Lesson");
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        topPanel.add(title, BorderLayout.CENTER);

        // Add a copyright text
        JLabel copyright = new JLabel("© Danielle Lechange and Firepower1122, 2023");
        copyright.setFont(new Font("Serif", Font.PLAIN, 20));

        // Create the main panel and configure its layout
        JPanel panel = new JPanel(new BorderLayout(0, 50));
        panel.add(topPanel, BorderLayout.NORTH);

        // Create center and centerBottom panels
        JPanel center = new JPanel(new GridLayout(2, 1));
        JPanel centerBottom = new JPanel(new GridLayout(3, 1));

        // Create filler panels
        JPanel fill0a = new JPanel();
        JPanel fill0b = new JPanel();
        JPanel fill1 = new JPanel();

        // Create a panel for the buttons in the centerBottom panel
        JPanel centerBottomA = new JPanel(new GridLayout(1, 3, 250, 0));
        centerBottomA.add(lesson);
        centerBottomA.add(fill1);
        centerBottomA.add(quiz);

        // Add components to the center and centerBottom panels
        centerBottom.add(fill0a);
        centerBottom.add(fill0b);
        centerBottom.add(centerBottomA);
        center.add(centerBottom);
        panel.add(center, BorderLayout.CENTER);

        // Create a panel for the copyright text and add it to the south of the main panel
        JPanel south = new JPanel(new FlowLayout());
        south.add(copyright);
        panel.add(south, BorderLayout.SOUTH);

        // Create filler panels for the sides
        JPanel fill2 = new JPanel();
        fill2.setPreferredSize(new Dimension(200, 0));
        panel.add(fill2, BorderLayout.EAST);
        JPanel fill3 = new JPanel();
        fill3.setPreferredSize(new Dimension(200, 0));
        panel.add(fill3, BorderLayout.WEST);

        // Add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);

        // Add action listeners to the buttons
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
