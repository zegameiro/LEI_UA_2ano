// Generated from BigIntCalc.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class BigIntCalcParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, NUMBER=11, ID=12, COMMENT=13, WS=14, ERROR=15;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_show = 2, RULE_assign = 3, RULE_expr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "show", "assign", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'show'", "'->'", "'+'", "'-'", "'*'", "'div'", "'mod'", 
			"'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "NUMBER", 
			"ID", "COMMENT", "WS", "ERROR"
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
	public String getGrammarFileName() { return "BigIntCalc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BigIntCalcParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
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
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitProgram(this);
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6710L) != 0)) {
				{
				{
				setState(11);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6708L) != 0)) {
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
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(21);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				show();
				}
				break;
			case T__3:
			case T__4:
			case T__8:
			case NUMBER:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ShowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterShow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitShow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitShow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowContext show() throws RecognitionException {
		ShowContext _localctx = new ShowContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_show);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(T__1);
			setState(24);
			expr(0);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(BigIntCalcParser.ID, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			expr(0);
			setState(27);
			match(T__2);
			setState(28);
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
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprAddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprUnaryContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprUnaryContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprUnary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNumberContext extends ExprContext {
		public TerminalNode NUMBER() { return getToken(BigIntCalcParser.NUMBER, 0); }
		public ExprNumberContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMultDivContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprMultDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprMultDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprMultDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprMultDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentesesContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprParentesesContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprParenteses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprParenteses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprParenteses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIDContext extends ExprContext {
		public TerminalNode ID() { return getToken(BigIntCalcParser.ID, 0); }
		public ExprIDContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprID(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprModContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprModContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).enterExprMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigIntCalcListener ) ((BigIntCalcListener)listener).exitExprMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigIntCalcVisitor ) return ((BigIntCalcVisitor<? extends T>)visitor).visitExprMod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__4:
				{
				_localctx = new ExprUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(31);
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
				setState(32);
				expr(7);
				}
				break;
			case T__8:
				{
				_localctx = new ExprParentesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(33);
				match(T__8);
				setState(34);
				expr(0);
				setState(35);
				match(T__9);
				}
				break;
			case NUMBER:
				{
				_localctx = new ExprNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(37);
				match(NUMBER);
				}
				break;
			case ID:
				{
				_localctx = new ExprIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(38);
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
						_localctx = new ExprMultDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(41);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(42);
						((ExprMultDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__5 || _la==T__6) ) {
							((ExprMultDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(43);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(44);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(45);
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
						setState(46);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(47);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(48);
						match(T__7);
						setState(49);
						expr(5);
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
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000f8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0003\u0000\f\b\u0000\u0001\u0000\u0005\u0000\u000f\b\u0000\n\u0000"+
		"\f\u0000\u0012\t\u0000\u0001\u0001\u0001\u0001\u0003\u0001\u0016\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004(\b\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u00043\b\u0004\n\u0004\f\u0004"+
		"6\t\u0004\u0001\u0004\u0000\u0001\b\u0005\u0000\u0002\u0004\u0006\b\u0000"+
		"\u0002\u0001\u0000\u0004\u0005\u0001\u0000\u0006\u0007;\u0000\u0010\u0001"+
		"\u0000\u0000\u0000\u0002\u0015\u0001\u0000\u0000\u0000\u0004\u0017\u0001"+
		"\u0000\u0000\u0000\u0006\u001a\u0001\u0000\u0000\u0000\b\'\u0001\u0000"+
		"\u0000\u0000\n\f\u0003\u0002\u0001\u0000\u000b\n\u0001\u0000\u0000\u0000"+
		"\u000b\f\u0001\u0000\u0000\u0000\f\r\u0001\u0000\u0000\u0000\r\u000f\u0005"+
		"\u0001\u0000\u0000\u000e\u000b\u0001\u0000\u0000\u0000\u000f\u0012\u0001"+
		"\u0000\u0000\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001"+
		"\u0000\u0000\u0000\u0011\u0001\u0001\u0000\u0000\u0000\u0012\u0010\u0001"+
		"\u0000\u0000\u0000\u0013\u0016\u0003\u0004\u0002\u0000\u0014\u0016\u0003"+
		"\u0006\u0003\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0015\u0014\u0001"+
		"\u0000\u0000\u0000\u0016\u0003\u0001\u0000\u0000\u0000\u0017\u0018\u0005"+
		"\u0002\u0000\u0000\u0018\u0019\u0003\b\u0004\u0000\u0019\u0005\u0001\u0000"+
		"\u0000\u0000\u001a\u001b\u0003\b\u0004\u0000\u001b\u001c\u0005\u0003\u0000"+
		"\u0000\u001c\u001d\u0005\f\u0000\u0000\u001d\u0007\u0001\u0000\u0000\u0000"+
		"\u001e\u001f\u0006\u0004\uffff\uffff\u0000\u001f \u0007\u0000\u0000\u0000"+
		" (\u0003\b\u0004\u0007!\"\u0005\t\u0000\u0000\"#\u0003\b\u0004\u0000#"+
		"$\u0005\n\u0000\u0000$(\u0001\u0000\u0000\u0000%(\u0005\u000b\u0000\u0000"+
		"&(\u0005\f\u0000\u0000\'\u001e\u0001\u0000\u0000\u0000\'!\u0001\u0000"+
		"\u0000\u0000\'%\u0001\u0000\u0000\u0000\'&\u0001\u0000\u0000\u0000(4\u0001"+
		"\u0000\u0000\u0000)*\n\u0006\u0000\u0000*+\u0007\u0001\u0000\u0000+3\u0003"+
		"\b\u0004\u0007,-\n\u0005\u0000\u0000-.\u0007\u0000\u0000\u0000.3\u0003"+
		"\b\u0004\u0006/0\n\u0004\u0000\u000001\u0005\b\u0000\u000013\u0003\b\u0004"+
		"\u00052)\u0001\u0000\u0000\u00002,\u0001\u0000\u0000\u00002/\u0001\u0000"+
		"\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u00005\t\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u0000"+
		"\u0006\u000b\u0010\u0015\'24";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}