import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LessonPage {
    private JPanel panel = new JPanel(new BorderLayout());

    public LessonPage(String title, String[] text, ImageIcon picture) {

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);

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
        
        panel.add(titlePanel, BorderLayout.NORTH);
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