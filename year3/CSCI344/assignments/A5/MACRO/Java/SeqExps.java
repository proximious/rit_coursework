//SeqExps:top//
//SeqExps:import//
import java.util.*;

// <seqExps> **= SEMI <exp>
public class SeqExps /*SeqExps:class*/ {

    public static final String $className = "SeqExps";
    public static final String $ruleString =
        "<seqExps> **= SEMI <exp>";

    public List<Exp> expList;
    public SeqExps(List<Exp> expList) {
//SeqExps:init//
        this.expList = expList;
    }

    public static SeqExps parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<seqExps>", scn$.lno);
        List<Exp> expList = new ArrayList<Exp>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Match match$ = t$.match;
            switch(match$) {
            case SEMI:
                scn$.match(Token.Match.SEMI, trace$);
                expList.add(Exp.parse(scn$, trace$));
                continue;
            default:
                return new SeqExps(expList);
            }
        }

    }

//SeqExps//
}
