// Generated from /home/jose/Desktop/LEI/2ºano/2ºSemestre/C/Práticas/Compilador/FractionsCalculator.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FractionsCalculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, LITERAL=12, INT=13, ID=14, NEWLINE=15, WS=16, LINE_COMMENT=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "LITERAL", "INT", "ID", "NEWLINE", "WS", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'print '", "'->'", "'-'", "'+'", "'*'", "':'", "'('", "')'", 
			"'^'", "'reduce'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"LITERAL", "INT", "ID", "NEWLINE", "WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FractionsCalculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FractionsCalculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23v\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\5\rJ\n\r\3\16\6\16M\n\16\r\16\16\16N\3\17\6\17R\n\17\r\17\16\17"+
		"S\3\17\7\17W\n\17\f\17\16\17Z\13\17\3\20\5\20]\n\20\3\20\3\20\5\20a\n"+
		"\20\3\21\6\21d\n\21\r\21\16\21e\3\21\3\21\3\22\3\22\3\22\3\22\7\22n\n"+
		"\22\f\22\16\22q\13\22\3\22\3\22\3\22\3\22\3o\2\23\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\6\3"+
		"\2\62;\4\2C\\c|\5\2\62;C\\c|\4\2\13\13\"\"\2}\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5\'\3\2"+
		"\2\2\7.\3\2\2\2\t\61\3\2\2\2\13\63\3\2\2\2\r\65\3\2\2\2\17\67\3\2\2\2"+
		"\219\3\2\2\2\23;\3\2\2\2\25=\3\2\2\2\27?\3\2\2\2\31F\3\2\2\2\33L\3\2\2"+
		"\2\35Q\3\2\2\2\37`\3\2\2\2!c\3\2\2\2#i\3\2\2\2%&\7=\2\2&\4\3\2\2\2\'("+
		"\7r\2\2()\7t\2\2)*\7k\2\2*+\7p\2\2+,\7v\2\2,-\7\"\2\2-\6\3\2\2\2./\7/"+
		"\2\2/\60\7@\2\2\60\b\3\2\2\2\61\62\7/\2\2\62\n\3\2\2\2\63\64\7-\2\2\64"+
		"\f\3\2\2\2\65\66\7,\2\2\66\16\3\2\2\2\678\7<\2\28\20\3\2\2\29:\7*\2\2"+
		":\22\3\2\2\2;<\7+\2\2<\24\3\2\2\2=>\7`\2\2>\26\3\2\2\2?@\7t\2\2@A\7g\2"+
		"\2AB\7f\2\2BC\7w\2\2CD\7e\2\2DE\7g\2\2E\30\3\2\2\2FI\5\33\16\2GH\7\61"+
		"\2\2HJ\5\33\16\2IG\3\2\2\2IJ\3\2\2\2J\32\3\2\2\2KM\t\2\2\2LK\3\2\2\2M"+
		"N\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\34\3\2\2\2PR\t\3\2\2QP\3\2\2\2RS\3\2\2"+
		"\2SQ\3\2\2\2ST\3\2\2\2TX\3\2\2\2UW\t\4\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2"+
		"\2XY\3\2\2\2Y\36\3\2\2\2ZX\3\2\2\2[]\7\17\2\2\\[\3\2\2\2\\]\3\2\2\2]^"+
		"\3\2\2\2^a\7\f\2\2_a\5#\22\2`\\\3\2\2\2`_\3\2\2\2a \3\2\2\2bd\t\5\2\2"+
		"cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\b\21\2\2h\"\3\2\2"+
		"\2ij\7\61\2\2jk\7\61\2\2ko\3\2\2\2ln\13\2\2\2ml\3\2\2\2nq\3\2\2\2op\3"+
		"\2\2\2om\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\f\2\2st\3\2\2\2tu\b\22\2\2u$"+
		"\3\2\2\2\f\2INSVX\\`eo\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}