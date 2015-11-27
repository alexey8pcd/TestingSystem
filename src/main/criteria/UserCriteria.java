package main.criteria;

/**
 * @author Alexey
 */
public final class UserCriteria implements Criteriable {

    private int scoredForThree;
    private int scoredForFour;
    private int scoredForFive;

    public UserCriteria(int scoredForThree, int scoredForFour, int scoredForFive) {
        setScoredForFive(scoredForFive);
        setScoredForFour(scoredForFour);
        setScoredForThree(scoredForThree);
    }

    public int getScoredForThree() {
        return scoredForThree;
    }

    public void setScoredForThree(int scoredForThree) {
        if (scoredForThree < 0 || scoredForThree > 98) {
            throw new IllegalArgumentException("Количество баллов для "
                    + "выставления оценки 3 не должно быть меньше 0 и больше 98");
        }
        this.scoredForThree = scoredForThree;
    }

    public int getScoredForFour() {
        return scoredForFour;
    }

    public void setScoredForFour(int scoredForFour) {
        if (scoredForThree < 0 || scoredForThree > 99) {
            throw new IllegalArgumentException("Количество баллов для "
                    + "выставления оценки 4 не должно быть меньше 0 и больше 99");
        }
        this.scoredForFour = scoredForFour;
    }

    public int getScoredForFive() {
        return scoredForFive;
    }

    public void setScoredForFive(int scoredForFive) {
        if (scoredForThree < 0 || scoredForThree > 100) {
            throw new IllegalArgumentException("Количество баллов для "
                    + "выставления оценки 5 не должно быть меньше 0 и больше 100");
        }
        this.scoredForFive = scoredForFive;
    }

    @Override
    public int calculateMark(double scored) {
        int r = (int) Math.round(scored * 100);
        if (r < scoredForThree) {
            return 2;
        }
        if (r < scoredForFour) {
            return 3;
        }
        if (r < scoredForFive) {
            return 4;
        }
        return 5;
    }

    @Override
    public String getName() {
        return "Пользовательская";
    }

    @Override
    public int calculateMark(int scored) {
        if (scored < scoredForThree) {
            return 2;
        }
        if (scored < scoredForFour) {
            return 3;
        }
        if (scored < scoredForFive) {
            return 4;
        }
        return 5;
    }

}
