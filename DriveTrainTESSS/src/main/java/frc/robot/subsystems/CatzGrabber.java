// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CatzGrabber extends SubsystemBase {
  private static CatzGrabber instance;
  
  private final int SPARK_GRABBER_ID = 3;

  public final PneumaticsModuleType PCM_TYPE = PneumaticsModuleType.CTREPCM;
  private final int GRABBER_PCM_PORT  = 0;

  private final double DEPLOY_POWER = 0.9;
  private final double DEPLOY_TIME = 0.5;

  private final double STOW_POWER = -0.4;
  private final double STOW_TIME = 1.0;

  private boolean isDeployed = false;
  private Solenoid grabberSolenoid;
  private final CANSparkMax sparkGrabber;


  public CatzGrabber() {
    grabberSolenoid = new Solenoid(PCM_TYPE, GRABBER_PCM_PORT);

    sparkGrabber = new CANSparkMax(SPARK_GRABBER_ID, MotorType.kBrushed);
    stowGrabber();
  }
/*
  public void toggleGrabber() {
    deployGrabber();
    System.out.println("Toggle Grabber");
    if (isDeployed == false) {
      deployGrabber();
    } 
    else {
      //stowGrabber();
    }
  }*/
  public void deployGrabber() {
    
    if(isDeployed == false) {
      System.out.println("Deploy Grabber");
      sparkGrabber.set(DEPLOY_POWER);
      Commands.waitSeconds(0.5);
      sparkGrabber.set(0);
      grabberSolenoid.set(true);
      isDeployed = true;
    }
  }
  public void stowGrabber() {
    if(isDeployed == true) {
      System.out.println("Stow Grabber");
      grabberSolenoid.set(false);
      sparkGrabber.set(STOW_POWER);
      Commands.waitSeconds(0.5);
      sparkGrabber.set(0);
      isDeployed = false;
    }
  }
  public static CatzGrabber getInstance()
  {
    if(instance == null)
    {
      instance = new CatzGrabber();
    } 
    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
