package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Projects.ProjectPushbot;

@TeleOp(name="PushbotDrive", group="Mecanum")
public class PushbotDrive extends LinearOpMode{
    private ProjectPushbot robot = new ProjectPushbot();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        boolean backRaised  = true;
        boolean frontRaised = true;
        boolean yPressed    = false;
        boolean aPressed    = false;
        boolean bPressed    = false;

        float speedMultiplier = 1;
        float speedMultiplier2 = 1;
        while(opModeIsActive()) {
            if (gamepad1.b){
                if(bPressed == false){
                    bPressed = true;
                    if(speedMultiplier == 1){
                        speedMultiplier = .5f;
                    }
                    else{
                        speedMultiplier = 1;
                    }
                }
            }
            else {
                bPressed = false;
            }
            speedMultiplier2 = .5f + gamepad1.right_trigger/2f;

            robot.leftMotor .setPower(-gamepad1.left_stick_y * speedMultiplier * speedMultiplier2);
            robot.rightMotor.setPower(-gamepad1.right_stick_y* speedMultiplier * speedMultiplier2);

            if (gamepad1.y){
                if(yPressed == false){
                    yPressed = true;
                    backRaised = !backRaised;
                    if(backRaised == true){
                        robot.leftServoF.setPosition(0);
                        robot.rightServoF.setPosition(1);
                    }else{
                        robot.leftServoF.setPosition(1);
                        robot.rightServoF.setPosition(0);
                    }
                }
            }
            else {
                yPressed = false;
            }

            if (gamepad1.a){
                if(aPressed == false){
                    aPressed = true;
                    frontRaised = !frontRaised;
                    if(frontRaised == true){
                        robot.leftServoB.setPosition(1);
                        robot.rightServoB.setPosition(0);
                    }else{
                        robot.leftServoB.setPosition(0);
                        robot.rightServoB.setPosition(1);
                    }
                }
            }
            else {
                aPressed = false;
            }
        }

        //Reset robot motors to stop when game is finished
        robot.Stop();
    }
}
