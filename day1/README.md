# Day 1

### Part 1

Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently, something isn't quite adding up.

Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.

For example, suppose your expense report contained the following:
```
1721
979
366
299
675
1456
```
In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.

Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you multiply them together?

#### Solution

In order to get to the answer, I used nested `for` loops. I created an `Expenses` object with one method that returns the expenses from the file as an `Array`. The outer and inner loop reads each expense, and then sums the value of each pair in the expenses array. if the sum of the pair is equal to 2020, then print the product and exit the program. here's the meat of it.

```
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
```

### Part 2

The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left over from a past vacation. They offer you a second one if you can find three numbers in your expense report that meet the same criteria.

Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together produces the answer, 241861950.

In your expense report, what is the product of the three entries that sum to 2020?

#### Solution

For this one, I wanted to take the chance to learn how to implement a more robust solution that could be used for any number of expenses. Although I realize just adding another `for` loop to meet the requirements of solving for triples would have been faster to solve, I think this is a more elegant solution that will make it easier to add features and debug in the future.

I added more methods to the `Expenses` object and gave it state - the expenses from the file as an array, and a array object that represents the indexes in the array the current program is on. I removed the nested for loops and created a recursive solution [stealing a lot from this Stack Overflow solution](https://stackoverflow.com/questions/19406290/how-to-make-n-nested-for-loops-recursively).

```
void Solve(int solveFor, int level) {
  int length = this.expenses.length;

  if (this.counters == null) {
    this.counters = new int[solveFor];
  }

  if(level == this.counters.length) {
    Check();
  }
  else {
    for (
      this.counters[level] = 0;
      this.counters[level] < length;
      this.counters[level]++) {
        Solve(solveFor, level + 1);
    }
  }
}
```

Just like in Part 1, this solution iterates through every possible set of the array for the given number of expenses to be tested, but it can take any number as the length of the set. The method `Check` is what actaully does the test for the sum of the numbers and spits out the solution if the condition is met. `Solve` is the recursive function that calls itself for each level that it needs to go down in order to get a set of n numbers to make a solution. Here's what it spat out for 3 expenses:

```
Counters: [37, 69, 109]
Array: [523, 551, 946]
Solution:272611658
```

## Discussion

While this solution works for any arbitrary number of expenses, it is quite slow for anything more than 4. As the number of expenses for the solution increases, the number of operations increase exponentially (*I think, though I should probably do the math on that*). I went up to 6, and gave up and cancelled out of the program because it was taking too long. One possible area of improvement would be to not process the same sets over and over again, possible using dynamic programming or some such thing.

Also, I did not do any tests for if the pair includes the same expenses within a given set. The program gave me the correct solution, all with different expenses, so I didn't see the need at this time to work out that subproblem. However, if this were a real problem (although it would be a strange use case), then I would want to work that out in the solution.
