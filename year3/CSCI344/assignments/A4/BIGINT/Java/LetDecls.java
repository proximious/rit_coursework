//LetDecls:top//
//LetDecls:import//
import java.util.*;

// <letDecls> **= <VAR> EQUALS <exp>
public class LetDecls /*LetDecls:class*/ {

    public static final String $className = "LetDecls";
    public static final String $ruleString =
        "<letDecls> **= <VAR> EQUALS <exp>";

    public List<Token> varList;
    public List<Exp> expList;
    public LetDecls(List<Token> varList, List<Exp> expList) {
        // check for duplicates during parsing
        Env.checkDuplicates(varList, " in let LHS identifiers");
//LetDecls:init//
        this.varList = varList;
        this.expList = expList;
    }

    public static LetDecls parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<letDecls>", scn$.lno);
        List<Token> varList = new ArrayList<Token>();
        List<Exp> expList = new ArrayList<Exp>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Match match$ = t$.match;
            switch(match$) {
            case VAR:
                varList.add(scn$.match(Token.Match.VAR, trace$));
                scn$.match(Token.Match.EQUALS, trace$);
                expList.add(Exp.parse(scn$, trace$));
                continue;
            default:
                return new LetDecls(varList, expList);
            }
        }

    }

    public Env addBindings(Env env) {
        Rands rands = new Rands(expList);
        List<Val> valList = rands.evalRands(env);
        Bindings bindings = new Bindings(varList, valList);
        return env.extendEnv(bindings);
    }

    public String toString() {
        return "... LetDecls ...";
    }

//LetDecls//
}
