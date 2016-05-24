package evogame;

import evogame.evotypes.EvoBoolean;
import evogame.evotypes.EvoDouble;

/**
 * Created by simonmarklucas on 22/05/2016.
 */
public class EvolvableParams implements ParamValues {

    // use intuition to choose uniform or mid-point random
    // values
    /*EvoDouble bigRad = new EvoDouble(20, 50).initMid();
    EvoDouble midRad = new EvoDouble(10, 30).initMid();
    EvoDouble smallRad = new EvoDouble(5, 20).initMid();*/

    EvoDouble minSpeed = new EvoDouble(1, 5).initMid();
    EvoDouble missileRadius = new EvoDouble(1, 20).initMid();
    EvoDouble ttl = new EvoDouble(10, 100).initUniform();
    EvoDouble missileAngle = new EvoDouble(150, 175).initMid();
    EvoBoolean missileWrappable = new EvoBoolean(false, true).initUniform();
    EvoDouble shipSizeScale = new EvoDouble(2, 15).initMid();
    EvoBoolean shipThrust = new EvoBoolean(false, true).initUniform();
    EvoDouble shipSpeed = new EvoDouble(.5, 3).initUniform();
    EvoDouble shipSteerStep = new EvoDouble(1,20).initUniform();
    EvoDouble gravity = new EvoDouble(.01, .1).initMid();

    /*@Override
    public int getBigRadius() {
        return bigRad.intValue();
    }

    @Override
    public int getMidRadius() {
        return midRad.intValue();
    }

    @Override
    public int getSmallRadius() {
        return smallRad.intValue();
    }*/

    @Override
    public double getMissileMinVelocity() {
        return minSpeed.doubleValue();
    }

    @Override
    public int getMissileRadius() {
        return missileRadius.intValue();
    }

    @Override
    public int getMissileTTL() {
        return ttl.intValue();
    }

    @Override
    public int getMissileAngle() {
        return missileAngle.intValue();
    }

    @Override
    public boolean getMissileWrappable() {
        return missileWrappable.booleanValue();
    }

    @Override
    public double getShipScaleSize() {
        return shipSizeScale.doubleValue();
    }

    @Override
    public boolean getShipThrust() {
        return shipThrust.booleanValue();
    }

    @Override
    public double getGravity() {
        return gravity.doubleValue();
    }

    @Override
    public double getShipSteerStep() {
        return shipSteerStep.doubleValue();
    }

    @Override
    public double getShipSpeed() {
        return shipSpeed.doubleValue();
    }

    public ParamValues mutatedCopy() {

        // would be much better to put all of these in
        // to a data structure (e.g. a Map) then simply make a copy
        // of the map and mutate all it's values!
        EvolvableParams copy = new EvolvableParams();
        /*copy.bigRad = bigRad.copy().mutate();
        copy.midRad = midRad.copy().mutate();
        copy.smallRad = smallRad.copy().mutate();*/
        copy.minSpeed = minSpeed.copy().mutate();
        copy.missileRadius = missileRadius.copy().mutate();
        copy.ttl = ttl.copy().mutate();
        copy.missileAngle = missileAngle.copy().mutate();
        copy.missileWrappable = missileWrappable.copy().mutate();
        copy.shipSizeScale = shipSizeScale.copy().mutate();
        copy.shipThrust = shipThrust.copy().mutate();
        copy.shipSpeed = shipSpeed.copy().mutate();
        copy.shipSteerStep = shipSteerStep.copy().mutate();
        copy.gravity = gravity.copy().mutate();

        return copy();
    }

    public ParamValues copy() {

        // would be much better to put all of these in
        // to a data structure (e.g. a Map) then simply make a copy
        // of the map and mutate all it's values!
        EvolvableParams copy = new EvolvableParams();
        /*copy.bigRad = bigRad.copy();
        copy.midRad = midRad.copy();
        copy.smallRad = smallRad.copy();*/
        copy.minSpeed = minSpeed.copy();
        copy.missileRadius = missileRadius.copy();
        copy.ttl = ttl.copy();
        copy.missileAngle = missileAngle.copy();
        copy.missileWrappable = missileWrappable.copy();
        copy.shipSizeScale = shipSizeScale.copy();
        copy.shipThrust = shipThrust.copy();
        copy.shipSpeed = shipSpeed.copy();
        copy.shipSteerStep = shipSteerStep.copy();
        copy.gravity = gravity.copy();

        return copy;
    }

    // could replace this with a JSON version (using the gson library)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append(String.format("Radii: [%d, %d, %d]\n", bigRad.intValue(), midRad.intValue(), smallRad.intValue()));
        sb.append("Missile speed:  " + minSpeed + "\n");
        sb.append("Missile radius: " + missileRadius.intValue() + "\n");
        sb.append("Missile TTL:    " + ttl.intValue() + "\n");
        sb.append("Missile Angle:    " + missileAngle.intValue() + "\n");
        sb.append("Missile Wrappable:    " + missileWrappable.booleanValue() + "\n");
        sb.append("Ship Scale:    " + shipSizeScale.doubleValue() + "\n");
        sb.append("Ship Thrust:    " + shipThrust.booleanValue() + "\n");
        sb.append("Ship Speed:    " + shipSpeed.doubleValue() + "\n");
        sb.append("Ship Steer:    " + shipSteerStep.doubleValue() + "\n");
        sb.append("Gravity:    " + gravity.doubleValue() + "\n");
        return sb.toString();
    }
}
