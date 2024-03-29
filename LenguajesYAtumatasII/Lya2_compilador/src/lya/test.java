package lya;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class test implements testConstants {
	static class Identifier {
		String name;
		String type;
		String description;
		String data;

		public Identifier(String name, String type, String data, String description) {
			this.name = name;
			this.type = type;
			this.description = description;
			this.data = data;
		}

		public Identifier(String name, String description) {
			this.name = name;
			type = "";
			this.description = description;
			data = "";
		}

		public String toString() {
			return String.format("%-10s %-10s %-10s %-10s", data, name, type, description);
		}
	}

	static String data = "";
	static String error = "";
	static List<Identifier> symbols = new ArrayList<Identifier>();

	@SuppressWarnings({ "static-access", "unused" })
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		try {
			test analizador = new test(new FileReader("src/lya/codigo.txt"));
			analizador.Programa();
			AnalisisSemantico as = new AnalisisSemantico("src/lya/codigo.txt");
			//AnalisisSemantico as= new
			//AnalisisSemantico(analizador.jj_input_stream.GetImage());
			if (error.equals(""))
				System.out.println("Exito, codigo correcto.");
			else {
//---------------System.out.println("Error, algo anda mal: ");
//---------------System.out.println(error);
			}
//---------------System.out.println("Identificadores encontrados: ");
			for (Identifier i : symbols) {
//---------------System.out.println(i.toString());
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			System.out.println("Error!!!!");
		}
	}

//llamar al metodo, empieza la gramatica.
	static final public void Programa() throws ParseException {
		class_declaration();
	}

	static final public void class_declaration() throws ParseException {
		try {
			jj_consume_token(modifier);
			jj_consume_token(class_word);
			class_identifier();
			jj_consume_token(open_key);
			field_declaration();
			statement();
			jj_consume_token(close_key);
		} catch (ParseException e) {
			error += "Error L\u00e9xico: \u005ct" + e.getMessage() + "\u005cn";
		} catch (TokenMgrError e) {
			error += "Error Sintactico: \u005ct" + e.getMessage() + "\u005cn";
		}
	}

	static final public void class_identifier() throws ParseException {
		jj_consume_token(identifier);
		symbols.add(new Identifier(token.image, "Clase"));
	}

	static final public void field_declaration() throws ParseException {
		variable_declaration();
	}

	static final public void variable_declaration() throws ParseException {
		jj_consume_token(modifier);
		data = type();
		variable_declarator();
		jj_consume_token(semicolon);
	}

	@SuppressWarnings("unused")
	static final public String type() throws ParseException {
		jj_consume_token(type_specifier);
		{
			if (true)
				return token.image;
		}
		throw new Error("Missing return statement in function");
	}

	static final public void variable_declarator() throws ParseException {
		String identifier;
		String type;
		Identifier ident;
		identifier = variable_identifier();
		jj_consume_token(equals);
		type = variable_type();
		ident = new Identifier(identifier, type, data, "Variable");
		symbols.add(ident);
	}

	@SuppressWarnings("unused")
	static final public String variable_identifier() throws ParseException {
		jj_consume_token(identifier);
		{
			if (true)
				return token.image;
		}
		throw new Error("Missing return statement in function");
	}

	@SuppressWarnings("unused")
	static final public String variable_type() throws ParseException {
		if (jj_2_1(2)) {
			jj_consume_token(integer_literal);
		} else if (jj_2_2(2)) {
			jj_consume_token(boolean_literal);
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return token.image;
		}
		throw new Error("Missing return statement in function");
	}

	static final public void if_statement() throws ParseException {
		jj_consume_token(if_declaration);
		jj_consume_token(open_parenthesis);
		expression();
		jj_consume_token(close_parenthesis);
		statement_block();
	}

	static final public void expression() throws ParseException {
		testing_expression();
	}

	static final public void testing_expression() throws ParseException {
		if (jj_2_3(2)) {
			jj_consume_token(integer_literal);
		} else if (jj_2_4(2)) {
			jj_consume_token(identifier);
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(relational_operator);
		if (jj_2_5(2)) {
			jj_consume_token(integer_literal);
		} else if (jj_2_6(2)) {
			jj_consume_token(identifier);
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void statement() throws ParseException {
		if (jj_2_7(2)) {
			variable_declaration();
		} else if (jj_2_8(2)) {
			if_statement();
		} else if (jj_2_9(2)) {
			while_statement();
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void while_statement() throws ParseException {
		jj_consume_token(while_declaration);
		jj_consume_token(open_parenthesis);
		expression();
		jj_consume_token(close_parenthesis);
		statement_block();
	}

	static final public void statement_block() throws ParseException {
		jj_consume_token(open_key);
		aritmetic_expression();
		jj_consume_token(close_key);
	}

	static final public void aritmetic_expression() throws ParseException {
		jj_consume_token(identifier);
		jj_consume_token(equals);
		jj_consume_token(integer_literal);
		jj_consume_token(aritmetic_operator);
		jj_consume_token(integer_literal);
		jj_consume_token(semicolon);
	}

	static private boolean jj_2_1(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_1();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(0, xla);
		}
	}

	static private boolean jj_2_2(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_2();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(1, xla);
		}
	}

	static private boolean jj_2_3(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_3();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(2, xla);
		}
	}

	static private boolean jj_2_4(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_4();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(3, xla);
		}
	}

	static private boolean jj_2_5(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_5();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(4, xla);
		}
	}

	static private boolean jj_2_6(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_6();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(5, xla);
		}
	}

	static private boolean jj_2_7(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_7();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(6, xla);
		}
	}

	static private boolean jj_2_8(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_8();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(7, xla);
		}
	}

	static private boolean jj_2_9(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_9();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(8, xla);
		}
	}

	static private boolean jj_3R_1() {
		if (jj_scan_token(modifier))
			return true;
		if (jj_3R_4())
			return true;
		return false;
	}

	static private boolean jj_3_7() {
		if (jj_3R_1())
			return true;
		return false;
	}

	static private boolean jj_3R_3() {
		if (jj_scan_token(while_declaration))
			return true;
		if (jj_scan_token(open_parenthesis))
			return true;
		return false;
	}

	static private boolean jj_3_3() {
		if (jj_scan_token(integer_literal))
			return true;
		return false;
	}

	static private boolean jj_3_9() {
		if (jj_3R_3())
			return true;
		return false;
	}

	static private boolean jj_3_4() {
		if (jj_scan_token(identifier))
			return true;
		return false;
	}

	static private boolean jj_3R_2() {
		if (jj_scan_token(if_declaration))
			return true;
		if (jj_scan_token(open_parenthesis))
			return true;
		return false;
	}

	static private boolean jj_3_1() {
		if (jj_scan_token(integer_literal))
			return true;
		return false;
	}

	static private boolean jj_3_5() {
		if (jj_scan_token(integer_literal))
			return true;
		return false;
	}

	static private boolean jj_3R_4() {
		if (jj_scan_token(type_specifier))
			return true;
		return false;
	}

	static private boolean jj_3_2() {
		if (jj_scan_token(boolean_literal))
			return true;
		return false;
	}

	static private boolean jj_3_6() {
		if (jj_scan_token(identifier))
			return true;
		return false;
	}

	static private boolean jj_3_8() {
		if (jj_3R_2())
			return true;
		return false;
	}

	static private boolean jj_initialized_once = false;
	/** Generated Token Manager. */
	static public testTokenManager token_source;
	static SimpleCharStream jj_input_stream;
	/** Current token. */
	static public Token token;
	/** Next token. */
	static public Token jj_nt;
	@SuppressWarnings("unused")
	static private int jj_ntk;
	static private Token jj_scanpos, jj_lastpos;
	static private int jj_la;
	static private int jj_gen;
	static final private int[] jj_la1 = new int[0];
	static private int[] jj_la1_0;
	static {
		jj_la1_init_0();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] {};
	}

	static final private JJCalls[] jj_2_rtns = new JJCalls[9];
	static private boolean jj_rescan = false;
	static private int jj_gc = 0;

	/** Constructor with InputStream. */
	public test(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public test(java.io.InputStream stream, String encoding) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser.  ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		try {
			jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new testTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	@SuppressWarnings("static-access")
	static public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Constructor. */
	public test(java.io.Reader stream) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new testTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	@SuppressWarnings("static-access")
	static public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Constructor with generated Token Manager. */
	public test(testTokenManager tm) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	public void ReInit(testTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	@SuppressWarnings("static-access")
	static private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			if (++jj_gc > 100) {
				jj_gc = 0;
				for (int i = 0; i < jj_2_rtns.length; i++) {
					JJCalls c = jj_2_rtns[i];
					while (c != null) {
						if (c.gen < jj_gen)
							c.first = null;
						c = c.next;
					}
				}
			}
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	@SuppressWarnings("serial")
	static private final class LookaheadSuccess extends java.lang.Error {
	}

	static final private LookaheadSuccess jj_ls = new LookaheadSuccess();

	@SuppressWarnings("static-access")
	static private boolean jj_scan_token(int kind) {
		if (jj_scanpos == jj_lastpos) {
			jj_la--;
			if (jj_scanpos.next == null) {
				jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
			} else {
				jj_lastpos = jj_scanpos = jj_scanpos.next;
			}
		} else {
			jj_scanpos = jj_scanpos.next;
		}
		if (jj_rescan) {
			int i = 0;
			Token tok = token;
			while (tok != null && tok != jj_scanpos) {
				i++;
				tok = tok.next;
			}
			if (tok != null)
				jj_add_error_token(kind, i);
		}
		if (jj_scanpos.kind != kind)
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			throw jj_ls;
		return false;
	}

	/** Get the next Token. */
	@SuppressWarnings("static-access")
	static final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	@SuppressWarnings("static-access")
	static final public Token getToken(int index) {
		Token t = token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	@SuppressWarnings({ "unused", "static-access" })
	static private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	static private int[] jj_expentry;
	static private int jj_kind = -1;
	static private int[] jj_lasttokens = new int[100];
	static private int jj_endpos;

	static private void jj_add_error_token(int kind, int pos) {
		if (pos >= 100)
			return;
		if (pos == jj_endpos + 1) {
			jj_lasttokens[jj_endpos++] = kind;
		} else if (jj_endpos != 0) {
			jj_expentry = new int[jj_endpos];
			for (int i = 0; i < jj_endpos; i++) {
				jj_expentry[i] = jj_lasttokens[i];
			}
			jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
				int[] oldentry = (int[]) (it.next());
				if (oldentry.length == jj_expentry.length) {
					for (int i = 0; i < jj_expentry.length; i++) {
						if (oldentry[i] != jj_expentry[i]) {
							continue jj_entries_loop;
						}
					}
					jj_expentries.add(jj_expentry);
					break jj_entries_loop;
				}
			}
			if (pos != 0)
				jj_lasttokens[(jj_endpos = pos) - 1] = kind;
		}
	}

	/** Generate ParseException. */
	static public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[21];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 0; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 21; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		jj_endpos = 0;
		jj_rescan_token();
		jj_add_error_token(0, 0);
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	static final public void enable_tracing() {
	}

	/** Disable tracing. */
	static final public void disable_tracing() {
	}

	static private void jj_rescan_token() {
		jj_rescan = true;
		for (int i = 0; i < 9; i++) {
			try {
				JJCalls p = jj_2_rtns[i];
				do {
					if (p.gen > jj_gen) {
						jj_la = p.arg;
						jj_lastpos = jj_scanpos = p.first;
						switch (i) {
						case 0:
							jj_3_1();
							break;
						case 1:
							jj_3_2();
							break;
						case 2:
							jj_3_3();
							break;
						case 3:
							jj_3_4();
							break;
						case 4:
							jj_3_5();
							break;
						case 5:
							jj_3_6();
							break;
						case 6:
							jj_3_7();
							break;
						case 7:
							jj_3_8();
							break;
						case 8:
							jj_3_9();
							break;
						}
					}
					p = p.next;
				} while (p != null);
			} catch (LookaheadSuccess ls) {
			}
		}
		jj_rescan = false;
	}

	static private void jj_save(int index, int xla) {
		JJCalls p = jj_2_rtns[index];
		while (p.gen > jj_gen) {
			if (p.next == null) {
				p = p.next = new JJCalls();
				break;
			}
			p = p.next;
		}
		p.gen = jj_gen + xla - jj_la;
		p.first = token;
		p.arg = xla;
	}

	static final class JJCalls {
		int gen;
		Token first;
		int arg;
		JJCalls next;
	}

}
