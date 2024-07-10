//Exp:top//
//Exp:import//
import java.util.*;

public abstract class Exp /*Exp:class*/ {

    public static final String $className = "Exp";
    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case LIT:
            return LitExp.parse(scn$,trace$);
        case VAR:
            return VarExp.parse(scn$,trace$);
        case ZEROP:
        case ADD1OP:
        case SUBOP:
        case DIVOP:
        case MULOP:
        case SUB1OP:
        case ADDOP:
            return PrimappExp.parse(scn$,trace$);
        default:
            throw new PLCCException(
                "Parse error",
                "Exp cannot begin with " + t$.errString()
            );
        }
    }

    public abstract Val eval(Env env);

//Exp//
}
