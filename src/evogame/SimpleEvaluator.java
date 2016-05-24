package evogame;

import core.*;
import utils.ElapsedCpuTimer;

/**
 * Created by simonmarklucas on 22/05/2016.
 */
/*public class SimpleEvaluator implements GameEvaluator {
    @Override
    public double evaluate(ParamValues evoParams) {

        // this evaluator optimises for a high score
        // using a particular agent and given a number
        // of game ticks

        GameParameters params = new GameParameters().injectValues(evoParams);
        int startLevel = 1;
        int nLives = 5;
        boolean visible = false;
        int nTicks = 1000;
        GameState gameState = new GameState(params, startLevel, nLives);
        Game game = new Game(gameState, visible);

        ElapsedTimer t = new ElapsedTimer();
        game.run(nTicks);
        System.out.println(t);

        System.out.println(gameState.score);

        return gameState.score;
    }

    @Override
    public boolean betterThanOrEquals(double x, double y) {
        return x >= y;
    }


}*/
