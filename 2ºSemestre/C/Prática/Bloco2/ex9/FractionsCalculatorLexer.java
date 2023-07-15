// Generated from FractionsCalculator.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FractionsCalculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

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
		"\u0004\u0000\u0013}\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0004\u000eT\b\u000e\u000b\u000e\f\u000eU\u0001\u000f\u0004\u000f"+
		"Y\b\u000f\u000b\u000f\f\u000fZ\u0001\u000f\u0005\u000f^\b\u000f\n\u000f"+
		"\f\u000fa\t\u000f\u0001\u0010\u0003\u0010d\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010h\b\u0010\u0001\u0011\u0004\u0011k\b\u0011\u000b\u0011\f\u0011"+
		"l\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012u\b\u0012\n\u0012\f\u0012x\t\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001v\u0000\u0013\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\u0001\u0000\u0004\u0001\u000009\u0002\u0000AZaz\u0003\u0000"+
		"09AZaz\u0002\u0000\t\t  \u0083\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0001\'\u0001\u0000\u0000\u0000"+
		"\u0003*\u0001\u0000\u0000\u0000\u0005,\u0001\u0000\u0000\u0000\u00073"+
		"\u0001\u0000\u0000\u0000\t;\u0001\u0000\u0000\u0000\u000b@\u0001\u0000"+
		"\u0000\u0000\rB\u0001\u0000\u0000\u0000\u000fD\u0001\u0000\u0000\u0000"+
		"\u0011F\u0001\u0000\u0000\u0000\u0013H\u0001\u0000\u0000\u0000\u0015J"+
		"\u0001\u0000\u0000\u0000\u0017L\u0001\u0000\u0000\u0000\u0019N\u0001\u0000"+
		"\u0000\u0000\u001bP\u0001\u0000\u0000\u0000\u001dS\u0001\u0000\u0000\u0000"+
		"\u001fX\u0001\u0000\u0000\u0000!g\u0001\u0000\u0000\u0000#j\u0001\u0000"+
		"\u0000\u0000%p\u0001\u0000\u0000\u0000\'(\u0005-\u0000\u0000()\u0005>"+
		"\u0000\u0000)\u0002\u0001\u0000\u0000\u0000*+\u0005;\u0000\u0000+\u0004"+
		"\u0001\u0000\u0000\u0000,-\u0005p\u0000\u0000-.\u0005r\u0000\u0000./\u0005"+
		"i\u0000\u0000/0\u0005n\u0000\u000001\u0005t\u0000\u000012\u0005 \u0000"+
		"\u00002\u0006\u0001\u0000\u0000\u000034\u0005r\u0000\u000045\u0005e\u0000"+
		"\u000056\u0005d\u0000\u000067\u0005u\u0000\u000078\u0005c\u0000\u0000"+
		"89\u0005e\u0000\u00009:\u0005 \u0000\u0000:\b\u0001\u0000\u0000\u0000"+
		";<\u0005r\u0000\u0000<=\u0005e\u0000\u0000=>\u0005a\u0000\u0000>?\u0005"+
		"d\u0000\u0000?\n\u0001\u0000\u0000\u0000@A\u0005\"\u0000\u0000A\f\u0001"+
		"\u0000\u0000\u0000BC\u0005*\u0000\u0000C\u000e\u0001\u0000\u0000\u0000"+
		"DE\u0005:\u0000\u0000E\u0010\u0001\u0000\u0000\u0000FG\u0005+\u0000\u0000"+
		"G\u0012\u0001\u0000\u0000\u0000HI\u0005-\u0000\u0000I\u0014\u0001\u0000"+
		"\u0000\u0000JK\u0005(\u0000\u0000K\u0016\u0001\u0000\u0000\u0000LM\u0005"+
		")\u0000\u0000M\u0018\u0001\u0000\u0000\u0000NO\u0005^\u0000\u0000O\u001a"+
		"\u0001\u0000\u0000\u0000PQ\u0005/\u0000\u0000Q\u001c\u0001\u0000\u0000"+
		"\u0000RT\u0007\u0000\u0000\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\u001e"+
		"\u0001\u0000\u0000\u0000WY\u0007\u0001\u0000\u0000XW\u0001\u0000\u0000"+
		"\u0000YZ\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[_\u0001\u0000\u0000\u0000\\^\u0007\u0002\u0000\u0000]\\\u0001"+
		"\u0000\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000"+
		"_`\u0001\u0000\u0000\u0000` \u0001\u0000\u0000\u0000a_\u0001\u0000\u0000"+
		"\u0000bd\u0005\r\u0000\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000"+
		"\u0000de\u0001\u0000\u0000\u0000eh\u0005\n\u0000\u0000fh\u0003%\u0012"+
		"\u0000gc\u0001\u0000\u0000\u0000gf\u0001\u0000\u0000\u0000h\"\u0001\u0000"+
		"\u0000\u0000ik\u0007\u0003\u0000\u0000ji\u0001\u0000\u0000\u0000kl\u0001"+
		"\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000"+
		"mn\u0001\u0000\u0000\u0000no\u0006\u0011\u0000\u0000o$\u0001\u0000\u0000"+
		"\u0000pq\u0005/\u0000\u0000qr\u0005/\u0000\u0000rv\u0001\u0000\u0000\u0000"+
		"su\t\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000"+
		"vw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000wy\u0001\u0000\u0000"+
		"\u0000xv\u0001\u0000\u0000\u0000yz\u0005\n\u0000\u0000z{\u0001\u0000\u0000"+
		"\u0000{|\u0006\u0012\u0000\u0000|&\u0001\u0000\u0000\u0000\t\u0000UZ]"+
		"_cglv\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}