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
    private  JLabel scoreCard;

    public static final int VELOCITY = 1;
    public static final int HEIGHT = 2;
    public static final int ANGLE = 3;
    public static final int MC = 4;
    public static final int SIMULATOR = 0;

    public double answer;
    public JTextField answerField = new JTextField( "???" );

    // Values to be inputted into the array in this order of priority: Velocity, Height, Angle, Distance(Answer), Scaling
    public QuizPage(JFrame frame, String title, String[] text, int type, double[] simValues) {
        this.frame = frame; // Assign frame

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);

        scoreCard = new JLabel("Score: " + score + "/5");
        
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
        JPanel contentText = new JPanel(new GridLayout(text.length + 2, 1, 0, 25));
        for (String paragraph : text) {
            JLabel lines = new JLabel(paragraph);
            contentText.add(lines);
        }

        JLabel attempts = new JLabel("Attempts: 0/3");

        // Button for starting the sim
        JButton startSim = new JButton("Start Simulator");
        startSim.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                boolean canStart = true;

                // Do not start if a simulation is in progress
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                    canStart = false;
                }

                // For checking answer input questions: if negative or error, do not start the simulation
                else if(type == MC)
                {
                    try {
                        answer = Double.parseDouble(answerField.getText());
                        physics.setAnswer(answer);
                        System.out.println(Double.parseDouble(answerField.getText()));
                    }
                    // If invalid input, set the answer to -1 for later checking
                    catch (NumberFormatException error) {
                        answer = -1;
                        physics.setAnswer(-1);
                        System.out.println("sussy amongus");
                    }

                    if(answer < 0)
                    {
                        JOptionPane.showMessageDialog( null, "Please enter a valid value!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                        canStart = false;
                    }
                }

                if(canStart)
                {
                    tries += 1;
                    attempts.setText("Attempts: " + tries + "/3");
                    physics.simulation();
                    // Set a timer to keep track of when the simulation is running
                    timer = new Timer(1000/10, new ActionListener() { @Override // 10 fps (does not need to be that fast)
                        public void actionPerformed(ActionEvent a) {
                            if(physics.getCorrect())
                            {
                                // If you have not already completed the simulation
                                if(!completed)
                                {
                                    // And have tried less than 3 times
                                    if(tries < 3)
                                    {
                                        // Add 1 to your score and mark the task as completed
                                        score += 1;
                                        completed = true;
                                    }
                                    System.out.println(score);
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
        JSlider initial_velocity_slider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);
        initial_velocity_slider.setPaintTicks(true);
        initial_velocity_slider.setPaintLabels(true);

        JLabel valueLabel = new JLabel("Initial Velocity: " + Integer.toString(initial_velocity_slider.getValue()) + " m/s");
        initial_velocity_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_velocity = (double)initial_velocity_slider.getValue()/100.0;
                physics.setInitialVelocity(initial_velocity);
                valueLabel.setText("Initial Velocity: " + Double.toString(initial_velocity) + " m/s");
                if(!physics.inProgress())
                {
                    physics.updateCircle();
                }
                System.out.println(score);
            }
        });
        
        JSlider initial_height_slider = new JSlider(JSlider.HORIZONTAL, 0, 10000, 0);
        initial_height_slider.setPaintTicks(true);
        initial_height_slider.setPaintLabels(true);

        JLabel heightLabel = new JLabel("Initial Height: " + Integer.toString(initial_height_slider.getValue()) + " m");
        initial_height_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_height = (double)initial_height_slider.getValue()/100.0;
                physics.setInitialHeight(initial_height);
                heightLabel.setText("Initial Height: " + Double.toString(initial_height) + " m");
                if(!physics.inProgress())
                {
                    physics.updateCircle();
                }
            }
        });

        JSlider initial_angle_slider = new JSlider(JSlider.HORIZONTAL, 0, 9000, 0);
        initial_angle_slider.setPaintTicks(true);
        initial_angle_slider.setPaintLabels(true);

        JLabel angleLabel = new JLabel("Angle: " + Integer.toString(initial_angle_slider.getValue()) + "°");
        initial_angle_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_angle = (double)initial_angle_slider.getValue()/100.0;
                physics.setInitialAngle(initial_angle);
                angleLabel.setText("Angle: " + Double.toString(initial_angle) + "°");
                if(!physics.inProgress())
                {
                    physics.updateCircle();
                }
            }
        });

        JPanel interactables = new JPanel(new GridLayout(2, 1));
        JPanel interactables1 = new JPanel(new GridLayout());
        interactables1.add(attempts);
        if(type == VELOCITY || type == SIMULATOR)
        {
            interactables.add(initial_velocity_slider);
            interactables.add(valueLabel);
            physics = new PhysicsEngine(0, simValues[0], 0, simValues[1], 5, simValues[2], simValues[3]);
        }
        if(type == HEIGHT || type == SIMULATOR)
        {
            interactables.add(initial_height_slider);
            interactables.add(heightLabel);
            physics = new PhysicsEngine(0, 0, simValues[0], simValues[1], 5, simValues[2], simValues[3]);
        }
        if(type == ANGLE || type == SIMULATOR)
        {
            interactables.add(initial_angle_slider);
            interactables.add(angleLabel);   
            physics = new PhysicsEngine(0, simValues[1], simValues[0], 0, 5, simValues[2], simValues[3]);
        }
        if(type == MC)
        {
            answerField.setColumns(10);
                    
            answerField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                    JOptionPane.showMessageDialog( null, "Press the start simulation button to begin.", "Info", JOptionPane.INFORMATION_MESSAGE );
                }
            });

            physics = new PhysicsEngine(0, simValues[1], simValues[0], simValues[2], 5, -1, simValues[3]);

            interactables.add(answerField);
        }

        JButton clearPoints = new JButton("Clear Points");
        clearPoints.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    physics.clearTrajectoryPoints();
                }
            }
        });

        interactables1.add(startSim);
        interactables1.add(clearPoints);
        contentText.add(interactables);
        contentText.add(interactables1);

        content.add(contentText);

        content.add(physics);

        panel.add(topArea, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        panel.add(rightPadding, BorderLayout.EAST); // Add right padding

        JPanel fill1 = new JPanel();
        fill1.setPreferredSize(new Dimension(200, 0));
        JPanel fill2 = new JPanel();
        fill2.setPreferredSize(new Dimension(200, 0));

        panel.add(fill1, BorderLayout.WEST);
        panel.add(fill2, BorderLayout.EAST);
        physics.repaint();
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
    
    public void updateScore()
    {
        scoreCard.setText("Score: " + score + "/5");
    }
}