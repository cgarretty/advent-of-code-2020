import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class ExpenseReport {
   public static void main(String args[]) throws Exception {
     int solveFor = 3;
     Expenses expenses = new Expenses();
     expenses.ReadExpenses("expenses.txt");
     int [] counters = new int [solveFor];
     expenses.Solve(0, counters);
   }

}

class Expenses {
  public int [] expenses;
  public int [] counters;

  void ReadExpenses (String filePath) throws Exception {
    Scanner scanner = new Scanner(new FileReader(filePath));
    int [] expenses = new int [200];
    int i = 0;

    while(scanner.hasNextInt()) {
        expenses[i++] = scanner.nextInt();
    }

    this.expenses = expenses;
  }

    static int Sum(int [] arr) {
      int i;
      int sum = 0;

      for (i = 0; i < arr.length; i++)
        sum +=  arr[i];

      return sum;
    }

    static int Mult(int [] arr) {
       int i;
       int prod = arr[0];

       for (i = 1; i < arr.length; i++)
          prod = prod * arr[i];

       return prod;
    }

    int [] MakeArray(int [] counters) {
      int [] arr = new int[counters.length];
      for (int i=0; i < counters.length; i++) {
        arr[i] = this.expenses[counters[i]];
      }
      return arr;
    }

    void Check(int [] counters) {
      int [] arr = MakeArray(counters);
        if (Sum(arr) == 2020) {
            System.out.println("Counters: " + Arrays.toString(counters));
            System.out.println("Array: " + Arrays.toString(arr));
            System.out.print("Solution:" + Mult(arr));
            System.exit(0); // this ends the program.
        }
    }

    void Solve(int level, int [] counters) {
      int length = this.expenses.length;
      if(level == counters.length) {
        Check(counters);
      }
      else {
        for (counters[level] = 0; counters[level] < length; counters[level]++) {
            Solve(level + 1, counters);
        }
      }
    }
  }
