// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.CatzGrabber;

public class TeleopGrabber extends CommandBase {
  public CatzGrabber grabber = CatzGrabber.getInstance();

  Supplier<Boolean> aButtonSupplier;
  Supplier<Boolean> bButtonSupplier;

  public TeleopGrabber(Supplier<Boolean> aButtonSupplier, Supplier<Boolean> bButtonSupplier) {
    this.aButtonSupplier = aButtonSupplier;
    this.bButtonSupplier = bButtonSupplier;
    
    addRequirements(grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Boolean aTrigger = aButtonSupplier.get();
    Boolean bTrigger = bButtonSupplier.get();

    grabber.deployGrabber(aTrigger);
    grabber.stowGrabber(bTrigger);
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
