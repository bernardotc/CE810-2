package execution;

import core.*;
import controller.*;
import controller.human.*;
import controller.sampleRHEA.*;
import controller.sampleRHEA.search.*;
import controller.sampleRHEA.strategy.*;
import controller.sampleOLMCTS.*;
import controller.nullController.NullController;
import controller.onesteplookahead.OneStepLookAhead;
import controller.fireForwardController.FireForwardController;
import controller.random.RandomController;
import controller.rotateAndShoot.RotateAndShoot;
import evogame.GameParameters;
import evogame.ParamValues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 * Created by simon lucas on 10/06/15.
 */
public class BattleTest {
    BattleView view;
    public static boolean VISUAL;
    public static boolean SHOW_ROLLOUTS;
    public static int MAX_TICKS_GAME;
    public static int NUM_GAMES_TO_PLAY;
    public static SimpleBattle battle;

    public static void playOneTest(String ply1, String ply2)
    {
        boolean visuals = true;
        SimpleBattle battle = new SimpleBattle(visuals, MAX_TICKS_GAME);
        BattleController p1 = createPlayer(ply1);
        BattleController p2 = createPlayer(ply2);

        double []res = battle.playGame(p1, p2);
    }

    public static SimpleBattle playOneTest(String ply1, String ply2, boolean visuals, GameParameters params)
    {
        battle = new SimpleBattle(visuals, 4000, params);
        BattleController p1 = createPlayer(ply1);
        BattleController p2 = createPlayer(ply2);

        double []res = battle.playGame(p1, p2);
        return battle;
    }

    public static void playN(String ply1, String ply2, String filename)
    {
        boolean visuals = false;
        double[][] results = new double[NUM_GAMES_TO_PLAY][MAX_TICKS_GAME*3];

        for(int i = 0; i < NUM_GAMES_TO_PLAY; ++i) {

            SimpleBattle battle = new SimpleBattle(visuals, MAX_TICKS_GAME);
            BattleController p1 = createPlayer(ply1);
            BattleController p2 = createPlayer(ply2);

            double []res = battle.playGame(p1, p2);
            System.arraycopy(res, 0, results[i], 0, MAX_TICKS_GAME*3);
        }

        int p1Wins, p1Ticks, p1Life, p1fFire, p1Points;
        int p2Wins, p2Ticks, p2Life, p2fFire, p2Points;
        p1Wins = p1Ticks = p1Life = p1fFire = p1Points = p2Wins = p2Ticks =  p2Life = p2fFire = p2Points = 0;
        for (int x = 0; x < NUM_GAMES_TO_PLAY; x++) {
            p1Wins += SimpleBattle.p1Statistics[x][0];
            p1Ticks += SimpleBattle.p1Statistics[x][1];
            p1Life += SimpleBattle.p1Statistics[x][2];
            p1fFire += SimpleBattle.p1Statistics[x][3];
            p1Points += SimpleBattle.p1Statistics[x][4];

            p2Wins += SimpleBattle.p2Statistics[x][0];
            p2Ticks += SimpleBattle.p2Statistics[x][1];
            p2Life += SimpleBattle.p2Statistics[x][2];
            p2fFire += SimpleBattle.p2Statistics[x][3];
            p2Points += SimpleBattle.p2Statistics[x][4];
        }
        System.out.println("Total wins player 1 = " + p1Wins + " " + p1Wins/(double)BattleTest.NUM_GAMES_TO_PLAY + "% / " + p1Ticks / NUM_GAMES_TO_PLAY + " / " + p1Life / (double) NUM_GAMES_TO_PLAY + " / " + p1fFire / (double) NUM_GAMES_TO_PLAY + " / " + p1Points / (double) NUM_GAMES_TO_PLAY);
        System.out.println("Total wins player 2 = " + p2Wins + " " + p2Wins/(double)BattleTest.NUM_GAMES_TO_PLAY + "% / " + p2Ticks / NUM_GAMES_TO_PLAY + " / " + p2Life / (double) NUM_GAMES_TO_PLAY + " / " + p2fFire / (double) NUM_GAMES_TO_PLAY + " / " + p2Points / (double) NUM_GAMES_TO_PLAY);
        System.out.println("Done.");
        //dump(results, filename);
    }

