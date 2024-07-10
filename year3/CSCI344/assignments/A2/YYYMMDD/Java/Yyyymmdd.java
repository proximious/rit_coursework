//Yyyymmdd:top//
//Yyyymmdd:import//
import java.util.*;

// <yyyymmdd> ::= <DIGIT4>yyyy SEP <DIGIT2>mm SEP <DIGIT2>dd NL
public class Yyyymmdd extends _Start /*Yyyymmdd:class*/ {

    public static final String $className = "Yyyymmdd";
    public static final String $ruleString =
        "<yyyymmdd> ::= <DIGIT4>yyyy SEP <DIGIT2>mm SEP <DIGIT2>dd NL";

    public Token yyyy;
    public Token mm;
    public Token dd;
    public Yyyymmdd(Token yyyy, Token mm, Token dd) {
//Yyyymmdd:init//
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
    }

    public static Yyyymmdd parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<yyyymmdd>", scn$.lno);
        Token yyyy = scn$.match(Token.Match.DIGIT4, trace$);
        scn$.match(Token.Match.SEP, trace$);
        Token mm = scn$.match(Token.Match.DIGIT2, trace$);
        scn$.match(Token.Match.SEP, trace$);
        Token dd = scn$.match(Token.Match.DIGIT2, trace$);
        scn$.match(Token.Match.NL, trace$);
        return new Yyyymmdd(yyyy, mm, dd);
    }

	public void $run(){
		String yearString = yyyy.toString();
		int year = Integer.parseInt(yearString);

		String monthString = mm.toString();
                int month = Integer.parseInt(monthString);

		String dayString = dd.toString();
                int day = Integer.parseInt(dayString);


		if (month == 1){
			month = 13;
			year--;
		}

		if (month == 2) {
			month = 14;
			year--;
		}

		int d = day;
		int m = month;
		int ymod = year % 100;
		int ydiv = year / 100;

		// found this equation on the internet
		int a = d + 13*(m + 1)/5 + ymod + ymod/4 + ydiv/4 + 5*ydiv;

		a = a % 7;

		switch (a){
			case 0:
				System.out.println("Saturday");
				break;
			case 1:
				System.out.println("Sunday");
                                break;
			case 2:
				System.out.println("Monday");
                                break;
			case 3:
				System.out.println("Tuesday");
                                break;
			case 4:
				System.out.println("Wednesday");
                                break;
			case 5:
				System.out.println("Thursday");
                                break;
			case 6:
				System.out.println("Friday");
                                break;
		}
	}

//Yyyymmdd//
}
