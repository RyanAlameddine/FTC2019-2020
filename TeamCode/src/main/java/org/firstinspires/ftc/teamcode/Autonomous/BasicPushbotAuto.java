package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

        RunAuto(p == Path.Red);

    }

    public void RunAuto(boolean red){
        robot.leftMotor .setPower(-.6f);
        robot.rightMotor.setPower(-.6f);
        sleep(956);
        robot.leftMotor .setPower(-.3f);
        robot.rightMotor.setPower(-.3f);
        sleep(500);

        robot.Stop();

        robot.leftServoB.setPosition(0);
        robot.rightServoB.setPosition(1);
        sleep(1200);

        robot.leftMotor .setPower(red ?  1f : .6f);
        robot.rightMotor.setPower(red ? .6f :  1f);
        sleep(1250);

        while(Math.abs(robot.imu.getAngularOrientation().firstAngle) > 20) {
            robot.leftMotor.setPower(!red ? -1 : 1);
            robot.rightMotor.setPower(!red ? 1 : -1);
        }
        //robot.leftMotor .setPower(!red ? -1 : 1);
        //robot.rightMotor.setPower(!red ?  1 : -1);
        //sleep(2000);

        robot.leftMotor .setPower(-.9f);
        robot.rightMotor.setPower(-.9f);

        robot.leftServoB.setPosition(1);
        robot.rightServoB.setPosition(0);
        sleep(1300);

        robot.leftMotor .setPower(.6f);
        robot.rightMotor.setPower(.6f);
        sleep(1000);
    }
}

enum Path{
    Red,
    Blue
}