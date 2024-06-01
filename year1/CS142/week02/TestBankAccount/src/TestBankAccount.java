/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 02-Classes
 * BankAccount
 *
 * This is complete test program for a BankAccount class that the students
 * will implement from a UML class diagram.
 *
 * If the students BankAccount class is completely correct this test program
 * will run as follows:
 *
 * $ java TestBankAccount
 * creating...
 * savings balance ok? YES
 * checking balance ok? YES
 * depositing...
 * savings balance ok? YES
 * checking balance ok? YES
 * withdrawing...
 * savings balance ok? YES
 * checking balance ok? YES
 * over withdrawing checking...
 * checking balance ok? YES
 * applyInterest...
 * savings balance ok? YES
 * checking balance ok? YES
 * toString...
 * savings.toString()? YES
 * checking.toString()? YES
 * equals...
 * savings.equals(savings)? YES
 * !savings.equals(checking)? YES
 * !savings.equals(1234)? YE
 *
 * If a NO comes up for any test it will include output for what was gotten
 * vs what was expected, e.g.
 *
 * checking balance ok? NO! Got: 0.0, Expected: 1.0
 *
 * @author RIT CS
 */
public class TestBankAccount {

    /**
     * A helper function for verifying the balance of a BankAccount is what
     * is expected.  If the balance matches it displays:
     *
     * {type} balance ok? YES
     *
     * If the balance doesn't match it displays:
     *
     * {type} balance ok? NO! Got: {observed}, Expected: {amount}
     *
     * @param account the BankAccount to verify
     * @param type a string indicating the type of account, e.g. "savings"
     * @param amount the expected balance of the account
     */
    public static void verifyBalance(BankAccount account, String type, double amount) {
        System.out.println(type + " balance ok? " +
                (account.getBalance() == amount ?
                        "YES" :
                        ("NO! Got: " + account.getBalance()) + ", Expected: " + amount));
    }

    /**
     * The main method runs all the tests for the BankAccount methods.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        System.out.println("creating...");
        // create a savings account with an initial balance of $1000
        BankAccount savings = new BankAccount(1234,1000.50);
        // create a checking account with an initial balance of $0
        BankAccount checking = new BankAccount(5678);

        // verify initial balances
        verifyBalance(savings, "savings", 1000.50);
        verifyBalance(checking, "checking", 0);

        // test deposit
        System.out.println("depositing...");
        savings.deposit(5.36);
        checking.deposit(593.00);

        // verify new balances
        verifyBalance(savings, "savings", 1005.86);
        verifyBalance(checking, "checking", 593.00);

        // test withdraw
        System.out.println("withdrawing...");
        if (!savings.withdraw(412.87)) {
            System.out.println("Error withdrawing from savings");
        }
        verifyBalance(savings, "savings", 592.99);
        if (!checking.withdraw(100)) {
            System.out.println("Error withdrawing from checking");
        }
        verifyBalance(checking, "checking", 493.00);

        // testing over withdrawing
        System.out.println("over withdrawing checking...");
        if (checking.withdraw(500)) {
            System.out.println("Error over withdrawing from checking");
        }
        verifyBalance(checking, "checking", 493.00);

        // testing applyInterest
        System.out.println("applyInterest...");
        savings.applyInterest();
        verifyBalance(savings, "savings", 1185.98);
        checking.applyInterest();
        verifyBalance(checking, "checking", 986.00);

        // testing toString
        System.out.println("toString...");
        String result = savings.toString();
        String expected = "BankAccount{account=1234, balance=1185.98}";
        System.out.println("savings.toString()? " +
                (result.equals(expected) ?
                        "YES" :
                        "NO! Got: " + result + ", Expected: " + expected));
        result = checking.toString();
        expected = "BankAccount{account=5678, balance=986.0}";
        System.out.println("checking.toString()? " +
                (result.equals(expected) ?
                        "YES" :
                        "NO! Got: " + result + ", Expected: " + expected));

        // test equals
        System.out.println("equals...");
        System.out.println("savings.equals(savings)? " +
                (savings.equals(savings) ? "YES" : "NO!"));
        System.out.println("!savings.equals(checking)? " +
                (!savings.equals(checking) ? "YES" : "NO!"));
        System.out.println("!savings.equals(1234)? " +
                (!savings.equals(1234) ? "YES" : "NO!"));
    }
}