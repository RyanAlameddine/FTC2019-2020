package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Project1 extends Project
{
    public DcMotor shoulderLeft;
    public DcMotor shoulderRight;

    /* Initialize standard Hardware interfaces */
    @Override
    public void init(HardwareMap ahwMap) {
        //Save reference to Hardware map
        hwMap = ahwMap;

        shoulderLeft  = hwMap.dcMotor.get("shoulderLeft");
        shoulderRight = hwMap.dcMotor.get("shoulderRight");
        shoulderLeft .setDirection(DcMotor.Direction.FORWARD);
        shoulderRight.setDirection(DcMotor.Direction.REVERSE);

        shoulderLeft .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoulderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shoulderLeft .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set all motors to zero power

        shoulderLeft .setPower(0);
        shoulderRight.setPower(0);
    }
}