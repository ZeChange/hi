import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lessons {
    private JFrame frame = new JFrame("Projectile Motion Lesson");
    private int pageOn = 0;
    private JPanel[] lessonList = new JPanel[5];
    private JScrollBar s = new JScrollBar();  

    public Lessons() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(s);

        JPanel lesson1 = lesson1();
        lessonList[0] = lesson1;
        JPanel lesson2 = lesson2();
        lessonList[1] = lesson2;
        JPanel lesson3 = lesson3();
        lessonList[2] = lesson3;
        JPanel lesson4 = lesson4();
        lessonList[3] = lesson4;
        JPanel lesson5 = lesson5();
        lessonList[4] = lesson5;
        
        for(int i = 0; i < lessonList.length; i++)
        {
            JPanel completePage = lessonList[i];

            JPanel buttons = new JPanel(new FlowLayout());
            Font button = new Font("Serif", Font.PLAIN, 20);;
            JButton back = new JButton("Back");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(pageOn);
                    if(pageOn != 0)
                    {
                        pageOn -= 1;
                        newScreen();
                    }
                    else {
                        HomePage home = new HomePage();
                    }
                }
            });
            back.setFont(button);


            JButton next = new JButton("Next");
            next.setFont(button);
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                    if(pageOn < lessonList.length-1)
                    {
                        pageOn += 1;
                        newScreen();
                    }
                    else {
                        goHomePage();
                    }
                }
            });
            buttons.add(back); buttons.add(next);
            completePage.add(buttons, BorderLayout.SOUTH);

            lessonList[i] = completePage;
        }

        frame.add(lessonList[0]);
        frame.setVisible(true);
    }   

    public void newScreen()
    {
        frame.dispose();
        frame = new JFrame("Projectile Motion Lesson");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(lessonList[pageOn]);
        frame.setVisible(true);
    }

    public void goHomePage() {
        frame.dispose();
        HomePage home = new HomePage();
    }

    public JPanel lesson1() {
        String title = "Lesson 1: Tutorial - Introduction to Projectile Motion";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel subsectionsPanel = new JPanel(new CardLayout());
        subsectionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding on the top
        subsectionsPanel.add(createSubsectionPanel("Overview of Projectile Motion", "<html>Projectile motion refers to the curved path followed by an object launched into the air, moving under the influence of gravity alone. It occurs when an object is propelled with an initial velocity and then moves freely in the air, subject only to the force of gravity.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Key Concepts and Principles", "<html>a. <span style=\"font-size: 105%;\">Horizontal and Vertical Components:</span><br><br>When an object is launched in projectile motion, its motion can be broken down into two components: horizontal and vertical. The horizontal component remains constant throughout the motion, while the vertical component is influenced by gravity.<br><br>b. <span style=\"font-size: 105%;\">Trajectory:</span><br><br>The trajectory of a projectile is the path it follows through the air. It is typically curved, forming a parabolic shape. The exact shape of the trajectory depends on factors such as the launch angle, initial velocity, and gravitational force.<br><br>c. <span style=\"font-size: 105%;\">Range:</span><br><br>The range of a projectile refers to the horizontal distance covered by the object before it lands. The range is determined by the initial velocity and launch angle of the projectile.<br><br>d. <span style=\"font-size: 105%;\">Time of Flight:</span><br><br>The time of flight is the total time taken for a projectile to complete its trajectory and return to the same level as the launch point. It is influenced by the launch angle and initial velocity.<br><br>e. <span style=\"font-size: 105%;\">Maximum Height:</span><br><br>The maximum height reached by a projectile is determined by the launch angle and initial velocity. The object reaches its highest point in the trajectory before falling back to the ground.<br><br></html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Real-Life Applications", "<html><span style=\"font-size: 105%;\">a. Sports:</span><br><br>Understanding projectile motion is crucial in sports such as basketball, baseball, soccer, and golf. Athletes use the principles of projectile motion to optimize their performance, such as calculating the optimal angle for a long jump or the trajectory for a soccer goal.<br><br><span style=\"font-size: 105%;\">b. Fireworks:</span><br><br>Fireworks displays involve projectiles launched into the air that explode at different heights and distances. The designers consider the principles of projectile motion to create visually appealing patterns.<br><br><span style=\"font-size: 105%;\">c. Projectile Weapons:</span><br><br>Projectile motion principles are employed in the design and functioning of artillery, missiles, and other projectile weapons. These weapons are launched with specific velocities and angles to accurately hit their targets.<br><br><span style=\"font-size: 105%;\">d. Astronomy:</span><br><br>The motion of celestial bodies, such as planets, moons, and satellites, follows the principles of projectile motion. Understanding these principles helps astronomers predict their paths and trajectories.<br><br></html>", subsectionsPanel));
        panel.add(subsectionsPanel, BorderLayout.CENTER);

        return panel;
    }

    public JPanel lesson2() {
        String title = "Lesson 2: Exploring Projectile Trajectories";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel subsectionsPanel = new JPanel(new CardLayout());
        subsectionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        subsectionsPanel.add(createSubsectionPanel("Introduction", "<html>In this tutorial, we will delve into projectile trajectories and explore two types of projectile motion examples. We will discuss the factors that impact how a projectile moves. Let's dive in!</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Types of Factors - Horizontal Launch", "<html><span style=\"font-size: 105%;\">Type 1: Horizontal Launch:</span><br><br>Imagine a scenario where an object is launched horizontally from an elevated platform.<br><span style=\"font-size: 105%;\">Observations:</span><br><br>- As the object is launched horizontally, its initial vertical velocity is zero.<br>- The only force acting on the object is gravity, causing it to accelerate vertically downward.<br>- The horizontal velocity remains constant throughout the motion.<br>- The object's trajectory is a downward-curving parabola.<br>- The time taken to reach the ground is determined by the vertical distance and the acceleration due to gravity.<br><br><span style=\"font-size: 105%;\">Real-Life Examples:</span><br><br>- A cannonball fired horizontally from a cliff.<br>- A skateboarder launching off a ramp horizontally.<br><br></html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Types of Factors - Angled Launch","<html><span style=\"font-size: 105%;\">Type 2: Angled Launch:</span><br><br>Now, let's imagine a scenario where an object is launched at an angle to the horizontal. This type of projectile motion is more common and presents interesting possibilities.<br><br><span style=\"font-size: 105%;\">Observations:</span><br><br>- The object has both horizontal and vertical components of velocity.<br>- The vertical component is influenced by gravity, causing the object to accelerate downward.<br>- The horizontal component remains constant throughout the motion.<br>- The object's trajectory forms a symmetric parabolic shape.<br>- The maximum height reached depends on the initial velocity and launch angle.<br>- The range of the projectile depends on the initial velocity and the launch angle.<br><br><span style=\"font-size: 105%;\">Real-Life Examples:</span><br><br>- A baseball player throwing a ball at an angle.<br>- A football kicked with an angle.<br><br></html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Factors Impacting Projectile Motion", "<html><span style=\"font-size: 105%;\">Factors Impacting Projectile Motion:</span><br><br>Several factors influence how a projectile moves through the air. Let's discuss these factors briefly:<br><br><span style=\"font-size: 105%;\">a. Launch Angle:</span><br><br>The angle at which the projectile is launched affects its range, maximum height, and overall trajectory. Different launch angles result in different paths.<br><br><span style=\"font-size: 105%;\">b. Initial Velocity:</span><br><br>The initial velocity of the projectile impacts both the range and the maximum height. A higher initial velocity leads to a longer range and greater height.<br><br><span style=\"font-size: 105%;\">c. Air Resistance:</span><br><br>In real-life scenarios, air resistance can affect the motion of a projectile. However, in these examples, we assume a negligible air resistance for simplicity.<br><br></html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Conclusion", "<html><span style=\"font-size: 105%;\">Conclusion:</span><br><br>Projectile motion offers a captivating study of how objects move through the air. By understanding the two types of projectile motion and the factors that influence their trajectories, we can gain insights into various real-life scenarios, from sports to artillery. Visualizing these examples through animations helps solidify our understanding of projectile motion.<br><br>Remember, physics is all around us.</html>", subsectionsPanel));
        panel.add(subsectionsPanel, BorderLayout.CENTER);

        return panel;
    }


    public JPanel lesson3() {
        String title = "Lesson 3: Calculating Range and Maximum Height of a Projectile";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
        JPanel subsectionsPanel = new JPanel(new CardLayout());
        subsectionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        subsectionsPanel.add(createSubsectionPanel("Introduction", "<html>In this tutorial, we will learn how to calculate the time of flight, range, and maximum height of a projectile. We will provide step-by-step explanations of the formulas used and include practice problems to reinforce the concepts. Let's dive in!</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Calculations", "<html><span style=\"font-size: 105%;\">Time of Flight:</span><br>The time of flight refers to the total time taken for a projectile to complete its trajectory. To calculate it, we need to consider the vertical motion of the projectile. The formula to calculate the time of flight is:<br>Formula: time of flight (t) = (2 * initial vertical velocity) / acceleration due to gravity<br><br><span style=\"font-size: 105%;\">Range:</span><br>The range of a projectile is the horizontal distance covered by the object before it lands. To calculate the range, we need to consider the horizontal motion of the projectile. The formula to calculate the range is:<br>Formula: range (R) = initial horizontal velocity * time of flight<br><br><span style=\"font-size: 105%;\">Maximum Height:</span><br>The maximum height reached by a projectile is an important parameter to determine. It represents the highest point in the trajectory. To calculate the maximum height, we need to consider the vertical motion of the projectile. The formula to calculate the maximum height is:<br>Formula: maximum height (H) = (initial vertical velocity)² / (2 * acceleration due to gravity)<br><br></html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Practice Problem 1", "<html><span style=\"font-size: 105%\"><b>Practice Problems:</b></span><br>Let's solve some practice problems to reinforce our understanding of calculating the time of flight, range, and maximum height.<br><br><span style=\"font-size: 105%\"><b>Problem 1:</b></span><br>A ball is thrown vertically upwards with an initial velocity of 15 m/s. Calculate the time of flight, range, and maximum height.<br><br><span style=\"font-size: 105%\"><b>Solution:</b></span><br>Given: initial vertical velocity (u) = 15 m/s, acceleration due to gravity (g) = -9.8 m/s²<br><br>Calculate the time of flight:<br>t = (2 * 15 m/s) / (-9.8 m/s²) ≈ 3.06 seconds<br><br>Calculate the range:<br>The ball was thrown vertically upwards, so the range is 0 meters.<br><br>Calculate the maximum height:<br>H = (15 m/s)² / (2 * -9.8 m/s²) ≈ 11.47 meters<br><br>Therefore, the time of flight is approximately 3.06 seconds, the range is 0 meters, and the maximum height is approximately 11.47 meters.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Practice Problem 2", "<html><span style=\"font-size: 105%\"><b>Practice Problems:</b></span><br>Let's solve some practice problems to reinforce our understanding of calculating the time of flight, range, and maximum height.<br><br><span style=\"font-size: 105%\"><b>Problem 2:</b></span><br>A projectile is launched at an angle of 30 degrees above the horizontal with an initial speed of 20 m/s. Calculate the time of flight, range, and maximum height.<br><br><span style=\"font-size: 105%\"><b>Solution:</b></span><br>Given: launch angle (θ) = 30 degrees, initial speed (v) = 20 m/s, acceleration due to gravity (g) = -9.8 m/s²<br><br>Calculate the initial vertical velocity:<br>initial vertical velocity = initial velocity * sin(θ) = 20 m/s * sin(30) ≈ 10 m/s<br><br>Calculate the initial horizontal velocity:<br>initial horizontal velocity = initial velocity * cos(θ) = 20 m/s * cos(30) ≈ 17.32 m/s<br><br>Calculate the time of flight:<br>t = (2 * 10 m/s) / (-9.8 m/s²) ≈ 2.04 seconds<br><br>Calculate the range:<br>R = 17.32 m/s * 2.04 s ≈ 35.32 meters<br><br>Calculate the maximum height:<br>H = (10 m/s)² / (2 * -9.8 m/s²) ≈ 5.10 meters<br><br>Therefore, the time of flight is approximately 2.04 seconds, the range is approximately 35.32 meters, and the maximum height is approximately 5.10 meters.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Conclusion", "<html>Calculating the time of flight, range, and maximum height of a projectile involves considering both the horizontal and vertical components of motion. By using the appropriate formulas and understanding the principles behind them, we can determine these important parameters. Practice solving various projectile motion problems to strengthen your understanding and application of these concepts.</html>", subsectionsPanel));
        panel.add(subsectionsPanel, BorderLayout.CENTER);

        return panel;
    }

    public JPanel lesson4() {
        String title = "Lesson 4: Projectiles Launched at an Angle";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
        JPanel subsectionsPanel = new JPanel(new CardLayout());
        subsectionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        subsectionsPanel.add(createSubsectionPanel("Introduction", "<html>In this tutorial, we will explore the equations used to understand projectile motion. We will focus on the horizontal and vertical components of motion and learn how to apply these equations to solve projectile motion problems. Let's get started!</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Horizontal and Vertical Components of Motion", "<html><span style=\"font-size: 105%\"><b>Horizontal and Vertical Components of Motion:</b></span><br>Projectile motion involves both horizontal and vertical components of motion. Understanding these components is essential for analyzing the motion of a projectile.<br><br><span style=\"font-size: 105%\"><b>a. Horizontal Component:</b></span><br>The horizontal component of motion remains constant throughout the projectile's trajectory. This means that there is no acceleration in the horizontal direction. The horizontal component is affected by the initial velocity and time of flight. The key equation for the horizontal component is:<br><span style=\"font-size: 105%\">Equation: Horizontal distance (d) = Initial horizontal velocity (v\u2080x) \u00D7 Time of flight (t)</span><br><br><span style=\"font-size: 105%\"><b>b. Vertical Component:</b></span><br>The vertical component of motion is influenced by gravity. The motion in the vertical direction follows the equations of uniformly accelerated motion. The vertical component is affected by the initial vertical velocity, acceleration due to gravity, and time. The key equations for the vertical component are:<br><span style=\"font-size: 105%\">Equation 1: Vertical displacement (h) = Initial vertical velocity (v\u2080y) \u00D7 Time (t) + (1/2) \u00D7 Acceleration due to gravity (g) \u00D7 Time\u00B2</span><br><span style=\"font-size: 105%\">Equation 2: Final vertical velocity (v) = Initial vertical velocity (v\u2080y) + Acceleration due to gravity (g) \u00D7 Time (t)</span></html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Applying Projectile Motion Equations", "<html><span style=\"font-size: 105%\"><b>Applying Projectile Motion Equations:</b></span><br>To solve projectile motion problems, we follow a systematic approach that involves analyzing the horizontal and vertical components separately and then combining the results.<br><br><span style=\"font-size: 105%\"><b>a. Identify Known and Unknown Variables:</b></span><br>Identify the known variables such as initial velocity, launch angle, and gravitational acceleration.Determine the unknown variable you need to find, which could be the maximum height, range, or time of flight.<br><br><span style=\"font-size: 105%\"><b>b. Resolve Initial Velocity into Components:</b></span><br>Decompose the initial velocity vector into its horizontal and vertical components using trigonometry.<br>The horizontal component is given by: <span style=\"font-size: 105%\">Initial horizontal velocity (v₀x) = Initial velocity (v₀) × cos(θ)</span><br>The vertical component is given by: <span style=\"font-size: 105%\">Initial vertical velocity (v₀y) = Initial velocity (v₀) × sin(θ)</span><br><br><span style=\"font-size: 105%\"><b>c. Solve for the Horizontal Component:</b></span><br>Use the horizontal component equation to calculate the horizontal distance (range) traveled by the projectile.<br><br><span style=\"font-size: 105%\"><b>d. Solve for the Vertical Component:</b></span><br>Use the vertical component equations to find the maximum height reached by the projectile and the time of flight.<br><br><span style=\"font-size: 105%\"><b>e. Combine Results:</b></span><br>Combine the results from the horizontal and vertical components to obtain a comprehensive understanding of the projectile's motion.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Practice Problem 1", "<html><span style=\"font-size: 105%\"><b>Practice Problems:</b></span><br>Let's solve a couple of practice problems to reinforce our understanding of projectile motion equations.<br><br><span style=\"font-size: 105%\"><b>Problem 1:</b></span><br>A ball is thrown horizontally from a height of 2 meters with an initial speed of 5 m/s. Determine the time of flight, range, and maximum height.<br><br><span style=\"font-size: 105%\"><b>Solution:</b></span><br>Given: Initial velocity (v₀) = 5 m/s, Launch angle (θ) = 0 degrees, Acceleration due to gravity (g) = 9.8 m/s².<br>Resolve the initial velocity into components:<br>Initial horizontal velocity (v₀x) = 5 m/s × cos(0) = 5 m/s<br>Initial vertical velocity (v₀y) = 5 m/s × sin(0) = 0 m/s<br>Calculate the time of flight:<br>Time of flight (t) = (2 × Initial vertical velocity) / Acceleration due to gravity<br>= (2 × 0 m/s) / 9.8 m/s²<br>= 0 seconds<br>Calculate the range:<br>Horizontal distance (d) = Initial horizontal velocity × Time of flight<br>= 5 m/s × 0 s<br>= 0 meters<br>Calculate the maximum height:<br>Vertical displacement (h) = Initial vertical velocity × Time + (1/2) × Acceleration due to gravity × Time²<br>= 0 m/s × 0 s + (1/2) × 9.8 m/s² × 0 s²<br>= 0 meters<br>Therefore, the time of flight is 0 seconds, the range is 0 meters, and the maximum height is 0 meters.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Practice Problem 2", "<html><span style=\"font-size: 105%\"><b>Problem 2:</b></span><br>A projectile is launched with an initial velocity of 15 m/s at an angle of 30 degrees above the horizontal. Determine the time of flight, range, and maximum height.<br><br><span style=\"font-size: 105%\"><b>Solution:</b></span><br>Given: Initial velocity (v₀) = 15 m/s, Launch angle (θ) = 30 degrees, Acceleration due to gravity (g) = 9.8 m/s².<br>Resolve the initial velocity into components:<br>Initial horizontal velocity (v₀x) = 15 m/s × cos(30) ≈ 12.99 m/s<br>Initial vertical velocity (v₀y) = 15 m/s × sin(30) ≈ 7.50 m/s<br>Calculate the time of flight:<br>Time of flight (t) = (2 × Initial vertical velocity) / Acceleration due to gravity<br>= (2 × 7.50 m/s) / 9.8 m/s²<br>≈ 1.53 seconds<br>Calculate the range:<br>Horizontal distance (d) = Initial horizontal velocity × Time of flight<br>= 12.99 m/s × 1.53 s<br>≈ 19.89 meters<br>Calculate the maximum height:<br>Vertical displacement (h) = Initial vertical velocity × Time + (1/2) × Acceleration due to gravity × Time²<br>= 7.50 m/s × 1.53 s + (1/2) × 9.8 m/s² × (1.53 s)²<br>≈ 5.34 meters<br>Therefore, the time of flight is approximately 1.53 seconds, the range is approximately 19.89 meters, and the maximum height is approximately 5.34 meters.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Conclusion", "<html>Understanding projectile motion equations is crucial for analyzing and solving problems involving the motion of objects launched into the air. By considering the horizontal and vertical components of motion and applying the respective equations, we can determine various parameters such as time of flight, range, and maximum height. Practice solving different projectile motion problems to enhance your understanding and proficiency in applying these equations.</html>", subsectionsPanel));
        panel.add(subsectionsPanel, BorderLayout.CENTER);
        
        return panel;
    }

    public JPanel lesson5() {
        String title = "Lesson 5: Factors That Impact Projectile Motion";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
        JPanel subsectionsPanel = new JPanel(new CardLayout());
        subsectionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        subsectionsPanel.add(createSubsectionPanel("Introduction", "<html>In this tutorial, we will explore the key factors that impact projectile motion. We will discuss the influence of initial velocity, launch angle, and gravity on the motion of a projectile. Please note that we will consider ideal conditions with no air resistance. Let's begin!</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Initial Velocity", "<html><span style=\"font-size: 105%\"><b>Initial Velocity:</b></span><br>The initial velocity of a projectile refers to the speed at which it is launched. It has a significant impact on the motion of the projectile. The higher the initial velocity, the farther the projectile will travel. Increasing the initial velocity will result in:<br>- Greater horizontal distance (range): A higher initial velocity will lead to a longer horizontal distance covered by the projectile.<br>- Higher maximum height: The maximum height reached by the projectile will also increase with a higher initial velocity.<br>- Longer time of flight: An increase in initial velocity will result in a longer time taken for the projectile to complete its trajectory.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Launch Angle", "<html><span style=\"font-size: 105%\"><b>Launch Angle:</b></span><br>The launch angle of a projectile refers to the angle at which it is launched with respect to the horizontal. The launch angle affects both the horizontal and vertical components of motion. Here's how changes in the launch angle impact the projectile:<br>- Maximum range: For a given initial velocity, there is an optimal launch angle that maximizes the horizontal distance (range) traveled by the projectile. This angle is typically around 45 degrees.<br>- Higher angles: Launching the projectile at a higher angle (greater than 45 degrees) will result in a shorter horizontal distance but a higher maximum height.<br>- Lower angles: Launching the projectile at a lower angle (less than 45 degrees) will result in a longer horizontal distance but a lower maximum height.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Gravity", "<html><span style=\"font-size: 105%\"><b>Gravity:</b></span><br>Gravity plays a fundamental role in projectile motion. It acts vertically downwards and affects the vertical component of motion. Here's how gravity impacts the motion of a projectile:<br>- Vertical motion: Gravity causes the projectile to accelerate downwards throughout its trajectory. The acceleration due to gravity is typically considered as -9.8 m/s\u00B2 (negative since it acts downwards). It affects the vertical displacement, maximum height, and time of flight of the projectile.<br>- Symmetrical path: The influence of gravity makes the path of the projectile symmetrical, forming a parabolic trajectory.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Impact of Changes in Factors", "<html><span style=\"font-size: 105%\"><b>Impact of Factors on Projectile Motion:</b></span><br>Let's examine how changes in these factors impact the overall motion of a projectile:<br>a. Increased initial velocity: Increasing the initial velocity will result in a longer horizontal distance, higher maximum height, and longer time of flight.<br>b. Decreased initial velocity: Decreasing the initial velocity will result in a shorter horizontal distance, lower maximum height, and shorter time of flight.<br>c. Higher launch angle: Increasing the launch angle (within a certain range) will result in a higher maximum height but a shorter horizontal distance.<br>d. Lower launch angle: Decreasing the launch angle (within a certain range) will result in a lower maximum height but a longer horizontal distance.<br>e. Increased gravity: Increasing the value of gravity will cause the projectile to experience a greater downward acceleration, resulting in a shorter vertical displacement and a shorter time of flight.</html>", subsectionsPanel));
        subsectionsPanel.add(createSubsectionPanel("Conclusion", "<html>Understanding the factors that impact projectile motion, including initial velocity, launch angle, and gravity, is essential for analyzing and predicting the behavior of projectiles. Changes to these factors can significantly affect the trajectory, range, and maximum height of a projectile. By considering these factors, we can better comprehend and solve problems related to projectile motion. Remember, in real-world scenarios, factors like air resistance would also influence projectile motion.</html>", subsectionsPanel));
        panel.add(subsectionsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSubsectionPanel(String title, String text, JPanel parentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Text
        JLabel textLabel = new JLabel(text);
        textLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding between paragraphs
        panel.add(textLabel, BorderLayout.CENTER);

        // Next and Back buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        buttonsPanel.add(backButton);
        buttonsPanel.add(nextButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
        backButton.addActionListener(e -> cardLayout.previous(parentPanel));
        nextButton.addActionListener(e -> cardLayout.next(parentPanel));

        return panel;
    }
}