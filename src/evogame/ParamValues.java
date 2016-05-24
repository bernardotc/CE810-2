package evogame;

/**
 * Created by simonmarklucas on 22/05/2016.
 */
public interface ParamValues {

    /*int getBigRadius();
    int getMidRadius();
    int getSmallRadius();*/

    double getMissileMinVelocity();
    int getMissileRadius();
    int getMissileTTL();
    int getMissileAngle();
    boolean getMissileWrappable();
    double getShipScaleSize();
    boolean getShipThrust();
    double getShipSteerStep();
    double getShipSpeed();
    double getGravity();

    ParamValues copy();
    ParamValues mutatedCopy();
}
