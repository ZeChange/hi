import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.lang.Math.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.Border;

public class PhysicsEngine extends JPanel{
    // begin the engine on command, can't let it run automatically
    public Timer timer;
    public double x; public double y;
    public double velocity_x; public double velocity_y;
    public double initial_velocity; public double launch_angle;
    public int radius;

    private double answer;
    private boolean correct = false;
    private ArrayList<Physics> sprites = new ArrayList<>();
    private double initial_velocity_scaling;
    private boolean running = false;
    
    public PhysicsEngine(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius, double answer, double initial_velocity_scaling){
        this.x = x/initial_velocity_scaling; this.y = y/initial_velocity_scaling;
        
        this.velocity_x = velocity_x/initial_velocity_scaling; this.velocity_y = velocity_y/initial_velocity_scaling;
        this.initial_velocity = initial_velocity/initial_velocity_scaling; this.launch_angle = launch_angle;
        this.radius = radius;
        this.answer = answer/initial_velocity_scaling;
        this.initial_velocity_scaling = initial_velocity_scaling;

        sprites.add(new Circle(this.x, this.y, this.velocity_x, this.velocity_y, this.initial_velocity, this.launch_angle, this.radius)); // sprite initialization (x, y, velocity_x, velocity_y, radius)
        for (Physics sprite : sprites) {
            Circle circle = (Circle) sprite;
        }
        repaint();

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
    }

    public void simulation()
    {
        running = true;
        sprites.remove(0);
        sprites.add(new Circle(this.x, this.y, this.velocity_x, this.velocity_y, this.initial_velocity, this.launch_angle, this.radius)); // sprite initialization (x, y, velocity_x, velocity_y, radius)
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
                            if(sprite.getX() < answer+(double)5*initial_velocity_scaling && sprite.getX() > answer-(double)5*initial_velocity_scaling)
                            {
                                correct = true;
                                System.out.println("bugatti");
                            }
                            running = false;
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

    public void setInitialVelocity(double initial_velocity)
    {
        this.initial_velocity = initial_velocity/initial_velocity_scaling;
    }

    public void setInitialHeight(double height) {
        this.y = getHeight() - height*4;
    }

    public void setInitialAngle(double angle) {
        this.launch_angle = angle;
    }

    public boolean getCorrect()
    {
        return correct;
    }

    public double getAnswer()
    {
        return answer;
    }

    public boolean inProgress()
    {
        return running;
    }

    protected void paintComponent(Graphics g) { // draw circle
        super.paintComponent(g);
        for (Physics sprite: sprites) {
            ((Circle)sprite).draw(g);
        }
    }

}

class Physics { // class that will be inherited, contains main physics logic 
    private double x;
    private double y;
    private double velocity_y;
    private double velocity_x;
    private double initial_velocity;
    private double launch_angle;
    
    private boolean isAirborne;
    private double gravity = 9.8 / 60; // 60 fps

    // all these values depend on the question given. If only some are given (e.g: initial velocity and launch angle to calculate for the rest of the stuff, then we will solve for others.)
    // x = distance, y = height, velocity_x = Vx, velocity_y = Vy, initial_velocity = V
    public Physics(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle) { 
        this.x = x;
        this.y = y;
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        this.initial_velocity = initial_velocity;
        this.launch_angle = launch_angle;

        this.velocity_x = this.initial_velocity * Math.cos(Math.toRadians(this.launch_angle));
        this.velocity_y = this.initial_velocity * Math.sin(Math.toRadians(this.launch_angle));

        if (this.velocity_y > 0) { // (down is up)
            this.velocity_y *= -1;
        }


        this.isAirborne = true;
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
        return (int)x;
    }

    public int getY() {
        return (int)y;
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

    public Circle(double x, double y, double velocity_x, double velocity_y, double initial_velocity, double launch_angle, int radius) {
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
