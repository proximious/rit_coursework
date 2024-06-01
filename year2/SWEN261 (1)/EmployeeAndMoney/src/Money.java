import java.util.Objects;

public class Money {
    private int dollars;
    private int cents;

    public int getDollars(){return dollars;}

    public int getCents(){return cents;}

    public Money(int dollars, int cents){
        this.dollars = dollars;
        this.cents = cents;
    }

    public Money addMoney(Money money){
        Money cash = new Money(this.dollars + money.dollars, this.cents + money.cents);

        while (cash.cents > 100){
            cash.dollars++;
            cash.cents -= 100;
        }

        return cash;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return dollars == money.dollars &&
                cents == money.cents;
    }


    @Override
    public int hashCode() {
        return Objects.hash(dollars, cents);
    }
}
