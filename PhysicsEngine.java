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
    private int tickCount = 0;

    public PhysicsEngine(double x, double y, double initial_velocity, double launch_angle, int radius, double answer, double initial_velocity_scaling) {
        this.x = x;
        this.y = y;

        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;
        this.radius = radius;
        this.answer = answer;
        this.initial_velocity_scaling = initial_velocity_scaling;

        sprites.add(new Circle(this.x, this.y, velocity_x, velocity_y, this.initial_velocity, this.launch_angle, this.radius, this.initial_velocity_scaling, getHeight()));
        repaint();

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

        trajectoryPoints = new ArrayList<>();
    }

    public void simulation() {
        running = true;
        sprites.remove(0);
        sprites.add(new Circle(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, radius, initial_velocity_scaling, getHeight()));
        timer = new Timer(17, new ActionListener() { // Approx 60 fps
            public void actionPerformed(ActionEvent e) {
                for (Physics sprite : sprites) {
                    Circle circle = (Circle) sprite;
                    //System.out.println(sprite.getX() + ", " + sprite.getY());
                    if (sprite.getY() >= getHeight() || sprite.getX() >= getWidth()) {
                        circle.grounded();
                        
                        circle.stop();
                        ((Timer) e.getSource()).stop();
                        if (sprite.getTrueX() < answer + 10*initial_velocity_scaling && sprite.getTrueX() > answer - 10*initial_velocity_scaling) {
                            correct = true;
                            System.out.println("bugatti");
                        }
                        running = false;
                        System.out.println("Answer: " + answer);
                        
                    } else {
                        circle.airborne();
                    }
                    sprite.gravity_and_move();

                    if(tickCount == 5)
                    {
                        updateTrajectoryPoints(circle.getX(), circle.getY()); 
                        tickCount = 0;
                    }
                    tickCount++;
                    repaint();
                }
                repaint();
            }
        });
        timer.start();
    }

    public void setInitialVelocity(double initial_velocity) {
        this.initial_velocity = initial_velocity;
    }

    public void setInitialHeight(double height) {
        this.y = height;
    }

    public void setInitialAngle(double angle) {
        this.launch_angle = angle;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void setScaling(double scaling) {
        initial_velocity_scaling = scaling;
        System.out.println(initial_velocity_scaling);
        repaint();
    }

    public void setGravity(double gravity) {
        sprites.get(0).setGravity(gravity);
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
            g.setColor(Color.BLACK);
            g.drawLine(sprite.getX(), sprite.getY(), (int)(sprite.getX()+sprite.getXVelocity() * Math.cos(Math.toRadians(this.launch_angle))), (int)(sprite.getY()-sprite.getYVelocity() * Math.sin(Math.toRadians(this.launch_angle))));
        }
        
        Random random = new Random();
        g.setColor(Color.RED);
        for (Point point : trajectoryPoints) {
            g.fillOval(point.x - 2, point.y - 2, 5, 5);
        }

        // -- Fixed Number of Markers for Every Simulation --
        // Draw markers for the x-axis
        for (int i = 0; i < 15; i++) {
            // Set the text for the point
            double position = Math.round((double)100 * (double)i * initial_velocity_scaling);
            g.setColor(new Color(50, 50, 50));
            g.drawString(Double.toString(position), 100 * i, getHeight() - 5);
            g.setColor(new Color(189, 189, 189));
            g.drawLine(i * 100, 0, i * 100, getHeight());
        }

        // Draw markers for the y-axis
        for (int i = 1; i < 10; i++) {
            // Set the text for the point
            double position = Math.round((double)100 * (double)i * initial_velocity_scaling);
            g.setColor(new Color(50, 50, 50));
            g.drawString(Double.toString(position), 0, getHeight() - (100 * i));
            g.setColor(new Color(189, 189, 189));
            g.drawLine(0, getHeight() - i*100, getWidth(), getHeight() - i * 100);
        }
    }

    public void updateCircle() {
        sprites.remove(0);
        sprites.add(new Circle(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, radius, initial_velocity_scaling, getHeight()));
        repaint();
    }

    private void updateTrajectoryPoints(double x, double y) {
        trajectoryPoints.add(new Point((int) x, (int) y));
    }

    public void clearTrajectoryPoints()
    {
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
    private static double gravity = 9.8;
    private double perTick = 0.001 * 17.0;
    private int height;

    public Physics(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, double initial_velocity_scaling, int height) {
        this.x = x;
        this.y = y;
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;

        this.initial_velocity_scaling = initial_velocity_scaling;
        this.height = height;

        this.velocity_x = this.initial_velocity * Math.cos(Math.toRadians(this.launch_angle));
        this.velocity_y = this.initial_velocity * Math.sin(Math.toRadians(this.launch_angle));

        this.isAirborne = true;
    }

    public void gravity_and_move() {
        if (isAirborne || velocity_y > 0) {
            velocity_y -= gravity * perTick;
            y += velocity_y * perTick;
            x += velocity_x * perTick;
        }
    }

    public void grounded() {
        isAirborne = false;
    }

    public void airborne() {
        isAirborne = true;
    }

    public int getX() {
        return (int) (x/initial_velocity_scaling);
    }

    public int getY() {
        System.out.println(x + ", " + y);
        return height - (int) (y/initial_velocity_scaling);
    }

    public double getTrueX() {
        return x;
    }

    public double getTrueY() {
        return x;
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
        velocity_x = 0;
        velocity_y = 0;
        y = 0;
    }

    public void setGravity(double value) {
        gravity = value;
        System.out.println("sussed");
    }
}

class Circle extends Physics {
    private int radius;

    public Circle(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius, double initial_velocity_scaling, int height) {
        super(x, y, velocity_x, velocity_y, initial_velocity, launch_angle, initial_velocity_scaling, height);
        this.radius = radius;
    }

    public void draw(Graphics g) {
        g.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    public double getLaunchAngle() {
        return launch_angle;
    }

    public int getRadius() {
        return radius;
    }
}