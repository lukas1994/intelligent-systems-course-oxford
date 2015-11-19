package mars;

import java.util.LinkedList;

/*
 * RESULTS:
 *  best: ESENEWNNWWWSWWSSSNEW (18)
 */
public class Search1 extends Search {
  private int maxFuel;

  public Search1(Planet planet, int startX, int startY, int maxFuel) {
    super(planet, startX, startY);
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

  protected State getInitialState() {
    Position position = Position.get(startX, startY);
    State s = new State(position);
    return extendState(s, maxFuel);
  }

  protected State randomNeighbour(State in) {
    Position position = Position.get(startX, startY);
    State out = new State(position);
    int use = random.nextInt(in.size());
    for (int i = 0; i < use; i++) {
      out.add(in.get(i));
    }
    return extendState(out, in.size() - use);
  }

  // we want to maximize the number of visited squares
  protected double objectiveFunction(State s) {
    return s.getVisited().size();
  }
}
