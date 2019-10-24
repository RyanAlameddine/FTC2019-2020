package org.firstinspires.ftc.teamcode.Projects;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Simple Mecanum drive configuration
 *
 * Created September 21, 2017 by Ryan Alameddine
 *
 *
 * @author Ryan Alameddine
 *
 * @version 4.0
 */

public class Project0
{
    /* Public OpMode members. */
    public DcMotor  frontRight = null;
    public DcMotor  frontLeft  = null;
    public DcMotor  backRight  = null;
    public DcMotor  backLeft   = null;

    public DcMotor liftMotor   = null;

    //private DcMotor armRight = null;
    //private DcMotor armLeft  = null;

    /* DiServo Claw servoes */
    //private Servo clawRight = null;
    //private Servo clawLeft  = null;

    //public Servo JewelArmServo = null;

    //public ColorSensor colorSensor = null;
    //public BNO055IMU imu = null;

    /* local OpMode members. */
    HardwareMap         hwMap   = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        //Save reference to Hardware map
        hwMap = ahwMap;

        //Define and Initialize Motors
        frontRight = hwMap.dcMotor.get("frontRight");
        frontLeft  = hwMap.dcMotor.get("frontLeft");
        backRight  = hwMap.dcMotor.get("backRight");
        backLeft   = hwMap.dcMotor.get("backLeft");
        liftMotor = hwMap.dcMotor.get("liftMotor");

        //armRight   = hwMap.dcMotor.get("armRight");
        //armLeft    = hwMap.dcMotor.get("armLeft");

        //Define and Initialize Servos
        //clawRight = hwMap.servo.get("clawRight");
        //clawLeft  = hwMap.servo.get("clawLeft");

        //JewelArmServo = hwMap.servo.get("jewelArmServo");

        //colorSensor = hwMap.colorSensor.get("colorSensor");

        //Define and Initialize Sensors
        //loadIMU();

        //Setup Motor directions and Encoder settings
        frontLeft .setDirection(DcMotor.Direction.REVERSE);
        backLeft  .setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight .setDirection(DcMotor.Direction.FORWARD);
        //armRight  .setDirection(DcMotor.Direction.REVERSE);
        //armLeft   .setDirection(DcMotor.Direction.FORWARD);

        frontLeft .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft  .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //armRight  .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //armLeft   .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //armRight  .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //armLeft   .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set all motors to zero power
        frontLeft .setPower(0);
        frontRight.setPower(0);
        backLeft  .setPower(0);
        backRight .setPower(0);

        //armRight  .setPower(0);
        //armLeft   .setPower(0);
    }


    /**
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    /*void loadIMU(){
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
        //imu = hwMap.get(BNO055IMU.class, "imu");
        //imu.initialize(parameters);
    }*/
}