    public static void playN(String ply1, String ply2, String filename, GameParameters params)
    {
        boolean visuals = false;
        double[][] results = new double[NUM_GAMES_TO_PLAY][MAX_TICKS_GAME*3];
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));

            for(int i = 0; i < NUM_GAMES_TO_PLAY; ++i) {
    
                SimpleBattle battle = new SimpleBattle(visuals, MAX_TICKS_GAME, params);
                BattleController p1 = createPlayer(ply1);
                BattleController p2 = createPlayer(ply2);
    
                battle.playGame(p1, p2);
                writer.write("*************************************** GAME " + (i + 1) + " ***************************************\n");
                writer.write("--------------------------------------- ACTIONS PLAYER 1 AND 2 ---------------------------------------\n");
                for (int x = 0; x < battle.ship1Actions.length; x++) {
                    if (battle.ship1Actions[x] == null && battle.ship2Actions[x] == null)
                        break;
                    writer.write("P1," + battle.ship1Actions[x] + ",P2," + battle.ship2Actions[x] + "\n");
                }
                writer.newLine();
                writer.newLine();
                writer.newLine();
                writer.write("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ POSITION MAP PLAYER 1 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        writer.write(battle.ship1PositionMatrix[x][y] + ",");
                    }
                    writer.write("\n");
                }
                writer.write("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ POSITION MAP PLAYER 2 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        writer.write(battle.ship2PositionMatrix[x][y] + ",");
                    }
                    writer.newLine();
                }
                writer.newLine();
                writer.newLine();
                writer.newLine();
            }
    
            int p1Wins, p1Ticks, p1Life, p1fFire, p1Points;
            int p2Wins, p2Ticks, p2Life, p2fFire, p2Points;
            p1Wins = p1Ticks = p1Life = p1fFire = p1Points = p2Wins = p2Ticks =  p2Life = p2fFire = p2Points = 0;
            for (int x = 0; x < NUM_GAMES_TO_PLAY; x++) {
                p1Wins += SimpleBattle.p1Statistics[x][0];
                p1Ticks += SimpleBattle.p1Statistics[x][1];
                p1Life += SimpleBattle.p1Statistics[x][2];
                p1fFire += SimpleBattle.p1Statistics[x][3];
                p1Points += SimpleBattle.p1Statistics[x][4];
    
                p2Wins += SimpleBattle.p2Statistics[x][0];
                p2Ticks += SimpleBattle.p2Statistics[x][1];
                p2Life += SimpleBattle.p2Statistics[x][2];
                p2fFire += SimpleBattle.p2Statistics[x][3];
                p2Points += SimpleBattle.p2Statistics[x][4];

                writer.write("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& GAME " + (x + 1) + " STATISTICS &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
                writer.write("Game,Player 1,Wins,Ticks,Life,Fire,Points,Player 2,Wins,Ticks,Life,Fire,Points\n");
                writer.write(x + ",Player 1 =," + SimpleBattle.p1Statistics[x][0] + "," + SimpleBattle.p1Statistics[x][1] + "," + SimpleBattle.p1Statistics[x][2] + "," + SimpleBattle.p1Statistics[x][3] + "," + SimpleBattle.p1Statistics[x][4] + "," + ",Player 2 =," + SimpleBattle.p2Statistics[x][0] + "," + SimpleBattle.p2Statistics[x][1] + "," + SimpleBattle.p2Statistics[x][2] + "," + SimpleBattle.p2Statistics[x][3] + "," + SimpleBattle.p2Statistics[x][4] + "\n");
            }
            writer.newLine();
            writer.newLine();
            writer.newLine();

            writer.write("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ GAMES AVERAGE SUMMARY $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
            writer.write(",Wins,Wins %,Ticks,Lives, Fire, Points\n");
            writer.write("Total wins player 1 (" + LoadAndRun.player1 + ") =," + p1Wins + "," + p1Wins / (double) BattleTest.NUM_GAMES_TO_PLAY + "%," + p1Ticks / NUM_GAMES_TO_PLAY + "," + p1Life / (double) NUM_GAMES_TO_PLAY + "," + p1fFire / (double) NUM_GAMES_TO_PLAY + "," + p1Points / (double) NUM_GAMES_TO_PLAY + "\n");
            writer.write("Total wins player 2 (" + LoadAndRun.player2 + ") =." + p2Wins + "," + p2Wins / (double) BattleTest.NUM_GAMES_TO_PLAY + "%," + p2Ticks / NUM_GAMES_TO_PLAY + "," + p2Life / (double) NUM_GAMES_TO_PLAY + "," + p2fFire / (double) NUM_GAMES_TO_PLAY + "," + p2Points / (double) NUM_GAMES_TO_PLAY + "\n");
            System.out.println("Total wins player 1 = " + p1Wins + " " + p1Wins / (double) BattleTest.NUM_GAMES_TO_PLAY + "% / " + p1Ticks / NUM_GAMES_TO_PLAY + " / " + p1Life / (double) NUM_GAMES_TO_PLAY + " / " + p1fFire / (double) NUM_GAMES_TO_PLAY + " / " + p1Points / (double) NUM_GAMES_TO_PLAY);
            System.out.println("Total wins player 2 = " + p2Wins + " " + p2Wins/(double)BattleTest.NUM_GAMES_TO_PLAY + "% / " + p2Ticks / NUM_GAMES_TO_PLAY + " / " + p2Life / (double) NUM_GAMES_TO_PLAY + " / " + p2fFire / (double) NUM_GAMES_TO_PLAY + " / " + p2Points / (double) NUM_GAMES_TO_PLAY);
            System.out.println("Done.");
            
            writer.close();
        } catch(Exception e)
        {
            System.out.println("MEH: " + e.toString());
            e.printStackTrace();
        }
    }

    private static void dump(double[][] results, String filename)
    {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));

            for (int i = 0; i < results.length; ++i) {
                for (int j = 0; j < results[i].length; ++j) {
                    writer.write(results[i][j] + ",");
                }
                writer.write("\n");
            }

            writer.close();

        }catch(Exception e)
        {
            System.out.println("MEH: " + e.toString());
            e.printStackTrace();
        }
    }


    public static BattleController createPlayer(String ply)
    {
        Random rnd1 = new Random();

        switch (ply)
        {
            case "RHCA":
                return new BattleEvoController(new CoevSearch(
                        new UniformCrossover(rnd1),
                        new PMutation(rnd1),
                        new TournamentSelection(rnd1),
                        new RandomPairing(rnd1),
                        rnd1));
            case "RHGA":
                return new BattleEvoController(new GASearch(
                        new UniformCrossover(rnd1),
                        new PMutation(rnd1),
                        new TournamentSelection(rnd1),
                        new RndOpponentGenerator(rnd1),
                        rnd1));
            case "ONESTEP":
                return new OneStepLookAhead();
            case "OLMCTS":
                return new SingleMCTSPlayer(rnd1);
            case "NULL":
                return new NullController();
            case "RND":
                return new RandomController(rnd1);
            case "WASD":
                return new WASDController();
            case "ARROWS":
                return new ArrowsController();
            case "FIREFOR":
                return new FireForwardController();
            case "RAS":
                return new RotateAndShoot();
            case "DETOLMCTS":
                return new SingleMCTSPlayerDetOpp(rnd1);
            case "FUN":
                return new controller.funGAController.BattleEvoController(new controller.funGAController.search.CoevSearch(
                        new controller.funGAController.strategy.IVCrossover(rnd1),
                        new controller.funGAController.strategy.PMutation(rnd1),
                        new controller.funGAController.strategy.TournamentSelection(rnd1),
                        new controller.funGAController.strategy.RandomPairing(rnd1),
                        rnd1));
            case "FUNT":
                return new controller.funGAController.BattleEvoController(new controller.funGAController.search.CoevSearch(
                        new controller.funGAController.strategy.TripleParentCrossover(rnd1),
                        new controller.funGAController.strategy.PMutation(rnd1),
                        new controller.funGAController.strategy.TournamentSelection(rnd1),
                        new controller.funGAController.strategy.RandomPairing(rnd1),
                        rnd1));
            case "FUNUNIFORM":
                return new controller.funGAController.BattleEvoController(new controller.funGAController.search.CoevSearch(
                        new controller.funGAController.strategy.UniformCrossover(rnd1),
                        new controller.funGAController.strategy.PMutation(rnd1),
                        new controller.funGAController.strategy.TournamentSelection(rnd1),
                        new controller.funGAController.strategy.RandomPairing(rnd1),
                        rnd1));
        }

        return new ArrowsController();
    }


}
