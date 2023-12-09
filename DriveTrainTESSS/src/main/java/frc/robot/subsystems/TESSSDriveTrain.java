// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TESSSDriveTrain extends SubsystemBase {
  /**
   * Declaring motor controllers, PWM motor port ids, and motor controller groups
   * for arcade drive using WPILIB DifferentialDrive class
   */

  private static TESSSDriveTrain instance;

  private Spark DRIVE_MOTOR_LT_BACK;
  private Spark DRIVE_MOTOR_LT_FRONT;
  private Spark DRIVE_MOTOR_RT_BACK;
  private Spark DRIVE_MOTOR_RT_FRONT;

  private final double DEADBAND = 0.04;

  /**
   * Current limit would normally be declared here as a variable, but it is not a
   * feature on TESSS due to the motor controller type being PWM not CAN
   */

  private final int SPARK_LT_BACK_ID = 2;
  private final int SPARK_LT_FRONT_ID = 3;
  private final int SPARK_RT_BACK_ID = 6;
  private final int SPARK_RT_FRONT_ID = 5; 

  private MotorControllerGroup LeftMotors;
  private MotorControllerGroup RightMotors;

  public DifferentialDrive CatzDrivetrainSubsystem;

  /**
   * Instantiating the motors, motorcontroller groups, and the differentialdrive
   * Also setting deadband
   */
  
  private TESSSDriveTrain() {
    DRIVE_MOTOR_LT_BACK = new Spark(SPARK_LT_BACK_ID);
    DRIVE_MOTOR_LT_FRONT = new Spark(SPARK_LT_FRONT_ID);
    DRIVE_MOTOR_RT_BACK = new Spark(SPARK_RT_BACK_ID);
    DRIVE_MOTOR_RT_FRONT = new Spark(SPARK_RT_FRONT_ID);

    DRIVE_MOTOR_LT_FRONT.setInverted(true);
    DRIVE_MOTOR_LT_BACK.setInverted(true);

    LeftMotors = new MotorControllerGroup(DRIVE_MOTOR_LT_BACK, DRIVE_MOTOR_LT_FRONT);
    RightMotors = new MotorControllerGroup(DRIVE_MOTOR_RT_BACK, DRIVE_MOTOR_RT_FRONT);

    CatzDrivetrainSubsystem = new DifferentialDrive(LeftMotors, RightMotors);
    CatzDrivetrainSubsystem.setDeadband(DEADBAND);

    /**
    * Current limit would normally be set here, but it is not a feature
    * on TESSS due to the motor controller type being PWM instead of CAN.
    */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Method is made to ensure that there can only be one instance of the drivetrain.
   * This prevents the issue of having multiple instances of the drivetrain that are
   * trying to perform different actions at the same time.
   */

  public static TESSSDriveTrain getInstance()
  {
    if(instance == null)
    {
      instance = new TESSSDriveTrain();
    } 
    return instance;
  }
}
