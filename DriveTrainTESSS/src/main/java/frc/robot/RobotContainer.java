// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.TeleopGrabber;
import frc.robot.subsystems.TESSSDriveTrain;
import frc.robot.subsystems.CatzGrabber;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static TESSSDriveTrain driveTrain = TESSSDriveTrain.getInstance();
  private static CatzGrabber grabber = CatzGrabber.getInstance();
  private CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.XBOX_DRV_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

  /*
   * Y axis of left joystick and X axis of right joystick are used as values for motor power in
   * calculating speed and rotational speed as parameters of the arcadeDrive from the WPILib
   * DifferentialDrive class
   * These joystick values are used as parameters in the TeleopDrive command
   */

  private void configureBindings() {
    driveTrain.setDefaultCommand(new TeleopDrive(() -> m_driverController.getLeftY(),
                                                 () -> m_driverController.getRightX()));
    m_driverController.a().onTrue(new TeleopGrabber(()-> m_driverController.a().getAsBoolean(), 
                                                    ()-> m_driverController.b().getAsBoolean()));  }
}
