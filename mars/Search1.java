package mars;

import java.util.Random;
import java.util.LinkedList;

/*
 * RESULTS:
 *  best: ESENEWNNWWWSWWSSSNEW (18)
 */
public class Search1 {
  private final Planet planet;
  private int startX, startY, maxFuel;

  private final Random random = new Random();

  public Search1(Planet planet, int startX, int startY, int maxFuel) {
    this.planet = planet;
    this.startX = startX;
    this.startY = startY;
    this.maxFuel = maxFuel;
  }

  private State extendState(State s, int length) {
    Position position = s.getCurrentPosition();
    for (int i = 0; i < length; i++) {
      LinkedList<Move> allOpptions = new LinkedList<>();
      LinkedList<Move> newOptions = new LinkedList<>();
      for (Move m: Move.MOVES) {
        Position next = position.next(m);
        if (planet.isAccessible(next)) {
          allOpptions.add(m);
          if (!s.contains(next)) {
            newOptions.add(m);
          }
        }
      }
      LinkedList<Move> options = newOptions;
      if (options.size() == 0) options = allOpptions;
      assert (options.size() > 0);
      Move m = options.get(random.nextInt(options.size()));
      s.add(m);
      position = position.next(m);
    }
    return s;
  }

  private State getInitialState() {
    Position position = new Position(startX, startY);
    State s = new State(position);
    return extendState(s, maxFuel);
  }

  private State randomNeighbour(State in) {
    Position position = new Position(startX, startY);
    State out = new State(position);
    int use = random.nextInt(in.size());
    for (int i = 0; i < use; i++) {
      out.add(in.get(i));
    }
    return extendState(out, in.size() - use);
  }

  // Simulated Annealing
  public State search() {
    State current = getInitialState(), bestS = current;
    for (int T = 1000; T > 0; T--) {
      State next = randomNeighbour(current);
      int delta = next.value() - current.value();
      // choose next state if it improves the objective function or if it
      // worsens it choose it with probability exp((delta-1) / T) to escape
      // local maxima
      // with lower deperature we're less likely to choose a solution that
      // decreases the objective function
      if (delta > 0 || Math.exp((delta-1) / T) > random.nextDouble()) {
        current = next;
        if (bestS.value() < current.value()) bestS = current;
      }
    }
    return bestS;
  }
}
