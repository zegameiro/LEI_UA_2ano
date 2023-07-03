// Generated from /home/jose/Desktop/LEI/2ºano/2ºSemestre/C/guiao-p/ex1/StrLang.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StrLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		VAR=10, INT=11, ID=12, STR=13, COMMENT=14, WS=15, ERROR=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"VAR", "INT", "ID", "STR", "COMMENT", "WS", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'print'", "'+'", "'-'", "'trim'", "'('", "')'", "'/'", 
			"'input'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "VAR", "INT", 
			"ID", "STR", "COMMENT", "WS", "ERROR"
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


	public StrLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "StrLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22o\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\6\13C\n\13\r\13\16\13D"+
		"\3\f\6\fH\n\f\r\f\16\fI\3\r\6\rM\n\r\r\r\16\rN\3\16\3\16\7\16S\n\16\f"+
		"\16\16\16V\13\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17^\n\17\f\17\16\17a"+
		"\13\17\3\17\3\17\3\17\3\17\3\20\6\20h\n\20\r\20\16\20i\3\20\3\20\3\21"+
		"\3\21\4T_\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22\3\2\5\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\2u\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2"+
		"\2\5%\3\2\2\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\64\3\2\2\2\17\66\3\2"+
		"\2\2\218\3\2\2\2\23:\3\2\2\2\25B\3\2\2\2\27G\3\2\2\2\31L\3\2\2\2\33P\3"+
		"\2\2\2\35Y\3\2\2\2\37g\3\2\2\2!m\3\2\2\2#$\7<\2\2$\4\3\2\2\2%&\7r\2\2"+
		"&\'\7t\2\2\'(\7k\2\2()\7p\2\2)*\7v\2\2*\6\3\2\2\2+,\7-\2\2,\b\3\2\2\2"+
		"-.\7/\2\2.\n\3\2\2\2/\60\7v\2\2\60\61\7t\2\2\61\62\7k\2\2\62\63\7o\2\2"+
		"\63\f\3\2\2\2\64\65\7*\2\2\65\16\3\2\2\2\66\67\7+\2\2\67\20\3\2\2\289"+
		"\7\61\2\29\22\3\2\2\2:;\7k\2\2;<\7p\2\2<=\7r\2\2=>\7w\2\2>?\7v\2\2?\24"+
		"\3\2\2\2@C\5\27\f\2AC\5\31\r\2B@\3\2\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2"+
		"DE\3\2\2\2E\26\3\2\2\2FH\t\2\2\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2"+
		"\2J\30\3\2\2\2KM\t\3\2\2LK\3\2\2\2MN\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\32\3"+
		"\2\2\2PT\7$\2\2QS\13\2\2\2RQ\3\2\2\2SV\3\2\2\2TU\3\2\2\2TR\3\2\2\2UW\3"+
		"\2\2\2VT\3\2\2\2WX\7$\2\2X\34\3\2\2\2YZ\7\61\2\2Z[\7\61\2\2[_\3\2\2\2"+
		"\\^\13\2\2\2]\\\3\2\2\2^a\3\2\2\2_`\3\2\2\2_]\3\2\2\2`b\3\2\2\2a_\3\2"+
		"\2\2bc\7\f\2\2cd\3\2\2\2de\b\17\2\2e\36\3\2\2\2fh\t\4\2\2gf\3\2\2\2hi"+
		"\3\2\2\2ig\3\2\2\2ij\3\2\2\2jk\3\2\2\2kl\b\20\2\2l \3\2\2\2mn\13\2\2\2"+
		"n\"\3\2\2\2\n\2BDINT_i\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}