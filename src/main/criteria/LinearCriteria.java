package main.criteria;

/**
 * @author Alexey
 */
public class LinearCriteria implements Criteriable {

    private static final LinearCriteria instance = new LinearCriteria();

    public static LinearCriteria getInstance() {
        return instance;
    }

    private LinearCriteria() {
    }

    @Override
    public int calculateMark(double scored) {
        return calculateMark((int) Math.round(scored * 100));
    }

    @Override
    public String getName() {
        return "Линейная";
    }

    @Override
    public int calculateMark(int scored) {
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
