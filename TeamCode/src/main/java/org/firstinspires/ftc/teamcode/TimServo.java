package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TimServo")

public class TimServo extends LinearOpMode {
    Servo claw;


    public void runOpMode() throws InterruptedException {
        claw = hardwareMap.servo.get("claw");


        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        waitForStart();
        while(opModeIsActive())    {
            tgtPower = -this.gamepad1.left_stick_y;
            // check to see if we need to move the servo.
            if (gamepad1.y) {
                // move to 0 degrees.
                claw.setPosition(0);
            } else if (gamepad1.x || gamepad1.b) {
                // move to 90 degrees.
                claw.setPosition(0.5);
            } else if (gamepad1.a) {
                // move to 180 degrees.
                claw.setPosition(0.725);
            }
            telemetry.addData("Servo Position", claw.getPosition());
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
