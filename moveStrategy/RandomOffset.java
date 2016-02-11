package motor.moveStrategy;

import motor.MotorIFC;

import java.util.Random;

public class RandomOffset extends MoveStrategyImpl
{

    final private int magnitude;
    public RandomOffset(MotorIFC motor, int mag)
    {
        super(motor);
        magnitude = mag;
    }

    public RandomOffset(MoveStrategyIFC strategy, int mag)
    {
        super(strategy);
        magnitude = mag;
    }

    @Override
    public void moveAbsoluteImpl(long pos) throws Exception
    {
        Random gen = new Random();
        int slipCount = gen.nextInt(magnitude)-magnitude/2;
        moveTheChained(pos + slipCount);

    }
}
