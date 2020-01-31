package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by ashley.peake on 8/30/2018.
 */

public class HardwareRoverRuckus {

    //Wheels
    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    int driveTime;

  //LiftMotors - Left & Right control lifting the arm; Extend controls the life mechanism
    public DcMotor LiftL;
    public DcMotor LiftLow;
    public DcMotor LiftU;
    //public DcMotor Lift;

    //Actuator motor
    public DcMotor claw;

    public Servo Grab;
    public Servo rightCollector;
    public Servo flap;
/*
    public CRServo leftSweep;
    public CRServo rightSweep;


    BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;


    ElapsedTime driveTime = new ElapsedTime();

    HardwareMap HWMap = null;




    //----------------------------------METHODS------------------------------------------------------


    //Constructor
    public HardwareRoverRuckus() {

    }


    //---------------------------------INIT--ROBOT---------------------------------------------------
    /*  This method allows us to initialize the motors and sensors only once.
        It is used in every other program after "Init" is pressed.
     */


    public void initializeRobot(HardwareMap hwMap) {

        HardwareMap HWMap = hwMap;

        //initialize motors

        leftFront = HWMap.dcMotor.get("leftFront");
        leftBack = HWMap.dcMotor.get("leftBack");
        rightFront = HWMap.dcMotor.get("rightFront");
        rightBack = HWMap.dcMotor.get("rightBack");

        LiftL = HWMap.dcMotor.get("liftL");
        LiftLow = HWMap.dcMotor.get("liftLow");
        LiftU = HWMap.dcMotor.get("liftU");

        claw = HWMap.dcMotor.get("claw");

      /*  armLeft = HWMap.dcMotor.get("armLeft");
        armRight = HWMap.dcMotor.get("armRight");
        armExtend = HWMap.dcMotor.get("armExtend");
*/
        Grab = HWMap.servo.get("Grab");
        Grab.setPosition(.5);
        //rightCollector = HWMap.servo.get("rightCollector");
        //flap = HWMap.servo.get("flap");

        /*

        leftSweep = HWMap.crservo.get("leftSweep");
        rightSweep = HWMap.crservo.get("rightSweep");

        leftCollector.setPosition(0);
        rightCollector.setPosition(1);
        flap.setPosition(0);

        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //initialize IMU sensor

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = HWMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);


     /*
        //Initialize Vuforia

        int cameraMonitorViewId = HWMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", HWMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameter = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameter.vuforiaLicenseKey = VUFORIA_KEY ;
        parameter.cameraDirection   = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameter);


        VuforiaTrackables targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
        VuforiaTrackable blueRover = targetsRoverRuckus.get(0);
        blueRover.setName("Blue-Rover");
        VuforiaTrackable redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName("Red-Footprint");
        VuforiaTrackable frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName("Front-Craters");
        VuforiaTrackable backSpace = targetsRoverRuckus.get(3);
        backSpace.setName("Back-Space");

        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsRoverRuckus);


        //"Place" each of the target images in their respective positions along the coordinate plane of the playing field

        OpenGLMatrix blueRoverLocationOnField = OpenGLMatrix
                .translation(0, mmFTCFieldWidth, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0));
        blueRover.setLocation(blueRoverLocationOnField);


        OpenGLMatrix redFootprintLocationOnField = OpenGLMatrix
                .translation(0, -mmFTCFieldWidth, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180));
        redFootprint.setLocation(redFootprintLocationOnField);


        OpenGLMatrix frontCratersLocationOnField = OpenGLMatrix
                .translation(-mmFTCFieldWidth, 0, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90));
        frontCraters.setLocation(frontCratersLocationOnField);


        OpenGLMatrix backSpaceLocationOnField = OpenGLMatrix
                .translation(mmFTCFieldWidth, 0, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90));
        backSpace.setLocation(backSpaceLocationOnField);


        final int CAMERA_FORWARD_DISPLACEMENT  = 110;   // eg: Camera is 110 mm in front of robot center
        final int CAMERA_VERTICAL_DISPLACEMENT = 200;   // eg: Camera is 200 mm above ground
        final int CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES,
                        CAMERA_CHOICE == FRONT ? 90 : -90, 0, 0));


        for (VuforiaTrackable trackable : allTrackables)
        {
            ((VuforiaTrackableDefaultListener)trackable.getListener()).setPhoneInformation(phoneLocationOnRobot, parameter.cameraDirection);
        }

        targetVisible = false;

        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetVisible = true;

                // getUpdatedRobotLocation() will return null if no new information is available since
                // the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        if (targetVisible) {
            // express position (translation) of robot in inches.
            VectorF translation = lastLocation.getTranslation();

            translation.get(0);
            translation.get(1);
            translation.get(2);

            // express the rotation of the robot in degrees.
            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            return (rotation.secondAngle);

        } else {
            return (0);
        }
        */


    }


