package mars;

public class Position {
  public final int x, y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Position next(Move m) {
    return new Position(x + m.dx, y + m.dy);
  }

  @Override
  public int hashCode() {
    return 2399 * x + y;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position))
      return false;
    if (obj == this)
      return true;

    Position other = (Position) obj;
    return x == other.x && y == other.y;
  }
}
