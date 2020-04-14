package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
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

    public ColorSensor colorSensor = null;

    public BNO055IMU imu = null;

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
        colorSensor = hwMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(false);
        loadIMU();
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

    private void loadIMU(){
        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }
}