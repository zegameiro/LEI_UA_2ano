// Generated from /home/jose/Desktop/LEI/2ºano/2ºSemestre/C/guiao-p/teste_bigInt/BigIntegerLang.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BigIntegerLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, VAR=15, STR=16, INT=17, 
		WS=18, COMMENT=19, ERROR=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "VAR", "STR", "INT", "WS", 
			"COMMENT", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'show'", "'->'", "'if'", "'then'", "'end'", "'else'", "'+'", 
			"'-'", "'*'", "'div'", "'mod'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "VAR", "STR", "INT", "WS", "COMMENT", "ERROR"
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


	public BigIntegerLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BigIntegerLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26|\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\6\20[\n\20\r\20\16\20\\\3\21\6\21`\n\21\r\21\16\21a\3"+
		"\22\6\22e\n\22\r\22\16\22f\3\23\6\23j\n\23\r\23\16\23k\3\23\3\23\3\24"+
		"\3\24\7\24r\n\24\f\24\16\24u\13\24\3\24\3\24\3\24\3\24\3\25\3\25\3s\2"+
		"\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26\3\2\6\4\2C\\c|\3\2\62;\5\2\13\f\17\17\""+
		"\"\4\2\f\f\17\17\2\u0081\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2"+
		"\2\5-\3\2\2\2\7\62\3\2\2\2\t\65\3\2\2\2\138\3\2\2\2\r=\3\2\2\2\17A\3\2"+
		"\2\2\21F\3\2\2\2\23H\3\2\2\2\25J\3\2\2\2\27L\3\2\2\2\31P\3\2\2\2\33T\3"+
		"\2\2\2\35V\3\2\2\2\37Z\3\2\2\2!_\3\2\2\2#d\3\2\2\2%i\3\2\2\2\'o\3\2\2"+
		"\2)z\3\2\2\2+,\7=\2\2,\4\3\2\2\2-.\7u\2\2./\7j\2\2/\60\7q\2\2\60\61\7"+
		"y\2\2\61\6\3\2\2\2\62\63\7/\2\2\63\64\7@\2\2\64\b\3\2\2\2\65\66\7k\2\2"+
		"\66\67\7h\2\2\67\n\3\2\2\289\7v\2\29:\7j\2\2:;\7g\2\2;<\7p\2\2<\f\3\2"+
		"\2\2=>\7g\2\2>?\7p\2\2?@\7f\2\2@\16\3\2\2\2AB\7g\2\2BC\7n\2\2CD\7u\2\2"+
		"DE\7g\2\2E\20\3\2\2\2FG\7-\2\2G\22\3\2\2\2HI\7/\2\2I\24\3\2\2\2JK\7,\2"+
		"\2K\26\3\2\2\2LM\7f\2\2MN\7k\2\2NO\7x\2\2O\30\3\2\2\2PQ\7o\2\2QR\7q\2"+
		"\2RS\7f\2\2S\32\3\2\2\2TU\7*\2\2U\34\3\2\2\2VW\7+\2\2W\36\3\2\2\2X[\5"+
		"!\21\2Y[\5#\22\2ZX\3\2\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2"+
		"] \3\2\2\2^`\t\2\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\"\3\2\2"+
		"\2ce\t\3\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2g$\3\2\2\2hj\t\4\2"+
		"\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\b\23\2\2n&\3\2"+
		"\2\2os\7%\2\2pr\13\2\2\2qp\3\2\2\2ru\3\2\2\2st\3\2\2\2sq\3\2\2\2tv\3\2"+
		"\2\2us\3\2\2\2vw\t\5\2\2wx\3\2\2\2xy\b\24\2\2y(\3\2\2\2z{\13\2\2\2{*\3"+
		"\2\2\2\t\2Z\\afks\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}