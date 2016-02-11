package motor.moveStrategy;

import motor.MotorIFC;

public class MoveRecorder extends MoveStrategyImpl
{

    public MoveRecorder(MotorIFC motor)
    {
        super(motor);
    }

    public MoveRecorder(MoveStrategyIFC strategy)
    {
        super(strategy);
    }

    @Override
    public void moveAbsoluteImpl(long pos) throws Exception
    {

        moveTheChained(pos);
        System.out.println(pos);
    }



}
