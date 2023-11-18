// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TESSSDriveTrain extends SubsystemBase {
  private static TESSSDriveTrain instance;

  private Spark DRIVE_MOTOR_LT_BACK;
  private Spark DRIVE_MOTOR_LT_FRONT;
  private Spark DRIVE_MOTOR_RT_BACK;
  private Spark DRIVE_MOTOR_RT_FRONT;

  public final double DEADBAND = 0.04;

  private final int SPARK_LT_BACK_ID = 1;
  private final int SPARK_LT_FRONT_ID = 2;
  private final int SPARK_RT_BACK_ID = 5;
  private final int SPARK_RT_FRONT_ID = 6; 

  public MotorControllerGroup RightMotors;
  public MotorControllerGroup LeftMotors;

  public DifferentialDrive CatzDrivetrainSubsystem;

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

    /*
    * Current limit would normally be set here, but it is not a feature
    * on TESSS due to motor controller type being PWM instead of CAN
    */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static TESSSDriveTrain getInstance()
  {
    if(instance == null)
    {
      instance = new TESSSDriveTrain();
    } 
    return instance;
  }
}
