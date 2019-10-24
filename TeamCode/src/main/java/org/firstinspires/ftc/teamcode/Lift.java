
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Lift")

public class Lift extends LinearOpMode {
    DcMotor LiftMotor;
    float LiftFloat = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        LiftMotor = hardwareMap.get(DcMotor.class, "LiftMotor");

        waitForStart();
        while (opModeIsActive()) {
            LiftMotor.setPower(gamepad1.left_stick_y * LiftFloat);
            if (gamepad1.a == true) {
                LiftFloat = 0.5f;
            } else {
                LiftFloat = 1;
            }
        }
        LiftMotor.setPower(0);

    }
}
