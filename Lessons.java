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

        // Lesson 1:
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
            back.addActionListener(new ActionListener() {@Override
                public void actionPerformed(ActionEvent e) {
                    if(pageOn != 0)
                    {
                        pageOn -= 1;
                        newScreen();
                    }
                }
            });
            back.setFont(button);


            JButton next = new JButton("Next");
            next.setFont(button);
            next.addActionListener(new ActionListener() {@Override
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
        String[] text = {
            "<html>In this tutorial, we will explore the fascinating concept of projectile motion. We will cover the overview of projectile motion, discuss key concepts and principles, and explore real-life applications. Let's get started!</html>",
            "<html>Overview of Projectile Motion:<br>Projectile motion refers to the curved path followed by an object launched into the air, moving under the influence of gravity alone. It occurs when an object is propelled with an initial velocity and then moves freely in the air, subject only to the force of gravity.</html>",
            "<html>Key Concepts and Principles:<br>a. Horizontal and Vertical Components:<br>When an object is launched in projectile motion, its motion can be broken down into two components: horizontal and vertical. The horizontal component remains constant throughout the motion, while the vertical component is influenced by gravity.<br>b. Trajectory:<br>The trajectory of a projectile is the path it follows through the air. It is typically curved, forming a parabolic shape. The exact shape of the trajectory depends on factors such as the launch angle, initial velocity, and gravitational force.<br>c. Range:<br>The range of a projectile refers to the horizontal distance covered by the object before it lands. The range is determined by the initial velocity and launch angle of the projectile.<br>d. Time of Flight:<br>The time of flight is the total time taken for a projectile to complete its trajectory and return to the same level as the launch point. It is influenced by the launch angle and initial velocity.<br>e. Maximum Height:<br>The maximum height reached by a projectile is determined by the launch angle and initial velocity. The object reaches its highest point in the trajectory before falling back to the ground.</html>",
            "<html>Real-Life Applications:<br>Projectile motion is prevalent in various real-life scenarios, including:<br>a. Sports:<br>Understanding projectile motion is crucial in sports such as basketball, baseball, soccer, and golf. Athletes use the principles of projectile motion to optimize their performance, such as calculating the optimal angle for a long jump or the trajectory for a soccer goal.<br>b. Fireworks:<br>Fireworks displays involve projectiles launched into the air that explode at different heights and distances. The designers consider the principles of projectile motion to create visually appealing patterns.<br>c. Projectile Weapons:<br>Projectile motion principles are employed in the design and functioning of artillery, missiles, and other projectile weapons. These weapons are launched with specific velocities and angles to accurately hit their targets.<br>d. Astronomy:<br>The motion of celestial bodies, such as planets, moons, and satellites, follows the principles of projectile motion. Understanding these principles helps astronomers predict their paths and trajectories.</html>",
            "<html>Conclusion:<br>Projectile motion is an exciting concept that finds applications in various fields. By understanding the key concepts and principles of projectile motion, you can gain insights into the motion of objects in the air. So, whether you're playing a sport, watching a fireworks display, or studying celestial objects, projectile motion is an essential concept to comprehend.<br><br>Keep exploring the world of physics, and you'll be amazed at how these principles shape the world around us!</html>"
        };

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Text
        JPanel textPanel = new JPanel(new GridLayout(text.length, 1));
        for (int i = 0; i < text.length; i++) {
            JLabel label = new JLabel(text[i]);
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding between paragraphs
            textPanel.add(label);
        }
        JScrollPane scrollPane = new JScrollPane(textPanel); // Wrap textPanel inside a JScrollPane for scrollable content
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }



    public JPanel lesson2() {
        String title = "Lesson 2: Exploring Projectile Trajectories";
        String[] text = {
            "<html>In this tutorial, we will delve into projectile trajectories and explore two types of projectile motion examples. We will use animations to visualize these examples and discuss the factors that impact how a projectile moves. Let's dive in!</html>",
            "<html>Type 1: Horizontal Launch:<br>Imagine a scenario where an object is launched horizontally from an elevated platform. Let's see how the projectile moves in this situation.</html>",
            "<html>Animation:<br>[Include an animation or provide a link to an online animation that demonstrates a horizontally launched projectile.]</html>",
            "<html>Observations:<br>- As the object is launched horizontally, its initial vertical velocity is zero.<br>- The only force acting on the object is gravity, causing it to accelerate vertically downward.<br>- The horizontal velocity remains constant throughout the motion.<br>- The object's trajectory is a downward-curving parabola.<br>- The time taken to reach the ground is determined by the vertical distance and the acceleration due to gravity.</html>",
            "<html>Real-Life Examples:<br>- A cannonball fired horizontally from a cliff.<br>- A skateboarder launching off a ramp horizontally.</html>",
            "<html>Type 2: Angled Launch:<br>Now, let's explore a scenario where an object is launched at an angle to the horizontal. This type of projectile motion is more common and presents interesting possibilities.</html>",
            "<html>Animation:<br>[Include an animation or provide a link to an online animation that demonstrates an angled launched projectile.]</html>",
            "<html>Observations:<br>- The object has both horizontal and vertical components of velocity.<br>- The vertical component is influenced by gravity, causing the object to accelerate downward.<br>- The horizontal component remains constant throughout the motion.<br>- The object's trajectory forms a symmetric parabolic shape.<br>- The maximum height reached depends on the initial velocity and launch angle.<br>- The range of the projectile depends on the initial velocity and the launch angle.</html>",
            "<html>Real-Life Examples:<br>- A baseball player throwing a ball at an angle.<br>- A football kicked with an angle.</html>",
            "<html>Factors Impacting Projectile Motion:<br>Several factors influence how a projectile moves through the air. Let's discuss these factors briefly:<br>a. Launch Angle: The angle at which the projectile is launched affects its range, maximum height, and overall trajectory. Different launch angles result in different paths.<br>b. Initial Velocity: The initial velocity of the projectile impacts both the range and the maximum height. A higher initial velocity leads to a longer range and greater height.<br>c. Air Resistance: In real-life scenarios, air resistance can affect the motion of a projectile. However, in these examples, we assume a negligible air resistance for simplicity.</html>",
            "<html>Conclusion:<br>Projectile motion offers a captivating study of how objects move through the air. By understanding the two types of projectile motion and the factors that influence their trajectories, we can gain insights into various real-life scenarios, from sports to artillery. Visualizing these examples through animations helps solidify our understanding of projectile motion.<br><br>Remember, physics is all around us.</html>"
        };

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Text
        JPanel textPanel = new JPanel(new GridLayout(text.length, 1));
        for (int i = 0; i < text.length; i++) {
            JLabel label = new JLabel(text[i]);
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding between paragraphs
            textPanel.add(label);
        }
        JScrollPane scrollPane = new JScrollPane(textPanel); // Wrap textPanel inside a JScrollPane for scrollable content
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


    public JPanel lesson3() {
    String title = "Lesson 3: Calculating Range and Maximum Height of a Projectile";
    String[] paragraphs = {
        "In this tutorial, we will learn how to calculate the time of flight, range, and maximum height of a projectile. We will provide step-by-step explanations of the formulas used and include practice problems to reinforce the concepts. Let's dive in!",
        "Time of Flight:",
        "The time of flight refers to the total time taken for a projectile to complete its trajectory. To calculate it, we need to consider the vertical motion of the projectile. The formula to calculate the time of flight is:",
        "Formula: time of flight (t) = (2 * initial vertical velocity) / acceleration due to gravity",
        "Range:",
        "The range of a projectile is the horizontal distance covered by the object before it lands. To calculate the range, we need to consider the horizontal motion of the projectile. The formula to calculate the range is:",
        "Formula: range (R) = initial horizontal velocity * time of flight",
        "Maximum Height:",
        "The maximum height reached by a projectile is an important parameter to determine. It represents the highest point in the trajectory. To calculate the maximum height, we need to consider the vertical motion of the projectile. The formula to calculate the maximum height is:",
        "Formula: maximum height (H) = (initial vertical velocity)² / (2 * acceleration due to gravity)",
        "Practice Problems:",
        "Let's solve some practice problems to reinforce our understanding of calculating the time of flight, range, and maximum height.",
        "Problem 1:",
        "A ball is thrown vertically upwards with an initial velocity of 15 m/s. Calculate the time of flight, range, and maximum height.",
        "Solution:",
        "Given: initial vertical velocity (u) = 15 m/s, acceleration due to gravity (g) = -9.8 m/s²",
        "Calculate the time of flight:",
        "t = (2 * 15 m/s) / (-9.8 m/s²) ≈ 3.06 seconds",
        "Calculate the range:",
        "The ball was thrown vertically upwards, so the range is 0 meters.",
        "Calculate the maximum height:",
        "H = (15 m/s)² / (2 * -9.8 m/s²) ≈ 11.47 meters",
        "Therefore, the time of flight is approximately 3.06 seconds, the range is 0 meters, and the maximum height is approximately 11.47 meters.",
        "Problem 2:",
        "A projectile is launched at an angle of 30 degrees above the horizontal with an initial speed of 20 m/s. Calculate the time of flight, range, and maximum height.",
        "Solution:",
        "Given: launch angle (θ) = 30 degrees, initial speed (v) = 20 m/s, acceleration due to gravity (g) = -9.8 m/s²",
        "Calculate the initial vertical velocity:",
        "initial vertical velocity = initial velocity * sin(θ) = 20 m/s * sin(30) ≈ 10 m/s",
        "Calculate the initial horizontal velocity:",
        "initial horizontal velocity = initial velocity * cos(θ) = 20 m/s * cos(30) ≈ 17.32 m/s",
        "Calculate the time of flight:",
        "t = (2 * 10 m/s) / (-9.8 m/s²) ≈ 2.04 seconds",
        "Calculate the range:",
        "R = 17.32 m/s * 2.04 s ≈ 35.32 meters",
        "Calculate the maximum height:",
        "H = (10 m/s)² / (2 * -9.8 m/s²) ≈ 5.10 meters",
        "Therefore, the time of flight is approximately 2.04 seconds, the range is approximately 35.32 meters, and the maximum height is approximately 5.10 meters.",
        "Conclusion:",
        "Calculating the time of flight, range, and maximum height of a projectile involves considering both the horizontal and vertical components of motion. By using the appropriate formulas and understanding the principles behind them, we can determine these important parameters. Practice solving various projectile motion problems to strengthen your understanding and application of these concepts."
    };

    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

    // Title
    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    panel.add(titleLabel, BorderLayout.NORTH);

    // Content
    JPanel contentPanel = new JPanel(new GridLayout(paragraphs.length, 1));
    for (String paragraph : paragraphs) {
        JLabel label = new JLabel("<html><p style='margin-bottom: 15px;'>" + paragraph + "</p></html>");
        contentPanel.add(label);
    }

    // Wrap contentPanel inside a JScrollPane
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    panel.add(scrollPane, BorderLayout.CENTER);

    return panel;
}




    public JPanel lesson4() {
        String title = "Lesson 4: Projectiles Launched at an Angle";
        String[] paragraphs = {
        "In this tutorial, we will explore the equations used to understand projectile motion. We will focus on the horizontal and vertical components of motion and learn how to apply these equations to solve projectile motion problems. Let's get started!",
        "Horizontal and Vertical Components of Motion:",
        "Projectile motion involves both horizontal and vertical components of motion. Understanding these components is essential for analyzing the motion of a projectile.",
        "a. Horizontal Component:",
        "The horizontal component of motion remains constant throughout the projectile's trajectory. This means that there is no acceleration in the horizontal direction. The horizontal component is affected by the initial velocity and time of flight. The key equation for the horizontal component is:",
        "Equation: Horizontal distance (d) = Initial horizontal velocity (v₀x) × Time of flight (t)",
        "b. Vertical Component:",
        "The vertical component of motion is influenced by gravity. The motion in the vertical direction follows the equations of uniformly accelerated motion. The vertical component is affected by the initial vertical velocity, acceleration due to gravity, and time. The key equations for the vertical component are:",
        "Equation 1: Vertical displacement (h) = Initial vertical velocity (v₀y) × Time (t) + (1/2) × Acceleration due to gravity (g) × Time²",
        "Equation 2: Final vertical velocity (v) = Initial vertical velocity (v₀y) + Acceleration due to gravity (g) × Time (t)",
        "Applying Projectile Motion Equations:",
        "To solve projectile motion problems, we follow a systematic approach that involves analyzing the horizontal and vertical components separately and then combining the results.",
        "a. Identify Known and Unknown Variables:",
        "Identify the known variables such as initial velocity, launch angle, and gravitational acceleration.",
        "Determine the unknown variable you need to find, which could be the maximum height, range, or time of flight.",
        "b. Resolve Initial Velocity into Components:",
        "Decompose the initial velocity vector into its horizontal and vertical components using trigonometry.",
        "The horizontal component is given by: Initial horizontal velocity (v₀x) = Initial velocity (v₀) × cos(θ)",
        "The vertical component is given by: Initial vertical velocity (v₀y) = Initial velocity (v₀) × sin(θ)",
        "c. Solve for the Horizontal Component:",
        "Use the horizontal component equation to calculate the horizontal distance (range) traveled by the projectile.",
        "d. Solve for the Vertical Component:",
        "Use the vertical component equations to find the maximum height reached by the projectile and the time of flight.",
        "e. Combine Results:",
        "Combine the results from the horizontal and vertical components to obtain a comprehensive understanding of the projectile's motion.",
        "Practice Problems:",
        "Let's solve a couple of practice problems to reinforce our understanding of projectile motion equations.",
        "Problem 1:",
        "A ball is thrown horizontally from a height of 2 meters with an initial speed of 5 m/s. Determine the time of flight, range, and maximum height.",
        "Solution:",
        "Given: Initial velocity (v₀) = 5 m/s, Launch angle (θ) = 0 degrees, Acceleration due to gravity (g) = 9.8 m/s².",
        "Resolve the initial velocity into components:",
        "Initial horizontal velocity (v₀x) = 5 m/s × cos(0) = 5 m/s",
        "Initial vertical velocity (v₀y) = 5 m/s × sin(0) = 0 m/s",
        "Calculate the time of flight:",
        "Time of flight (t) = (2 × Initial vertical velocity) / Acceleration due to gravity",
        "= (2 × 0 m/s) / 9.8 m/s²",
        "= 0 seconds",
        "Calculate the range:",
        "Horizontal distance (d) = Initial horizontal velocity × Time of flight",
        "= 5 m/s × 0 s",
        "= 0 meters",
        "Calculate the maximum height:",
        "Vertical displacement (h) = Initial vertical velocity × Time + (1/2) × Acceleration due to gravity × Time²",
        "= 0 m/s × 0 s + (1/2) × 9.8 m/s² × 0 s²",
        "= 0 meters",
        "Therefore, the time of flight is 0 seconds, the range is 0 meters, and the maximum height is 0 meters.",
        "Problem 2:",
        "A projectile is launched with an initial velocity of 15 m/s at an angle of 30 degrees above the horizontal. Determine the time of flight, range, and maximum height.",
        "Solution:",
        "Given: Initial velocity (v₀) = 15 m/s, Launch angle (θ) = 30 degrees, Acceleration due to gravity (g) = 9.8 m/s².",
        "Resolve the initial velocity into components:",
        "Initial horizontal velocity (v₀x) = 15 m/s × cos(30) ≈ 12.99 m/s",
        "Initial vertical velocity (v₀y) = 15 m/s × sin(30) ≈ 7.50 m/s",
        "Calculate the time of flight:",
        "Time of flight (t) = (2 × Initial vertical velocity) / Acceleration due to gravity",
        "= (2 × 7.50 m/s) / 9.8 m/s²",
        "≈ 1.53 seconds",
        "Calculate the range:",
        "Horizontal distance (d) = Initial horizontal velocity × Time of flight",
        "= 12.99 m/s × 1.53 s",
        "≈ 19.89 meters",
        "Calculate the maximum height:",
        "Vertical displacement (h) = Initial vertical velocity × Time + (1/2) × Acceleration due to gravity × Time²",
        "= 7.50 m/s × 1.53 s + (1/2) × 9.8 m/s² × (1.53 s)²",
        "≈ 5.34 meters",
        "Therefore, the time of flight is approximately 1.53 seconds, the range is approximately 19.89 meters, and the maximum height is approximately 5.34 meters.",
        "Conclusion:",
        "Understanding projectile motion equations is crucial for analyzing and solving problems involving the motion of objects launched into the air. By considering the horizontal and vertical components of motion and applying the respective equations, we can determine various parameters such as time of flight, range, and maximum height. Practice solving different projectile motion problems to enhance your understanding and proficiency in applying these equations."
    };
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Content
        JPanel contentPanel = new JPanel(new GridLayout(paragraphs.length, 1));
        for (String paragraph : paragraphs) {
            JLabel label = new JLabel("<html><p style='margin-bottom: 15px;'>" + paragraph + "</p></html>");
            contentPanel.add(label);
        }

        // Wrap contentPanel inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public JPanel lesson5() {
        String title = "Lesson 5: Factors That Impact Projectile Motion";
        String[] paragraphs = {
            "In this tutorial, we will explore the key factors that impact projectile motion. We will discuss the influence of initial velocity, launch angle, and gravity on the motion of a projectile. Please note that we will consider ideal conditions with no air resistance. Let's begin!",
            "Initial Velocity:",
            "The initial velocity of a projectile refers to the speed at which it is launched. It has a significant impact on the motion of the projectile. The higher the initial velocity, the farther the projectile will travel. Increasing the initial velocity will result in:",
            "- Greater horizontal distance (range): A higher initial velocity will lead to a longer horizontal distance covered by the projectile.",
            "- Higher maximum height: The maximum height reached by the projectile will also increase with a higher initial velocity.",
            "- Longer time of flight: An increase in initial velocity will result in a longer time taken for the projectile to complete its trajectory.",
            "Launch Angle:",
            "The launch angle of a projectile refers to the angle at which it is launched with respect to the horizontal. The launch angle affects both the horizontal and vertical components of motion. Here's how changes in the launch angle impact the projectile:",
            "- Maximum range: For a given initial velocity, there is an optimal launch angle that maximizes the horizontal distance (range) traveled by the projectile. This angle is typically around 45 degrees.",
            "- Higher angles: Launching the projectile at a higher angle (greater than 45 degrees) will result in a shorter horizontal distance but a higher maximum height.",
            "- Lower angles: Launching the projectile at a lower angle (less than 45 degrees) will result in a longer horizontal distance but a lower maximum height.",
            "Gravity:",
            "Gravity plays a fundamental role in projectile motion. It acts vertically downwards and affects the vertical component of motion. Here's how gravity impacts the motion of a projectile:",
            "- Vertical motion: Gravity causes the projectile to accelerate downwards throughout its trajectory. The acceleration due to gravity is typically considered as -9.8 m/s² (negative since it acts downwards). It affects the vertical displacement, maximum height, and time of flight of the projectile.",
            "- Symmetrical path: The influence of gravity makes the path of the projectile symmetrical, forming a parabolic trajectory.",
            "Impact of Changes in Factors:",
            "Let's examine how changes in these factors impact the overall motion of a projectile:",
            "a. Increased initial velocity: Increasing the initial velocity will result in a longer horizontal distance, higher maximum height, and longer time of flight.",
            "b. Decreased initial velocity: Decreasing the initial velocity will result in a shorter horizontal distance, lower maximum height, and shorter time of flight.",
            "c. Higher launch angle: Increasing the launch angle (within a certain range) will result in a higher maximum height but a shorter horizontal distance.",
            "d. Lower launch angle: Decreasing the launch angle (within a certain range) will result in a lower maximum height but a longer horizontal distance.",
            "e. Increased gravity: Increasing the value of gravity will cause the projectile to experience a greater downward acceleration, resulting in a shorter vertical displacement and a shorter time of flight.",
            "Conclusion:",
            "Understanding the factors that impact projectile motion, including initial velocity, launch angle, and gravity, is essential for analyzing and predicting the behavior of projectiles. Changes to these factors can significantly affect the trajectory, range, and maximum height of a projectile. By considering these factors, we can better comprehend and solve problems related to projectile motion. Remember, in real-world scenarios, factors like air resistance would also influence projectile motion."
        };

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Add padding on the left

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Content
        JPanel contentPanel = new JPanel(new GridLayout(paragraphs.length, 1));
        for (String paragraph : paragraphs) {
            JLabel label = new JLabel("<html><p style='margin-bottom: 15px;'>" + paragraph + "</p></html>");
            contentPanel.add(label);
        }

        // Wrap contentPanel inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

}
