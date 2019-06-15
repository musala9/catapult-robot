package net.maxdev.ftc;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import net.maxdev.ftc.utils.catapultmotor;
import net.maxdev.ftc.utils.drive_motors;
import java.text.DecimalFormat;

@TeleOp(name = "Drive_Test", group = "Drive")
public class Drive extends OpMode {
    catapultmotor catapult = new catapultmotor ();
    ElapsedTime runtime = new ElapsedTime();
    drive_motors wheels = new drive_motors();
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    public void init() {
        catapult.init (telemetry, hardwareMap);
        wheels.init (telemetry, hardwareMap);
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        wheels.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_y) ;
        if (catapultmotor.perfect_loaded && gamepad1.a) catapult.throw_thing();
        if (gamepad1.b) catapult.loading_thing();
        if (gamepad1.x) catapult.reset_motor();
        if (gamepad1.y) catapult.force_stop();
        if (gamepad1.right_bumper) {drive_motors.Half_Power=true; drive_motors.Three_Quarters=false;}
        if (gamepad1.left_bumper) {drive_motors.Half_Power=false; drive_motors.Three_Quarters=false;}
        if (gamepad1.right_trigger > 0.5) {
            drive_motors.Half_Power=false;
            drive_motors.Three_Quarters=true;}  //trb vazut cum merge right_trigger
        catapult.debug();
        wheels.debug();
        telemetry.addLine().addData("Run Time", df2.format(runtime.time()));
        telemetry.update();
    }


    @Override
    public void stop() {}
}
