//Alex Iacob
//ai9388@rit.edu

package hashing;

public class GoodHashFunc {

    public static int computeHash(String input) {
        int totalHash = 0;
        int[] hashedValues = new int[input.length()];

        for (int i = 0; i < input.length(); i++){
            hashedValues[i] = input.charAt(i);
            // creating the hash value for each individual value
            totalHash += hashedValues[i] * (Math.pow(31, input.length() - 1 - i));
        }
        return totalHash;
    }

    public static void main(String[] args){
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Enter a word or series of words:");
        String userInput = in.nextLine();
        in.close();

        System.out.println("The computed hash value for the input is: " + computeHash(userInput));
    }

}
