package mars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/*
 * RESULTS:
 *  best: SWWNWNNSWESESESESNEENNNEWWNNEWWNSWSNW (37)
 */
public class Search2 extends Search {
  private final HashMap<Position, HashMap<Position, LinkedList<Move>>> SP;
  private HashSet<Position> visited;

  public Search2(Planet planet, int startX, int startY) {
    super(planet, startX, startY);
    SP = new HashMap<>();
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        if (planet.isAccessible(Position.get(x, y))) {
          SP.put(Position.get(x, y), new HashMap<Position, LinkedList<Move>>());
          bfs(Position.get(x, y));
        }
      }
    }
  }

  private static class BFSWrapper {
    public Position position;
    public LinkedList<Move> path;
    public BFSWrapper(Position position, LinkedList<Move> path) {
      this.position = position;
      this.path = path;
    }
  }

  private void bfs(Position start) {
    HashSet<Position> visited = new HashSet<>();
    LinkedList<BFSWrapper> q = new LinkedList<>();
    q.add(new BFSWrapper(start, new LinkedList<Move>()));
    while (!q.isEmpty()) {
      BFSWrapper current = q.remove();
      SP.get(start).put(current.position, current.path);
      visited.add(current.position);
      for (Move m: Move.MOVES) {
        Position next = current.position.next(m);
        if (planet.isAccessible(next) && !visited.contains(next)) {
          LinkedList<Move> newPath = new LinkedList<>(current.path);
          newPath.add(m);
          q.add(new BFSWrapper(next, newPath));
        }
      }
    }
  }

  private State extendState(State s) {
    Position position = s.getCurrentPosition();
    while (true) {
      HashSet<Position> remaining = new HashSet<>(SP.keySet());
      remaining.removeAll(s.getVisited());
      if (remaining.size() == 0) break;

      // try to visit neighbour
      LinkedList<Move> newOptions = new LinkedList<>();
      for (Move m: Move.MOVES) {
        Position next = position.next(m);
        if (remaining.contains(next)) {
          newOptions.add(m);
        }
      }
      if (newOptions.size() > 0) {
        Move m = newOptions.get(random.nextInt(newOptions.size()));
        s.add(m);
        position = position.next(m);
      }
      else {
        // jump to different place
        Position bestNext = null;
        int bestD = -1;
        for (Position next: remaining) {
          int d = SP.get(position).get(next).size();
          if (bestD == -1 || bestD > d) {
            bestNext = next;
            bestD = d;
          }
        }
        assert (bestNext != null);

        LinkedList<Move> moves = SP.get(position).get(bestNext);
        for (Move m: moves) {
          s.add(m);
          position = position.next(m);
        }
      }
    }

    return s;
  }

  protected State getInitialState() {
    Position position = Position.get(startX, startY);
    State s = new State(position);
    return extendState(s);
  }

  protected State randomNeighbour(State in) {
    Position position = Position.get(startX, startY);
    State out = new State(position);
    int use = random.nextInt(in.size());
    for (int i = 0; i < use; i++) {
      out.add(in.get(i));
    }
    return extendState(out);
  }

  // we want to minimize the length of the path
  protected double objectiveFunction(State s) {
    // negative size() because we want to minimize the objective function and
    // search() maximizes the objective function
    return -s.size();

  }
}
