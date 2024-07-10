//Start:top//
//Start:import//
import java.util.*;

public abstract class Start extends _Start /*Start:class*/ {

    public static final String $className = "Start";
    public static Start parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case ONE:
            return Ones.parse(scn$,trace$);
        case ZERO:
            return Zeroes.parse(scn$,trace$);
        default:
            throw new PLCCException(
                "Parse error",
                "Start cannot begin with " + t$.errString()
            );
        }
    }

//Start//
}
