package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Projects.MecanumProject;
import org.firstinspires.ftc.teamcode.Projects.Project0;
import org.firstinspires.ftc.teamcode.Projects.Project1;
import org.firstinspires.ftc.teamcode.Projects.ProjectMecCam;

@Autonomous(name="VuforiaAuto", group="Mecanum")
public class VuforiaAuto extends LinearOpMode{
    public ProjectMecCam robot = new ProjectMecCam();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize ProjectMecanum with hardwareMap configuration
        robot.init(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AclOCKX/////AAABmVMcwvR8lE0inBkpYnUkWaw7ay7BC7I7cnlDQhYSBmWArpdTIiM8c2VKv7t8NMx+gJbqipUTvEY5njhhVXdcTDJOg6RmtybMYHMbDBevdAGg+SIPSeBOeXi5pJd6xlDDPu1JbSPQRyEZKZmWXxoIkht2JZD0DC6QxdGJUxVi0n0FD0dLVRAuWSr+ROLsi13GA126OzIJvm6Ego5RXeEAsM/RPk3mUH40iSeXU6mum+LzdV/yl8u0VC2YeHMH6GNKWGa0Ux+DB5Fp2f8Mjnz+xnuXaralDWBhR2r3DzKE979cNXeCpY9WtmRatRKWdhpOSm3QwLfAowIeZOZ6tH8EU8q4qN61rzLpzd/A6rR4QLp5";
        parameters.cameraName = robot.webcam;

        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);
        VuforiaTrackables trackables = vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable skystoneTrackable = trackables.get(0);
        skystoneTrackable.setName("Skystone");

        trackables.activate();
        waitForStart();


        while(opModeIsActive()){
            OpenGLMatrix location = ((VuforiaTrackableDefaultListener) trackables.get(0).getListener()).getUpdatedRobotLocation();
            if(location != null) {
                telemetry.addData("Location", location.getTranslation());
            }
            telemetry.update();
        }


        //Reset robot motors to stop when game is finished
        robot.frontLeft .setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft  .setPower(0);
        robot.backRight .setPower(0);
    }
}
