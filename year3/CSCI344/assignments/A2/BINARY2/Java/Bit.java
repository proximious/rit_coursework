//Bit:top//
//Bit:import//
import java.util.*;

public abstract class Bit /*Bit:class*/ {

    public static final String $className = "Bit";
    public static Bit parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case ONE:
            return BitOne.parse(scn$,trace$);
        case ZERO:
            return BitZero.parse(scn$,trace$);
        default:
            throw new PLCCException(
                "Parse error",
                "Bit cannot begin with " + t$.errString()
            );
        }
    }

	public abstract int eval();

//Bit//
}
