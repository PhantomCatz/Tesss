// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TESSSDriveTrain;
public class teleopdrive extends CommandBase {
  public TESSSDriveTrain driveTrain = TESSSDriveTrain.getInstance();
  Supplier<Double> leftStickYSupplier;
  Supplier<Double> rightStickYSupplier;
  /** Creates a new teleopdrive. */

  public teleopdrive(Supplier<Double> leftStickYSupplier, 
                      Supplier<Double> rightStickYSupplier)
  {
    this.leftStickYSupplier = leftStickYSupplier;
    this.rightStickYSupplier = rightStickYSupplier;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftStickPower = leftStickYSupplier.get();
    double rightStickPower = rightStickYSupplier.get();

  
    driveTrain.TESSS_drive.arcadeDrive(leftStickPower, rightStickPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
