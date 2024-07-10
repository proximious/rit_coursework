//NumsNull:top//
//NumsNull:import//
import java.util.*;

// <nums>:NumsNull ::= 
public class NumsNull extends Nums /*NumsNull:class*/ {

    public static final String $className = "NumsNull";
    public static final String $ruleString =
        "<nums>:NumsNull ::= ";


    public NumsNull() {
//NumsNull:init//

    }

    public static NumsNull parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<nums>:NumsNull", scn$.lno);
        return new NumsNull();
    }

        public void $run() {
            return;
        }

//NumsNull//
}
