//XY:top//
//XY:import//
import java.util.*;

// <xnyn>:XY ::= X <xnyn> Y
public class XY extends Xnyn /*XY:class*/ {

    public static final String $className = "XY";
    public static final String $ruleString =
        "<xnyn>:XY ::= X <xnyn> Y";

    public Xnyn xnyn;

    public XY(Xnyn xnyn) {
//XY:init//
        this.xnyn = xnyn;
    }

    public static XY parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<xnyn>:XY", scn$.lno);
        scn$.match(Token.Match.X, trace$);
        Xnyn xnyn = Xnyn.parse(scn$, trace$);
        scn$.match(Token.Match.Y, trace$);
        return new XY(xnyn);
    }

//XY//
}
