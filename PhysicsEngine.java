import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Math.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.Border;
import java.util.Random;

public class PhysicsEngine extends JPanel {
    public Timer timer;
    public double x;
    public double y;
    public double velocity_x = 0;
    public double velocity_y = 0;
    public double initial_velocity;
    public double launch_angle;
    public int radius;

    private double answer;
    private boolean correct = false;
    private ArrayList<Physics> sprites = new ArrayList<>();
    private double initial_velocity_scaling;
    private boolean running = false;
    private ArrayList<Point> trajectoryPoints; // Stores the trajectory points

    public PhysicsEngine(double x, double y, double initial_velocity, double launch_angle, int radius, double answer, double initial_velocity_scaling) {
        // Initialize the physics engine with the provided parameters
        this.x = x;
        this.y = y;

        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;
        this.radius = radius;
        this.answer = answer / initial_velocity_scaling;
        this.initial_velocity_scaling = initial_velocity_scaling;

        // Create the initial circle sprite and add it to the list
        sprites.add(new Circle(this.x, this.y, velocity_x, velocity_y, this.initial_velocity, this.launch_angle, this.radius, this.initial_velocity_scaling, getHeight()));
        repaint();

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

        trajectoryPoints = new ArrayList<>();
    }

    public void simulation() {
        // Start the simulation
        running = true;
        sprites.remove(0);
        sprites.add(new Circle(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, radius, initial_velocity_scaling, getHeight()));
        timer = new Timer(17, new ActionListener() { // Approx 60 fps
            public void actionPerformed(ActionEvent e) {
                for (Physics sprite : sprites) {
                    Circle circle = (Circle) sprite;
                    // Check if the sprite has hit the ground
                    if (sprite.getY() + circle.getRadius() >= getHeight() - circle.getRadius()) {
                        circle.grounded();
                        
                        circle.stop();
                        ((Timer) e.getSource()).stop();
                        // Check if the sprite's x-coordinate is close to the answer
                        if (sprite.getX() < answer + 5 && sprite.getX() > answer - 5) {
                            correct = true;
                            System.out.println("bugatti");
                        }
                        running = false;
                        System.out.println("Answer: " + answer);
                        
                    } else {
                        circle.airborne();
                    }
                    sprite.gravity_and_move();
                    updateTrajectoryPoints(circle.getX(), circle.getY()); 
                    repaint();
                }
                repaint();
            }
        });
        timer.start();
    }

    public void setInitialVelocity(double initial_velocity) {
        // Update the initial velocity and repaint the panel
        this.initial_velocity = initial_velocity;
        repaint();
    }

    public void setInitialHeight(double height) {
        // Update the initial height and trigger a change in height
        this.y = height;
        changeHeight();
    }

    public void setInitialAngle(double angle) {
        // Update the launch angle and repaint the panel
        this.launch_angle = angle;
        repaint();
    }

    public boolean getCorrect() {
        return correct;
    }

    public double getAnswer() {
        return answer;
    }

    public boolean inProgress() {
        return running;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Physics sprite : sprites) {
            ((Circle) sprite).draw(g);
        }
        
        Random random = new Random();
        g.setColor(Color.RED);
        for (Point point : trajectoryPoints) {
            g.fillOval(point.x - 2, point.y - 2, 5, 5);
        }

        for (int i = 0; i < 15; i++) {
            double position = (double)100 * (double)i * initial_velocity_scaling;
            g.setColor(new Color(50, 50, 50));
            g.drawString(Double.toString(position), 100 * i - 15, getHeight() - 5);
            g.setColor(new Color(189, 189, 189));
            g.drawLine(i * 100, 0, i * 100, getHeight() - 20);
        }
    }

    public void changeHeight() {
        // Change the height and update the circle sprite
        sprites.remove(0);
        sprites.add(new Circle(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, radius, initial_velocity_scaling, getHeight()));
        repaint();
    }

    private void updateTrajectoryPoints(double x, double y) {
        // Add a new point to the trajectory points list
        trajectoryPoints.add(new Point((int) x, (int) y));
    }

    public void clearTrajectoryPoints()
    {
        // Clear the trajectory points list
        trajectoryPoints.clear();
        repaint();
    }
}

class Physics {
    private double x;
    private double y;
    private double velocity_y;
    private double velocity_x;
    private double initial_velocity;
    public double launch_angle;
    private double initial_velocity_scaling;

    private boolean isAirborne;
    private double gravity = 9.80665 * 0.001 * 17;
    private int height;

    public Physics(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, double initial_velocity_scaling, int height) {
        // Initialize the physics object with the provided parameters
        this.x = x;
        this.y = y;
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;

        this.initial_velocity_scaling = initial_velocity_scaling;
        this.height = height;

        // Calculate the x and y velocities based on the initial velocity and launch angle
        this.velocity_x = this.initial_velocity * Math.cos(Math.toRadians(this.launch_angle));
        this.velocity_y = this.initial_velocity * Math.sin(Math.toRadians(this.launch_angle));

        this.isAirborne = true;
    }

    public void gravity_and_move() {
        // Apply gravity and update the position based on the current velocities
        if (isAirborne || velocity_y > 0) {
            y += velocity_y;
            velocity_y -= gravity;
            x += velocity_x;
        }
    }

    public void grounded() {
        // Set the object as grounded (not airborne)
        isAirborne = false;
    }

    public void airborne() {
        // Set the object as airborne
        isAirborne = true;
    }

    public int getX() {
        // Return the x-coordinate scaled by the initial velocity scaling
        return (int) (x/initial_velocity_scaling);
    }

    public int getY() {
        // Return the y-coordinate scaled by the initial velocity scaling
        System.out.println(x + ", " + y);
        return height - (int) (y/initial_velocity_scaling);
    }

    public double getYVelocity() {
        return velocity_y;
    }

    public double getXVelocity() {
        return velocity_x;
    }

    public double getInitialVelocity() {
        return initial_velocity;
    }

    public void stop() {
        // Stop the object's movement by setting velocities and gravity to zero
        gravity = 0;
        velocity_x = 0;
        velocity_y = 0;
        y = 0;
    }
}

class Circle extends Physics {
    private int radius;

    public Circle(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius, double initial_velocity_scaling, int height) {
        // Initialize the circle object with the provided parameters
        super(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, initial_velocity_scaling, height);
        this.radius = radius;
    }

    public void draw(Graphics g) {
        // Draw the circle on the graphics object
        g.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    public double getLaunchAngle() {
        return launch_angle;
    }

    public int getRadius() {
        return radius;
    }
}
