package org.usfirst.frc.team5940.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    //RobotDrive myRobot;  // class that handles basic drive operations
    Joystick controller = new Joystick(0);  // set to ID 1 in DriverStation
    Victor victor1 = new Victor(0);
    Victor victor2 = new Victor(1);
    Victor victor3 = new Victor(2);
    Victor victor4 = new Victor(3);
    
    public Robot() {
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
    	SmartDashboard.putNumber("Hello_World", 42);
        //myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	//VAR INIT
        	float leftOut;
        	float rightOut;
        	
        	float leftVIn = (float)controller.getY();
        	float rightHIn = (float)controller.getZ();
        	
        	float invLeftVIn = -leftVIn;
        	float invRightHIn = -rightHIn;
        	
        	//COMPUPTING
        	//Set motors to forward speed
        	leftOut = invLeftVIn;
        	rightOut = invLeftVIn;
        	
        	//Modify for turning
        	leftOut -= invRightHIn;
        	rightOut += invRightHIn;
        	
        	//Output data
        	SmartDashboard.putNumber("Pre Calc Left", leftOut);
        	SmartDashboard.putNumber("Pre Calc Right", rightOut);
        	
        	//See if either is more than 1 or less then -1
        	boolean aEA1 = getAbsEitherAbove1(leftOut, rightOut);
        	
        	//If it is
        	if (aEA1) {
        		//Get the necessary scale factor
	        	float scale = getAbsHighest(leftOut, rightOut);
	        	
	        	//Scale them
	        	leftOut /= scale;
	        	rightOut /= scale;
	        	
	        	//Output scale
	        	SmartDashboard.putNumber("Scale Factor", scale);
        	}
        	
        	//Output data
        	SmartDashboard.putBoolean("Abs Either A One", aEA1);
        	SmartDashboard.putNumber("Post Calc Left", leftOut);
        	SmartDashboard.putNumber("Post Calc Right", rightOut);
        	
        	//OUTPUT
        	//Invert motor set
        	//leftOut = -leftOut;
        	rightOut = -rightOut;
        	
        	//Write PWM
        	victor1.set(leftOut);
        	victor3.set(leftOut);
        	
        	victor2.set(rightOut);
        	victor4.set(rightOut);
        	
        	/*//myRobot.tankDrive(leftStick, rightStick);
        	//myRobot.arcadeDrive(leftStick);
        	//victor1.setRaw(255);
        	//myRobot.
        	
        	float left = 0;
        	float right = 0;
        	
        	left = (float)controller.getY();
        	right = (float)controller.getThrottle();
        	
        	left = -left;
        	right = -right;
        	SmartDashboard.putNumber("Left", left);
        	SmartDashboard.putNumber("Right", right);
        	
        	left = (float) Math.pow(left, 3);
        	right = (float) Math.pow(right, 3);
        	
        	left = left;
        	right = -right;
        	
        	victor1.set(left);
        	victor3.set(left);
        	
        	victor2.set(right);
        	victor4.set(right);
        	
        	SmartDashboard.putNumber("Controller data 1", controller.getX());
        	SmartDashboard.putNumber("Controller data 2", controller.getY());
        	SmartDashboard.putNumber("Controller data 3", controller.getZ());
        	SmartDashboard.putNumber("Controller data 4", controller.getThrottle());*/
            Timer.delay(0.005);		// wait for a motor update time
        }
    }
    
    float getAbsHighest(float in1, float in2) {
    	in1 = Math.abs(in1);
    	in2 = Math.abs(in2);
    	if(in1 > in2) return in1;
    	return in2;
    }

    boolean getAbsEitherAbove1(float in1, float in2) {
    	in1 = Math.abs(in1);
    	in2 = Math.abs(in2);
    	if(in1 > 1 || in2 > 1) return true;
    	return false;
    }
}