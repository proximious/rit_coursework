//Rands:top//
//Rands:import//
import java.util.*;

// <rands> **= <exp> +COMMA
public class Rands /*Rands:class*/ {

    public static final String $className = "Rands";
    public static final String $ruleString =
        "<rands> **= <exp> +COMMA";

    public List<Exp> expList;
    public Rands(List<Exp> expList) {
//Rands:init//
        this.expList = expList;
    }

    public static Rands parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<rands>", scn$.lno);
        List<Exp> expList = new ArrayList<Exp>();
        // first trip through the parse
        Token t$ = scn$.cur();
        Token.Match match$ = t$.match;
        switch(match$) {
        case ADD1OP:
        case MULOP:
        case LIT:
        case LBRACE:
        case ZEROP:
        case IF:
        case DOT:
        case LET:
        case VAR:
        case DIVOP:
        case ADDOP:
        case LETINORDER:
        case SUB1OP:
        case SUBOP:
        case PROC:
            while(true) {
                expList.add(Exp.parse(scn$, trace$));
                t$ = scn$.cur();
                match$ = t$.match;
                if (match$ != Token.Match.COMMA)
                    break; // not a separator, so we're done
                scn$.match(match$, trace$);
            }
        } // end of switch
        return new Rands(expList);

    }

    public String toString() {
        String s = "";   // the string to return
        String sep = ""; // no separator for the first expression
        // get all of the expressions in the expression list
        for (Exp e : expList) {
            s += sep + e;
            sep = ",";   // commas separate the rest of the expressions
        }
        return s;
    }

    // evaluate the individual terms in expList using the given environment
    // and return a List<Val> object
    public List<Val> evalRands(Env env) {
        List<Val> args = new ArrayList<Val>(expList.size());
        for (Exp e : expList)
            args.add(e.eval(env));
        return args;
    }

//Rands//
}
