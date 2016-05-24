package evogame;

/**
 * Created by bdtrev on 24/05/2016.
 */
public class TryParams implements ParamValues {

    /*@Override
    public int getBigRadius() {
        return 50;
    }

    @Override
    public int getMidRadius() {
        return 10;
    }

    @Override
    public int getSmallRadius() {
        return 30;
    }*/

    @Override
    public double getMissileMinVelocity() {
        return 4;
    }

    @Override
    public int getMissileRadius() {
        return 4;
    }

    @Override
    public int getMissileTTL() {
        return 100;
    }

    @Override
    public int getMissileAngle() {
        return 170;
    }

    @Override
    public boolean getMissileWrappable() {
        return true;
    }

    @Override
    public double getShipScaleSize() {
        return 5;
    }

    @Override
    public boolean getShipThrust() {
        return true;
    }

    @Override
    public double getShipSteerStep() {
        return 10;
    }

    @Override
    public double getShipSpeed() {
        return 1;
    }

    @Override
    public double getGravity() {
        return 0.0;
    }

    public ParamValues copy() {
        // for now just return this, but should really make a proper copy
        return this;
    }

    @Override
    public ParamValues mutatedCopy() {
        return null;
    }
}
