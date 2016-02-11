package motor.moveStrategy;

import motor.MotorIFC;

public class PlainMove extends MoveStrategyImpl
{

    public PlainMove(MotorIFC motor)
    {
        super(motor);
    }


    public PlainMove(MoveStrategyIFC strategy)
    {
        super(strategy);

    }
    @Override
    public void moveAbsoluteImpl(long pos) throws Exception
    {
        moveTheChained(pos);
    }
}
