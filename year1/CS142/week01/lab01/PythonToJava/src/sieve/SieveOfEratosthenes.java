//Alex Iacob
//ai9388@rit.edu

package sieve;

import java.util.Scanner;

public class SieveOfEratosthenes {

    public static int[] makeSieve(int upperBound) {
        // creating an empty array of 0's to be filled
        int[] primeNumbers = new int[upperBound + 1];

        // filling up the array with 0's
        for (int i = 0; i < upperBound; i++){
            primeNumbers[i] = 0;
        }

        // verifying if the other numbers are divisible by any other number
        for (int j = 2; j * j <= upperBound; j++){
            if (primeNumbers[j] == 0){
                for (int i = j * j; i <= upperBound; i += j){
                    primeNumbers[i] = 1;
                }
            }
        }
        //hard coding false for 0 and 1
        primeNumbers[0] = 1;
        primeNumbers[1] = 1;

        return primeNumbers;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an upper bound:");
        int userUpperBoundInput = input.nextInt();
        System.out.println("Enter a positive number(0 to quit): ");
        int userIntInput = input.nextInt();
        if(userUpperBoundInput <= 0){
            System.out.println("Goodbye");
        } else {
            while (userIntInput != 0) {
                if (makeSieve(userUpperBoundInput)[userIntInput] == 0){
                    System.out.println(userIntInput + " is a prime number.");
                } else {
                    System.out.println(userIntInput + " is not a prime number.");
                }
                System.out.println("Enter a positive number(0 to quit): ");
                userIntInput = input.nextInt();
            }
            System.out.println("Goodbye");
        }
    }

}
