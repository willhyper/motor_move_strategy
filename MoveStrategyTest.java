package motor;

import motor.moveStrategy.*;


/**
 * Created by chaoweichen on 1/7/16.
 */
public class MoveStrategyTest {

    public static void main(String[] args) throws Exception {

        MotorIFC motor = new Motor();

        MoveStrategyIFC recorder = new MoveRecorder(motor);
        MoveStrategyIFC plain = new PlainMove(recorder);

        out("Plain move from somewhere to 100");
        plain.moveAbsoluteImpl(100);
        out(String.format("Plain move from %d to 0",motor.getPosition()));
        plain.moveAbsoluteImpl(0);

        out("\nNow chain the stepper logic. move to 100 with step of 20");
        MoveStrategyIFC stepper = new Stepper(plain,20);
        stepper.moveAbsoluteImpl(100);
        out("Move back to 0 with stepper");
        stepper.moveAbsoluteImpl(0);

        out("\nrestart with plain. hook up random (to simulate noise) so motor moves to ~100");
        MoveStrategyIFC random = new RandomOffset(plain,10);
        random.moveAbsoluteImpl(100);
        out("move back to ~0");
        random.moveAbsoluteImpl(0);

        out("\nuse Retry to make motor attempt to move to 100 up to 3 times. Then try to move back to 0 up to 3 times");
        MoveStrategyIFC retry = new MoveRetry(random,3);
        retry.moveAbsoluteImpl(100);
        retry.moveAbsoluteImpl(0);

        out("\nfancy cascading strategy: stepper - antibacklash - recorder ");
        out("move from 0 to 100 to 0. See how the chained strategy works");
        MoveStrategyIFC abl = new Antibacklash(recorder,1,7);
        stepper = new Stepper(abl,20);
        stepper.moveAbsoluteImpl(100);
        stepper.moveAbsoluteImpl(0);




    }


    static private void out(String msg){
        System.out.println(msg);
    }
}
