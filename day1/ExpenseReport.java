import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class ExpenseReport {
  public static void main(String args[]) throws Exception {
    Expenses expenses = new Expenses();
    expenses.ReadExpenses("expenses.txt");
    expenses.Solve(3, 0);
  }
}

class Expenses {
  public int[] expenses;
  public int[] counters;

  void ReadExpenses(String filePath) throws Exception {
    Scanner scanner = new Scanner(new FileReader(filePath));
    int[] expenses = new int[200];
    int i = 0;

    while (scanner.hasNextInt()) {
      expenses[i++] = scanner.nextInt();
    }
    this.expenses = expenses;
  }

  static int Sum(int[] arr) {
    int i;
    int sum = 0;

    for (i = 0; i < arr.length; i++) sum += arr[i];

    return sum;
  }

  static int Mult(int[] arr) {
    int i;
    int prod = arr[0];

    for (i = 1; i < arr.length; i++) prod = prod * arr[i];

    return prod;
  }

  int[] MakeArray() {
    int[] arr = new int[this.counters.length];
    for (int i = 0; i < this.counters.length; i++) {
      arr[i] = this.expenses[counters[i]];
    }
    return arr;
  }

  void Check() {
    int[] arr = MakeArray();
    if (Sum(arr) == 2020) {
      System.out.println("Counters: " + Arrays.toString(counters));
      System.out.println("Array: " + Arrays.toString(arr));
      System.out.println("Solution:" + Mult(arr));
      System.exit(0); // this ends the program.
    }
  }

  void Solve(int solveFor, int level) {
    int length = this.expenses.length;

    if (this.counters == null) {
      this.counters = new int[solveFor];
    }

    if (level == this.counters.length) {
      Check();
    } else {
      for (this.counters[level] = 0; this.counters[level] < length; this.counters[level]++) {
        Solve(solveFor, level + 1);
      }
    }
  }
}
