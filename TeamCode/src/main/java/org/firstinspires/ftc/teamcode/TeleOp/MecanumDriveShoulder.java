package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.Projects.Project1;

@TeleOp(name="MecanumDriveShoulder", group="Mecanum")
public class MecanumDriveShoulder extends LinearOpMode{
    private Project1 robot = new Project1();

    /* Setting variables */

    //Speed multiplier. The higher it is, the more likely to clip at high speeds because motor max is 1
    private float speedMultiplier = 1;

    //Speed multiplier when slow mode is active
    private float slowModeMultiplier = .4f;

    /* Calculation variables DO NOT CHANGE */

    //Robot controls:
    private float speed       = 0;
    private float angle       = 0;
    private float direction   = 0;
    private VectorF vectorF   = null;
    private boolean isTurning = false;
    private float rotation    = 0;

    //Individual motor powers:
    float frontLeft   = 0;
    float frontRight  = 0;
    float backLeft    = 0;
    float backRight   = 0;

    //Highest motor power: (used for clipping high speeds above 1)
    private float greatestNum = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {

            if(gamepad1.dpad_down) {
                robot.shoulderLeft.setPower(1);
                robot.shoulderRight.setPower(1);
            } else if(gamepad1.dpad_down) {
                robot.shoulderLeft.setPower(-1);
                robot.shoulderRight.setPower(-1);
            }else{
                robot.shoulderRight.setPower(0);
                robot.shoulderLeft.setPower(0);
            }
        }

        //Reset robot motors to stop when game is finished
        robot.shoulderRight.setPower(0);
        robot.shoulderLeft.setPower(0);
    }
}
