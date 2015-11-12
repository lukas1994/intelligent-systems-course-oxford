package mars;

import java.util.LinkedList;
import java.util.HashSet;

public class State extends LinkedList<Move> {
  private final Position startPosition;
  private Position currentPosition;
  private final HashSet<Position> visited = new HashSet<>();

  public State(Position startPosition) {
    super();
    this.startPosition = startPosition;
    this.currentPosition = startPosition;
    visited.add(currentPosition);
  }

  public boolean add(Move m) {
    currentPosition = currentPosition.next(m);
    visited.add(currentPosition);
    return super.add(m);
  }

  public boolean contains(Position p) {
    return visited.contains(p);
  }

  public Position getCurrentPosition() {
    return currentPosition;
  }

  public void print() {
    for (Move m: this) System.out.print(m.toString());
    System.out.println();
    System.out.println(" --> value: " + value());
  }

  public int value() {
    HashSet<Position> s = new HashSet<>();
    Position p = new Position(0, 0);
    s.add(p);
    for (Move m: this) {
      p = p.next(m);
      s.add(p);
    }
    return s.size();
  }
}
