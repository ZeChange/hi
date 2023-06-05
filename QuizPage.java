import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizPage {
    private JFrame frame;

    public QuizPage() {
        JFrame frame = new JFrame("Projectile Motion Quiz");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton gay = new JButton("CLICK IF GAY");
        panel.add(gay);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }
}