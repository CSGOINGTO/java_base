package design_patterns.adapter.type2;

/**
 * 110AC Adaptee
 */
public class AC110 implements AC {

    public final int output = 110;

    @Override
    public int outputAc() {
        return output;
    }
}
