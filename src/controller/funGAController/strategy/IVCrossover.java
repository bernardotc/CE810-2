package controller.funGAController.strategy;

import java.util.Random;

/**
 * Created by bdtrev on 19/05/2016.
 */
public class IVCrossover implements ICrossover {
    Random rnd;

    public IVCrossover(Random rnd)
    {
        this.rnd = rnd;
    }

    @Override
    public controller.funGAController.search.GAIndividual uniformCross(controller.funGAController.search.GAIndividual parentA, controller.funGAController.search.GAIndividual parentB) {

        int[] newInd = new int[parentA.m_genome.length];

        int randomInt = rnd.nextInt(parentA.m_genome.length);

        for(int i = 0; i < parentA.m_genome.length; ++i) {
            if (randomInt == i) {
                    if (rnd.nextFloat() < 0.5f) {
                        newInd[i] = parentA.m_genome[rnd.nextInt(parentA.m_genome.length)];
                    } else {
                        newInd[i] = parentA.m_genome[rnd.nextInt(parentB.m_genome.length)];
                    }
            } else {
                if (parentA.m_genome[i] != parentB.m_genome[i]) {
                    if (rnd.nextFloat() < 0.5f) {
                        newInd[i] = parentA.m_genome[i];
                    } else {
                        newInd[i] = parentB.m_genome[i];
                    }
                } else {
                    newInd[i] = parentA.m_genome[i];
                }
            }
        }

        return new controller.funGAController.search.GAIndividual(newInd, parentA.playerID, parentA.search);
    }
}