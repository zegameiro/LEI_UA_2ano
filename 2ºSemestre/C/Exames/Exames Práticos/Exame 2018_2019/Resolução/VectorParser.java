// Generated from Vector.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class VectorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NUMBER=10, ID=11, VECTOR=12, COMMENT=13, WS=14, Error=15;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_show = 2, RULE_assign = 3, RULE_expression = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "show", "assign", "expression"
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

	@Override
	public String getGrammarFileName() { return "Vector.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VectorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(VectorParser.EOF, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7478L) != 0)) {
				{
				{
				setState(11);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7476L) != 0)) {
					{
					setState(10);
					stat();
					}
				}

				setState(13);
				match(T__0);
				}
				}
				setState(18);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(19);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public ShowContext show() {
			return getRuleContext(ShowContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(23);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				show();
				}
				break;
			case T__3:
			case T__4:
			case T__7:
			case NUMBER:
			case ID:
			case VECTOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				assign();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ShowContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ShowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterShow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitShow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitShow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowContext show() throws RecognitionException {
		ShowContext _localctx = new ShowContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_show);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__1);
			setState(26);
			expression(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(VectorParser.ID, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			expression(0);
			setState(29);
			match(T__2);
			setState(30);
			match(ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprVectorContext extends ExpressionContext {
		public TerminalNode VECTOR() { return getToken(VectorParser.VECTOR, 0); }
		public ExprVectorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprVector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprVector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprVector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAddSubContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExprAddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprUnaryContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprUnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprUnary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNumberContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(VectorParser.NUMBER, 0); }
		public ExprNumberContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMultsContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExprMultsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprMults(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprMults(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprMults(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentesesContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprParentesesContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprParenteses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprParenteses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprParenteses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIDContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(VectorParser.ID, 0); }
		public ExprIDContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).enterExprID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VectorListener ) ((VectorListener)listener).exitExprID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VectorVisitor ) return ((VectorVisitor<? extends T>)visitor).visitExprID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
				{
				_localctx = new ExprUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(33);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(34);
				expression(7);
				}
				break;
			case T__7:
				{
				_localctx = new ExprParentesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(35);
				match(T__7);
				setState(36);
				expression(0);
				setState(37);
				match(T__8);
				}
				break;
			case VECTOR:
				{
				_localctx = new ExprVectorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(39);
				match(VECTOR);
				}
				break;
			case NUMBER:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				match(NUMBER);
				}
				break;
			case ID:
				{
				_localctx = new ExprIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(50);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultsContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(44);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(45);
						((ExprMultsContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((ExprMultsContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(46);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(47);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(48);
						((ExprAddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
							((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(49);
						expression(6);
						}
						break;
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000f8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0003\u0000\f\b\u0000\u0001\u0000\u0005\u0000\u000f\b\u0000\n\u0000"+
		"\f\u0000\u0012\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u0018\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004+\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u00043\b\u0004\n\u0004\f\u0004"+
		"6\t\u0004\u0001\u0004\u0000\u0001\b\u0005\u0000\u0002\u0004\u0006\b\u0000"+
		"\u0002\u0001\u0000\u0004\u0005\u0001\u0000\u0006\u0007;\u0000\u0010\u0001"+
		"\u0000\u0000\u0000\u0002\u0017\u0001\u0000\u0000\u0000\u0004\u0019\u0001"+
		"\u0000\u0000\u0000\u0006\u001c\u0001\u0000\u0000\u0000\b*\u0001\u0000"+
		"\u0000\u0000\n\f\u0003\u0002\u0001\u0000\u000b\n\u0001\u0000\u0000\u0000"+
		"\u000b\f\u0001\u0000\u0000\u0000\f\r\u0001\u0000\u0000\u0000\r\u000f\u0005"+
		"\u0001\u0000\u0000\u000e\u000b\u0001\u0000\u0000\u0000\u000f\u0012\u0001"+
		"\u0000\u0000\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001"+
		"\u0000\u0000\u0000\u0011\u0013\u0001\u0000\u0000\u0000\u0012\u0010\u0001"+
		"\u0000\u0000\u0000\u0013\u0014\u0005\u0000\u0000\u0001\u0014\u0001\u0001"+
		"\u0000\u0000\u0000\u0015\u0018\u0003\u0004\u0002\u0000\u0016\u0018\u0003"+
		"\u0006\u0003\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0017\u0016\u0001"+
		"\u0000\u0000\u0000\u0018\u0003\u0001\u0000\u0000\u0000\u0019\u001a\u0005"+
		"\u0002\u0000\u0000\u001a\u001b\u0003\b\u0004\u0000\u001b\u0005\u0001\u0000"+
		"\u0000\u0000\u001c\u001d\u0003\b\u0004\u0000\u001d\u001e\u0005\u0003\u0000"+
		"\u0000\u001e\u001f\u0005\u000b\u0000\u0000\u001f\u0007\u0001\u0000\u0000"+
		"\u0000 !\u0006\u0004\uffff\uffff\u0000!\"\u0007\u0000\u0000\u0000\"+\u0003"+
		"\b\u0004\u0007#$\u0005\b\u0000\u0000$%\u0003\b\u0004\u0000%&\u0005\t\u0000"+
		"\u0000&+\u0001\u0000\u0000\u0000\'+\u0005\f\u0000\u0000(+\u0005\n\u0000"+
		"\u0000)+\u0005\u000b\u0000\u0000* \u0001\u0000\u0000\u0000*#\u0001\u0000"+
		"\u0000\u0000*\'\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*)\u0001"+
		"\u0000\u0000\u0000+4\u0001\u0000\u0000\u0000,-\n\u0006\u0000\u0000-.\u0007"+
		"\u0001\u0000\u0000.3\u0003\b\u0004\u0007/0\n\u0005\u0000\u000001\u0007"+
		"\u0000\u0000\u000013\u0003\b\u0004\u00062,\u0001\u0000\u0000\u00002/\u0001"+
		"\u0000\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u0000"+
		"45\u0001\u0000\u0000\u00005\t\u0001\u0000\u0000\u000064\u0001\u0000\u0000"+
		"\u0000\u0006\u000b\u0010\u0017*24";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}