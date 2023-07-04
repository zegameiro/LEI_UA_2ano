// Generated from Vector.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class VectorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NUMBER=10, ID=11, VECTOR=12, COMMENT=13, WS=14, Error=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"NUMBER", "ID", "VECTOR", "COMMENT", "WS", "Error"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'show'", "'->'", "'+'", "'-'", "'*'", "'.'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "NUMBER", 
			"ID", "VECTOR", "COMMENT", "WS", "Error"
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


	public VectorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Vector.g4"; }

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
		"\u0004\u0000\u000fg\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0004\t7\b\t\u000b\t\f\t8\u0001"+
		"\t\u0001\t\u0004\t=\b\t\u000b\t\f\t>\u0003\tA\b\t\u0001\n\u0004\nD\b\n"+
		"\u000b\n\f\nE\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"L\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0004\fQ\b\f\u000b\f\f\fR\u0001"+
		"\f\u0005\fV\b\f\n\f\f\fY\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0004"+
		"\r`\b\r\u000b\r\f\ra\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001W\u0000"+
		"\u000f\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u0001\u0000\u0003\u0001\u000009\u0002\u000009az\u0003\u0000"+
		"\t\n\r\r  n\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000"+
		"\u0003!\u0001\u0000\u0000\u0000\u0005&\u0001\u0000\u0000\u0000\u0007)"+
		"\u0001\u0000\u0000\u0000\t+\u0001\u0000\u0000\u0000\u000b-\u0001\u0000"+
		"\u0000\u0000\r/\u0001\u0000\u0000\u0000\u000f1\u0001\u0000\u0000\u0000"+
		"\u00113\u0001\u0000\u0000\u0000\u00136\u0001\u0000\u0000\u0000\u0015C"+
		"\u0001\u0000\u0000\u0000\u0017G\u0001\u0000\u0000\u0000\u0019P\u0001\u0000"+
		"\u0000\u0000\u001b_\u0001\u0000\u0000\u0000\u001de\u0001\u0000\u0000\u0000"+
		"\u001f \u0005;\u0000\u0000 \u0002\u0001\u0000\u0000\u0000!\"\u0005s\u0000"+
		"\u0000\"#\u0005h\u0000\u0000#$\u0005o\u0000\u0000$%\u0005w\u0000\u0000"+
		"%\u0004\u0001\u0000\u0000\u0000&\'\u0005-\u0000\u0000\'(\u0005>\u0000"+
		"\u0000(\u0006\u0001\u0000\u0000\u0000)*\u0005+\u0000\u0000*\b\u0001\u0000"+
		"\u0000\u0000+,\u0005-\u0000\u0000,\n\u0001\u0000\u0000\u0000-.\u0005*"+
		"\u0000\u0000.\f\u0001\u0000\u0000\u0000/0\u0005.\u0000\u00000\u000e\u0001"+
		"\u0000\u0000\u000012\u0005(\u0000\u00002\u0010\u0001\u0000\u0000\u0000"+
		"34\u0005)\u0000\u00004\u0012\u0001\u0000\u0000\u000057\u0007\u0000\u0000"+
		"\u000065\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u000086\u0001\u0000"+
		"\u0000\u000089\u0001\u0000\u0000\u00009@\u0001\u0000\u0000\u0000:<\u0005"+
		".\u0000\u0000;=\u0007\u0000\u0000\u0000<;\u0001\u0000\u0000\u0000=>\u0001"+
		"\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000"+
		"?A\u0001\u0000\u0000\u0000@:\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000"+
		"\u0000A\u0014\u0001\u0000\u0000\u0000BD\u0007\u0001\u0000\u0000CB\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000"+
		"EF\u0001\u0000\u0000\u0000F\u0016\u0001\u0000\u0000\u0000GH\u0005[\u0000"+
		"\u0000HK\u0003\u0013\t\u0000IJ\u0005,\u0000\u0000JL\u0003\u0013\t\u0000"+
		"KI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000"+
		"\u0000MN\u0005]\u0000\u0000N\u0018\u0001\u0000\u0000\u0000OQ\u0005#\u0000"+
		"\u0000PO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RP\u0001\u0000"+
		"\u0000\u0000RS\u0001\u0000\u0000\u0000SW\u0001\u0000\u0000\u0000TV\t\u0000"+
		"\u0000\u0000UT\u0001\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000WX\u0001"+
		"\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000XZ\u0001\u0000\u0000\u0000"+
		"YW\u0001\u0000\u0000\u0000Z[\u0005\n\u0000\u0000[\\\u0001\u0000\u0000"+
		"\u0000\\]\u0006\f\u0000\u0000]\u001a\u0001\u0000\u0000\u0000^`\u0007\u0002"+
		"\u0000\u0000_^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a_\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"cd\u0006\r\u0000\u0000d\u001c\u0001\u0000\u0000\u0000ef\t\u0000\u0000"+
		"\u0000f\u001e\u0001\u0000\u0000\u0000\t\u00008>@EKRWa\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}