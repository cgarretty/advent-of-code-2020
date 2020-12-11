import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class Toboggan {
  public static void main(String args[]) throws Exception {
    Map map = new Map("map.txt");
    int[] s = new int[5];
    int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
    for (int k = 0; k < slopes.length; k++) {
      Sled sled = new Sled();
      int n = 0;
      while (sled.y < map.length - 1) {
        sled.move(slopes[k][0], slopes[k][1]);
        if (map.isTree(sled.x, sled.y)) {
          n++;
        }
      }
      s[k] = n;
      System.out.println("Slope: (" + slopes[k][0] + ", " + slopes[k][1] + ")");
      System.out.println("Trees Hit: " + n);
    }
    System.out.println("Solution: " + mult(s));
    System.out.println();
  }

  static long mult(int[] arr) {
    long prod = 1;
    for (int i = 0; i < arr.length; i++) {
      prod = prod * arr[i];
    }

    return prod;
  }
}

class Map {
  public ArrayList<Row> rows = new ArrayList<Row>();
  public int length;

  Map(String filePath) throws Exception {
    Scanner scanner = new Scanner(new FileReader(filePath));
    while (scanner.hasNextLine()) {
      this.rows.add(new Row(scanner.nextLine()));
    }
    this.rows = rows;
    this.length = this.rows.size();
  }

  boolean isTree(int x, int y) {
    if (y > this.length) {
      return false;
    }
    Row row = this.rows.get(y);
    if (row.get(x) == '#') {
      return true;
    }
    return false;
  }
}

class Row {
  public char[] row;

  Row(String row) {
    char[] ch = new char[row.length()];
    for (int i = 0; i < row.length(); i++) {
      ch[i] = row.charAt(i);
    }
    this.row = ch;
  }

  char get(int x) {
    return this.row[x % this.row.length]; // modulo makes the world go 'round
  }
}

class Sled {
  public int x;
  public int y;

  void move(int x, int y) {
    this.x += x;
    this.y += y;
  }
}
