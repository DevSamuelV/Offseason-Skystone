package org.firstinspires.ftc.teamcode.PinkCode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PinkCode.Hardware.HardWare;

public class DriveSystem {
    public HardWare hwmap;
    private Telemetry telemetry;

    private int ROTATIONS_MAX = 0;
    private int ROTATIONS_MIN = 0;
    private int ROTATIONS_CURRENT = 0;

    private Boolean TIMER_RUNNING = false;

    public DriveSystem(HardwareMap hmap, Telemetry tel) {
        hwmap = new HardWare(hmap);
        telemetry = tel;

        hwmap.Init();
    }

    public void BaseDrive(double leftx, double lefty, double rightx) {
        double r = Math.hypot(leftx, lefty);
        double robotAngle = Math.atan2(leftx, lefty) - Math.PI / 4;
        double localRightX = rightx;

        double v1 = r * Math.cos(robotAngle) + localRightX;
        double v2 = r * Math.sin(robotAngle) - localRightX;
        double v3 = r * Math.sin(robotAngle) + localRightX;
        double v4 = r * Math.cos(robotAngle) - localRightX;

        telemetry.addData("v1", v1);
        telemetry.addData("v2", v2);
        telemetry.addData("v3", v3);
        telemetry.addData("v4", v4);
        telemetry.update();

        if(leftx > .1 || leftx < -.1) {
            v1 -= v1 / 7;
            v3 -= v3 / 7;
        }

        drive_by_command(false,-v1,-v2,-v3,-v4);
    }

    public void SafeTowerDrive(double tower_drive, boolean encoderDrive) {
//        telemetry.addData("data", hwmap.dcMotor_tower_left.getCurrentPosition());
//        telemetry.addData("data", hwmap.dcMotor_tower_right.getCurrentPosition());
//        telemetry.addData("power", hwmap.dcMotor_tower_left.getPower());
//        telemetry.addData("power", hwmap.dcMotor_tower_right.getPower());
//        telemetry.update();

        if (encoderDrive) {
            // 1120
            // if gamepad left_stick_y = -1 tower up

            // not been tested
//            hwmap.dcMotor_tower_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            hwmap.dcMotor_tower_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            hwmap.dcMotor_tower_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            hwmap.dcMotor_tower_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//            double diameter = 111.6584;
//            double circumference = Math.PI * diameter;
//            double rotNeeded = 18 / circumference;
//            int encoderDivingTarget = (int) (rotNeeded * 1120);
//
//            hwmap.dcMotor_tower_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            hwmap.dcMotor_tower_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            hwmap.dcMotor_tower_left.setTargetPosition(999);
//            hwmap.dcMotor_tower_right.setTargetPosition(999);
//
//            UnsafeTowerDrive(1.0, 1.0);
//
//            telemetry.addData("Tower", "Moving Tower DOWN");
//            telemetry.update();
//
//            // if gamepad left_stick_y = 1 tower down
//            if (tower_drive == 1) {
//                try {
//                    // not been tested
//                    hwmap.dcMotor_tower_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                    hwmap.dcMotor_tower_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//                    hwmap.dcMotor_tower_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                    hwmap.dcMotor_tower_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//                    hwmap.dcMotor_tower_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    hwmap.dcMotor_tower_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//                    diameter = 111.6584;
//                    circumference = Math.PI * diameter;
//                    rotNeeded = 18 / circumference;
//                    encoderDivingTarget = (int) (rotNeeded / 1120);
//
//                    hwmap.dcMotor_tower_left.setTargetPosition(encoderDivingTarget);
//                    hwmap.dcMotor_tower_right.setTargetPosition(encoderDivingTarget);
//
//                    UnsafeTowerDrive(-0.6, -0.6);
//
//                    telemetry.addData("Tower", "Moving Tower UP");
//                    telemetry.update();
//                } catch (Exception e) {
//                    telemetry.addData("err", e.getCause());
//                    telemetry.update();
//                }
//            }
        }
    }

    public void drive_by_command(boolean strafe, double rightF,double rightB, double leftF,double leftB)
    {
        // Define Commands
        if(strafe)
        {
            hwmap.dc_base_front_right.setPower(-rightF);
            hwmap.dc_base_back_right.setPower(rightB);
            hwmap.dc_base_front_left.setPower(leftF);
            hwmap.dc_base_back_left.setPower(-leftB);
        }
        else {
            hwmap.dc_base_front_right.setPower(rightF);
            hwmap.dc_base_back_right.setPower(rightB);
            hwmap.dc_base_front_left.setPower(leftF);
            hwmap.dc_base_back_left.setPower(leftB);
        }
    }
    // Method for Stopping the Drive Train
    public void Stop() {
        // Define Commands
        hwmap.dc_base_front_right.setPower(0);
        hwmap.dc_base_back_right.setPower(0);
        hwmap.dc_base_front_left.setPower(0);
        hwmap.dc_base_back_left.setPower(0);
    }
}
