package evogame;

import execution.BattleTest;
import execution.LoadAndRun;

/**
 * Created by simonmarklucas on 22/05/2016.
 *
 * Aim is to make a simple random game and test it
 *
 *
 */
public class RandomGameVisualTest {

    public static void main(String[] args) {

        EvolvableParams evolvableParams = new EvolvableParams();

        System.out.println(evolvableParams);

        GameParameters params = new GameParameters().injectValues(evolvableParams);
        boolean visible = false;
        BattleTest.playOneTest(LoadAndRun.player1, LoadAndRun.player2, visible, params);

    }

    public static void runVisually(ParamValues evoParams) {
        GameParameters params = new GameParameters().injectValues(evoParams);

        boolean visible = true;
        BattleTest.playOneTest(LoadAndRun.player1, LoadAndRun.player2, visible, params);


    }


}
