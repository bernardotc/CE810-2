package evogame;

import core.SimpleBattle;
import execution.BattleTest;
import execution.LoadAndRun;
import utils.StatSummary;

/**
 * Created by bdtrev on 23/05/2016.
 */
public class RateEvaluator implements GameEvaluator {
    @Override
    public double evaluate(ParamValues evoParams) {
        double perfectLives = 1;
        GameParameters params = new GameParameters().injectValues(evoParams);
        boolean visible = false;
        StatSummary summary = new StatSummary();

        for (int x = 0; x < GameEvolver.GAMES; x++) {
            SimpleBattle battle = BattleTest.playOneTest(LoadAndRun.player1, LoadAndRun.player2, visible, params);
            //System.out.println(battle.stats.get(battle.s1.getId()).life);
            summary.add(battle.stats.get(battle.s1.getId()).life);
        }

        summary.mean();
        //System.out.print(summary.toString());
        return Math.abs(perfectLives-summary.mean());
    }

    @Override
    public boolean betterThanOrEquals(double x, double y) {
        return x <= y;
    }
}
