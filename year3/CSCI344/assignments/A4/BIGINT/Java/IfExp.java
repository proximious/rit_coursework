//IfExp:top//
//IfExp:import//
import java.util.*;

// <exp>:IfExp ::= IF <exp>testExp THEN <exp>trueExp ELSE <exp>falseExp
public class IfExp extends Exp /*IfExp:class*/ {

    public static final String $className = "IfExp";
    public static final String $ruleString =
        "<exp>:IfExp ::= IF <exp>testExp THEN <exp>trueExp ELSE <exp>falseExp";

    public Exp testExp;
    public Exp trueExp;
    public Exp falseExp;
    public IfExp(Exp testExp, Exp trueExp, Exp falseExp) {
//IfExp:init//
        this.testExp = testExp;
        this.trueExp = trueExp;
        this.falseExp = falseExp;
    }

    public static IfExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:IfExp", scn$.lno);
        scn$.match(Token.Match.IF, trace$);
        Exp testExp = Exp.parse(scn$, trace$);
        scn$.match(Token.Match.THEN, trace$);
        Exp trueExp = Exp.parse(scn$, trace$);
        scn$.match(Token.Match.ELSE, trace$);
        Exp falseExp = Exp.parse(scn$, trace$);
        return new IfExp(testExp, trueExp, falseExp);
    }

    public Val eval(Env env) {
        Val testVal = testExp.eval(env);
        if (testVal.isTrue())
            return trueExp.eval(env);
        else
            return falseExp.eval(env);
    }

    public String toString() {
        return "if " + testExp + " then " + trueExp + " else " +falseExp;
    }

//IfExp//
}
