package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Projects.ProjectPushbot;

@Autonomous(name="SkystoneOnlyAuto", group="Mecanum")
public class SkystoneOnlyAuto extends LinearOpMode{
    public ProjectPushbot robot = new ProjectPushbot();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);
        robot.colorSensor.enableLed(true);

        robot.leftMotor .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftMotor .setTargetPosition(600);
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


        Path p = Path.Blue;


        robot.leftServoF.setPosition(.5f);
        robot.rightServoF.setPosition(.5f);
        while(!isStarted()){
            if(gamepad1.b){
                p = Path.Red;
            }
            if(gamepad1.x){
                p = Path.Blue;
            }
            telemetry.addData("Path:", p);
            telemetry.update();
        }

        robot.leftServoF.setPosition(1);
        robot.rightServoF.setPosition(0);
        sleep(200);

        robot.leftMotor.setPower(.6);
        robot.rightMotor.setPower(.6);


        WaitTillTargetReached(50, true);
        telemetry.addData("hi", "you got here");
        telemetry.update();
        OpenGLMatrix location = null;
        robot.leftMotor.setTargetPosition(getL()+280);
        robot.rightMotor.setTargetPosition(getR()+280);
        int count = 0;
        while(location == null){

            location = ((VuforiaTrackableDefaultListener) trackables.get(0).getListener()).getUpdatedRobotLocation();
            count++;
            telemetry.addData("count", count);
            telemetry.update();
            if(count>210000){
                break;
            }
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

        float x;
        if(location != null) {
            x = location.getTranslation().get(0); //gets 0
        }else{
            x = 0;
        }
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
        RunAuto(p == Path.Blue);
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

            //telemetry.addData("left", leftDifference);
            //telemetry.addData("right", rightDifference);
            //telemetry.update();
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

    public void RunAuto(boolean blue){
        //directionMultiplier
        int dM = blue ? -1 : 1;

        robot.leftMotor.setTargetPosition(getL() - 428*dM);
        robot.rightMotor.setTargetPosition(getR() + 428*dM);

        WaitTillTargetReached(50, true);

        robot.leftMotor.setTargetPosition(getL() + 480);
        robot.rightMotor.setTargetPosition(getR() + 480);

        WaitTillTargetReached(50, true);
        if(blue){
            robot.leftMotor.setTargetPosition(getL() - 250 * dM);
            robot.rightMotor.setTargetPosition(getR() + 250 * dM);
        }else {
            robot.leftMotor.setTargetPosition(getL() - 267 * dM);
            robot.rightMotor.setTargetPosition(getR() + 267 * dM);
        }

        WaitTillTargetReached(50, true);

        robot.leftMotor.setPower(1);
        robot.rightMotor.setPower(1);
        robot.leftMotor.setTargetPosition(getL() + 2353);
        robot.rightMotor.setTargetPosition(getR() + 2353);

        WaitTillTargetReached(45, true);

        robot.leftServoF.setPosition(1);
        robot.rightServoF.setPosition(0);

        sleep(1000);

        robot.leftMotor.setTargetPosition(getL() - 1000);
        robot.rightMotor.setTargetPosition(getR() - 1000);

        WaitTillTargetReached(45, true);

    }
}
