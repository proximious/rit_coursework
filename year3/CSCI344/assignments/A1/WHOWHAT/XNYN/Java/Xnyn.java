//Xnyn:top//
//Xnyn:import//
import java.util.*;

public abstract class Xnyn /*Xnyn:class*/ {

    public static final String $className = "Xnyn";

    public static Xnyn parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case X:
            return XY.parse(scn$,trace$);
        case Y:
        case HASH:
            return Nil.parse(scn$,trace$);
        default:
            throw new PLCCException(
                "Parse error",
                "Xnyn cannot begin with " + t$.errString()
            );
        }
    }

//Xnyn//
}
