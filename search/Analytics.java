package search;

// singleton class to keep track of the number of nodes generated and the max
// frontier size
public class Analytics {
  private static final Analytics INSTANCE = new Analytics();

  private static int numNodes = 0;
  private static int maxFrontierSize = 0;

  public static Analytics getInstance() {
    return INSTANCE;
  }

  public void newNodeGenerated() {
    numNodes++;
  }
  public void updateMaxFrontierSize(int size) {
    if (maxFrontierSize < size) {
      maxFrontierSize = size;
    }
  }

  public int getNumNodes() {
    return numNodes;
  }
  public int getMaxFrontierSize() {
    return maxFrontierSize;
  }
}
