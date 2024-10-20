import java.util.*;
import java.util.regex.*;

// Token class with match patterns (used with the built-in Scan class)
public class Token {

    // this is set to an error message string
    // if there are pattern compile errors
    public static String patternFail = null; //
    public static final Match $eof = Match.$EOF;

    public enum Match {
        NL ("\\n"),
        ZERO ("0"),
        ONE ("1"),
        $ERROR (null),
        $EOF (null);

        public String pattern;
        public boolean skip;
        public Pattern cPattern; // compiled pattern

        // a token pattern (skip == false)
        Match(String pattern) {
            this(pattern, false);
        }

        Match(String pattern, boolean skip) {
            this.pattern = pattern;
            this.skip = skip;
            if (pattern != null) {
                try {
                    this.cPattern = Pattern.compile(pattern, Pattern.DOTALL);
                } catch (PatternSyntaxException e) {
                    if (patternFail == null) {
                        patternFail = "Lexical specification errors() for";
                    }
                    patternFail += (" " +this);
                    this.cPattern = null;
                }
            }
        }

        // Use this to force loading Match class to compile patterns.
        public static String init() {
            return patternFail;
        }
    }

    public Match match;      // token match
    public String str;       // this token's lexeme
    public int lno;          // the line number where this token was found
    public String line;      // the text line where this token appears

    public Token() {
        match = null;
        str = null;
        lno = 0;
        line = null;
    }

    public Token(Match match, String str, int lno, String line) {
        this.match = match;
        this.str = str;
        this.lno = lno;
        this.line = line;
    }

    public Token(Match match, String str) {
        this(match, str, 0, null);
    }

    public String toString() {
        return str;
    }

    public String errString() {
        switch(match) {
        case $EOF:
        case $ERROR:
            return str;
        default:
            return match.toString(); // just the match name
        }
    }

    public boolean isEOF() {
        return this.match == $eof;
    }

    public static void main(String [] args) {
        String msg = Match.init();
        if (msg != null)
            System.out.println(msg);
        for (Match match: Match.values()) {
            if (match.pattern == null)
                continue;
            String what;
            if (match.skip)
                what = "skip";
            else
                what = "token";
            System.out.println(
                String.format("%s %s '%s'", what, match, match.pattern)
            );
        }
        if (msg != null)
            System.exit(1);
    }

//Token//

}
