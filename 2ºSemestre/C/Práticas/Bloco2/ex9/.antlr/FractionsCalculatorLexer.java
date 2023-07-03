// Generated from /home/jose/Desktop/LEI/2ºano/2ºSemestre/C/Práticas/Bloco2/ex9/FractionsCalculator.g4 by ANTLR 4.9.2
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, INT=15, ID=16, NEWLINE=17, 
		WS=18, LINE_COMMENT=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "INT", "ID", "NEWLINE", "WS", 
			"LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "';'", "'print '", "'reduce '", "'read'", "'\"'", "'*'", 
			"':'", "'+'", "'-'", "'('", "')'", "'^'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "INT", "ID", "NEWLINE", "WS", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20V"+
		"\n\20\r\20\16\20W\3\21\6\21[\n\21\r\21\16\21\\\3\21\7\21`\n\21\f\21\16"+
		"\21c\13\21\3\22\5\22f\n\22\3\22\3\22\5\22j\n\22\3\23\6\23m\n\23\r\23\16"+
		"\23n\3\23\3\23\3\24\3\24\3\24\3\24\7\24w\n\24\f\24\16\24z\13\24\3\24\3"+
		"\24\3\24\3\24\3x\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\6\3\2\62;\4\2C\\c|\5\2"+
		"\62;C\\c|\4\2\13\13\"\"\2\u0085\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2"+
		"\5,\3\2\2\2\7.\3\2\2\2\t\65\3\2\2\2\13=\3\2\2\2\rB\3\2\2\2\17D\3\2\2\2"+
		"\21F\3\2\2\2\23H\3\2\2\2\25J\3\2\2\2\27L\3\2\2\2\31N\3\2\2\2\33P\3\2\2"+
		"\2\35R\3\2\2\2\37U\3\2\2\2!Z\3\2\2\2#i\3\2\2\2%l\3\2\2\2\'r\3\2\2\2)*"+
		"\7/\2\2*+\7@\2\2+\4\3\2\2\2,-\7=\2\2-\6\3\2\2\2./\7r\2\2/\60\7t\2\2\60"+
		"\61\7k\2\2\61\62\7p\2\2\62\63\7v\2\2\63\64\7\"\2\2\64\b\3\2\2\2\65\66"+
		"\7t\2\2\66\67\7g\2\2\678\7f\2\289\7w\2\29:\7e\2\2:;\7g\2\2;<\7\"\2\2<"+
		"\n\3\2\2\2=>\7t\2\2>?\7g\2\2?@\7c\2\2@A\7f\2\2A\f\3\2\2\2BC\7$\2\2C\16"+
		"\3\2\2\2DE\7,\2\2E\20\3\2\2\2FG\7<\2\2G\22\3\2\2\2HI\7-\2\2I\24\3\2\2"+
		"\2JK\7/\2\2K\26\3\2\2\2LM\7*\2\2M\30\3\2\2\2NO\7+\2\2O\32\3\2\2\2PQ\7"+
		"`\2\2Q\34\3\2\2\2RS\7\61\2\2S\36\3\2\2\2TV\t\2\2\2UT\3\2\2\2VW\3\2\2\2"+
		"WU\3\2\2\2WX\3\2\2\2X \3\2\2\2Y[\t\3\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2"+
		"\2\\]\3\2\2\2]a\3\2\2\2^`\t\4\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2"+
		"\2\2b\"\3\2\2\2ca\3\2\2\2df\7\17\2\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2gj\7"+
		"\f\2\2hj\5\'\24\2ie\3\2\2\2ih\3\2\2\2j$\3\2\2\2km\t\5\2\2lk\3\2\2\2mn"+
		"\3\2\2\2nl\3\2\2\2no\3\2\2\2op\3\2\2\2pq\b\23\2\2q&\3\2\2\2rs\7\61\2\2"+
		"st\7\61\2\2tx\3\2\2\2uw\13\2\2\2vu\3\2\2\2wz\3\2\2\2xy\3\2\2\2xv\3\2\2"+
		"\2y{\3\2\2\2zx\3\2\2\2{|\7\f\2\2|}\3\2\2\2}~\b\24\2\2~(\3\2\2\2\13\2W"+
		"\\_aeinx\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}