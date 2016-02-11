package motor.moveStrategy;


import motor.MotorIFC;

public class Antibacklash extends MoveStrategyImpl
{
    final private int antibackMagnitude;
    final private int direction;
    public Antibacklash(MotorIFC motor, int direction, int mag)
    {
        super(motor);
        assert (direction == 1) || (direction == -1);
        assert mag > 0;
        this.direction = direction;
        antibackMagnitude = mag;
    }

    public Antibacklash(MoveStrategyIFC strategy, int direction, int mag)
    {
        super(strategy);
        assert (direction == 1) || (direction == -1);
        assert mag > 0;
        this.direction = direction;
        antibackMagnitude = mag;

    }

    @Override
    public void moveAbsoluteImpl(long toPos) throws Exception
    {

        long currentPos = motor.getPosition();
        if(toPos == currentPos)return;

        boolean isSameSign = (direction * (toPos - currentPos) > 0);

        if (isSameSign)
            moveTheChained(toPos);//only need 1 settle time
        else
        {
            long tempPosition = (direction == 1) ? toPos - antibackMagnitude : toPos + antibackMagnitude;
            moveTheChained(tempPosition); // 1 settle time
            moveTheChained(toPos); // another settle time
        }

    }
}
