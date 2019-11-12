package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.Projects.MecanumProject;
import org.firstinspires.ftc.teamcode.Projects.Project0;

@Autonomous(name="BasicAuto", group="Mecanum")
public class BasicAuto extends LinearOpMode{
    public MecanumProject robot = new Project0();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        robot.backLeft.setPower(1);
        robot.backRight.setPower(1);
        robot.frontLeft.setPower(1);
        robot.frontRight.setPower(1);

        sleep(200);

        //Reset robot motors to stop when game is finished
        robot.frontLeft .setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft  .setPower(0);
        robot.backRight .setPower(0);
    }
}
