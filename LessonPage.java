import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LessonPage {
    private JPanel panel = new JPanel(new BorderLayout());
    private JFrame frame;

    public LessonPage(JFrame frame, String title, String[] text, ImageIcon picture) {
        this.frame = frame;

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.CENTER);
        
        ImageIcon homeIcon = new ImageIcon("2144.png");
        Image image = homeIcon.getImage(); // Get the original image
        Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedHomeIcon = new ImageIcon(resizedImage);
        JButton homeButton = new JButton(resizedHomeIcon);
        homeButton.setPreferredSize(new Dimension(100, 100));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homepage = new HomePage();
            }
        });

        topPanel.add(homeButton, BorderLayout.EAST);


        JPanel content = new JPanel(new GridLayout(1, 2));
        JPanel contentText = new JPanel(new GridLayout(text.length, 1, 0, 25));
        for(String paragraph : text)
        {
            JLabel lines = new JLabel(paragraph);
            contentText.add(lines);
        }
        content.add(contentText);
        JLabel contentPicture = new JLabel(picture);
        content.add(contentPicture);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);

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