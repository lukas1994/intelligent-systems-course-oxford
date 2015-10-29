package tour;

import search.*;

public class BFTS_Demo {
	public static final boolean TREE = false;
	public static final boolean BFS = false;
	public static final boolean ITD = true;

	public static void main(String[] args) {
		Cities romania = SetUpRomania.getRomaniaMap();
		//Cities romania = SetUpRomania.getRomaniaMapSmall();
		City startCity = romania.getState("Bucharest");

		NodeFunction nodeFunction = new AStarFunction(new HeuristicFunction(romania, startCity));
		Frontier frontier = new BestFirstFrontier(nodeFunction);

		Search search = new GraphSearch(frontier);

		GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
		Node root = new Node(null, null, new TourState(startCity));
		Node solution = search.runSearch(root, goalTest);
		new TourPrinting().printSolution(solution);

		System.out.println(Analytics.getInstance().getNumNodes() + " nodes generated.");
		System.out.println("max frontier size: " + Analytics.getInstance().getMaxFrontierSize());
	}
}
