// Generated from /home/tiago/UA/2 Ano/2 Semestre/C/PFinal/c2023-adv-02/src/xAdv.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class xAdvLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, AutomatonProperty=6, WS=7, Ignore=8, 
		Value=9, INT=10, ID=11, NEWLINE=12, SingleLineComment=13, BlockComment=14, 
		ERROR=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "AutomatonProperty", "WS", "Ignore", 
			"Value", "INT", "ID", "NEWLINE", "SingleLineComment", "BlockComment", 
			"ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'define'", "'automaton'", "'{'", "':'", "'}'", null, null, "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "AutomatonProperty", "WS", "Ignore", 
			"Value", "INT", "ID", "NEWLINE", "SingleLineComment", "BlockComment", 
			"ERROR"
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


	public xAdvLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "xAdv.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u0098\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7L\n\7\3\b\6\bO\n\b\r\b\16\bP\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\6\n^\n\n\r\n\16\n_\3\n\3\n\3\n\3\n\5\nf\n\n\3\13\3"+
		"\13\7\13j\n\13\f\13\16\13m\13\13\3\f\3\f\7\fq\n\f\f\f\16\ft\13\f\3\r\5"+
		"\rw\n\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u0080\n\16\f\16\16\16\u0083"+
		"\13\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u008d\n\17\f\17\16"+
		"\17\u0090\13\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\4\u0081\u008e\2\21"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21\3\2\b\4\2\13\13\"\"\3\2\63;\3\2\62;\4\2C\\c|\5\2\62;C\\c|\3\2\61"+
		"\61\2\u00a2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2"+
		"\2\5(\3\2\2\2\7\62\3\2\2\2\t\64\3\2\2\2\13\66\3\2\2\2\rK\3\2\2\2\17N\3"+
		"\2\2\2\21T\3\2\2\2\23e\3\2\2\2\25g\3\2\2\2\27n\3\2\2\2\31v\3\2\2\2\33"+
		"|\3\2\2\2\35\u0088\3\2\2\2\37\u0096\3\2\2\2!\"\7f\2\2\"#\7g\2\2#$\7h\2"+
		"\2$%\7k\2\2%&\7p\2\2&\'\7g\2\2\'\4\3\2\2\2()\7c\2\2)*\7w\2\2*+\7v\2\2"+
		"+,\7q\2\2,-\7o\2\2-.\7c\2\2./\7v\2\2/\60\7q\2\2\60\61\7p\2\2\61\6\3\2"+
		"\2\2\62\63\7}\2\2\63\b\3\2\2\2\64\65\7<\2\2\65\n\3\2\2\2\66\67\7\177\2"+
		"\2\67\f\3\2\2\289\7n\2\29:\7k\2\2:;\7p\2\2;<\7g\2\2<=\7e\2\2=>\7q\2\2"+
		">?\7n\2\2?@\7q\2\2@L\7t\2\2AB\7e\2\2BC\7q\2\2CD\7n\2\2DE\7q\2\2EL\7t\2"+
		"\2FG\7n\2\2GH\7c\2\2HI\7d\2\2IJ\7g\2\2JL\7n\2\2K8\3\2\2\2KA\3\2\2\2KF"+
		"\3\2\2\2L\16\3\2\2\2MO\t\2\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2"+
		"QR\3\2\2\2RS\b\b\2\2S\20\3\2\2\2TU\7=\2\2UV\3\2\2\2VW\b\t\2\2W\22\3\2"+
		"\2\2XY\7$\2\2Y]\5\27\f\2Z[\5\17\b\2[\\\5\27\f\2\\^\3\2\2\2]Z\3\2\2\2^"+
		"_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7$\2\2bf\3\2\2\2cf\5\27\f\2"+
		"df\5\25\13\2eX\3\2\2\2ec\3\2\2\2ed\3\2\2\2f\24\3\2\2\2gk\t\3\2\2hj\t\4"+
		"\2\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\26\3\2\2\2mk\3\2\2\2nr\t"+
		"\5\2\2oq\t\6\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\30\3\2\2\2t"+
		"r\3\2\2\2uw\7\17\2\2vu\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\f\2\2yz\3\2\2\2"+
		"z{\b\r\2\2{\32\3\2\2\2|}\t\7\2\2}\u0081\t\7\2\2~\u0080\13\2\2\2\177~\3"+
		"\2\2\2\u0080\u0083\3\2\2\2\u0081\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082"+
		"\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\5\31\r\2\u0085\u0086\3"+
		"\2\2\2\u0086\u0087\b\16\2\2\u0087\34\3\2\2\2\u0088\u0089\7\61\2\2\u0089"+
		"\u008a\7,\2\2\u008a\u008e\3\2\2\2\u008b\u008d\13\2\2\2\u008c\u008b\3\2"+
		"\2\2\u008d\u0090\3\2\2\2\u008e\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7,\2\2\u0092\u0093\7\61"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\17\2\2\u0095\36\3\2\2\2\u0096\u0097"+
		"\13\2\2\2\u0097 \3\2\2\2\f\2KP_ekrv\u0081\u008e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}