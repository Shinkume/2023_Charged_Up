/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;
//this is a test change
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
//import frc.robot.commands.Intake.shoot;
//import java.lang.Math;
//import frc.robot.OI; unused
import frc.robot.commands.autonomous.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveTrain Drive; // could be redundant , if we delete drivetrain get rid of this
  public static AprilTagVision Cameras; // used for helping line up bot thru apriltags use 16h5
  public static OI m_oi;

  public Command m_autonomousCommand;
  public SendableChooser<Command> m_chooser;
  

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.42
   */

  public void robotInit() {
    RobotMap.init();
    
    Drive = new DriveTrain();
    Cameras = new AprilTagVision();
    // Cameras = new Vision();
    m_oi = new OI();
    // may use again later

    // m_chooser = new SendableChooser<Command>();
    // m_chooser.setDefaultOption("auto1", new ParallelCommandGroup(
    //   new dropShooter(), new scuffedCommand()
    // )
    
    // );
    // m_chooser.addOption("breakStartLine", new breakStartLine());
    // m_chooser.addOption("auto2", new auto2());
    // SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    SmartDashboard.updateValues();
    CommandScheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    System.out.println(SmartDashboard.getKeys());
    m_autonomousCommand = m_chooser.getSelected();
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  //comment
  @Override
  public void autonomousPeriodic() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    CommandScheduler.getInstance().run();
    m_autonomousCommand = null;
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    SmartDashboard.putData("Encoder", m_chooser);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    //}
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
