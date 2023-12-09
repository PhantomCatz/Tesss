// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CatzGrabber;

public class TeleopGrabber extends CommandBase {
  public CatzGrabber grabber = CatzGrabber.getInstance();
  Supplier<Boolean> abuttonSupplier;
  Supplier<Boolean> bbuttonSupplier;

  public TeleopGrabber(Supplier<Boolean> abuttonSupplier , Supplier<Boolean> bbuttonSupplier) {
    this.abuttonSupplier = abuttonSupplier;
    this.bbuttonSupplier = bbuttonSupplier;
    addRequirements(grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(abuttonSupplier.get() == true) {
      grabber.deployGrabber();
    }
    else if(bbuttonSupplier.get() == true) {
      grabber.stowGrabber();
    }

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
