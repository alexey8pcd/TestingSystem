package main.criteria;

/**
 * @author Alexey
 */
public class CubicProgressiveCriteria implements Criteriable {

    private static final CubicProgressiveCriteria instance
            = new CubicProgressiveCriteria();

    public static CubicProgressiveCriteria getInstance() {
        return instance;
    }

    private CubicProgressiveCriteria() {
    }

    @Override
    public int calculateMark(double scored) {
        int r = (int) Math.round(scored * scored * scored * 100);
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
        return "Прогрессивная(жесткая)";
    }

    @Override
    public int calculateMark(int scored) {
        scored = scored * scored * scored / 10000;
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
