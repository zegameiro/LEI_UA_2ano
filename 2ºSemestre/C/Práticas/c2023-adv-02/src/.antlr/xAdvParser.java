// Generated from /home/tiago/UA/2 Ano/2 Semestre/C/PFinal/c2023-adv-02/src/xAdv.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class xAdvParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, AutomatonProperty=6, WS=7, Ignore=8, 
		Value=9, INT=10, ID=11, NEWLINE=12, SingleLineComment=13, BlockComment=14, 
		ERROR=15;
	public static final int
		RULE_program = 0, RULE_definitions = 1, RULE_automatonStyle = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definitions", "automatonStyle"
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

	@Override
	public String getGrammarFileName() { return "xAdv.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public xAdvParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(xAdvParser.EOF, 0); }
		public List<DefinitionsContext> definitions() {
			return getRuleContexts(DefinitionsContext.class);
		}
		public DefinitionsContext definitions(int i) {
			return getRuleContext(DefinitionsContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6);
				definitions();
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(11);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionsContext extends ParserRuleContext {
		public AutomatonStyleContext automatonStyle() {
			return getRuleContext(AutomatonStyleContext.class,0);
		}
		public DefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitions; }
	}

	public final DefinitionsContext definitions() throws RecognitionException {
		DefinitionsContext _localctx = new DefinitionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definitions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(T__0);
			{
			setState(14);
			automatonStyle();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AutomatonStyleContext extends ParserRuleContext {
		public List<TerminalNode> AutomatonProperty() { return getTokens(xAdvParser.AutomatonProperty); }
		public TerminalNode AutomatonProperty(int i) {
			return getToken(xAdvParser.AutomatonProperty, i);
		}
		public List<TerminalNode> Value() { return getTokens(xAdvParser.Value); }
		public TerminalNode Value(int i) {
			return getToken(xAdvParser.Value, i);
		}
		public AutomatonStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonStyle; }
	}

	public final AutomatonStyleContext automatonStyle() throws RecognitionException {
		AutomatonStyleContext _localctx = new AutomatonStyleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_automatonStyle);
		int _la;
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				match(T__1);
				setState(17);
				match(T__2);
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(18);
					match(AutomatonProperty);
					setState(19);
					match(T__3);
					setState(20);
					match(Value);
					}
					}
					setState(23); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AutomatonProperty );
				setState(25);
				match(T__4);
				}
				break;
			case Value:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(Value);
				setState(27);
				match(T__2);
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(28);
					match(AutomatonProperty);
					setState(29);
					match(T__3);
					setState(30);
					match(Value);
					}
					}
					setState(33); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AutomatonProperty );
				setState(35);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21)\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\6\2\n\n\2\r\2\16\2\13\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\6\4\30\n\4\r\4\16\4\31\3\4\3\4\3\4\3\4\3\4\3\4\6\4\"\n\4\r\4\16"+
		"\4#\3\4\5\4\'\n\4\3\4\2\2\5\2\4\6\2\2\2)\2\t\3\2\2\2\4\17\3\2\2\2\6&\3"+
		"\2\2\2\b\n\5\4\3\2\t\b\3\2\2\2\n\13\3\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2"+
		"\f\r\3\2\2\2\r\16\7\2\2\3\16\3\3\2\2\2\17\20\7\3\2\2\20\21\5\6\4\2\21"+
		"\5\3\2\2\2\22\23\7\4\2\2\23\27\7\5\2\2\24\25\7\b\2\2\25\26\7\6\2\2\26"+
		"\30\7\13\2\2\27\24\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32"+
		"\33\3\2\2\2\33\'\7\7\2\2\34\35\7\13\2\2\35!\7\5\2\2\36\37\7\b\2\2\37 "+
		"\7\6\2\2 \"\7\13\2\2!\36\3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$%\3\2"+
		"\2\2%\'\7\7\2\2&\22\3\2\2\2&\34\3\2\2\2\'\7\3\2\2\2\6\13\31#&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}