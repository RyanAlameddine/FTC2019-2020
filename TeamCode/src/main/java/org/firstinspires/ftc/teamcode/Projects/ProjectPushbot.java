package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ProjectPushbot extends Project
{
    public DcMotor rightMotor = null;
    public DcMotor leftMotor  = null;
    public Servo   leftServo  = null;
    public Servo   rightServo = null;

    /* Initialize standard Hardware interfaces */
    @Override
    public void init(HardwareMap ahwMap) {
        //Save reference to Hardware map
        hwMap = ahwMap;

        //Define and Initialize Motors
        rightMotor = hwMap.dcMotor.get("rightMotor");
        leftMotor  = hwMap.dcMotor.get("leftMotor");
        leftServo  = hwMap.servo.get("leftServo");
        rightServo = hwMap.servo.get("rightServo");

        //Setup Motor directions and Encoder settings
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor .setDirection(DcMotor.Direction.REVERSE);

        leftMotor .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set all motors to zero power
        Stop();
    }

    public void Stop(){
        leftMotor .setPower(0);
        rightMotor.setPower(0);
        leftServo .setPosition(1);
        rightServo.setPosition(0);
    }
}