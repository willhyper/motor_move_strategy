package motor.moveStrategy;

import motor.MotorIFC;

public class MoveRetry extends MoveStrategyImpl
{

    final private int retryNumber;
    private int attempt;


    public MoveRetry(MotorIFC motor, int retryNumber)
    {
        super(motor);
        this.retryNumber = retryNumber;
    }


    public MoveRetry(MoveStrategyIFC strategy, int retryNumber) {
        super(strategy);
        this.retryNumber = retryNumber;

    }

    @Override
    public void moveAbsoluteImpl(long destination) throws Exception
    {


        attempt = 0;
        while(motor.getPosition() != destination && attempt++ < retryNumber){

            try{
                moveTheChained(destination);
            }
            catch (Exception e){

            }

        }

    }


}
