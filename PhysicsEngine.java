import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.lang.Math.*;

public class PhysicsEngine {
    // begin the engine on command, can't let it run automatically
    public void initiate() {
        JFrame frame = new JFrame("Projectile Motion Physics"); // Main Window
        AppliedPhysics appliedPhysics = new AppliedPhysics();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(appliedPhysics);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class Physics { // class that will be inherited, contains main physics logic 
    protected int x;
    protected int y;
    protected double velocity_y;
    protected double velocity_x;
    protected double initial_velocity;
    protected double launch_angle;
    
    private boolean isAirborne;

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

    // public void applyGravity() {
    //     if (isAirborne || velocity_y < 0) {
    //         double gravity = 9.8/60; // 9.8 pixels/s^2 = 9.8 m/s^2
    //         velocity_y += gravity;
    //     }
    // }

    public void gravity_and_move() { // simulate gravity and update coordinates
        if (isAirborne || velocity_y < 0) {
            double gravity = 9.8 / 60; // 60 fps
            velocity_y += gravity;
            y += velocity_y;
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

    public AppliedPhysics() {
        sprites.add(new Circle(50, 1080, 0, 0, 50, 60, 5)); // sprite initialization (x, y, velocity_x, velocity_y, radius)
        

        Timer timer = new Timer(1000/60, new ActionListener() { // 60 fps, update ball movement every 16.666666667 milliseconds
            public void actionPerformed(ActionEvent e) {
                for (Physics sprite : sprites) {
                    Circle circle = (Circle) sprite;
                    System.out.println(sprite.getX() + ", " + sprite.getY() + " " + circle.getXVelocity() + ", " + circle.getYVelocity()); // testing purposes
                    // sprite.applyGravity();
                    // sprite.move();
                    if ((sprite.getY() + circle.getRadius()) >= getHeight()) { // if the ball touches the ground, not the first time but the second time
                        circle.grounded();
                        if(circle.getYVelocity() > 0)
                        {
                            System.exit(0); // stop program
                        }
                    } 
                    else{
                        circle.airborne();
                    }
                    // sprite.applyGravity(); // this is used for application of physics logic
                    sprite.gravity_and_move();
                }
                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) { // draw circle
        super.paintComponent(g);
        for (Physics sprite: sprites) {
            ((Circle)sprite).draw(g);
        }
    }
}
