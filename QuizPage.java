import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizPage {
    private JPanel panel = new JPanel(new BorderLayout());
    private int initial_velocity;
    private PhysicsEngine physics;
    private JPanel physicsPanel;
    private JFrame frame; // Add frame variable

    public QuizPage(JFrame frame, String title, String[] text) {
        this.frame = frame; // Assign frame

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);

        // Home button
        ImageIcon homeIcon = new ImageIcon("2144.png");
        Image image = homeIcon.getImage(); // Get the original image
        Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedHomeIcon = new ImageIcon(resizedImage);
        JButton homeButton = new JButton(resizedHomeIcon);
        homeButton.setPreferredSize(new Dimension(100, 100));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage homepage = new HomePage();
                frame.dispose();
            }
        });

        JPanel topArea = new JPanel(new BorderLayout());
        topArea.add(titlePanel, BorderLayout.CENTER);
        topArea.add(homeButton, BorderLayout.EAST);

        JPanel rightPadding = new JPanel();
        rightPadding.setPreferredSize(new Dimension(50, 0));

        JPanel content = new JPanel(new GridLayout(2, 1));
        JPanel contentText = new JPanel(new GridLayout(text.length + 1, 1, 0, 25));
        for (String paragraph : text) {
            JLabel lines = new JLabel(paragraph);
            contentText.add(lines);
        }

        content.add(contentText);
        physics = new PhysicsEngine(100, 430, 0, 0, 0, 60, 5);
        physicsPanel = physics.getSimulation();
        content.add(physicsPanel);

        panel.add(topArea, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        panel.add(rightPadding, BorderLayout.EAST); // Add right padding

        JPanel fill1 = new JPanel();
        fill1.setPreferredSize(new Dimension(200, 0));
        JPanel fill2 = new JPanel();
        fill2.setPreferredSize(new Dimension(200, 0));

        panel.add(fill1, BorderLayout.WEST);
        panel.add(fill2, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }
}
