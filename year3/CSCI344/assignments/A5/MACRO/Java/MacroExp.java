//MacroExp:top//
//MacroExp:import//
import java.util.*;

// <exp>:MacroExp ::= <macro>
public class MacroExp extends Exp /*MacroExp:class*/ {

    public static final String $className = "MacroExp";
    public static final String $ruleString =
        "<exp>:MacroExp ::= <macro>";

    public Macro macro;
    public MacroExp(Macro macro) {
//MacroExp:init//
        this.macro = macro;
    }

    public static MacroExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:MacroExp", scn$.lno);
        Macro macro = Macro.parse(scn$, trace$);
        return new MacroExp(macro);
    }

	public Val eval(Env env) {
		return macro.makeMacro();
	}

//MacroExp//
}
