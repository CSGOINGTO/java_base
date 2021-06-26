package design_patterns.responsibityChain;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链
 */
public class ResponsibilityChain {
    private final List<Responsibility> responsibilities;

    private int index = 0;

    public ResponsibilityChain() {
        this.responsibilities = new ArrayList<>();
    }

    public void process(Request request) {
        if (this.index < this.responsibilities.size()) {
            this.responsibilities.get(index++).process(request, this);
        }
    }

    /**
     * 注册
     */
    public void register(Responsibility responsibility) {
        this.responsibilities.add(responsibility);
    }
}
