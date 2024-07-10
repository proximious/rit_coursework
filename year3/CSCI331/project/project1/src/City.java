import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename City.java
 * 
 * Helper file to Search.java to get parameters
 */
public class City {
    public String start;
    public String dest;
    public static Scanner scanner;

    public City(String start, String dest) {
      this.start = start;
      this.dest = dest;
    }


    /**
     * Gets the starting and destination file
     * 
     * @param filename name of file
     * @return new City object with start and destination cities
     */
    public static City getFromFile(String filename) {
        try {
          if (filename.equals("-")) {
              System.out.println("Enter two cities separated by a new line: ");
              scanner = new Scanner(System.in);
          } else {
              scanner = new Scanner(new File(filename));
          }
      } catch (FileNotFoundException e) {
        System.err.println("File not found: " + filename);
        System.exit(0);
      }
      return new City(scanner.nextLine(), scanner.nextLine());
    }


    /**
     * Makes sure that the given cities are in city.dat
     * 
     * @param cities all cities
     */
    public void validate(Set<String> cities) {
      if (!cities.contains(this.start)) {
        System.err.println(String.format("No such city: %s", this.start));
        System.exit(0);
      }
      if (!cities.contains(this.dest)) {
        System.err.println(String.format("No such city: %s", this.dest));
        System.exit(0);
      }
    }
  }