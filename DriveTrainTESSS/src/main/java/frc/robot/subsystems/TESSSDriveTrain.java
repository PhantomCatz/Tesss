// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TESSSDriveTrain extends SubsystemBase {
  public static TESSSDriveTrain instance;

  Spark sparkLtBack;
  Spark sparkLtFront;
  Spark sparkRtBack;
  Spark sparkRtFront;

  public final int SPARK_LT_BACK_ID = 2;
  public final int SPARK_LT_FRONT_ID = 1;
  public final int SPARK_RT_BACK_ID = 6;
  public final int SPARK_RT_FRONT_ID = 5; 

  public MotorControllerGroup LeftMotors;
  public MotorControllerGroup RightMotors;

  public DifferentialDrive TESSS_drive;

  private TESSSDriveTrain() {
    sparkLtBack = new Spark(SPARK_LT_BACK_ID);
    sparkLtFront = new Spark(SPARK_LT_FRONT_ID);
    sparkRtBack = new Spark(SPARK_RT_BACK_ID);
    sparkRtFront = new Spark(SPARK_RT_FRONT_ID);

    sparkLtFront.setInverted(true);
    sparkLtBack.setInverted(true);

    LeftMotors = new MotorControllerGroup(sparkLtBack, sparkLtFront);
    RightMotors = new MotorControllerGroup(sparkRtBack, sparkRtFront);

    TESSS_drive = new DifferentialDrive(LeftMotors, RightMotors);
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
