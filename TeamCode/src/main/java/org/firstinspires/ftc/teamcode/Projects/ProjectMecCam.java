package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class ProjectMecCam extends MecanumProject
{
    public WebcamName webcam;

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

        webcam = hwMap.get(WebcamName.class, "webcam");

        //Setup Motor directions and Encoder settings
        frontLeft .setDirection(DcMotor.Direction.REVERSE);
        backLeft  .setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight .setDirection(DcMotor.Direction.FORWARD);

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