package motor;

/**
 * Created by chaoweichen on 1/7/16.
 */
public interface MotorIFC {
    public long getPosition();

    public void moveAbsoluteImpl(long pos) throws Exception;

}
