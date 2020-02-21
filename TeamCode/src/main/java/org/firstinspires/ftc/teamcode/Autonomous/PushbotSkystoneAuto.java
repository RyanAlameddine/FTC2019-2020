package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Projects.ProjectMecCam;
import org.firstinspires.ftc.teamcode.Projects.ProjectPushbot;

@Autonomous(name="PushbotSkystoneAuto", group="Mecanum")
public class PushbotSkystoneAuto extends LinearOpMode{
    public ProjectPushbot robot = new ProjectPushbot();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);

        robot.leftMotor .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftMotor.setTargetPosition(600);
        robot.rightMotor.setTargetPosition(600);
        robot.leftMotor .setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AclOCKX/////AAABmVMcwvR8lE0inBkpYnUkWaw7ay7BC7I7cnlDQhYSBmWArpdTIiM8c2VKv7t8NMx+gJbqipUTvEY5njhhVXdcTDJOg6RmtybMYHMbDBevdAGg+SIPSeBOeXi5pJd6xlDDPu1JbSPQRyEZKZmWXxoIkht2JZD0DC6QxdGJUxVi0n0FD0dLVRAuWSr+ROLsi13GA126OzIJvm6Ego5RXeEAsM/RPk3mUH40iSeXU6mum+LzdV/yl8u0VC2YeHMH6GNKWGa0Ux+DB5Fp2f8Mjnz+xnuXaralDWBhR2r3DzKE979cNXeCpY9WtmRatRKWdhpOSm3QwLfAowIeZOZ6tH8EU8q4qN61rzLpzd/A6rR4QLp5";
        parameters.cameraName = robot.camera;

        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);
        VuforiaTrackables trackables = vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable trackable = trackables.get(0);
        trackable.setName("Skystone");

        trackables.activate();

        robot.leftServoF.setPosition(1);
        robot.rightServoF.setPosition(0);
        waitForStart();


        robot.leftMotor.setPower(.7);
        robot.rightMotor.setPower(.7);


        WaitTillTargetReached(50, true);
        telemetry.addData("hi", "you got here");
        telemetry.update();
        OpenGLMatrix location = null;
        robot.leftMotor.setTargetPosition(getL()+200);
        robot.rightMotor.setTargetPosition(getR()+200);
        while(location == null){
            location = ((VuforiaTrackableDefaultListener) trackables.get(0).getListener()).getUpdatedRobotLocation();
            if(location != null) {
                telemetry.addData("Location", location.getTranslation());
            }
            telemetry.update();
        }

        WaitTillTargetReached(50, true);
        robot.leftMotor.setPower(.7);
        robot.rightMotor.setPower(.7);
        /*OpenGLMatrix location = ((VuforiaTrackableDefaultListener) trackables.get(0).getListener()).getUpdatedRobotLocation();
        while(location == null){
            location = ((VuforiaTrackableDefaultListener) trackables.get(0).getListener()).getUpdatedRobotLocation();
        }*/

        float x = location.getTranslation().get(0); //gets 0
        telemetry.addData("x", x);
        telemetry.update();

        int turnAmount = 0;

        if (x<-130){
            robot.leftMotor.setTargetPosition(140 + getL());
            robot.rightMotor.setTargetPosition(getR() - 140);
            WaitTillTargetReached(50, true);
            turnAmount = 140 + 25;


        }
        else if(x>130){
            robot.rightMotor.setTargetPosition(getR() + 153);
            robot.leftMotor.setTargetPosition(getL() - 153);
            WaitTillTargetReached(50, true);
            turnAmount = -153 - 25;
        }

        robot.rightMotor.setTargetPosition(getR() + 1254);
        robot.leftMotor.setTargetPosition(getL() + 1254);
        WaitTillTargetReached(50, true);

        robot.rightMotor.setPower(.5);
        robot.leftMotor.setPower(.5);
        robot.rightMotor.setTargetPosition(getR() + 483);
        robot.leftMotor.setTargetPosition(getL() + 483);

        robot.rightMotor.setPower(.7);
        robot.leftMotor.setPower(.7);

        WaitTillTargetReached(50, true);

        robot.rightServoF.setPosition(1);
        robot.leftServoF.setPosition(0);
        sleep(876);

        robot.leftMotor.setTargetPosition(getL() - 1182);
        robot.rightMotor.setTargetPosition(getR() - 1182);




        WaitTillTargetReached(50, true);

        robot.leftMotor.setTargetPosition(getL() - turnAmount);
        robot.rightMotor.setTargetPosition((getR() + turnAmount));

        WaitTillTargetReached(50, true);
        //Entering transition state

        robot.leftMotor.setTargetPosition(getL() - 428);
        robot.rightMotor.setTargetPosition(getR() + 428);

        WaitTillTargetReached(50, true);

        robot.leftMotor.setTargetPosition(getL() + 523);
        robot.rightMotor.setTargetPosition(getR() + 523);

        WaitTillTargetReached(50, true);

        robot.leftMotor.setTargetPosition(getL() - 267);
        robot.rightMotor.setTargetPosition(getR() + 267);

        WaitTillTargetReached(50, true);

        robot.leftMotor.setPower(1);
        robot.rightMotor.setPower(1);

        robot.leftServoF.setPosition(1);
        robot.rightServoF.setPosition(0);
        robot.leftMotor.setTargetPosition(getL() + 2945);
        robot.rightMotor.setTargetPosition(getR() + 2945);
        //robot.leftMotor.setTargetPosition(getL() + 3453);
        //robot.rightMotor.setTargetPosition(getR() + 3453);


        WaitTillTargetReached(45, true);

        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        sleep(300);

        robot.leftMotor.setPower(1f);
        robot.rightMotor.setPower(1f);

        robot.leftMotor.setTargetPosition(getL() - 3945);
        robot.rightMotor.setTargetPosition(getR() - 3945);
        WaitTillTargetReached(45, true);

        robot.leftMotor.setPower(.6f);
        robot.rightMotor.setPower(.6f);
        robot.leftMotor.setTargetPosition(getL() + 227);
        robot.rightMotor.setTargetPosition(getR() - 227);
        WaitTillTargetReached(45, true);


        robot.leftMotor.setTargetPosition(getL() + 1000);
        robot.rightMotor.setTargetPosition(getR() + 1000);
        WaitTillTargetReached(45, true);
        robot.rightServoF.setPosition(1);
        robot.leftServoF.setPosition(0);
        sleep(476);

        robot.leftMotor.setTargetPosition(getL() - 1000);
        robot.rightMotor.setTargetPosition(getR() - 1000);
        WaitTillTargetReached(45, true);

        robot.leftMotor.setTargetPosition(getL() - 227);
        robot.rightMotor.setTargetPosition(getR() + 227);
        WaitTillTargetReached(45, true);

        robot.leftMotor.setPower(1);
        robot.rightMotor.setPower(1);
        robot.leftMotor.setTargetPosition(getL() + 2945);
        robot.rightMotor.setTargetPosition(getR() + 2945);

        robot.leftMotor.setPower(.7);
        robot.rightMotor.setPower(.7);
        robot.leftMotor.setTargetPosition(getL() - 752);
        robot.rightMotor.setTargetPosition(getR() + 752);

        WaitTillTargetReached(50, true);


        robot.leftMotor .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RunAuto(false);
        /*robot.leftServoB.setPosition(0);
        robot.rightServoB.setPosition(1);
        sleep(576);

        robot.leftMotor.setPower(.7);
        robot.rightMotor.setPower(.7);
        robot.leftMotor.setTargetPosition(getL() + 1257);
        robot.rightMotor.setTargetPosition(getR() + 957);

        WaitTillTargetReached(50, true);

        robot.leftServoB.setPosition(1);
        robot.rightServoB.setPosition(0);
        sleep(482);

        robot.leftMotor.setTargetPosition(getL() + 2862);
        robot.rightMotor.setTargetPosition(getR() + 2862);

        WaitTillTargetReached(50, true);*/

        robot.Stop();

    }

    private void WaitTillTargetReached(int tolerance, boolean lock){
        int leftDifference = Math.abs(robot.leftMotor.getTargetPosition() - robot.leftMotor.getCurrentPosition());
        int rightDifference = Math.abs(robot.rightMotor.getTargetPosition() - robot.rightMotor.getCurrentPosition());
        while(leftDifference > tolerance || rightDifference > tolerance)
        //while(robot.leftMotor.isBusy() || robot.rightMotor.isBusy())
        {
            leftDifference = Math.abs(robot.leftMotor.getTargetPosition() - robot.leftMotor.getCurrentPosition());
            rightDifference = Math.abs(robot.rightMotor.getTargetPosition() - robot.rightMotor.getCurrentPosition());

            telemetry.addData("left", leftDifference);
            telemetry.addData("right", rightDifference);
            telemetry.update();
            sleep(1);
        }
        if(!lock)
        {
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
        }
    }


    private int getL (){
        return robot.leftMotor.getTargetPosition();


    }
    private int getR (){
        return robot.rightMotor.getTargetPosition();


    }

    public void RunAuto(boolean red){

        robot.leftMotor .setPower(-.3f);
        robot.rightMotor.setPower(-.3f);
        sleep(1426);

        robot.Stop();

        robot.leftServoB.setPosition(0);
        robot.rightServoB.setPosition(1);
        sleep(1200);

        robot.leftMotor .setPower(red ?  1f : .6f);
        robot.rightMotor.setPower(red ? .6f :  1f);
        sleep(1250);

        robot.leftMotor .setPower(!red ? -1 : 1);
        robot.rightMotor.setPower(!red ?  1 : -1);
        sleep(1758);

        robot.leftMotor .setPower(-.9f);
        robot.rightMotor.setPower(-.9f);

        robot.leftServoB.setPosition(1);
        robot.rightServoB.setPosition(0);
        sleep(1300);

        robot.leftMotor.setPower(.6f);
        robot.rightMotor.setPower(.6f);
        sleep(200);

        robot.leftMotor.setPower(0);
        sleep(200);
        robot.leftMotor.setPower(.6f);
        sleep(400);

        robot.leftMotor.setPower(.6f);
        robot.rightMotor.setPower(-.6f);
        sleep(726);

        robot.leftMotor.setPower(0f);
        robot.rightMotor.setPower(0f);

        robot.leftServoF.setPosition(1);
        robot.rightServoF.setPosition(0);
        sleep(768);

        robot.leftMotor.setPower(-.6f);
        robot.rightMotor.setPower(-.6f);
        sleep(431);

        robot.leftMotor.setPower(-.6f);
        robot.rightMotor.setPower(.6f);
        sleep(635);

        robot.leftMotor.setPower(.7f);
        robot.rightMotor.setPower(.7f);
        sleep(294);


    }
}
