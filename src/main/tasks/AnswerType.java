package main.tasks;

public enum AnswerType {

    RIGHT,
    WRONG;

    public static AnswerType getAnswerType(int ordinal) {
        switch (ordinal) {
            case 0:
                return RIGHT;
            case 1:
                return WRONG;
        }
        return null;
    }
}
