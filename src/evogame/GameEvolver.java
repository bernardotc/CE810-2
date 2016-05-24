package evogame;

import evogame.EvolvableParams;
import evogame.ParamValues;
import execution.BattleTest;
import execution.LoadAndRun;
import utils.BarChart;

import java.util.ArrayList;

public class GameEvolver {

    public static int GAMES = 5;
    double bestScore;
    ParamValues bestYet;
    GameEvaluator eval;
    ArrayList<Double> fitnessHistory;

    public static void main(String[] args) {
        int nEvals = 10;
        LoadAndRun.player1 = "FUNT";
        LoadAndRun.player2 = "RHCA";
        BattleTest.NUM_GAMES_TO_PLAY= (nEvals*2+1) * GAMES + 1;

        GameEvolver evo = new GameEvolver(new EvolvableParams(), new RateEvaluator());
        //GameEvolver evo = new GameEvolver(new EvolvableParams(), new SimpleEvaluator());
        //GameEvolver evo = new GameEvolver(new EvolvableParams(), new TestEvaluator());
        evo.run(nEvals);
        System.out.println("Best Found: " + evo.bestYet);
        System.out.println("Fitness: " + evo.bestScore);


        // now run it visually

        BarChart.display(evo.fitnessHistory, "Fitness Evolution");

        RandomGameVisualTest.runVisually(evo.bestYet);

    }

    public GameEvolver(ParamValues bestYet, GameEvaluator eval) {
        this.bestYet = bestYet;
        this.eval = eval;
        this.bestScore = eval.evaluate(bestYet);
        fitnessHistory = new ArrayList<>();
        fitnessHistory.add(bestScore);
    }

    public ParamValues run(int nEvals) {
        for (int i=0; i<nEvals; i++) {
            // randomly mutate the best yet
            ParamValues mut = bestYet.mutatedCopy();
            double scoreForBest = eval.evaluate(bestYet);
            double scoreForMutation = eval.evaluate(mut);
            if (eval.betterThanOrEquals(scoreForMutation, scoreForBest)) {
                bestYet = mut;
                bestScore = scoreForMutation;
            } else {
                bestScore = scoreForBest;
            }
            //System.out.println("-------\t" + i + "\t " + bestScore);
            fitnessHistory.add(bestScore);
        }
        return bestYet;
    }
}
