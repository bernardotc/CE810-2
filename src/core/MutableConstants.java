package core;

import evogame.DefaultParams;
import evogame.GameParameters;
import evogame.ParamValues;

/**
 * Created by bdtrev on 24/05/2016.
 */
public class MutableConstants {
    public static GameParameters params = new GameParameters().injectValues(new DefaultParams());

    public static void setMutableConstants(GameParameters param) {
        params = param;
    }
}
