package main.criteria;

public interface Criteriable {

    /**
     * Вычисляет оценки на основании отношения количества набранных баллов к 
     * максимально возможному числу баллов.
     * @param scored дробное число от 0 до 1
     * @return значение оценки(2, 3, 4 или 5)
     */
    public int calculateMark(double scored);
    /**
     * Вычисляет оценки на основании количества набранных баллов из 100
     * @param scored целое число от 0 до 100
     * @return значение оценки(2, 3, 4 или 5)
     */
    public int calculateMark(int scored);
    public String getName();
}
