import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class QuizPage {
    public Timer timer;
    private JPanel panel = new JPanel(new BorderLayout());
    private PhysicsEngine physics;
    private JPanel physicsPanel;
    private JFrame frame; // Add frame variable
    private int tries = 0;
    private boolean completed = false;
    private static int score = 0;

    public QuizPage(JFrame frame, String title, String[] text) {
        this.frame = frame; // Assign frame

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);

        JLabel scoreCard = new JLabel("Score: 0/5");

        // Home button
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

        JPanel topArea = new JPanel(new BorderLayout());
        topArea.add(scoreCard, BorderLayout.WEST);
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

        JLabel attempts = new JLabel("Attempts: 0/5");

        // Button for starting the sim
        JButton startSim = new JButton("Start Simulator");
        startSim.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    tries += 1;
                    attempts.setText("Attempts: " + tries + "/5");
                    physics.simulation();
                    timer = new Timer(1000/10, new ActionListener() { @Override // 10 fps (does not need to be that fast)
                        public void actionPerformed(ActionEvent a) {
                            if(physics.getCorrect())
                            {
                                if(!completed)
                                {
                                    if(tries < 5)
                                    {
                                        score += 1;
                                        completed = true;
                                    }
                                    scoreCard.setText("Score: " + score + "/5");
                                    JOptionPane.showMessageDialog( null, "You have completed the simulation!", "Success!", JOptionPane.INFORMATION_MESSAGE );
                                }

                                timer.stop();
                            }
                            else if(!physics.inProgress())
                            {
                                timer.stop();
                            }
                        }
                    });
                    timer.start();
                }
            }
        });

        // bound to a scale of 10 pixels
        JSlider initial_velocity_slider = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
        initial_velocity_slider.setPaintTicks(true);
        initial_velocity_slider.setPaintLabels(true);

        JLabel valueLabel = new JLabel(Integer.toString(initial_velocity_slider.getValue()));
        initial_velocity_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_velocity = (double)initial_velocity_slider.getValue();
                physics.setInitialVelocity(initial_velocity);
                valueLabel.setText(Double.toString(initial_velocity) + " m/s");
            }
        });
        
        
        JSlider initial_height_slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        initial_height_slider.setPaintTicks(true);
        initial_height_slider.setPaintLabels(true);

        JLabel heightLabel = new JLabel(Integer.toString(initial_height_slider.getValue()));
        initial_height_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_height = (double)initial_height_slider.getValue();
                physics.setInitialHeight(initial_height);
                heightLabel.setText(Double.toString(initial_height) + " m");
                if(!physics.inProgress())
                {
                    physics.changeHeight();
                }
            }
        });

        JSlider initial_angle_slider = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
        initial_angle_slider.setPaintTicks(true);
        initial_angle_slider.setPaintLabels(true);

        JLabel angleLabel = new JLabel(Integer.toString(initial_angle_slider.getValue()));
        initial_angle_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_angle = (double)initial_angle_slider.getValue();
                physics.setInitialAngle(initial_angle);
                angleLabel.setText(Double.toString(initial_angle) + "°");
            }
        });

        JPanel interactables = new JPanel();
        interactables.add(attempts);
        interactables.add(startSim);
        interactables.add(initial_velocity_slider);
        interactables.add(valueLabel);
        interactables.add(initial_height_slider);
        interactables.add(heightLabel);
        interactables.add(initial_angle_slider);
        interactables.add(angleLabel);
        contentText.add(interactables);

        content.add(contentText);

        physics = new PhysicsEngine(100, 0, 0, 0, 0, 0, 5, 2370, 10);
        physicsPanel = physics;
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

    public boolean finished() {
        return physics.getCorrect();
    }

    public int getTries() {
        return tries;
    }

    public double getAnswer() {
        return physics.getAnswer();
    }
}