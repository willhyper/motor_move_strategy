package motor.moveStrategy;

import motor.MotorIFC;

public class Stepper extends MoveStrategyImpl
{

    final private int stepSize;
    public Stepper(MotorIFC motor, int stepSize)
    {

        super(motor);
        assert stepSize >0;
        this.stepSize = stepSize;
    }

    public Stepper(MoveStrategyIFC strategy, int stepSize)
    {
        super(strategy);
        assert stepSize >0;
        this.stepSize = stepSize;
    }

    @Override
    public void moveAbsoluteImpl(long destinationPos) throws Exception
    {

        long currentPos = motor.getPosition();
        if(currentPos==destinationPos)
            return;
        else if(destinationPos > currentPos)
        {

            long nextPos = motor.getPosition() + stepSize;
            while(destinationPos > nextPos)
            {
                moveTheChained(nextPos);
                nextPos = motor.getPosition() + stepSize;
            }
            moveTheChained(destinationPos);
        }
        else
        {

            long nextPos = motor.getPosition() - stepSize;
            while(destinationPos < nextPos)
            {
                moveTheChained(nextPos);
                nextPos = motor.getPosition() - stepSize;
            }
            moveTheChained(destinationPos);


        }

    }
}
