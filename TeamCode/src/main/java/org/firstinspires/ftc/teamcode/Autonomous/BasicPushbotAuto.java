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

        Path p = Path.Blue;


        while(!isStarted()){
            if(gamepad1.b){
                p = Path.Red;
            }
            if(gamepad1.x){
                p = Path.Blue;
            }
            telemetry.addData("Path:", p);
            telemetry.update();
        }
        //waitForStart();

        //robot.rightMotor.setPower(.4f);
        //robot.leftMotor .setPower(.4f);
        //sleep(1500);

        RunAuto(p == Path.Blue);

    }

    public void RunAuto(boolean blue){
        robot.leftMotor .setPower(.6f);
        robot.rightMotor.setPower(.6f);
        sleep(1700);
        robot.Stop();

        robot.leftServo .setPosition(0);
        robot.rightServo.setPosition(1);
        sleep(1000);

        robot.leftMotor .setPower(blue ?  -1f : -.5f);
        robot.rightMotor.setPower(blue ? -.5f :  -1f);
        sleep(1900);

        robot.leftMotor .setPower(blue ? -1 : 1);
        robot.rightMotor.setPower(blue ?  1 : -1);
        sleep(2000);

        for(int i = 0; i < 2; i++) {
            robot.leftMotor .setPower(-.8f);
            robot.rightMotor.setPower(-.8f);
            sleep(500);

            robot.leftMotor .setPower(blue ? -1 : 1);
            robot.rightMotor.setPower(blue ? 1 : -1);
            sleep(2000);

            robot.leftMotor .setPower(.75f);
            robot.rightMotor.setPower(.75f);
            sleep(500);
        }

        robot.leftMotor .setPower(.9f);
        robot.rightMotor.setPower(.9f);

        robot.leftServo .setPosition(1);
        robot.rightServo.setPosition(0);
        sleep(1000);

        robot.leftMotor .setPower(-.6f);
        robot.rightMotor.setPower(-.6f);
        sleep(1800);
    }
}

enum Path{
    Red,
    Blue
}