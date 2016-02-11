package motor.moveStrategy;

import motor.MotorIFC;

// all subClass should use moveTheChained and motor to implement specific logic
abstract public class MoveStrategyImpl implements MoveStrategyIFC
{
    final protected MotorIFC motor;
    final private MoveStrategyImpl strategy;


    public MoveStrategyImpl(MotorIFC motor)
    {
        this.motor = motor;
        this.strategy = null;
    }

    public MoveStrategyImpl(MoveStrategyIFC strategy)     //not MoveStrategyImpl because users dont deal with it.
    {
        this.strategy = (MoveStrategyImpl) strategy;
        MoveStrategyImpl s = this.strategy;
        while(s.strategy != null)
            s = s.strategy;
        this.motor = s.motor;
    }


    /**
     * use the chained strategy first. if strategy is null, then use motor
     */
    final protected void moveTheChained(long pos) throws Exception
    {
        if(strategy != null)
            strategy.moveAbsoluteImpl(pos);
        else
            motor.moveAbsoluteImpl(pos);
    }

}
