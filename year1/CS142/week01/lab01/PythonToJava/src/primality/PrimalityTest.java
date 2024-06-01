//Alex Iacob
//ai9388@rit.edu

package primality;

import java.util.Scanner;

public class PrimalityTest {

    public static boolean isPrime(int number) {
        if (number == 1){
            //slightly controversial, but 1 is not a prime number
            return false;
        } else if (number == 2){
            //slightly controversial, but 2 is a prime number
            return true;
        } else if (number % 2 == 0){
            // checks whether the number is even with the exception of 2
            return false;
        } else{
            //checks every other odd number from 3 to the number
            for (int i = 3; i * i <= number; i+= 2) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number(0 to quit):");
        int userInput = input.nextInt();
        if(userInput <= 0){
            System.out.println("Goodbye");
        } else {
            while (userInput != 0) {
                if (isPrime(userInput)) {
                    System.out.println(userInput + " is a prime number.");
                } else {
                    System.out.println(userInput + " is not a prime number.");
                }
                System.out.println("Enter a number(0 to quit):");
                userInput = input.nextInt();
            }
            System.out.println("Goodbye");
        }
    }

}