    //--------------------------DRIVE--FORWARD-------------------------------------------------------

    /*  This method allows the robot to drive forward based on encoder values.
        A distance is given that is converted to an encoder position in the code.
        leftDirection and rightDirection set the direction of the motors to allow
        the robot to either mover straight ot turn.
     */


    public void driveStraight(double power, double totalInches, int Direction) { //For driving

        // declare variables for this method

        int NewLeftFrontTarget;
        int NewLeftBackTarget;
        int NewRightFrontTarget;
        int NewRightBackTarget;

        //set encoder values to zero

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // counts = inches * 1120 (counts per revolution)/ 4*pi (circumference of wheels)
        // 1120/4*pi ~ 89 - we found that there was less error when given a precise number

        NewLeftBackTarget = (int) (totalInches * 99);
        NewLeftFrontTarget = (int) (totalInches * 99);
        NewRightBackTarget = (int) (totalInches * 99);
        NewRightFrontTarget = (int) (totalInches * 99);

        //get current position of the encoders at the start of the method

        leftFront.setTargetPosition(Direction * NewLeftFrontTarget);
        leftBack.setTargetPosition(Direction * NewLeftBackTarget);
        rightFront.setTargetPosition(-Direction * NewRightFrontTarget);
        rightBack.setTargetPosition(-Direction * NewRightBackTarget);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //reset the timeout time  and start motion


        driveTime = 0;

        leftFront.setPower(power * Direction * .99);
        leftBack.setPower(power * Direction * .99);

        rightFront.setPower(power * -Direction * .99) ;
        rightBack.setPower(power * -Direction * .99);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {

        }

        // stops all motion

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }

    public void driveTurn(double power, double totalInches, int Direction) { //For driving

        // declare variables for this method

        int NewLeftFrontTarget;
        int NewLeftBackTarget;
        int NewRightFrontTarget;
        int NewRightBackTarget;

        //set encoder values to zero

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // counts = inches * 1120 (counts per revolution)/ 4*pi (circumference of wheels)
        // 1120/4*pi ~ 89 - we found that there was less error when given a precise number

        NewLeftBackTarget = (int) (totalInches * 89 * .9);
        NewLeftFrontTarget = (int) (totalInches * 89 * .5);
        NewRightBackTarget = (int) (totalInches * 89 * .8);
        NewRightFrontTarget = (int) (totalInches * 89);

        //get current position of the encoders at the start of the method

        leftFront.setTargetPosition(Direction * NewLeftFrontTarget);
        leftBack.setTargetPosition(Direction * NewLeftBackTarget);
        rightFront.setTargetPosition(Direction * NewRightFrontTarget);
        rightBack.setTargetPosition(Direction * NewRightBackTarget);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //reset the timeout time  and start motion

        driveTime = 0;

        leftFront.setPower(power * Direction * .8);
        leftBack.setPower(power * Direction * .9);
        rightFront.setPower(power * Direction);
        rightBack.setPower(power * Direction * .8);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {

        }

        // stops all motion

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }


