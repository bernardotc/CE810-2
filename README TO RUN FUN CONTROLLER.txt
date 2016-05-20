FUN controller can be found in the controllers package. The package named funGAController will have all the source code.

To run FUN team controller named FUNT (T comes from Triple parent crossover), you need to declare the 
following case in the createPlayer method of BattleTest.java 

case "FUNT":
	return new controller.funGAController.BattleEvoController(new controller.funGAController.search.CoevSearch(
			new controller.funGAController.strategy.TripleParentCrossover(rnd1),
			new controller.funGAController.strategy.PMutation(rnd1),
			new controller.funGAController.strategy.TournamentSelection(rnd1),
			new controller.funGAController.strategy.RandomPairing(rnd1),
			rnd1));
			
The next step is to simply put FUNT as one of the players in the cofing.properties file.