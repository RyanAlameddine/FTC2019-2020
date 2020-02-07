package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class ProjectPushbot extends Project
{
    public DcMotor rightMotor = null;
    public DcMotor leftMotor  = null;
    public Servo   leftServoF = null;
    public Servo  rightServoF = null;
    public Servo   leftServoB = null;
    public Servo  rightServoB = null;

    public WebcamName camera = null;

    /* Initialize standard Hardware interfaces */
    @Override
    public void init(HardwareMap ahwMap) {
        //Save reference to Hardware map
        hwMap = ahwMap;

        //Define and Initialize Motors
        rightMotor  = hwMap.dcMotor.get("rightMotor");
        leftMotor   = hwMap.dcMotor.get("leftMotor");
        leftServoF  = hwMap.servo.get("leftServoF");
        rightServoF = hwMap.servo.get("rightServoF");
        leftServoB  = hwMap.servo.get("leftServoB");
        rightServoB = hwMap.servo.get("rightServoB");

        camera = hwMap.get(WebcamName.class, "camera");

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
        leftMotor  .setPower(0);
        rightMotor .setPower(0);
        leftServoF .setPosition(0);
        rightServoF.setPosition(1); //hacks the mainframe
        leftServoB .setPosition(1);
        rightServoB.setPosition(0);
    }
}