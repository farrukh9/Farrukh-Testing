package org.usfirst.frc.team5940.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends SampleRobot {
    
	 Victor victor0 = new Victor(0);
	 Victor victor1 = new Victor(1);
	 Victor victor2 = new Victor(2);
	 Victor victor3 = new Victor(3);
   
	 Joystick controller = new Joystick(0);
    
	 public Robot() {
        
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
        
        while (isOperatorControl() && isEnabled()) {
        	
        	float left;
        	float right;
        	
        	left = (float) controller.getRawAxis(1);
        	right = (float) controller.getRawAxis(5);
        	
        	left = -left;
        	right = -right;
        	
        	victor0.set(left);
        	victor2.set(left);
        	
        	victor1.set(right);
        	victor3.set(right);
        	
            Timer.delay(0.005);		// wait for a motor update time
        }
       
    }

}
