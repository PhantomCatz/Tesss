// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CatzGrabber extends SubsystemBase {
  private final int SPARK_GRABBER_ID = 3;

  public final PneumaticsModuleType PCM_TYPE = PneumaticsModuleType.CTREPCM;
  private final int GRABBER_PCM_PORT  = 4;

  private final double DEPLOY_POWER = 0.2;
  private final double DEPLOY_TIME = 0.5;

  private final double STOW_POWER = -0.4;
  private final double STOW_TIME = 1.0;

  private boolean isDeployed = false;
  private Solenoid grabberSolenoid;
  private final CANSparkMax sparkGrabber;

  public CatzGrabber() {
    grabberSolenoid = new Solenoid(PCM_TYPE, GRABBER_PCM_PORT);

    sparkGrabber = new CANSparkMax(SPARK_GRABBER_ID, MotorType.kBrushed);
  }

  public void deployGrabber() {
    if (isDeployed == false) {
      sparkGrabber.set(DEPLOY_POWER);
      Timer.delay(DEPLOY_TIME);
      sparkGrabber.set(0);
      grabberSolenoid.set(true);
    }
    isDeployed = true;
  }

  public void stowGrabber() {
    if (isDeployed == true) {
      grabberSolenoid.set(false);
      sparkGrabber.set(STOW_POWER);
      Timer.delay(STOW_TIME);
      sparkGrabber.set(0);
    }
    isDeployed = false;
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
