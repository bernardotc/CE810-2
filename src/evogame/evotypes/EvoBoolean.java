package evogame.evotypes;

import java.util.Random;

/**
 * Created by bdtrev on 23/05/2016.
 */
public class EvoBoolean {

    Random rand = new Random();

    public static void main(String[] args) {
        // test it

        EvoBoolean evo = new EvoBoolean(false, true);
        System.out.println(evo);


    }

    boolean min;
    boolean max;

    // difference between min and max
    double range;

    boolean x;

    public EvoBoolean(boolean min, boolean max) {
        this.min = min;
        this.max = max;
        range = (max != min) ? 1 : 0;
    }

    public EvoBoolean(boolean min, boolean max, boolean def) {
        this.min = min;
        this.max = max;
        range = (max != min) ? 1 : 0;
        x = def;
    }


    public boolean booleanValue() { return x; }

    public EvoBoolean initUniform() {
        x = rand.nextBoolean();
        return this;
    }

    public EvoBoolean mutate() {
        x = rand.nextBoolean();
        return this;
    }

    public EvoBoolean copy() {
        return new EvoBoolean(min, max, x);
    }

    public String toString() {
        return String.format("",x);
    }

}
