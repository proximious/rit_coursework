import java.util.*;
import java.math.BigInteger;

public class IntVal extends Val {

    public BigInteger val;

    public IntVal(String s) {
        val = new BigInteger(s);
    }

    public IntVal(BigInteger v) {
        val = v;
    }

    public IntVal intVal() {
        return this;
    }

    public boolean isTrue() {
        return val.signum() != 0;
    }

    public String toString() {
        return "" + val;
    }

    public static void main(String [] args) {
        //Val v = new IntVal(3);
        //Val w = new IntVal(0);
        //System.out.println("v=" + v);
        //System.out.println("w=" + w);
	}

	public IntVal add (IntVal v){
		return new IntVal(val.add(v.val));
	}

	public IntVal add1 (){
		return new IntVal(val.add(BigInteger.ONE));
	}

	public IntVal subtract (IntVal v) {
		return new IntVal(val.subtract(v.val));
	}

	public IntVal multiply (IntVal v){
                return new IntVal(val.multiply(v.val));
        }

	public IntVal divide (IntVal v){
                if (v.val.signum() == 0) {
			throw new PLCCException("Can't divide by zero");
		} else {
			return new IntVal(val.divide(v.val));
		}
        }

	public IntVal sub1 (){
                return new IntVal(val.subtract(BigInteger.ONE));
        }

	public IntVal zerop (IntVal v){
		if (v.val.signum() == 0) {
			return new IntVal(BigInteger.ONE);
		} else {
			return new IntVal(BigInteger.ZERO);
		}
        }
}