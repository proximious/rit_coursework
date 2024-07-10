import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: ESSSimulation.java
 * <p>
 * Runs the animal conflict logic situation with help from Animal.java
 */
public class ESSSimulation {
    // declaring the default values for user arguments
    public static int popSize; // guaranteed to show
    public static int percentHawks = 20; // optional
    public static int percentDoves; // calculated later in program
    public static int resourceAmt = 50; // optional
    public static int costHawkHawk = 100; // optional

    // other global variables
    public static ArrayList<Animal> animals = new ArrayList<>();
    public static int encounterNumber = 1;
    public static int numOfHawks; // calculated later in program
    public static int numOfDoves; // calculated later in program


    /**
     * Loads the correct amount of hawks and doves into `animals`
     */
    public static void loadAnimalArray() {
        for (int i = 0; i < popSize; i++) {
            if (i < numOfHawks) {
                Animal h = new Animal(i, "Hawk", 0);
                animals.add(h);
            } else {
                Animal d = new Animal(i, "Dove", 0);
                animals.add(d);
            }
        }
    }


    /**
     * Increments the amount of encounters that the animals have
     *
     * @param array the array of hawks and doves
     */
    public static void increment(ArrayList<Animal> array) {
        // go through the list and place all alive animals into new arraylist
        ArrayList<Animal> aliveAnimals = new ArrayList<>();
        for (Animal current : array) {
            if (!current.getStatus().equals("DEAD")) {
                aliveAnimals.add(current);
            }
        }

        int randomIndex1 = (int) (Math.random() * (aliveAnimals.size() - 1));
        int randomIndex2 = (int) (Math.random() * (aliveAnimals.size() - 1));

        // making sure that the indexes are not the same
        while (randomIndex1 == randomIndex2) {
            randomIndex2 = (int) (Math.random() * (aliveAnimals.size() - 1));
        }

        Animal animal1 = aliveAnimals.get(randomIndex1);
        Animal animal2 = aliveAnimals.get(randomIndex2);

        String ani1name = animal1.getStatus();
        String ani2name = animal2.getStatus();

        // printing the encounter number and the status of each animal
        System.out.println("Encounter: " + encounterNumber);
        System.out.println(animal1.getStatusString());
        System.out.println(animal2.getStatusString());

        // the four possible outcomes are Dove-Hawk, Dove-Dove, Hawk-Dove, Hawk-Hawk
        if (ani1name.equals("Dove") && ani2name.equals("Hawk")) { // dove-hawk interaction
            // the hawk gets all of the resource, and dove does not die and gets no resource
            animal2.addResource(resourceAmt);
            System.out.println("Dove-Hawk: Dove: +0\tHawk: +" + resourceAmt);

        } else if (ani1name.equals("Dove") && ani2name.equals("Dove")) { // dove-dove interaction
            // both doves split the resource among themselves and both leave unharmed
            int splitResource = resourceAmt / 2;
            animal1.addResource(splitResource);
            animal2.addResource(splitResource);
            System.out.println("Dove-Dove: Dove: +" + splitResource + "\tDove: +" + splitResource);

        } else if (ani1name.equals("Hawk") && ani2name.equals("Dove")) { // hawk-dove interaction
            // the hawk gets all of the resource, and dove does not die and gets no resource
            animal1.addResource(resourceAmt);
            System.out.println("Hawk-Dove: Hawk: +" + resourceAmt + "\tDove: +0");

        } else if (ani1name.equals("Hawk") && ani2name.equals("Hawk")) { // hawk-hawk interaction
            // first chosen hawk gets the resource, but they both bear the interaction fee
            // once the hawk goes below 0 resource, it dies
            int splitResource = resourceAmt / 2;
            int resultingCost = splitResource - costHawkHawk;
            animal1.addResource(resultingCost);
            animal2.subtractResource(costHawkHawk);
            System.out.println("Hawk-Hawk: Hawk: +" + resultingCost + "\tHawk : -" + costHawkHawk);

            if (animal1.checkDead()) {
                System.out.println("Hawk one has died");
                animal1.changeStatusToDead();
            }

            if (animal2.checkDead()) {
                System.out.println("Hawk two has died");
                animal2.changeStatusToDead();
            }
        }

        String s1 = animal1.getResourceString();
        String s2 = animal2.getResourceString();
        System.out.println(s1 + "\t" + s2);
        System.out.println();

        encounterNumber++;
    }


