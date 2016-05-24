package evogame;

/**
 * Created by simonmarklucas on 22/05/2016.
 *
 *   The purpose of this class is to take a set of evolvable values and
 *   inject them into values and data structures of direct use to the game.
 *
 *   The reason to do this is to allow the evolved parameters to be used
 *   exactly as they were in the original implementation of the game -
 *   e.g. the array of radii, without the need to develop evolvable
 *   versions of every data structure
 */


public class GameParameters {



    // these default parameters are not really needed
    // except when objects are made with the default constructor
    // which could be convenient sometimes

    // more normally call would be made to injectValues before using
    // the object

    //public int[] radii = {30, 20, 10};


    public double missileMinVelocity = 2;
    public int missileRadius = 3;
    public int missileTTL = 30;
    public int missileAngle = 170;
    public boolean missileWrappable = false;
    public double shipScaleSize = 8;
    public boolean shipThrust = true;
    public double shipSpeed = 1;
    public double shipSteerStep = 10;
    public double gravity = .01;


    public GameParameters() {
    }

    public GameParameters injectValues(ParamValues values) {

        /*radii[0] = values.getBigRadius();
        radii[1] = values.getMidRadius();
        radii[2] = values.getSmallRadius();*/

        missileMinVelocity = values.getMissileMinVelocity();
        missileRadius = values.getMissileRadius();
        missileTTL = values.getMissileTTL();
        missileAngle = values.getMissileAngle();
        missileWrappable = values.getMissileWrappable();
        shipScaleSize = values.getShipScaleSize();
        shipThrust = values.getShipThrust();
        shipSpeed = values.getShipSpeed();
        shipSteerStep = values.getShipSteerStep();
        gravity = values.getGravity();

        // returning the object allows method call chaining
        // see usage in constructor of GameState
        return this;

    }

    public GameParameters copy() {
        return this;
    }



}
