package npuzzle;

import search.*;

public class MisplacedTilesHeuristicFunction implements NodeFunction {

  public int calculate(Node node) {
    if (node.state == null || !(node.state instanceof Tiles)) {
      throw new RuntimeException("wrong state type (Tiles expected)");
    }
    Tiles tiles = (Tiles) node.state;
    int num = 1, error = 0;
    for (int i = 0; i < tiles.getWidth(); i++) {
      for (int j = 0; j < tiles.getWidth(); j++) {
        int tile = tiles.getTile(i, j);
        if (tile != Tiles.EMPTY_TILE && tile != num) {
          error++;
        }
        num++;
      }
    }
    return error;
  }
}
