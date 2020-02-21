package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.Projects.Project0;
import org.firstinspires.ftc.teamcode.Projects.ProjectLift;

@TeleOp(name="LiftBotDrive", group="Mecanum")
public class LiftBotDrive extends LinearOpMode{
    private ProjectLift robot = new ProjectLift();

    /* Setting variables */

    //Speed multiplier. The higher it is, the more likely to clip at high speeds because motor max is 1
    private float speedMultiplier = 1;

    private int initialState = 0;
    boolean xPressed = false;
    boolean yPressed = false;
    boolean yRaised = false;

    //Speed multiplier when slow mode is active
    boolean slowMode = false;
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
        initialState = robot.liftMotor.getCurrentPosition();
        waitForStart();

        boolean aPressed = false;
        boolean raised = false;
        while(opModeIsActive()) {
            if (gamepad2.a){
                if(aPressed == false){
                    aPressed = true;
                    raised = !raised;
                    if(raised){
                        robot.clawServo.setPosition(0);
                    }
                    else{
                        robot.clawServo.setPosition(1);
                    }
                }
            }
            else {
                aPressed = false;
            }

            if (gamepad1.y){
                if(yPressed == false){
                    yPressed = true;
                    yRaised = !yRaised;
                    if(yRaised){
                        robot.leftPlatform.setPosition(1);
                        robot.rightPlatform.setPosition(0);
                    }
                    else{
                        robot.leftPlatform.setPosition(0);
                        robot.rightPlatform.setPosition(1);
                    }
                }
            }
            else {
                aPressed = false;
            }

            if(gamepad2.dpad_up){
                robot.leftIntake.setPower(1);
                robot.rightIntake.setPower(-1);
            }else if(gamepad2.dpad_down){
                robot.leftIntake.setPower(-1);
                robot.rightIntake.setPower(1);
            }else{
                robot.leftIntake.setPower(0);
                robot.rightIntake.setPower(0);
            }

            robot.slideServo.setPower(gamepad2.right_stick_y);
            if(robot.liftMotor.getCurrentPosition() < -10000 + initialState && -gamepad2.left_stick_y < 0){
                robot.slideServo.setPower(0);
            }else {
                robot.liftMotor.setPower(-gamepad2.left_stick_y);
            }

            telemetry.addData("lift", robot.liftMotor.getCurrentPosition());
            telemetry.addData("lift", initialState);

            telemetry.update();

            /*if(gamepad1.a){
                robot.leftIntake.setPower(1);
                robot.rightIntake.setPower(-1);
            }else if(gamepad1.y){
                robot.leftIntake.setPower(-1);
                robot.rightIntake.setPower(1);
            }else{
                robot.leftIntake.setPower(0);
                robot.rightIntake.setPower(0);
            }*/


            //robot.clawServo.setPosition(gamepad1.right_trigger);

            //slow mode toggle
            if(gamepad1.x && !xPressed){
                slowMode = !slowMode;
                xPressed = true;
            }

            if(!gamepad1.x){
                xPressed = false;
            }

            //region Mecanum Drive math and controls
            //Get the 2 dimensional vector of the direction of left stick and rotation based on right stick
            vectorF    = new VectorF(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            speed      = vectorF.magnitude();
            vectorF    = new VectorF(vectorF.get(0) / speed, vectorF.get(1) / speed);
            angle      = (float) Math.atan2(vectorF.get(0), vectorF.get(1));
            direction  = -gamepad1.right_stick_x;

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
