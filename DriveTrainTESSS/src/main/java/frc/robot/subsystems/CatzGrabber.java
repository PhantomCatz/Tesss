// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class CatzGrabber extends SubsystemBase {
  private static CatzGrabber instance;
  
  private final int SPARK_GRABBER_ID = 3;

  public final PneumaticsModuleType PCM_TYPE = PneumaticsModuleType.CTREPCM;
  private final int GRABBER_PCM_PORT  = 1;

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

  public void deployGrabber(Boolean aButtonSupplier) {
    /*
     * If press again then it won't perform the action again
     */
    if (isDeployed == false) {
      sparkGrabber.set(DEPLOY_POWER);
      // Commands.waitSeconds(0.5);
      sparkGrabber.set(0);
      grabberSolenoid.set(true);
      System.out.println("g");
    }
    isDeployed = true;
  }

  public void stowGrabber(Boolean bButtonSupplier) {
    if (isDeployed == true) {
      grabberSolenoid.set(false);
      // Commands.waitSeconds(0.5);
      sparkGrabber.set(STOW_POWER);
      sparkGrabber.set(0);
    }
    isDeployed = false;
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
