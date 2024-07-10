//Exp:top//
//Exp:import//
import java.util.*;

public abstract class Exp /*Exp:class*/ {

    public static final String $className = "Exp";
    public static Exp parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case VAR:
            return VarExp.parse(scn$,trace$);
        case IF:
            return IfExp.parse(scn$,trace$);
        case PROC:
            return ProcExp.parse(scn$,trace$);
        case SUB1OP:
        case SUBOP:
        case ADDOP:
        case MULOP:
        case DIVOP:
        case ADD1OP:
        case ZEROP:
            return PrimappExp.parse(scn$,trace$);
        case LBRACE:
            return SeqExp.parse(scn$,trace$);
        case LET:
            return LetExp.parse(scn$,trace$);
        case DOT:
            return AppExp.parse(scn$,trace$);
        case MACRO:
            return MacroExp.parse(scn$,trace$);
        case LIT:
            return LitExp.parse(scn$,trace$);
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
