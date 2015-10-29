package tour;

import java.util.LinkedHashSet;
import java.util.Set;

import search.*;

public class HeuristicFunction implements NodeFunction {
  private final Cities cities;
  private final City goalCity;

  public HeuristicFunction(Cities cities, City goalCity) {
    this.cities = cities;
    this.goalCity = goalCity;
  }

  public int calculate(Node node) {
    if (node.state == null || !(node.state instanceof TourState)) {
      throw new RuntimeException("wrong state type (TourState expected)");
    }
    TourState state = (TourState) node.state;

    Set<City> remaining = new LinkedHashSet<City>(cities.getAllCities());
    remaining.removeAll(state.getVisitedCities());

    City furthest = null;
    int maxd = -1;
    for (City c: remaining) {
      int d = c.getShortestDistanceTo(state.getCurrentCity());
      if (d > maxd) {
        maxd = d;
        furthest = c;
      }
    }

    if (furthest == null) {
      return 0;
    }
    else {
      return maxd + furthest.getShortestDistanceTo(goalCity);
    }
  }
}
