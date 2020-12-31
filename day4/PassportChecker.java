import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class PassportChecker {
  // create main program loop
  public static void main(String args[]) throws Exception {
    // 1. create passports from file
    Passports passports = new Passports();
    passports.readPassports("passports.txt");
    // 2. count valid passports
    passports.validate();
    // 3. print result
    System.out.println("Tested: " + passports.length);
    System.out.println("Valid: " + passports.validated);
  }
}

// Create a passports object
class Passports {
  // 1. create attributes for valid count & passports.
  public ArrayList<Passport> passports = new ArrayList<Passport>();
  public int length;
  public int validated;

  void readPassports(String filePath) throws Exception {
    Scanner scanner = new Scanner(new FileReader(filePath));
    String passport = "";
    String newline = System.getProperty("line.separator");

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line == "") {
        this.passports.add(new Passport(passport));
        passport = "";
      } else {
        passport += line.trim() + " ";
      }
    }
    this.passports.add(new Passport(passport));
    this.length = this.passports.size();
  }
  // 2. method for counting valid passports
  void validate() {
    boolean[] checks = new boolean[this.length];
    for (int i = 0; i < this.length; i++) {
      checks[i] = passports.get(i).check(); // returns bool
    }
    this.validated = this.sum(checks);
  }

  static int sum(boolean[] bools) {
    int sum = 0;
    for (boolean b : bools) {
      sum += b ? 1 : 0;
    }
    return sum;
  }
}

class Passport {
  public Hashtable<String, String> fields = new Hashtable<String, String>();

  Passport(String p) {
    parsePassport(p);
  }

  boolean check() {
    String[] all_fields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
    for (int i = 0; i < all_fields.length; i++) {
      if (all_fields[i] != "cid") {
        String f = fields.get(all_fields[i]);
        if (f == null) {
          return false;
        }
      }
    }
    return true;
  }

  void parsePassport(String passport) {
    String[] fields = passport.split(" ");
    for (int i = 0; i < fields.length; i++) {
      Field f = new Field(fields[i]);
      this.fields.put(f.key, f.value);
    }
  }
}

class Field {
  public String key;
  public String value;

  public Field(String field) {
    String[] f = field.split(":");
    this.key = f[0];
    this.value = f[1];
  }
}
