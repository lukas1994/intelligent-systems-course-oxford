package mars;

public class Move {
  public final int dx, dy;
  public char c;

  public static Move E = new Move(0, 1, 'E');
  public static Move W = new Move(0, -1, 'W');
  public static Move N = new Move(-1, 0, 'N');
  public static Move S = new Move(1, 0, 'S');

  public static Move[] MOVES = new Move[] {E, W, N, S};

  private Move(int dx, int dy, char c) {
    this.dx = dx;
    this.dy = dy;
    this.c = c;
  }

  public Move back() {
    switch (c) {
      case 'E': return W;
      case 'W': return E;
      case 'N': return S;
      case 'S': return N;
    }
    throw new RuntimeException("back: invalid move");
  }

  public String toString() {
    return ""+c;
  }
}
