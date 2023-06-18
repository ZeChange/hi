import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FreeSimulator {
    public Timer timer;
    private JPanel panel = new JPanel(new BorderLayout());
    private PhysicsEngine physics;
    private JPanel physicsPanel;
    private JFrame frame = new JFrame("Simulator"); // Add frame variable
    private double scaling = 1.0;
    private ArrayList<JButton> gravity = new ArrayList<>();

    public static final double MOON = 1.62;
    public static final double EARTH = 9.8;
    public static final double MARS = 3.71;
    public static final double JUPITER = 24.79;
    public static final double VENUS = 8.87;

    public double answer;
    public JTextField answerField = new JTextField( "???" );

    // Values to be inputted into the array in this order of priority: Velocity, Height, Angle, Distance(Answer), Scaling
    public FreeSimulator() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Simulator");
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
                frame.dispose();
                HomePage homepage = new HomePage();
            }
        });

        JPanel topArea = new JPanel(new BorderLayout());
        topArea.add(titlePanel, BorderLayout.CENTER);
        topArea.add(homeButton, BorderLayout.EAST);

        JPanel rightPadding = new JPanel();
        rightPadding.setPreferredSize(new Dimension(50, 0));

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

                if(canStart)
                {
                    physics.simulation();
                }
                
            }
        });

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

        // VALUE SLIDERS
        // ------------------------------------------------------
        // bound to a scale of 10 pixels
        JSlider initial_velocity_slider = new JSlider(JSlider.HORIZONTAL, 0, 1500, 0);
        initial_velocity_slider.setPaintTicks(true);
        initial_velocity_slider.setPaintLabels(true);

        JLabel valueLabel = new JLabel("Initial Velocity: " + Integer.toString(initial_velocity_slider.getValue()) + " m/s");
        initial_velocity_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_velocity = (double)initial_velocity_slider.getValue()/10.0;
                physics.setInitialVelocity(initial_velocity);
                valueLabel.setText("Initial Velocity: " + Double.toString(initial_velocity) + " m/s");
                if(!physics.inProgress())
                {
                    physics.updateCircle();
                }
            }
        });
        
        JSlider initial_height_slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 0);
        initial_height_slider.setPaintTicks(true);
        initial_height_slider.setPaintLabels(true);

        JLabel heightLabel = new JLabel("Initial Height: " + Integer.toString(initial_height_slider.getValue()) + " m");
        initial_height_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_height = (double)initial_height_slider.getValue()/10.0;
                physics.setInitialHeight(initial_height);
                heightLabel.setText("Initial Height: " + Double.toString(initial_height) + " m");
                if(!physics.inProgress())
                {
                    physics.updateCircle();
                }
            }
        });

        JSlider initial_angle_slider = new JSlider(JSlider.HORIZONTAL, 0, 900, 0);
        initial_angle_slider.setPaintTicks(true);
        initial_angle_slider.setPaintLabels(true);

        JLabel angleLabel = new JLabel("Angle of Launch: " + Integer.toString(initial_angle_slider.getValue()) + "°");
        initial_angle_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent f) {
                double initial_angle = (double)initial_angle_slider.getValue()/10.0;
                physics.setInitialAngle(initial_angle);
                angleLabel.setText("Angle of Launch: " + Double.toString(initial_angle) + "°");
                if(!physics.inProgress())
                {
                    physics.updateCircle();
                }
            }
        });

        // ZOOM BUTTONS
        // ------------------------------------------------------
        JButton zoomIn = new JButton("+");
        zoomIn.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    scaling -= 0.1;
                    System.out.println("sus");
                    physics.setScaling(scaling);
                }

            }
        });

        JButton zoomOut = new JButton("-");
        zoomOut.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    scaling += 0.1;
                    System.out.println("sus");
                    physics.setScaling(scaling);
                }
            }
        });

        // GRAVITY BUTTONS
        // ------------------------------------------------------
        JButton moonGravity = new JButton("Moon Gravity");
        JButton earthGravity = new JButton("Earth Gravity");
        JButton marsGravity = new JButton("Mars Gravity");
        JButton jupiterGravity = new JButton("Jupiter Gravity");
        JButton venusGravity = new JButton("Venus Gravity");
        gravity.add(moonGravity);
        gravity.add(earthGravity);
        gravity.add(marsGravity);
        gravity.add(jupiterGravity);
        gravity.add(venusGravity);

        moonGravity.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    physics.setGravity(MOON);
                    gravityChange();

                    moonGravity.setBackground(Color.GREEN);
                }
            }
        });

        earthGravity.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    physics.setGravity(EARTH);
                    gravityChange();

                    earthGravity.setBackground(Color.GREEN);
                }
            }
        });

        marsGravity.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    physics.setGravity(MARS);
                    gravityChange();

                    marsGravity.setBackground(Color.GREEN);
                }
            }
        });

        jupiterGravity.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    physics.setGravity(JUPITER);
                    gravityChange();

                    jupiterGravity.setBackground(Color.GREEN);
                }
            }
        });

        venusGravity.addActionListener(new ActionListener() {@Override
            public void actionPerformed(ActionEvent e) {
                if(physics.inProgress())
                {
                    JOptionPane.showMessageDialog( null, "Please wait for the simulation to finish!", "Alert!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    physics.setGravity(VENUS);
                    gravityChange();

                    venusGravity.setBackground(Color.GREEN);
                }
            }
        });

        JPanel interactables = new JPanel(new GridLayout(5, 1));

        JPanel interactables1 = new JPanel(new GridLayout());
        interactables1.add(initial_velocity_slider);
        interactables1.add(valueLabel);

        JPanel interactables2 = new JPanel(new GridLayout());
        interactables2.add(initial_height_slider);
        interactables2.add(heightLabel);

        JPanel interactables3 = new JPanel(new GridLayout());
        interactables3.add(initial_angle_slider);
        interactables3.add(angleLabel);   

        JPanel interactables4 = new JPanel(new FlowLayout());
        interactables4.add(startSim);
        interactables4.add(zoomIn);
        interactables4.add(zoomOut);
        interactables4.add(clearPoints);

        JPanel interactables5 = new JPanel(new FlowLayout());
        interactables5.add(moonGravity);
        interactables5.add(earthGravity);
        interactables5.add(marsGravity);
        interactables5.add(jupiterGravity);
        interactables5.add(venusGravity);

        interactables.add(interactables1);
        interactables.add(interactables2);
        interactables.add(interactables3);
        interactables.add(interactables4);
        interactables.add(interactables5);

        physics = new PhysicsEngine(0, 0, 0, 0, 5, -1, 1);    


        JPanel content = new JPanel(new GridLayout(2, 1));
        content.add(interactables);
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

        frame.add(panel);
        frame.setVisible(true);
    }

    private void gravityChange()
    {
        for(int i = 0; i < gravity.size(); i++)
        {
            JButton button = gravity.get(i);
            button.setBackground(Color.GRAY);
            gravity.set(i, button);
        }
    }
}
