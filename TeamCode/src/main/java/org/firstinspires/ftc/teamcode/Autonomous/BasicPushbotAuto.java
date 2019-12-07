package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Projects.MecanumProject;
import org.firstinspires.ftc.teamcode.Projects.Project0;
import org.firstinspires.ftc.teamcode.Projects.ProjectPushbot;

@Autonomous(name="BasicPushbotAuto", group="Mecanum")
public class BasicPushbotAuto extends LinearOpMode{
    public ProjectPushbot robot = new ProjectPushbot();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        robot.rightMotor.setPower(.4f);
        robot.leftMotor .setPower(.4f);
        sleep(1500);

    }
}
