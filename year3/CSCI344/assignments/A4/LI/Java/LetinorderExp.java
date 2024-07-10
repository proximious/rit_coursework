//LetinorderExp:top//
//LetinorderExp:import//
import java.util.*;

// <exp>:LetinorderExp ::= LETINORDER <letDecls> IN <exp>
public class LetinorderExp extends Exp /*LetinorderExp:class*/ {

    public static final String $className = "LetinorderExp";
    public static final String $ruleString =
        "<exp>:LetinorderExp ::= LETINORDER <letDecls> IN <exp>";

    public LetDecls letDecls;
    public Exp exp;
    public LetinorderExp(LetDecls letDecls, Exp exp) {
//LetinorderExp:init//
        this.letDecls = letDecls;
        this.exp = exp;
    }

    public static LetinorderExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:LetinorderExp", scn$.lno);
        scn$.match(Token.Match.LETINORDER, trace$);
        LetDecls letDecls = LetDecls.parse(scn$, trace$);
        scn$.match(Token.Match.IN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new LetinorderExp(letDecls, exp);
    }

	public Val eval(Env env) {
		Env nenv = letDecls.addSequentially(env);
        	return exp.eval(nenv);
	}

//LetinorderExp//
}
