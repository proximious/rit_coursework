//Macro:top//
//Macro:import//
import java.util.*;

// <macro> ::= MACRO LPAREN <formals> RPAREN <exp>
public class Macro /*Macro:class*/ {

    public static final String $className = "Macro";
    public static final String $ruleString =
        "<macro> ::= MACRO LPAREN <formals> RPAREN <exp>";

    public Formals formals;
    public Exp exp;
    public Macro(Formals formals, Exp exp) {
//Macro:init//
        this.formals = formals;
        this.exp = exp;
    }

    public static Macro parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<macro>", scn$.lno);
        scn$.match(Token.Match.MACRO, trace$);
        scn$.match(Token.Match.LPAREN, trace$);
        Formals formals = Formals.parse(scn$, trace$);
        scn$.match(Token.Match.RPAREN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new Macro(formals, exp);
    }

	public Val makeMacro(){
		return new MacroVal(formals, exp);
	}


//Macro//
}
