// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TESSSDriveTrain;
public class TeleopDrive extends CommandBase {
  /**
   * Making the instance of the driveTrain and declaring the suppliers
   * Constructor is used to initialize the suppliers
   * Drivetrain instance is made as a requirement for the constructor
   */

  public TESSSDriveTrain driveTrain = TESSSDriveTrain.getInstance();
  Supplier<Double> leftStickYSupplier;
  Supplier<Double> rightStickXSupplier;

  public TeleopDrive(Supplier<Double> leftStickYSupplier, Supplier<Double> rightStickXSupplier)
  {
    this.leftStickYSupplier = leftStickYSupplier;
    this.rightStickXSupplier = rightStickXSupplier;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  /**
   * Getting values from the suppliers and inputting them as parameters for the arcadedrive
   * leftStickPower makes the robot drive forward and backward while the rightStickPower makes
   * the robot turn by calculating how much power one side will drive in comparison to the other
   * 
   * leftStickPower will make both sides of drivetrain spin in the same direction at full power
   * when the left joystick is moved completely up or down on the Y axis
   * 
   * rightStickPower will make sides of drivetrain spin in opposite directions at full power when
   * right the joystick is moved completely to the left or right on the X axis
   */
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftStickPower = leftStickYSupplier.get();
    double rightStickPower = rightStickXSupplier.get();
    driveTrain.CatzDrivetrainSubsystem.arcadeDrive(leftStickPower, rightStickPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
