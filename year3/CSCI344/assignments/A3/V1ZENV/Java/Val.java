import java.util.*;

public abstract class Val {

    public static Val [] toArray(List<Val> valList) {
        int n = valList.size();
        return valList.toArray(new Val[n]);
    }

    public boolean isTrue() {
        return true; // any Val is true except for an IntVal of zero
    }

    public IntVal intVal() {
        throw new PLCCException(this + ": not an Int");
    }
}