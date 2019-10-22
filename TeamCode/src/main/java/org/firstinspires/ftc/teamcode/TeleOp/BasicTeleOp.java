package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Projects.Project0;

@TeleOp(name="Basic TeleOp")
public class BasicTeleOp extends LinearOpMode {
    Project0 robot = new Project0();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        //Servo claw =  hardwareMap.servo.get("claw");
        //Servo claw = null;
        waitForStart();

        while(opModeIsActive()) {
            if(Math.abs(gamepad1.right_stick_y) < .1){
                robot.liftMotor.setTargetPosition(robot.liftMotor.getCurrentPosition());
                telemetry.addData("Locked", true);
                robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.liftMotor.setPower(1);

            }else {
                robot.liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                robot.liftMotor.setPower(gamepad1.right_stick_y * .6f);
                telemetry.addData("Locked", false);

            }
            telemetry.update();
        }

        robot.StopMotors();

    }
}
