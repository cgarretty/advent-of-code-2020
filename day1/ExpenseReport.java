import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class ExpenseReport {
   public static void main(String args[]) throws Exception {
     int i;
     int j;
     int x;
     int y;
     int [] expenses = new Expenses().ReadExpenses();

     // accessing each element of array
     for (i = 0; i < expenses.length; i++) {
            x = expenses[i];
            for (j = 0; j < expenses.length; j++) {
              if (i != j) {
                y = expenses[j];
                if (x + y == 2020) {
                  System.out.println("x:" + x);
                  System.out.println("y:" + y);
                  System.out.println("Solution:" + (x*y));
                  System.exit(0);
                }
              }
            }
     };
   }
}

class Expenses {

  int [] ReadExpenses () throws Exception {
    Scanner scanner = new Scanner(new FileReader("expenses.txt"));
    int [] expenses = new int [200];
    int i = 0;

    while(scanner.hasNextInt()) {
        expenses[i++] = scanner.nextInt();
    }

    return expenses;
  }
}
