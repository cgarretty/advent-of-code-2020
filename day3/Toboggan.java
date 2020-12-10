import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class Toboggan {
  public static void main(String args[]) throws Exception {
    Map map = new Map("map.txt");
    Sled sled = new Sled();
    int moves = map.length - 1;
    int n = 0;
    for (int i = 0; i < moves; i++) {
      sled.move(3, 1);
      if (map.isTree(sled.x, sled.y)) {
        n++;
      }
    }
    System.out.println("Moves Made: " + map.length);
    System.out.println("Tree Hit: " + n);
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
