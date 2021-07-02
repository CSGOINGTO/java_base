package design_patterns.adapter.type2;

/**
 * Target，适配接口，一般是新接口
 */
public interface DC5Adapter {

    boolean support(AC ac);

    int outputAC5V(AC ac);

}
