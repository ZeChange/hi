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

        JButton simulator = new JButton("Simulator");
        simulator.setFont(button);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(lesson);
        buttons.add(simulator);
        buttons.add(quiz);

        // Create a centered title panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Projectile Motion");
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        topPanel.add(title, BorderLayout.CENTER);

        // Add a copyright text
        JLabel copyright = new JLabel("© Danielle Lechange and Firepower1122, 2023");
        copyright.setFont(new Font("Serif", Font.PLAIN, 20));

        // Create the main panel and configure its layout
        JPanel panel = new JPanel(new BorderLayout(0, 50));
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.CENTER);
        panel.add(copyright, BorderLayout.SOUTH);

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

        simulator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FreeSimulator simulator = new FreeSimulator();
                frame.dispose();
            }
        });
    }
}