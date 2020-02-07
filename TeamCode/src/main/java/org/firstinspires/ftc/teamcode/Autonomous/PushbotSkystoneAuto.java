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


        if (x<-130){
            robot.leftMotor.setTargetPosition(138 + getL());
            robot.rightMotor.setTargetPosition(getR() - 138);
            WaitTillTargetReached(50, true);



        }
        else if(x>130){
            robot.rightMotor.setTargetPosition(138 + getR());
            robot.leftMotor.setTargetPosition(getL() - 138);
            WaitTillTargetReached(50, true);
        }

        robot.rightMotor.setTargetPosition(getR() + 1183);
        robot.leftMotor.setTargetPosition(getL() + 1883);

        WaitTillTargetReached(50, true);


        //Reset robot motors to stop when game is finished
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
}
