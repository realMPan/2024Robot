// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Actions.Drivetrain.MoveToAprilTag;
import frc.robot.commands.Actions.Drivetrain.TimedDrive;
import frc.robot.commands.Actions.EndEffector.ArmToPosition;
import frc.robot.commands.Actions.EndEffector.IntakeToVelocity;
import frc.robot.utility.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoPiece extends SequentialCommandGroup {
  /** Creates a new TwoPiece. */
  public TwoPiece() {
    Alliance team = DriverStation.getAlliance().get();
    MoveToAprilTag command;
    if (team == Alliance.Blue){
      command = new MoveToAprilTag(Constants.AprilTagData.Blue.Amp.ID);
    }else{
      command = new MoveToAprilTag(Constants.AprilTagData.Red.Amp.ID);
      
    }
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    command, 
    new ArmToPosition(0), 
    new TimedDrive(0, 0, 0, 90),
    new ArmToPosition(0),
    new IntakeToVelocity(0),
    new ArmToPosition(0).alongWith(command)
    );
  }
}
