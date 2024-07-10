import java.util.*;

public class IntVal extends Val {

    public int val;

    public IntVal(String s) {
	val = Integer.parseInt(s);
    }

    public IntVal(int v) {
	val = v;
    }

    public IntVal intVal() {
        return this;
    }

    public String toString() {
	return "" + val;
    }

    public boolean isTrue() {
	return val != 0;
    }

    public static void main(String [] args) {
	Val v = new IntVal(3);
	Val w = new IntVal(0);
	System.out.println("v=" + v);
	System.out.println("w=" + w);
    }

}