package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Project0 extends MecanumProject
{
    /* Initialize standard Hardware interfaces */
    @Override
    public void init(HardwareMap ahwMap) {
        //Save reference to Hardware map
        hwMap = ahwMap;

        //Define and Initialize Motors
        frontRight = hwMap.dcMotor.get("frontRight");
        frontLeft  = hwMap.dcMotor.get("frontLeft");
        backRight  = hwMap.dcMotor.get("backRight");
        backLeft   = hwMap.dcMotor.get("backLeft");

        //Setup Motor directions and Encoder settings
        frontLeft .setDirection(DcMotor.Direction.FORWARD);
        backLeft  .setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight .setDirection(DcMotor.Direction.REVERSE);

        frontLeft .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft  .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set all motors to zero power
        frontLeft .setPower(0);
        frontRight.setPower(0);
        backLeft  .setPower(0);
        backRight .setPower(0);
    }
}