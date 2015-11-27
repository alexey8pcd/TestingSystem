package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
Author Alexey
*/
public class Parameters {
    public static final Dimension COMPONENT_SIZE = new Dimension(100, 20);
    public static final Dimension SCREEN_SIZE = 
            Toolkit.getDefaultToolkit().getScreenSize();
    public static final int PASSWORD_LENGTH = 5;
    public static final Color BACKGROUND_COLOR = Color.CYAN;
    public static final Color RIGHT_ANSWER_COLOR = Color.GREEN;
    public static final Color WRONG_ANSWER_COLOR = Color.RED;
}
