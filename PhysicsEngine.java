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
    public double velocity_x;
    public double velocity_y;
    public double initial_velocity;
    public double launch_angle;
    public int radius;

    private double answer;
    private boolean correct = false;
    private ArrayList<Physics> sprites = new ArrayList<>();
    private double initial_velocity_scaling;
    private boolean running = false;
    private double scaling;
    private ArrayList<Point> trajectoryPoints; // Stores the trajectory points

    public PhysicsEngine(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius, double answer, double initial_velocity_scaling) {
        this.x = x / initial_velocity_scaling;
        this.y = y / initial_velocity_scaling;

        this.velocity_x = velocity_x / initial_velocity_scaling;
        this.velocity_y = velocity_y / initial_velocity_scaling;
        this.initial_velocity = initial_velocity / initial_velocity_scaling;
        this.launch_angle = launch_angle;
        this.radius = radius;
        this.answer = answer / initial_velocity_scaling;
        this.initial_velocity_scaling = initial_velocity_scaling;

        this.scaling = initial_velocity_scaling;

        sprites.add(new Circle(this.x, this.y, this.velocity_x, this.velocity_y, this.initial_velocity, this.launch_angle, this.radius));
        repaint();

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

        trajectoryPoints = new ArrayList<>();
    }

    public void simulation() {
        running = true;
        sprites.remove(0);
        sprites.add(new Circle(this.x, this.y, this.velocity_x, this.velocity_y, this.initial_velocity, this.launch_angle, this.radius));
        timer = new Timer(1000 / 60, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Physics sprite : sprites) {
                    Circle circle = (Circle) sprite;
                    if ((sprite.getY() + circle.getRadius() >= (getHeight() - circle.getRadius()))) {
                        circle.grounded();
                        if (circle.getYVelocity() > 0) {
                            circle.stop();
                            ((Timer) e.getSource()).stop();
                            if (sprite.getX() < answer + (double) 5 * initial_velocity_scaling && sprite.getX() > answer - (double) 5 * initial_velocity_scaling) {
                                correct = true;
                                System.out.println("bugatti");
                            }
                            running = false;
                        }
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
        this.initial_velocity = initial_velocity / initial_velocity_scaling;
        repaint();
    }

    public void setInitialHeight(double height) {
        this.y = getHeight() - height * 4;
        repaint();
    }

    public void setInitialAngle(double angle) {
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
            double position = 100 * i * scaling;
            g.setColor(new Color(50, 50, 50));
            g.drawString(Double.toString(position), 100 * i - 15, getHeight() - 5);
            g.setColor(new Color(189, 189, 189));
            g.drawLine(i * 100, 0, i * 100, getHeight() - 20);
        }
    }

    public void changeHeight() {
        sprites.remove(0);
        sprites.add(new Circle(this.x, this.y, this.velocity_x, this.velocity_y, this.initial_velocity, this.launch_angle, this.radius));
        repaint();
    }

    private void updateTrajectoryPoints(double x, double y) {
        trajectoryPoints.add(new Point((int) x, (int) y));
    }
}

class Physics {
    private double x;
    private double y;
    private double velocity_y;
    private double velocity_x;
    private double initial_velocity;
    public double launch_angle;

    private boolean isAirborne;
    private double gravity = 9.8 / 60;

    public Physics(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle) {
        this.x = x;
        this.y = y;
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;

        this.velocity_x = this.initial_velocity * Math.cos(Math.toRadians(this.launch_angle));
        this.velocity_y = this.initial_velocity * Math.sin(Math.toRadians(this.launch_angle));

        if (this.velocity_y > 0) {
            this.velocity_y *= -1;
        }

        this.isAirborne = true;
    }

    public void gravity_and_move() {
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
        return (int) x;
    }

    public int getY() {
        return (int) y;
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
        gravity = 0;
        velocity_x = 0;
        velocity_y = 0;
    }
}

class Circle extends Physics {
    private int radius;

    public Circle(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius) {
        super(x, y, velocity_x, velocity_y, initial_velocity, launch_angle);
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