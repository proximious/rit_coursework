//Prim:top//
//Prim:import//
import java.util.*;

public abstract class Prim /*Prim:class*/ {

    public static final String $className = "Prim";
    public static Prim parse(Scan scn$, Trace trace$) {
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case MULOP:
            return MulPrim.parse(scn$,trace$);
        case ZEROP:
            return ZeropPrim.parse(scn$,trace$);
        case ADDOP:
            return AddPrim.parse(scn$,trace$);
        case NEGOP:
            return NegPrim.parse(scn$,trace$);
        case DIVOP:
            return DivPrim.parse(scn$,trace$);
        case SUB1OP:
            return Sub1Prim.parse(scn$,trace$);
        case ADD1OP:
            return Add1Prim.parse(scn$,trace$);
        case SUBOP:
            return SubPrim.parse(scn$,trace$);
        default:
            throw new PLCCException(
                "Parse error",
                "Prim cannot begin with " + t$.errString()
            );
        }
    }

    // apply the primitive to the passed values
    public abstract Val apply(Val [] va);

//Prim//
}
