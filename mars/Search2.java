package mars;

import java.util.HashSet;

public class Search2 {
  private final Planet planet;
  private int startX, startY;

  private HashSet<Position> visited;
  private State current;

  public Search2(Planet planet, int startX, int startY) {
    this.planet = planet;
    this.startX = startX;
    this.startY = startY;
  }

  private void dfs(Position p) {
    visited.add(p);
    for (Move m: Move.MOVES) {
      Position next = p.next(m);
      if (planet.isAccessible(next) && !visited.contains(next)) {
        current.add(m);
        if (visited.size() == 27) return;
        dfs(next);
        current.add(m.back());
      }
    }
  }

  // construct a spanning tree
  public State search() {
    Position p = new Position(startX, startY);
    visited = new HashSet<>();
    current = new State(p);
    dfs(p);
    return current;
  }
}
