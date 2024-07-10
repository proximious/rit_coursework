//Nil:top//
//Nil:import//
import java.util.*;

// <xnyn>:Nil ::= 
public class Nil extends Xnyn /*Nil:class*/ {

    public static final String $className = "Nil";
    public static final String $ruleString =
        "<xnyn>:Nil ::= ";



    public Nil() {
//Nil:init//

    }

    public static Nil parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<xnyn>:Nil", scn$.lno);
        return new Nil();
    }

//Nil//
}
