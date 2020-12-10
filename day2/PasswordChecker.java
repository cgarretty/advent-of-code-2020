import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordChecker {
  // create main program loop
  public static void main(String args[]) throws Exception {
    // 1. read in passwords from file
    Passwords passwords = new Passwords();
    passwords.readPasswords("passwords.txt");
    // 2. check each password, and keep a tally of valid and invalid passwords.
    boolean[] checks = new boolean[passwords.length];
    for (int i = 0; i < passwords.length; i++) {
      checks[i] = passwords.passwords.get(i).check(); // returns bool
    }
    System.out.println("Tested: " + passwords.length);
    System.out.println("Valid:" + sum(checks));
  }

  static int sum(boolean[] bools) {
    int sum = 0;
    for (boolean b : bools) {
      sum += b ? 1 : 0;
    }
    return sum;
  }
}

// Create a passwords object
class Passwords {
  public ArrayList<Password> passwords;
  public int length;

  void readPasswords(String filePath) throws Exception {
    Scanner scanner = new Scanner(new FileReader(filePath));
    ArrayList<Password> passwords = new ArrayList<Password>(); // resizeable arrays!
    int i = 0;

    while (scanner.hasNextLine()) {
      passwords.add(new Password(scanner.nextLine()));
    }
    this.passwords = passwords;
    this.length = this.passwords.size();
  }
}

class Password {
  public String password;
  public int[] policy = new int[2];
  public char letter;

  Password(String p) {
    parsePassword(p);
  }

  boolean check() {
    int n = 0;
    for (int i = 0; i < this.password.length(); i++) {
      if (i + 1 == this.policy[0] | (i + 1 == this.policy[1])) {
        if (this.letter == this.password.charAt(i)) {
          n++;
        }
      }
    }
    if (n == 1) {
      return true;
    }
    return false;
  }

  void parsePassword(String password) {
    String[] p = new String[2];
    p = password.split(": ");
    this.password = p[1];
    parsePolicy(p[0]);
  }

  void parsePolicy(String policy) {
    String[] p = new String[2];
    String[] n = new String[2];
    p = policy.split(" ");
    this.letter = p[1].charAt(0);
    n = p[0].split("-");
    for (int i = 0; i < 2; i++) {
      this.policy[i] = Integer.parseInt(n[i]);
    }
  }
}
