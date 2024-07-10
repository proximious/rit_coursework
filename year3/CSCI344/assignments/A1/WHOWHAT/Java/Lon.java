//Lon:top//
//Lon:import//
import java.util.*;

// <lon> ::= LPAREN <nums> RPAREN
public class Lon extends _Start /*Lon:class*/ {

    public static final String $className = "Lon";
    public static final String $ruleString =
        "<lon> ::= LPAREN <nums> RPAREN";

    public Nums nums;
    public Lon(Nums nums) {
//Lon:init//
        this.nums = nums;
    }

    public static Lon parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<lon>", scn$.lno);
        scn$.match(Token.Match.LPAREN, trace$);
        Nums nums = Nums.parse(scn$, trace$);
        scn$.match(Token.Match.RPAREN, trace$);
        return new Lon(nums);
    }

        public void $run() {
            System.out.print("( ");
            nums.$run();
            System.out.println(")");
        }

//Lon//
}
