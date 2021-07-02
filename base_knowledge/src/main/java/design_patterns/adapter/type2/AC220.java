package design_patterns.adapter.type2;

/**
 * 220AC adapter
 */
public class AC220 implements AC {

    public final int output = 220;

    @Override
    public int outputAc() {
        return output;
    }
}
