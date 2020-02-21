package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ProjectLift extends MecanumProject
{
    public DcMotor liftMotor = null;
    public DcMotor leftIntake = null;
    public DcMotor rightIntake = null;

    public Servo clawServo = null;
    public CRServo slideServo = null;

    public Servo leftPlatform = null;
    public Servo rightPlatform = null;

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

        liftMotor  = hwMap.dcMotor.get("liftMotor");
        leftIntake = hwMap.dcMotor.get("leftIntake");
        rightIntake= hwMap.dcMotor.get("rightIntake");
        slideServo = hwMap.crservo.get("slideServo");

        clawServo    = hwMap.servo.get("clawServo");
        leftPlatform = hwMap.servo.get("leftPlatform");
        rightPlatform= hwMap.servo.get("rightPlatform");

        //Setup Motor directions and Encoder settings
        frontLeft .setDirection(DcMotor.Direction.REVERSE);
        backLeft  .setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight .setDirection(DcMotor.Direction.FORWARD);


        frontLeft .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft  .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftMotor .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftIntake .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftMotor .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set all motors to zero power
        Stop();
    }
    public void Stop(){
        frontLeft .setPower(0);
        frontRight.setPower(0);
        backLeft  .setPower(0);
        backRight .setPower(0);

        liftMotor .setPower(0);
        rightIntake.setPower(0);
        leftIntake.setPower(0);
    }
}