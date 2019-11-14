package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.MathF;
import org.firstinspires.ftc.teamcode.Projects.Project0;

@TeleOp(name="MecanumDrive", group="Mecanum")
public class MecanumDrive extends LinearOpMode{
    private Project0 robot = new Project0();

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
            //region Mecanum Drive math and controls
            //Get the 2 dimensional vector of the direction of left stick and rotation based on right stick
            vectorF    = new VectorF(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            speed      = vectorF.magnitude();
            vectorF    = new VectorF(vectorF.get(0) / speed, vectorF.get(1) / speed);
            angle      = (float) Math.atan2(vectorF.get(0), vectorF.get(1));
            direction  = gamepad1.right_stick_x;

            //Apply mathematical operations to find speeds of each motor
            frontLeft  = (float) (speed * Math.sin(angle + Math.PI / 4) + direction) * speedMultiplier;
            frontRight = (float) (speed * Math.cos(angle + Math.PI / 4) - direction) * speedMultiplier;
            backLeft   = (float) (speed * Math.cos(angle + Math.PI / 4) + direction) * speedMultiplier;
            backRight  = (float) (speed * Math.sin(angle + Math.PI / 4) - direction) * speedMultiplier;

            //Make sure that speed never exceeds 1. If so, divide by largest
            greatestNum = Math.abs(frontLeft);
            if (Math.abs(frontRight) > greatestNum) {
                greatestNum = Math.abs(frontRight);
            }
            if (Math.abs(backLeft)   > greatestNum) {
                greatestNum = Math.abs(backLeft);
            }
            if (Math.abs(backRight)  > greatestNum) {
                greatestNum = Math.abs(backRight);
            }

            if (greatestNum > 1) {
                frontLeft  /= greatestNum;
                frontRight /= greatestNum;
                backLeft   /= greatestNum;
                backRight  /= greatestNum;
            }
            //endregion

            robot.frontLeft .setPower(Float.isNaN(frontLeft)  ? 0 : frontLeft  * (gamepad1.left_trigger < .8 ? 1 : slowModeMultiplier));
            robot.frontRight.setPower(Float.isNaN(frontRight) ? 0 : frontRight * (gamepad1.left_trigger < .8 ? 1 : slowModeMultiplier));
            robot.backLeft  .setPower(Float.isNaN(backLeft)   ? 0 : backLeft   * (gamepad1.left_trigger < .8 ? 1 : slowModeMultiplier));
            robot.backRight .setPower(Float.isNaN(backRight)  ? 0 : backRight  * (gamepad1.left_trigger < .8 ? 1 : slowModeMultiplier));
        }

        //Reset robot motors to stop when game is finished
        robot.frontLeft .setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft  .setPower(0);
        robot.backRight .setPower(0);
    }
}
