package main.criteria;

/**
 * @author Alexey
 */
public class ProgressiveCriteria implements Criteriable {

    private static final ProgressiveCriteria instance
            = new ProgressiveCriteria();

    public static ProgressiveCriteria getInstance() {
        return instance;
    }

    private ProgressiveCriteria() {
    }

    @Override
    public int calculateMark(double scored) {
        int r = (int) Math.round(scored * scored * 100);
        if (r < 25) {
            return 2;
        }
        if (r < 50) {
            return 3;
        }
        if (r < 75) {
            return 4;
        }
        return 5;
    }

    @Override
    public String getName() {
        return "Прогрессивная(мягкая)";
    }

    @Override
    public int calculateMark(int scored) {
        scored = scored * scored / 100;
        if (scored < 25) {
            return 2;
        }
        if (scored < 50) {
            return 3;
        }
        if (scored < 75) {
            return 4;
        }
        return 5;
    }

}
