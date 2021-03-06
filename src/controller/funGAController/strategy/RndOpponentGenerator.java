package controller.funGAController.strategy;

import controller.funGAController.strategy.*;
import controller.funGAController.strategy.OpponentGenerator;
import core.ActionMap;
import controller.funGAController.search.GAIndividual;

import java.util.Random;

/**
 * Created by dperez on 19/01/16.
 */
public class RndOpponentGenerator implements OpponentGenerator {

    Random rnd;

    public RndOpponentGenerator (Random rnd)
    {
        this.rnd = rnd;
    }

    @Override
    public GAIndividual getOpponent(int numActions) {
        GAIndividual ind = new GAIndividual(numActions, -1, null);
        ind.randomize(rnd, ActionMap.ActionMap.length);
        return ind;
    }
}
