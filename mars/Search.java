package mars;

import java.util.Random;

public abstract class Search {
  protected final Planet planet;
  protected int startX, startY;

  protected final Random random = new Random();

  public Search(Planet planet, int startX, int startY) {
    this.planet = planet;
    this.startX = startX;
    this.startY = startY;
  }

  abstract protected State getInitialState();

  abstract protected State randomNeighbour(State in);

  abstract protected double objectiveFunction(State s);

  // Simulated Annealing
  public State search() {
    State current = getInitialState(), bestS = current;
    for (int T = 1000; T > 0; T--) {
      State next = randomNeighbour(current);
      double delta = objectiveFunction(next) - objectiveFunction(current);
      // choose next state if it improves the objective function or if it
      // worsens it choose it with probability exp((delta-1) / T) to escape
      // local maxima
      // with lower deperature we're less likely to choose a solution that
      // decreases the objective function
      if (delta > 0 || Math.exp((delta-1) / T) > random.nextDouble()) {
        current = next;
        if (objectiveFunction(bestS) < objectiveFunction(current)) bestS = current;
      }
    }
    return bestS;
  }

  public static void main(String[] args) {
    Search1 s1 = new Search1(new Planet(), 4, 4, 20);
    State s = s1.search();
    s.print();
    System.out.println(" --> " + s.getVisited().size() + " grids visited");

    System.out.println("-------------------");

    Search2 s2 = new Search2(new Planet(), 4, 4);
    s = s2.search();
    s.print();
    System.out.println(" --> length: " + s.size());
  }
}