    //------------------------------DRIVE--SIDEWAYS--------------------------------------------------
    /*  This method makes the robot move in a sideways direction.
        It is separate from the other drive method because the motors on each side must turn
        in opposite directions. (See Mecanum Drive diagram)
        Because the weight on the robot is not evenly balanced, we found that each motor
        needed to run at a slightly different power (and thus had a slightly different encoder count).
     */


    public void driveMecanum(double power, double totalInches, int leftFrontDirection, int leftBackDirection, int rightFrontDirection, int rightBackDirection) { //For driving

        // declare variables for this method

        int NewLeftFrontTarget;
        int NewLeftBackTarget;
        int NewRightFrontTarget;
        int NewRightBackTarget;

        //set encoder values to zero
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // counts = inches * 1120 (counts per revolution)/ 4*pi (circumference of wheels)
        // 1120/4*pi ~ 89 - we found that there was less error when given a precise number


       NewLeftFrontTarget = (int) (totalInches * 89);
        NewLeftBackTarget = (int) (totalInches * 89);
        NewRightFrontTarget = (int) (totalInches * 89);
        NewRightBackTarget = (int) (totalInches * 89);

        //get current position of the encoders at the start of the method

        leftFront.setTargetPosition(leftFrontDirection * NewLeftFrontTarget);
        leftBack.setTargetPosition(leftBackDirection * NewLeftBackTarget);
        rightFront.setTargetPosition(rightFrontDirection * NewRightFrontTarget);
        rightBack.setTargetPosition(rightBackDirection * NewRightBackTarget);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //reset the timeout time  and start motion

        driveTime = 0;

        //powers scaled scaled based on trials & by the same as encoder values, so that everything
        //stops at the same time

        leftFront.setPower(power * leftFrontDirection * .9);
        leftBack.setPower(power * leftBackDirection * .9);
        rightFront.setPower(power * rightFrontDirection * .9);
        rightBack.setPower(power * rightBackDirection * .9);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {

        }

        // stops all motion

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }

    public void moveClaw (float joystickAngle) {
       claw.setPower(joystickAngle * -.4);
    }



    //------------------------------------TURN-BY-IMU------------------------------------------------

    /*  This program allows the robot to turn based on the IMU Sensor that is built into the
        REV hub. It is used to orient the robot once it lands from the lander during autonomous.
     */


  /*  public void turnIMU(double power, int degrees) {

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if (degrees < angles.firstAngle) {

            power = -power;
        }

        //set power for direction of turn based on relation between current and desired position

        leftBack.setPower(power);
        leftFront.setPower(power * 1.5);
        rightBack.setPower(power * 1.25);
        rightFront.setPower(power);

        if (degrees < angles.firstAngle) {
            while (angles.firstAngle >= degrees) {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }


        else if (degrees > angles.firstAngle){
            while (angles.firstAngle <= degrees) {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }


        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
    }

    //------------------------------------LIFT-ARM---------------------------------------------------

    /*  This program allows the robot to lift its arm to either attatch to the lander or place elements in it
     */

   /* public void liftArm(double power) {

        armRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armLeft.setPower(power);
        armRight.setPower(-power);


    }

    public void liftArmEncoder(double power, int encoder, int direction) {

        armLeft.setTargetPosition(encoder * direction);
        armRight.setTargetPosition(-encoder * direction);

        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armLeft.setPower(power * direction);
        armRight.setPower(-power * direction);

        while (armLeft.isBusy() && armRight.isBusy()) ;


    }

    //-------------------------BALL-COLLECTOR--------------------------------------------------------

    public void rotateSweeper(double powerLeft, double powerRight) {

        leftSweep.setPower(powerLeft);
        rightSweep.setPower(powerRight);

    }
*/
    public void Grabber( double position){

        Grab.setPosition(position); }

    }
/*
    public void rotateFlap(double position){

        flap.setPosition(position);
    }

    //-----------------------------------------------------------------------------------------------


    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

*/



