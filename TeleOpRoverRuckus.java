package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;

/**
 * Created by mandy.peake and helen.watson on 8/30/2019.
 */

@TeleOp (name= "TeleOpRoverRuckus", group= "Linear Opmode")
public class TeleOpRoverRuckus extends LinearOpMode {

    HardwareRoverRuckus Bumblebarry = new HardwareRoverRuckus();

    ElapsedTime runtime = new ElapsedTime();

    @Override

    public void runOpMode() {

        System.out.println("Starting up");
        telemetry.addData("init pressed", "about to initialize");
        telemetry.update();


        System.out.println("Initialize Robot");
        Bumblebarry.initializeRobot(hardwareMap);
        System.out.println("Robot Initialized");

     //   Rover.rightCollector.setPosition(1);
     //   Rover.leftCollector.setPosition(0);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        composeTelemetry();

        waitForStart();

      //  Rover.imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);


        while (opModeIsActive()) {

          //  telemetry.addData("armExtend", Rover.armExtend.getCurrentPosition());
         //   telemetry.addData("Left Arm Encoder", Rover.armLeft.getCurrentPosition());
          //  telemetry.addData("Right Arm Encoder", Rover.armRight.getCurrentPosition());
           // telemetry.update();


            double fwdBack = gamepad1.right_stick_y;
            double strafe = gamepad1.right_stick_x;
            double turn = gamepad1.left_stick_x;

            if (gamepad1.start) { // drive robot at slower speed for fine adjustments while carrying gold

                Bumblebarry.leftFront.setPower((fwdBack + 1.5*strafe - turn) * .25);
                Bumblebarry.leftBack.setPower((fwdBack - 1.5*strafe - turn) * .25);
                Bumblebarry.rightFront.setPower((-fwdBack + 1.5*strafe - turn) * .25);
                Bumblebarry.rightBack.setPower((-fwdBack - 1.5*strafe - turn) * .25);

            } else { // drive robot normally at full speed

                Bumblebarry.leftFront.setPower((fwdBack + strafe - turn));
                Bumblebarry.leftBack.setPower((fwdBack - strafe - turn));
                Bumblebarry.rightFront.setPower((-fwdBack + strafe - turn));
                Bumblebarry.rightBack.setPower((-fwdBack - strafe - turn));

            }

            if (gamepad2.left_bumper) {
                Bumblebarry.LiftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bumblebarry.LiftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bumblebarry.LiftL.setTargetPosition(100);
                Bumblebarry.LiftL.setPower(.85);
                Bumblebarry.LiftLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bumblebarry.LiftLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bumblebarry.LiftLow.setTargetPosition(100);
                Bumblebarry.LiftLow.setPower(.9);
            } else if (gamepad2.left_trigger > 0.5) {
                Bumblebarry.LiftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bumblebarry.LiftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bumblebarry.LiftL.setTargetPosition(-100);
                Bumblebarry.LiftL.setPower(-.85);
                Bumblebarry.LiftLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bumblebarry.LiftLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bumblebarry.LiftLow.setTargetPosition(-100);
                Bumblebarry.LiftLow.setPower(-.95);
            } else {
                Bumblebarry.LiftL.setPower(0);
                Bumblebarry.LiftLow.setPower(0);
            }
            if (gamepad2.right_bumper) {
                Bumblebarry.LiftU.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bumblebarry.LiftU.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bumblebarry.LiftU.setTargetPosition(100);
                Bumblebarry.LiftU.setPower(.6);
            } else if (gamepad2.right_trigger > 0.5) {
                Bumblebarry.LiftU.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Bumblebarry.LiftU.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Bumblebarry.LiftU.setTargetPosition(-100);
                Bumblebarry.LiftU.setPower(-.6);
            } else {
                Bumblebarry.LiftU.setPower(0);
            }
            {
                if (gamepad2.dpad_down) {
                    Bumblebarry.LiftLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    Bumblebarry.LiftLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Bumblebarry.LiftLow.setTargetPosition(-100);
                    Bumblebarry.LiftLow.setPower(-.9);
                }
                else if (gamepad2.dpad_up) {
                    Bumblebarry.LiftLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    Bumblebarry.LiftLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Bumblebarry.LiftLow.setTargetPosition(100);
                    Bumblebarry.LiftLow.setPower(.9);

                }

                { if (gamepad2.dpad_right) {
                    Bumblebarry.LiftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    Bumblebarry.LiftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Bumblebarry.LiftL.setTargetPosition(100);
                    Bumblebarry.LiftL.setPower(.85);
                }
                 else if (gamepad2.dpad_left){
                    Bumblebarry.LiftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    Bumblebarry.LiftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Bumblebarry.LiftL.setTargetPosition(-100);
                    Bumblebarry.LiftL.setPower(-.85);
                }
                }

                    //Grabber thing

                    if (gamepad2.a) {
                        Bumblebarry.Grabber(.5);
                    } else if (gamepad2.y) {
                        Bumblebarry.Grabber(.9);
                    } else if (gamepad2.x) {
                        Bumblebarry.Grabber (.1);
                    }
            }

          /*  if (gamepad1.x){
                Rover.rotateSweeper(1, 0);
            } else if (gamepad1.b){
                Rover.rotateSweeper(.5, .5);
            }
*/

            //Ball Flap
/*

            if (gamepad2.x) {
                Rover.rotateFlap(1);
            } else if (gamepad2.a){
                Rover.rotateFlap(.5);
            } else if (gamepad2.b){

                Rover.rotateFlap(0);
            }
*/

/*

            //Arm Movements

            if (gamepad2.y) {
                Rover.liftArmEncoder(-.5, -500, 1);

            } else {
                Rover.liftArm(gamepad2.right_stick_y * .6 + gamepad2.left_stick_y);
            }

*/

            //Lift going up and down

            /*
            if (gamepad2.dpad_up) {
                Bumblebarry.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Bumblebarry.Lift.setPower(.5);
            } else if (gamepad2.dpad_down) {
                Bumblebarry.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Bumblebarry.Lift.setPower(-.5);
            } else {
                Bumblebarry.Lift.setPower(0);
            }
            */

            // Claw moving forward and backwards
            if (Math.abs (gamepad2.left_stick_y) > 0.002){
                Bumblebarry.moveClaw(gamepad2.left_stick_y);
            } else {
                Bumblebarry.moveClaw(0.0f);
            }



        }

    }

    //----------------------------------------------------------------------------------------------
    // Telemetry Configuration
    //----------------------------------------------------------------------------------------------

    public void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
              //  Rover.angles = Rover.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
               // Rover.gravity = Rover.imu.getGravity();
            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override
                    public String value() {
                       return "";
                        // return Rover.imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override
                    public String value() {
                       return "";
                        // return Rover.imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override
                    public String value() {
                       return "";
                       // return formatAngle(Rover.angles.angleUnit, Rover.angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override
                    public String value() {
                       return "";
                       // return formatAngle(Rover.angles.angleUnit, Rover.angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override
                    public String value() {
                        return "";
                        //return formatAngle(Rover.angles.angleUnit, Rover.angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override
                    public String value() {
                        return "";
                        //return Rover.gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override
                    public String value() {
                        return String.format(Locale.getDefault(), "%.3f"//,
                               // Math.sqrt(Rover.gravity.xAccel * Rover.gravity.xAccel
                                      //  + Rover.gravity.yAccel * Rover.gravity.yAccel
                                      //  + Rover.gravity.zAccel * Rover.gravity.zAccel)
                                );
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------
    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }


}


