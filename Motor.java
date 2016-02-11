package motor;

/**
 * Created by chaoweichen on 1/7/16.
 */
public class Motor implements MotorIFC{

    private long currentPos;

    public Motor(){
        currentPos = 0;
    }

    @Override
    public long getPosition() {
        return currentPos;
    }

    @Override
    public void moveAbsoluteImpl(long pos) throws Exception {
        currentPos = pos;

    }
}
