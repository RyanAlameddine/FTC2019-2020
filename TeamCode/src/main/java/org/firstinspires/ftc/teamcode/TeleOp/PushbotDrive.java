package org.firstinspires.ftc.teamcode.TeleOp;

import android.widget.ToggleButton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.teamcode.MathF;
import org.firstinspires.ftc.teamcode.Projects.Project0;
import org.firstinspires.ftc.teamcode.Projects.ProjectPushbot;

@TeleOp(name="PushbotDrive", group="Mecanum")
public class PushbotDrive extends LinearOpMode{
    private ProjectPushbot robot = new ProjectPushbot();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        waitForStart();

        boolean raised = true;
        boolean bPressed = false;

        boolean aPressed = false;
        float speedMultiplier = 1;
        while(opModeIsActive()) {
            if (gamepad1.a){
                if(aPressed == false){
                    aPressed = true;
                    if(speedMultiplier == 1){
                        speedMultiplier = .5f;
                    }
                    else{
                        speedMultiplier = 1;
                    }
                }
            }
            else {
                aPressed = false;
            }

            robot.leftMotor .setPower(-gamepad1.left_stick_y * speedMultiplier);
            robot.rightMotor.setPower(-gamepad1.right_stick_y* speedMultiplier);

            if (gamepad1.b){
                if(bPressed == false){
                    bPressed = true;
                    raised = !raised;
                    if(raised == true){
                        robot.leftServo .setPosition(0);
                        robot.rightServo.setPosition(1);
                    }else{
                        robot.leftServo .setPosition(1);
                        robot.rightServo.setPosition(0);
                    }
                }
            }
            else {
                bPressed = false;
            }


        }

        //Reset robot motors to stop when game is finished
        robot.Stop();
    }
}
