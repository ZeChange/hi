import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.lang.Math.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.Border;

public class PhysicsEngine {
    // begin the engine on command, can't let it run automatically
    private JPanel simulation = new JPanel();
    private int x; private int y;
    private int velocity_x; private int velocity_y;
    private int initial_velocity; private double launch_angle;
    private int radius;

    public PhysicsEngine(int x, int y, int velocity_x, int velocity_y, int initial_velocity, double launch_angle, int radius) {
        this.x = x; this.y = y;
        this.velocity_x = velocity_x; this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity; this.launch_angle = launch_angle;
        this.radius = radius;

        simulation = new AppliedPhysics(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, radius);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        simulation.setBorder(blackline);
    }

    public JPanel getSimulation()
    {
        return simulation;
    }
}

class Physics { // class that will be inherited, contains main physics logic 
    private int x;
    private int y;
    private double velocity_y;
    private double velocity_x;
    private double initial_velocity;
    private double launch_angle;
    
    private boolean isAirborne;
    private double gravity = 9.8 / 60; // 60 fps

    // all these values depend on the question given. If only some are given (e.g: initial velocity and launch angle to calculate for the rest of the stuff, then we will solve for others.)
    // x = distance, y = height, velocity_x = Vx, velocity_y = Vy, initial_velocity = V
    public Physics(int x, int y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle) { 
        this.x = x;
        this.y = y;
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;
        this.isAirborne = true;
        
        // calculations if we are only given initial_velocity and launch_angle, will determine velocity_x and velocity_y
        // IMPORTANT: /5 or else certain values will not fit our screen, purely situational
        if (this.initial_velocity != 0 && this.launch_angle != 0) { // purely situational, both MUST be 0 or else there will be disasterous consequences. 
            this.velocity_x = this.initial_velocity * Math.cos(Math.toRadians(this.launch_angle));
            this.velocity_y = this.initial_velocity * Math.sin(Math.toRadians(this.launch_angle));

            if (this.velocity_y > 0) { // (down is up)
                this.velocity_y *= -1;
            }
        }
    }

    public void gravity_and_move() { // simulate gravity and update coordinates
        if (isAirborne || velocity_y < 0) {
            y += velocity_y;
            velocity_y += gravity;
            x += velocity_x;
        }
    }

    public void grounded() {
        isAirborne = false;
    }

    public void airborne() {
        isAirborne = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getYVelocity() {
        return velocity_y;
    }

    public double getXVelocity() {
        return velocity_x;
    }

    public void stop() {
        gravity = 0;
        velocity_x = 0;
        velocity_y = 0;
    }
}

class Circle extends Physics { // circle class
    private int radius;

    public Circle(int x, int y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius) {
        super(x, y, velocity_x, velocity_y, initial_velocity, launch_angle);
        this.radius = radius;
    }

    public void draw(Graphics g) {
        g.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    public int getRadius() {
        return radius;
    }
}

class AppliedPhysics extends JPanel {
    private ArrayList<Physics> sprites = new ArrayList<>();
    public Timer timer;
    private int x; private int y;
    private int velocity_x; private int velocity_y;
    private int initial_velocity; private double launch_angle;
    private int radius;

    public AppliedPhysics(int x, int y, int velocity_x, int velocity_y, int initial_velocity, double launch_angle, int radius) {
        this.x = x; this.y = y;
        this.velocity_x = velocity_x; this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity; this.launch_angle = launch_angle;
        this.radius = radius;
        
        // Button for starting the sim
        JButton startSim = new JButton("Start Simulator");
        startSim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulation();
            }
        });
        add(startSim);

        // bound to a scale of 10 pixels
        JSlider initial_velocity_slider = new JSlider(JSlider.HORIZONTAL, 0, 15, 0);
        initial_velocity_slider.setPaintTicks(true);
        initial_velocity_slider.setPaintLabels(true);
        
        JLabel valueLabel = new JLabel(Integer.toString(initial_velocity_slider.getValue()));
        initial_velocity_slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int initial_velocity = initial_velocity_slider.getValue();
                setInitialVelocity(initial_velocity);
                valueLabel.setText(Integer.toString(initial_velocity));
            }
        });
        add(initial_velocity_slider);
        add(valueLabel);
    }

    protected void simulation()
    {
        sprites.add(new Circle(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, radius)); // sprite initialization (x, y, velocity_x, velocity_y, radius)
        timer = new Timer(1000/60, new ActionListener() { // 60 fps, update ball movement every 16.666666667 milliseconds
            public void actionPerformed(ActionEvent e) {
                for (Physics sprite : sprites) {
                    Circle circle = (Circle) sprite;
                    //System.out.println(sprite.getX() + ", " + sprite.getY() + " " + circle.getXVelocity() + ", " + circle.getYVelocity()); // testing purposes
                    if ((sprite.getY() + circle.getRadius() >= (getHeight() - 1 * circle.getRadius()))) { // if the ball touches the ground, not the first time but the second time
                        circle.grounded();
                        if(circle.getYVelocity() > 0)
                        {
                            circle.stop();
                            ((Timer)e.getSource()).stop();
                        }
                    } 
                    else{
                        circle.airborne();
                    }
                    sprite.gravity_and_move();
                }
                repaint();
            }
        });
        timer.start();
    }

    public void setInitialVelocity(int initial_velocity)
    {
        this.initial_velocity = initial_velocity;
    }

    public void setInitialHeight(int height) {
        this.y = getHeight() - y;
    }

    protected void paintComponent(Graphics g) { // draw circle
        super.paintComponent(g);
        for (Physics sprite: sprites) {
            ((Circle)sprite).draw(g);
        }
    }
}