    /**
     * Prints the menu for the user
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("===============MENU=============");
        System.out.println("1 ) Starting Stats");
        System.out.println("2 ) Display Individuals and Points");
        System.out.println("3 ) Display Sorted");
        System.out.println("4 ) Have 1000 interactions");
        System.out.println("5 ) Have 10000 interactions");
        System.out.println("6 ) Have N interactions");
        System.out.println("7 ) Step through interactions 'Stop' to return to menu");
        System.out.println("8 ) Quit");
        System.out.println("================================");
        System.out.println("Only use numbers when choosing an option");
    }


    /**
     * Displays the starting statistics
     */
    public static void displayStartingValues() {
        System.out.println("Population size: " + popSize);
        System.out.println("Percentage of Hawks: " + percentHawks + "%");
        System.out.println("Number of Hawks: " + numOfHawks);
        System.out.println();
        System.out.println("Percentage of Doves: " + percentDoves + "%");
        System.out.println("Number of Doves: " + numOfDoves);
        System.out.println();
        System.out.println("Each resource is worth: " + resourceAmt);
        System.out.println("Cost of Hawk-Hawk interaction is: " + costHawkHawk);
    }


    /**
     * Displays all of the dead and alive animals individually while updating
     *
     * @param array all of the animals
     */
    public static void displayIndividualsInPopulation(ArrayList<Animal> array) {
        int living = 0;
        for (int i = 0; i < array.size(); i++) {
            Animal current = animals.get(i);
            String aniS = current.getStatus();
            int aniR = current.getResource();
            if (!aniS.equals("DEAD")) {
                living++;
            }
            System.out.println("Individual[" + i + "]=" + aniS + ":" + aniR);
        }
        System.out.println("Living: " + living);
    }


    /**
     * Displays all of the animals with the resource amount
     *
     * @param array all of the animals
     */
    public static void displayAnimalResourceAmount(ArrayList<Animal> array) {
        array.sort(new ResourceSorter());

        for (Animal a : array) {
            String aniS = a.getStatus();
            int aniR = a.getResource();
            System.out.println(aniS + ":" + aniR);
        }
    }


    /**
     * Runs the program
     *
     * @param args system arguments
     */
    public static void main(String[] args) {
        // getting the user arguments
        if (args.length == 0 || args.length > 5) {
            System.err.println("Usage: ./ESSSimulation popSize [percentHawks] " +
                    "[resourceAmt] [costHawk-Hawk]");
            System.exit(0);
        } else if (args.length == 1) {
            popSize = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            popSize = Integer.parseInt(args[0]);
            percentHawks = Integer.parseInt(args[1]);
        } else if (args.length == 3) {
            popSize = Integer.parseInt(args[0]);
            percentHawks = Integer.parseInt(args[1]);
            resourceAmt = Integer.parseInt(args[2]);
        } else if (args.length == 4) {
            popSize = Integer.parseInt(args[0]);
            percentHawks = Integer.parseInt(args[1]);
            resourceAmt = Integer.parseInt(args[2]);
            costHawkHawk = Integer.parseInt(args[3]);
        }

        percentDoves = 100 - percentHawks;
        float percentage = (float) ((float) percentHawks / 100.0);

        numOfHawks = (int) (popSize * percentage);
        numOfDoves = popSize - numOfHawks;

        loadAnimalArray();
        printMenu();
        Scanner scan1 = new Scanner(System.in);
        int userInput = scan1.nextInt();
        //initiate main loop
        while (userInput != 8) {
            switch (userInput) {
                case 1:
                    // must show the original starting stats
                    displayStartingValues();
                    break;
                case 2:
                    // show each individual animal and its resource points
                    displayIndividualsInPopulation(animals);
                    break;
                case 3:
                    // show each animal with its resource
                    displayAnimalResourceAmount(animals);
                    break;
                case 4:
                    // run increment() 1000 times
                    int a = 0;
                    while (a < 1000) {
                        increment(animals);
                        a++;
                    }
                    break;
                case 5:
                    // run increment() 10000 times
                    int b = 0;
                    while (b < 10000) {
                        increment(animals);
                        b++;
                    }
                    break;
                case 6:
                    // run increment N times
                    Scanner scan2 = new Scanner(System.in);
                    int c = scan2.nextInt();
                    int d = 0;
                    while (d < c) {
                        increment(animals);
                        d++;
                    }
                    break;
                case 7:
                    // lets the program run step by step
                    System.out.println("Press Enter to increment and type 'Stop' to exit");
                    Scanner scan3 = new Scanner(System.in);
                    String str = scan3.nextLine();
                    while (!str.equals("Stop")) {
                        if (str.isEmpty()) {
                            increment(animals);
                        }
                        str = scan3.nextLine();
                    }
                    break;
                default:
                    System.out.println("Number is not usable in menu");
                    break;
            }

            printMenu();
            userInput = scan1.nextInt();
        }
    }
}
