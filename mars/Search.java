package mars;

public class Search {
  public static void main(String[] args) {
    Search1 s1 = new Search1(new Planet(), 4, 4, 20);
    s1.search().print();

    System.out.println("-------------------");

    Search2 s2 = new Search2(new Planet(), 4, 4);
    s2.search().print();
  }
}
