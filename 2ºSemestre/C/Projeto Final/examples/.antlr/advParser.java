// Generated from /home/tiago/UA/2 Ano/2 Semestre/C/PFinal/c2023-adv-02/examples/adv.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class advParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, Number=75, ID=76, SYMBOL=77, STRING=78, WS=79, NEWLINE=80, SingleLineComment=81, 
		BlockComment=82, ERROR=83;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_importstat = 2, RULE_alphabetDef = 3, 
		RULE_alphabetElement = 4, RULE_automatonDef = 5, RULE_automatonNFADef = 6, 
		RULE_automatonDFADef = 7, RULE_automatonStat = 8, RULE_automatonFor = 9, 
		RULE_automatonWhile = 10, RULE_automatonIf = 11, RULE_automatonElse = 12, 
		RULE_stateDef = 13, RULE_propertiesDef = 14, RULE_propertyElement = 15, 
		RULE_transitionDef = 16, RULE_transitionElement = 17, RULE_viewDef = 18, 
		RULE_viewStat = 19, RULE_viewFor = 20, RULE_viewWhile = 21, RULE_viewIf = 22, 
		RULE_viewElse = 23, RULE_transitionRedefine = 24, RULE_transitionPoint = 25, 
		RULE_transitionLabelAlter = 26, RULE_transitionLabelAlterWithComma = 27, 
		RULE_transition = 28, RULE_placeDef = 29, RULE_placeElement = 30, RULE_gridDef = 31, 
		RULE_gridOptions = 32, RULE_animationDef = 33, RULE_viewportDef = 34, 
		RULE_viewportOn = 35, RULE_viewportStat = 36, RULE_viewportWhile = 37, 
		RULE_viewportIf = 38, RULE_viewportElse = 39, RULE_viewportFor = 40, RULE_viewportInstructions = 41, 
		RULE_viewportInstructionsShowElement = 42, RULE_playDef = 43, RULE_decl = 44, 
		RULE_algebricOP = 45, RULE_expr = 46, RULE_assign = 47, RULE_list = 48, 
		RULE_point = 49, RULE_pointRect = 50, RULE_pointPol = 51, RULE_type = 52, 
		RULE_gridProperties = 53, RULE_propertiesKeys = 54;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "importstat", "alphabetDef", "alphabetElement", "automatonDef", 
			"automatonNFADef", "automatonDFADef", "automatonStat", "automatonFor", 
			"automatonWhile", "automatonIf", "automatonElse", "stateDef", "propertiesDef", 
			"propertyElement", "transitionDef", "transitionElement", "viewDef", "viewStat", 
			"viewFor", "viewWhile", "viewIf", "viewElse", "transitionRedefine", "transitionPoint", 
			"transitionLabelAlter", "transitionLabelAlterWithComma", "transition", 
			"placeDef", "placeElement", "gridDef", "gridOptions", "animationDef", 
			"viewportDef", "viewportOn", "viewportStat", "viewportWhile", "viewportIf", 
			"viewportElse", "viewportFor", "viewportInstructions", "viewportInstructionsShowElement", 
			"playDef", "decl", "algebricOP", "expr", "assign", "list", "point", "pointRect", 
			"pointPol", "type", "gridProperties", "propertiesKeys"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'.txt'", "'alphabet'", "'{'", "','", "'}'", "'-'", 
			"'NFA'", "'<<<'", "'>>>'", "'complete'", "'DFA'", "'for'", "'in'", "'while'", 
			"'do'", "'if'", "'else'", "'state'", "';'", "'['", "'='", "']'", "'transition'", 
			"'->'", "'view'", "'of'", "'as'", "'--'", "'#label'", "'<'", "'>'", "'place'", 
			"'at'", "'grid'", "'animation'", "'viewport'", "'++'", "'on'", "'show'", 
			"'pause'", "'play'", "'+'", "'not'", "'*'", "'/'", "'%'", "'>='", "'<='", 
			"'=='", "'!='", "'and'", "'or'", "'('", "')'", "'read'", "'[prompt='", 
			"'{{'", "'}}'", "':'", "'number'", "'point'", "'list'", "'string'", "'boolean'", 
			"'step'", "'margin'", "'color'", "'line'", "'initial'", "'accepting'", 
			"'align'", "'slope'", "'highlighted'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "Number", "ID", "SYMBOL", "STRING", "WS", "NEWLINE", 
			"SingleLineComment", "BlockComment", "ERROR"
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
	public String getGrammarFileName() { return "adv.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public advParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public AlphabetDefContext alphabetDef() {
			return getRuleContext(AlphabetDefContext.class,0);
		}
		public TerminalNode EOF() { return getToken(advParser.EOF, 0); }
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
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			alphabetDef();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__7) | (1L << T__10) | (1L << T__11) | (1L << T__25) | (1L << T__35) | (1L << T__41))) != 0)) {
				{
				{
				setState(111);
				stat();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
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

	public static class StatContext extends ParserRuleContext {
		public ImportstatContext importstat() {
			return getRuleContext(ImportstatContext.class,0);
		}
		public AutomatonDefContext automatonDef() {
			return getRuleContext(AutomatonDefContext.class,0);
		}
		public ViewDefContext viewDef() {
			return getRuleContext(ViewDefContext.class,0);
		}
		public AnimationDefContext animationDef() {
			return getRuleContext(AnimationDefContext.class,0);
		}
		public PlayDefContext playDef() {
			return getRuleContext(PlayDefContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(119);
				importstat();
				}
				break;
			case T__7:
			case T__10:
			case T__11:
				{
				setState(120);
				automatonDef();
				}
				break;
			case T__25:
				{
				setState(121);
				viewDef();
				}
				break;
			case T__35:
				{
				setState(122);
				animationDef();
				}
				break;
			case T__41:
				{
				setState(123);
				playDef();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ImportstatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ImportstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importstat; }
	}

	public final ImportstatContext importstat() throws RecognitionException {
		ImportstatContext _localctx = new ImportstatContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__0);
			setState(127);
			match(ID);
			setState(128);
			match(T__1);
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

	public static class AlphabetDefContext extends ParserRuleContext {
		public List<AlphabetElementContext> alphabetElement() {
			return getRuleContexts(AlphabetElementContext.class);
		}
		public AlphabetElementContext alphabetElement(int i) {
			return getRuleContext(AlphabetElementContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public AlphabetDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alphabetDef; }
	}

	public final AlphabetDefContext alphabetDef() throws RecognitionException {
		AlphabetDefContext _localctx = new AlphabetDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_alphabetDef);
		try {
			int _alt;
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(T__2);
				setState(131);
				match(T__3);
				setState(137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(132);
						alphabetElement();
						setState(133);
						match(T__4);
						}
						} 
					}
					setState(139);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				setState(140);
				alphabetElement();
				setState(141);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(T__2);
				setState(144);
				match(ID);
				setState(145);
				match(T__6);
				setState(146);
				match(ID);
				}
				break;
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

	public static class AlphabetElementContext extends ParserRuleContext {
		public List<TerminalNode> SYMBOL() { return getTokens(advParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(advParser.SYMBOL, i);
		}
		public AlphabetElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alphabetElement; }
	}

	public final AlphabetElementContext alphabetElement() throws RecognitionException {
		AlphabetElementContext _localctx = new AlphabetElementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_alphabetElement);
		try {
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(SYMBOL);
				setState(150);
				match(T__6);
				setState(151);
				match(SYMBOL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				match(SYMBOL);
				}
				break;
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

	public static class AutomatonDefContext extends ParserRuleContext {
		public AutomatonNFADefContext automatonNFADef() {
			return getRuleContext(AutomatonNFADefContext.class,0);
		}
		public AutomatonDFADefContext automatonDFADef() {
			return getRuleContext(AutomatonDFADefContext.class,0);
		}
		public AutomatonDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonDef; }
	}

	public final AutomatonDefContext automatonDef() throws RecognitionException {
		AutomatonDefContext _localctx = new AutomatonDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_automatonDef);
		try {
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				automatonNFADef();
				}
				break;
			case T__10:
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				automatonDFADef();
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

	public static class AutomatonNFADefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public TransitionDefContext transitionDef() {
			return getRuleContext(TransitionDefContext.class,0);
		}
		public List<StateDefContext> stateDef() {
			return getRuleContexts(StateDefContext.class);
		}
		public StateDefContext stateDef(int i) {
			return getRuleContext(StateDefContext.class,i);
		}
		public List<AutomatonStatContext> automatonStat() {
			return getRuleContexts(AutomatonStatContext.class);
		}
		public AutomatonStatContext automatonStat(int i) {
			return getRuleContext(AutomatonStatContext.class,i);
		}
		public AutomatonNFADefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonNFADef; }
	}

	public final AutomatonNFADefContext automatonNFADef() throws RecognitionException {
		AutomatonNFADefContext _localctx = new AutomatonNFADefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_automatonNFADef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__7);
			setState(160);
			match(ID);
			setState(161);
			match(T__8);
			setState(163); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(162);
					stateDef();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(165); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(168); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(167);
				automatonStat();
				}
				}
				setState(170); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
			setState(172);
			transitionDef();
			setState(173);
			match(T__9);
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

	public static class AutomatonDFADefContext extends ParserRuleContext {
		public Token complete;
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public TransitionDefContext transitionDef() {
			return getRuleContext(TransitionDefContext.class,0);
		}
		public List<StateDefContext> stateDef() {
			return getRuleContexts(StateDefContext.class);
		}
		public StateDefContext stateDef(int i) {
			return getRuleContext(StateDefContext.class,i);
		}
		public List<AutomatonStatContext> automatonStat() {
			return getRuleContexts(AutomatonStatContext.class);
		}
		public AutomatonStatContext automatonStat(int i) {
			return getRuleContext(AutomatonStatContext.class,i);
		}
		public AutomatonDFADefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonDFADef; }
	}

	public final AutomatonDFADefContext automatonDFADef() throws RecognitionException {
		AutomatonDFADefContext _localctx = new AutomatonDFADefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_automatonDFADef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(175);
				((AutomatonDFADefContext)_localctx).complete = match(T__10);
				}
			}

			setState(178);
			match(T__11);
			setState(179);
			match(ID);
			setState(180);
			match(T__8);
			setState(182); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(181);
					stateDef();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(184); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(187); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(186);
				automatonStat();
				}
				}
				setState(189); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
			setState(191);
			transitionDef();
			setState(192);
			match(T__9);
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

	public static class AutomatonStatContext extends ParserRuleContext {
		public AutomatonForContext automatonFor() {
			return getRuleContext(AutomatonForContext.class,0);
		}
		public AutomatonIfContext automatonIf() {
			return getRuleContext(AutomatonIfContext.class,0);
		}
		public AutomatonWhileContext automatonWhile() {
			return getRuleContext(AutomatonWhileContext.class,0);
		}
		public PropertiesDefContext propertiesDef() {
			return getRuleContext(PropertiesDefContext.class,0);
		}
		public AlgebricOPContext algebricOP() {
			return getRuleContext(AlgebricOPContext.class,0);
		}
		public AutomatonStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonStat; }
	}

	public final AutomatonStatContext automatonStat() throws RecognitionException {
		AutomatonStatContext _localctx = new AutomatonStatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_automatonStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(194);
				automatonFor();
				}
				break;
			case 2:
				{
				setState(195);
				automatonIf();
				}
				break;
			case 3:
				{
				setState(196);
				automatonWhile();
				}
				break;
			case 4:
				{
				setState(197);
				propertiesDef();
				}
				break;
			case 5:
				{
				setState(198);
				algebricOP();
				}
				break;
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

	public static class AutomatonForContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<AutomatonStatContext> automatonStat() {
			return getRuleContexts(AutomatonStatContext.class);
		}
		public AutomatonStatContext automatonStat(int i) {
			return getRuleContext(AutomatonStatContext.class,i);
		}
		public AutomatonForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonFor; }
	}

	public final AutomatonForContext automatonFor() throws RecognitionException {
		AutomatonForContext _localctx = new AutomatonForContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_automatonFor);
		int _la;
		try {
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(T__12);
				setState(202);
				match(ID);
				setState(203);
				match(T__13);
				setState(204);
				expr(0);
				setState(205);
				automatonStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				match(T__12);
				setState(208);
				match(ID);
				setState(209);
				match(T__13);
				setState(210);
				expr(0);
				setState(211);
				match(T__8);
				setState(213); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(212);
					automatonStat();
					}
					}
					setState(215); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(217);
				match(T__9);
				}
				break;
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

	public static class AutomatonWhileContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<AutomatonStatContext> automatonStat() {
			return getRuleContexts(AutomatonStatContext.class);
		}
		public AutomatonStatContext automatonStat(int i) {
			return getRuleContext(AutomatonStatContext.class,i);
		}
		public AutomatonWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonWhile; }
	}

	public final AutomatonWhileContext automatonWhile() throws RecognitionException {
		AutomatonWhileContext _localctx = new AutomatonWhileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_automatonWhile);
		int _la;
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				match(T__14);
				setState(222);
				expr(0);
				setState(223);
				match(T__15);
				setState(224);
				automatonStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				match(T__14);
				setState(227);
				expr(0);
				setState(228);
				match(T__15);
				setState(229);
				match(T__8);
				setState(231); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(230);
					automatonStat();
					}
					}
					setState(233); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(235);
				match(T__9);
				}
				break;
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

	public static class AutomatonIfContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<AutomatonStatContext> automatonStat() {
			return getRuleContexts(AutomatonStatContext.class);
		}
		public AutomatonStatContext automatonStat(int i) {
			return getRuleContext(AutomatonStatContext.class,i);
		}
		public AutomatonElseContext automatonElse() {
			return getRuleContext(AutomatonElseContext.class,0);
		}
		public AutomatonIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonIf; }
	}

	public final AutomatonIfContext automatonIf() throws RecognitionException {
		AutomatonIfContext _localctx = new AutomatonIfContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_automatonIf);
		int _la;
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(T__16);
				setState(240);
				expr(0);
				setState(241);
				match(T__15);
				setState(242);
				automatonStat();
				setState(244);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(243);
					automatonElse();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(T__16);
				setState(247);
				expr(0);
				setState(248);
				match(T__15);
				setState(249);
				match(T__8);
				setState(251); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(250);
					automatonStat();
					}
					}
					setState(253); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(255);
				match(T__9);
				setState(257);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(256);
					automatonElse();
					}
					break;
				}
				}
				break;
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

	public static class AutomatonElseContext extends ParserRuleContext {
		public List<AutomatonStatContext> automatonStat() {
			return getRuleContexts(AutomatonStatContext.class);
		}
		public AutomatonStatContext automatonStat(int i) {
			return getRuleContext(AutomatonStatContext.class,i);
		}
		public AutomatonElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_automatonElse; }
	}

	public final AutomatonElseContext automatonElse() throws RecognitionException {
		AutomatonElseContext _localctx = new AutomatonElseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_automatonElse);
		int _la;
		try {
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				match(T__17);
				setState(262);
				automatonStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(T__17);
				setState(264);
				match(T__8);
				setState(266); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(265);
					automatonStat();
					}
					}
					setState(268); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(270);
				match(T__9);
				}
				break;
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

	public static class StateDefContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public StateDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateDef; }
	}

	public final StateDefContext stateDef() throws RecognitionException {
		StateDefContext _localctx = new StateDefContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stateDef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(T__18);
			setState(279);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(275);
					match(ID);
					setState(276);
					match(T__4);
					}
					} 
				}
				setState(281);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(282);
			match(ID);
			setState(283);
			match(T__19);
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

	public static class PropertiesDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public List<PropertyElementContext> propertyElement() {
			return getRuleContexts(PropertyElementContext.class);
		}
		public PropertyElementContext propertyElement(int i) {
			return getRuleContext(PropertyElementContext.class,i);
		}
		public PropertiesDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertiesDef; }
	}

	public final PropertiesDefContext propertiesDef() throws RecognitionException {
		PropertiesDefContext _localctx = new PropertiesDefContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_propertiesDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(ID);
			setState(287); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(286);
				propertyElement();
				}
				}
				setState(289); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__20 );
			setState(291);
			match(T__19);
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

	public static class PropertyElementContext extends ParserRuleContext {
		public PropertiesKeysContext propertiesKeys() {
			return getRuleContext(PropertiesKeysContext.class,0);
		}
		public TerminalNode Number() { return getToken(advParser.Number, 0); }
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public PropertyElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyElement; }
	}

	public final PropertyElementContext propertyElement() throws RecognitionException {
		PropertyElementContext _localctx = new PropertyElementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_propertyElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__20);
			setState(294);
			propertiesKeys();
			setState(295);
			match(T__21);
			setState(302);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(297); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(296);
					match(ID);
					}
					}
					setState(299); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				}
				break;
			case Number:
				{
				setState(301);
				match(Number);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(304);
			match(T__22);
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

	public static class TransitionDefContext extends ParserRuleContext {
		public List<TransitionElementContext> transitionElement() {
			return getRuleContexts(TransitionElementContext.class);
		}
		public TransitionElementContext transitionElement(int i) {
			return getRuleContext(TransitionElementContext.class,i);
		}
		public TransitionDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionDef; }
	}

	public final TransitionDefContext transitionDef() throws RecognitionException {
		TransitionDefContext _localctx = new TransitionDefContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_transitionDef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(T__23);
			setState(312);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(307);
					transitionElement();
					setState(308);
					match(T__4);
					}
					} 
				}
				setState(314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(315);
			transitionElement();
			setState(316);
			match(T__19);
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

	public static class TransitionElementContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public List<TerminalNode> SYMBOL() { return getTokens(advParser.SYMBOL); }
		public TerminalNode SYMBOL(int i) {
			return getToken(advParser.SYMBOL, i);
		}
		public TransitionElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionElement; }
	}

	public final TransitionElementContext transitionElement() throws RecognitionException {
		TransitionElementContext _localctx = new TransitionElementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_transitionElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(ID);
			setState(319);
			match(T__24);
			setState(324);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(320);
					match(SYMBOL);
					setState(321);
					match(T__4);
					}
					} 
				}
				setState(326);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(327);
			match(SYMBOL);
			setState(328);
			match(T__24);
			setState(329);
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

	public static class ViewDefContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public List<ViewStatContext> viewStat() {
			return getRuleContexts(ViewStatContext.class);
		}
		public ViewStatContext viewStat(int i) {
			return getRuleContext(ViewStatContext.class,i);
		}
		public ViewDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewDef; }
	}

	public final ViewDefContext viewDef() throws RecognitionException {
		ViewDefContext _localctx = new ViewDefContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_viewDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(T__25);
			setState(332);
			match(ID);
			setState(333);
			match(T__26);
			setState(334);
			match(ID);
			setState(335);
			match(T__8);
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__30 - 13)) | (1L << (T__32 - 13)) | (1L << (T__34 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0)) {
				{
				{
				setState(336);
				viewStat();
				}
				}
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(342);
			match(T__9);
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

	public static class ViewStatContext extends ParserRuleContext {
		public AlgebricOPContext algebricOP() {
			return getRuleContext(AlgebricOPContext.class,0);
		}
		public ViewForContext viewFor() {
			return getRuleContext(ViewForContext.class,0);
		}
		public PlaceDefContext placeDef() {
			return getRuleContext(PlaceDefContext.class,0);
		}
		public TransitionRedefineContext transitionRedefine() {
			return getRuleContext(TransitionRedefineContext.class,0);
		}
		public TransitionLabelAlterWithCommaContext transitionLabelAlterWithComma() {
			return getRuleContext(TransitionLabelAlterWithCommaContext.class,0);
		}
		public GridDefContext gridDef() {
			return getRuleContext(GridDefContext.class,0);
		}
		public ViewIfContext viewIf() {
			return getRuleContext(ViewIfContext.class,0);
		}
		public ViewWhileContext viewWhile() {
			return getRuleContext(ViewWhileContext.class,0);
		}
		public ViewStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewStat; }
	}

	public final ViewStatContext viewStat() throws RecognitionException {
		ViewStatContext _localctx = new ViewStatContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_viewStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(344);
				algebricOP();
				}
				break;
			case 2:
				{
				setState(345);
				viewFor();
				}
				break;
			case 3:
				{
				setState(346);
				placeDef();
				}
				break;
			case 4:
				{
				setState(347);
				transitionRedefine();
				}
				break;
			case 5:
				{
				setState(348);
				transitionLabelAlterWithComma();
				}
				break;
			case 6:
				{
				setState(349);
				gridDef();
				}
				break;
			case 7:
				{
				setState(350);
				viewIf();
				}
				break;
			case 8:
				{
				setState(351);
				viewWhile();
				}
				break;
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

	public static class ViewForContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ViewStatContext> viewStat() {
			return getRuleContexts(ViewStatContext.class);
		}
		public ViewStatContext viewStat(int i) {
			return getRuleContext(ViewStatContext.class,i);
		}
		public ViewForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewFor; }
	}

	public final ViewForContext viewFor() throws RecognitionException {
		ViewForContext _localctx = new ViewForContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_viewFor);
		int _la;
		try {
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(354);
				match(T__12);
				setState(355);
				match(ID);
				setState(356);
				match(T__13);
				setState(357);
				expr(0);
				setState(358);
				viewStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				match(T__12);
				setState(361);
				match(ID);
				setState(362);
				match(T__13);
				setState(363);
				expr(0);
				setState(364);
				match(T__8);
				setState(366); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(365);
					viewStat();
					}
					}
					setState(368); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__30 - 13)) | (1L << (T__32 - 13)) | (1L << (T__34 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(370);
				match(T__9);
				}
				break;
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

	public static class ViewWhileContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ViewStatContext> viewStat() {
			return getRuleContexts(ViewStatContext.class);
		}
		public ViewStatContext viewStat(int i) {
			return getRuleContext(ViewStatContext.class,i);
		}
		public ViewWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewWhile; }
	}

	public final ViewWhileContext viewWhile() throws RecognitionException {
		ViewWhileContext _localctx = new ViewWhileContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_viewWhile);
		int _la;
		try {
			setState(390);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				match(T__14);
				setState(375);
				expr(0);
				setState(376);
				match(T__15);
				setState(377);
				viewStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(379);
				match(T__14);
				setState(380);
				expr(0);
				setState(381);
				match(T__15);
				setState(382);
				match(T__8);
				setState(384); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(383);
					viewStat();
					}
					}
					setState(386); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__30 - 13)) | (1L << (T__32 - 13)) | (1L << (T__34 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(388);
				match(T__9);
				}
				break;
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

	public static class ViewIfContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ViewStatContext> viewStat() {
			return getRuleContexts(ViewStatContext.class);
		}
		public ViewStatContext viewStat(int i) {
			return getRuleContext(ViewStatContext.class,i);
		}
		public ViewElseContext viewElse() {
			return getRuleContext(ViewElseContext.class,0);
		}
		public ViewIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewIf; }
	}

	public final ViewIfContext viewIf() throws RecognitionException {
		ViewIfContext _localctx = new ViewIfContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_viewIf);
		int _la;
		try {
			setState(412);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(392);
				match(T__16);
				setState(393);
				expr(0);
				setState(394);
				match(T__15);
				setState(395);
				viewStat();
				setState(397);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(396);
					viewElse();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(399);
				match(T__16);
				setState(400);
				expr(0);
				setState(401);
				match(T__15);
				setState(402);
				match(T__8);
				setState(404); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(403);
					viewStat();
					}
					}
					setState(406); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__30 - 13)) | (1L << (T__32 - 13)) | (1L << (T__34 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(408);
				match(T__9);
				setState(410);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(409);
					viewElse();
					}
					break;
				}
				}
				break;
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

	public static class ViewElseContext extends ParserRuleContext {
		public List<ViewStatContext> viewStat() {
			return getRuleContexts(ViewStatContext.class);
		}
		public ViewStatContext viewStat(int i) {
			return getRuleContext(ViewStatContext.class,i);
		}
		public ViewElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewElse; }
	}

	public final ViewElseContext viewElse() throws RecognitionException {
		ViewElseContext _localctx = new ViewElseContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_viewElse);
		int _la;
		try {
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(414);
				match(T__17);
				setState(415);
				viewStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				match(T__17);
				setState(417);
				match(T__8);
				setState(419); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(418);
					viewStat();
					}
					}
					setState(421); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__30 - 13)) | (1L << (T__32 - 13)) | (1L << (T__34 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(423);
				match(T__9);
				}
				break;
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

	public static class TransitionRedefineContext extends ParserRuleContext {
		public TransitionContext transition() {
			return getRuleContext(TransitionContext.class,0);
		}
		public List<TransitionPointContext> transitionPoint() {
			return getRuleContexts(TransitionPointContext.class);
		}
		public TransitionPointContext transitionPoint(int i) {
			return getRuleContext(TransitionPointContext.class,i);
		}
		public TransitionRedefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionRedefine; }
	}

	public final TransitionRedefineContext transitionRedefine() throws RecognitionException {
		TransitionRedefineContext _localctx = new TransitionRedefineContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_transitionRedefine);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			transition();
			setState(428);
			match(T__27);
			setState(429);
			transitionPoint();
			setState(430);
			match(T__28);
			setState(436);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(431);
					transitionPoint();
					setState(432);
					match(T__28);
					}
					} 
				}
				setState(438);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			setState(439);
			transitionPoint();
			setState(440);
			match(T__19);
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

	public static class TransitionPointContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<PropertyElementContext> propertyElement() {
			return getRuleContexts(PropertyElementContext.class);
		}
		public PropertyElementContext propertyElement(int i) {
			return getRuleContext(PropertyElementContext.class,i);
		}
		public TransitionPointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionPoint; }
	}

	public final TransitionPointContext transitionPoint() throws RecognitionException {
		TransitionPointContext _localctx = new TransitionPointContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_transitionPoint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			expr(0);
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(443);
				propertyElement();
				}
				}
				setState(448);
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

	public static class TransitionLabelAlterContext extends ParserRuleContext {
		public TransitionContext transition() {
			return getRuleContext(TransitionContext.class,0);
		}
		public List<PropertyElementContext> propertyElement() {
			return getRuleContexts(PropertyElementContext.class);
		}
		public PropertyElementContext propertyElement(int i) {
			return getRuleContext(PropertyElementContext.class,i);
		}
		public TransitionLabelAlterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionLabelAlter; }
	}

	public final TransitionLabelAlterContext transitionLabelAlter() throws RecognitionException {
		TransitionLabelAlterContext _localctx = new TransitionLabelAlterContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_transitionLabelAlter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			transition();
			setState(450);
			match(T__29);
			setState(454);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(451);
				propertyElement();
				}
				}
				setState(456);
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

	public static class TransitionLabelAlterWithCommaContext extends ParserRuleContext {
		public TransitionLabelAlterContext transitionLabelAlter() {
			return getRuleContext(TransitionLabelAlterContext.class,0);
		}
		public TransitionLabelAlterWithCommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transitionLabelAlterWithComma; }
	}

	public final TransitionLabelAlterWithCommaContext transitionLabelAlterWithComma() throws RecognitionException {
		TransitionLabelAlterWithCommaContext _localctx = new TransitionLabelAlterWithCommaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_transitionLabelAlterWithComma);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			transitionLabelAlter();
			setState(458);
			match(T__19);
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

	public static class TransitionContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_transition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			match(T__30);
			setState(461);
			match(ID);
			setState(462);
			match(T__4);
			setState(463);
			match(ID);
			setState(464);
			match(T__31);
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

	public static class PlaceDefContext extends ParserRuleContext {
		public List<PlaceElementContext> placeElement() {
			return getRuleContexts(PlaceElementContext.class);
		}
		public PlaceElementContext placeElement(int i) {
			return getRuleContext(PlaceElementContext.class,i);
		}
		public PlaceDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_placeDef; }
	}

	public final PlaceDefContext placeDef() throws RecognitionException {
		PlaceDefContext _localctx = new PlaceDefContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_placeDef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(T__32);
			setState(472);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(467);
					placeElement();
					setState(468);
					match(T__4);
					}
					} 
				}
				setState(474);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			setState(475);
			placeElement();
			setState(476);
			match(T__19);
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

	public static class PlaceElementContext extends ParserRuleContext {
		public PlaceElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_placeElement; }
	 
		public PlaceElementContext() { }
		public void copyFrom(PlaceElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TransitionplaceElementContext extends PlaceElementContext {
		public TransitionLabelAlterContext transitionLabelAlter() {
			return getRuleContext(TransitionLabelAlterContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TransitionplaceElementContext(PlaceElementContext ctx) { copyFrom(ctx); }
	}
	public static class IDplaceElementContext extends PlaceElementContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IDplaceElementContext(PlaceElementContext ctx) { copyFrom(ctx); }
	}

	public final PlaceElementContext placeElement() throws RecognitionException {
		PlaceElementContext _localctx = new PlaceElementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_placeElement);
		try {
			setState(485);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new IDplaceElementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(478);
				match(ID);
				setState(479);
				match(T__33);
				setState(480);
				expr(0);
				}
				break;
			case T__30:
				_localctx = new TransitionplaceElementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(481);
				transitionLabelAlter();
				setState(482);
				match(T__33);
				setState(483);
				expr(0);
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

	public static class GridDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<GridOptionsContext> gridOptions() {
			return getRuleContexts(GridOptionsContext.class);
		}
		public GridOptionsContext gridOptions(int i) {
			return getRuleContext(GridOptionsContext.class,i);
		}
		public GridDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gridDef; }
	}

	public final GridDefContext gridDef() throws RecognitionException {
		GridDefContext _localctx = new GridDefContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_gridDef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			match(T__34);
			setState(488);
			match(ID);
			setState(489);
			expr(0);
			setState(490);
			match(T__20);
			setState(496);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(491);
					gridOptions();
					setState(492);
					match(T__4);
					}
					} 
				}
				setState(498);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			setState(499);
			gridOptions();
			setState(500);
			match(T__22);
			setState(501);
			match(T__19);
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

	public static class GridOptionsContext extends ParserRuleContext {
		public GridPropertiesContext gridProperties() {
			return getRuleContext(GridPropertiesContext.class,0);
		}
		public TerminalNode Number() { return getToken(advParser.Number, 0); }
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public GridOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gridOptions; }
	}

	public final GridOptionsContext gridOptions() throws RecognitionException {
		GridOptionsContext _localctx = new GridOptionsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_gridOptions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			gridProperties();
			setState(504);
			match(T__21);
			setState(511);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(506); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(505);
					match(ID);
					}
					}
					setState(508); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				}
				break;
			case Number:
				{
				setState(510);
				match(Number);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class AnimationDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public List<ViewportDefContext> viewportDef() {
			return getRuleContexts(ViewportDefContext.class);
		}
		public ViewportDefContext viewportDef(int i) {
			return getRuleContext(ViewportDefContext.class,i);
		}
		public List<ViewportOnContext> viewportOn() {
			return getRuleContexts(ViewportOnContext.class);
		}
		public ViewportOnContext viewportOn(int i) {
			return getRuleContext(ViewportOnContext.class,i);
		}
		public AnimationDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_animationDef; }
	}

	public final AnimationDefContext animationDef() throws RecognitionException {
		AnimationDefContext _localctx = new AnimationDefContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_animationDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(T__35);
			setState(514);
			match(ID);
			setState(515);
			match(T__8);
			setState(518); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(518);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__36:
					{
					setState(516);
					viewportDef();
					}
					break;
				case T__38:
					{
					setState(517);
					viewportOn();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(520); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__36 || _la==T__38 );
			setState(522);
			match(T__9);
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

	public static class ViewportDefContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ViewportDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportDef; }
	}

	public final ViewportDefContext viewportDef() throws RecognitionException {
		ViewportDefContext _localctx = new ViewportDefContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_viewportDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			match(T__36);
			setState(525);
			match(ID);
			setState(526);
			match(T__12);
			setState(527);
			match(ID);
			setState(528);
			match(T__33);
			setState(529);
			expr(0);
			setState(530);
			match(T__28);
			setState(531);
			match(T__37);
			setState(532);
			expr(0);
			setState(533);
			match(T__19);
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

	public static class ViewportOnContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public List<ViewportStatContext> viewportStat() {
			return getRuleContexts(ViewportStatContext.class);
		}
		public ViewportStatContext viewportStat(int i) {
			return getRuleContext(ViewportStatContext.class,i);
		}
		public ViewportOnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportOn; }
	}

	public final ViewportOnContext viewportOn() throws RecognitionException {
		ViewportOnContext _localctx = new ViewportOnContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_viewportOn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			match(T__38);
			setState(536);
			match(ID);
			setState(537);
			match(T__8);
			setState(539); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(538);
				viewportStat();
				}
				}
				setState(541); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__39 - 13)) | (1L << (T__40 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
			setState(543);
			match(T__9);
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

	public static class ViewportStatContext extends ParserRuleContext {
		public PropertiesDefContext propertiesDef() {
			return getRuleContext(PropertiesDefContext.class,0);
		}
		public ViewportForContext viewportFor() {
			return getRuleContext(ViewportForContext.class,0);
		}
		public ViewportInstructionsContext viewportInstructions() {
			return getRuleContext(ViewportInstructionsContext.class,0);
		}
		public AlgebricOPContext algebricOP() {
			return getRuleContext(AlgebricOPContext.class,0);
		}
		public ViewportIfContext viewportIf() {
			return getRuleContext(ViewportIfContext.class,0);
		}
		public ViewportWhileContext viewportWhile() {
			return getRuleContext(ViewportWhileContext.class,0);
		}
		public ViewportStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportStat; }
	}

	public final ViewportStatContext viewportStat() throws RecognitionException {
		ViewportStatContext _localctx = new ViewportStatContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_viewportStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(545);
				propertiesDef();
				}
				break;
			case 2:
				{
				setState(546);
				viewportFor();
				}
				break;
			case 3:
				{
				setState(547);
				viewportInstructions();
				}
				break;
			case 4:
				{
				setState(548);
				algebricOP();
				}
				break;
			case 5:
				{
				setState(549);
				viewportIf();
				}
				break;
			case 6:
				{
				setState(550);
				viewportWhile();
				}
				break;
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

	public static class ViewportWhileContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ViewportStatContext> viewportStat() {
			return getRuleContexts(ViewportStatContext.class);
		}
		public ViewportStatContext viewportStat(int i) {
			return getRuleContext(ViewportStatContext.class,i);
		}
		public ViewportWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportWhile; }
	}

	public final ViewportWhileContext viewportWhile() throws RecognitionException {
		ViewportWhileContext _localctx = new ViewportWhileContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_viewportWhile);
		int _la;
		try {
			setState(569);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(553);
				match(T__14);
				setState(554);
				expr(0);
				setState(555);
				match(T__15);
				setState(556);
				viewportStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(558);
				match(T__14);
				setState(559);
				expr(0);
				setState(560);
				match(T__15);
				setState(561);
				match(T__8);
				setState(563); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(562);
					viewportStat();
					}
					}
					setState(565); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__39 - 13)) | (1L << (T__40 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(567);
				match(T__9);
				}
				break;
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

	public static class ViewportIfContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ViewportStatContext> viewportStat() {
			return getRuleContexts(ViewportStatContext.class);
		}
		public ViewportStatContext viewportStat(int i) {
			return getRuleContext(ViewportStatContext.class,i);
		}
		public ViewportElseContext viewportElse() {
			return getRuleContext(ViewportElseContext.class,0);
		}
		public ViewportIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportIf; }
	}

	public final ViewportIfContext viewportIf() throws RecognitionException {
		ViewportIfContext _localctx = new ViewportIfContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_viewportIf);
		int _la;
		try {
			setState(590);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(571);
				match(T__16);
				setState(572);
				expr(0);
				setState(573);
				match(T__15);
				setState(574);
				viewportStat();
				setState(576);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(575);
					viewportElse();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(578);
				match(T__16);
				setState(579);
				expr(0);
				setState(580);
				match(T__15);
				setState(581);
				match(T__8);
				setState(583); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(582);
					viewportStat();
					}
					}
					setState(585); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__39 - 13)) | (1L << (T__40 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(587);
				match(T__9);
				setState(588);
				viewportElse();
				}
				break;
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

	public static class ViewportElseContext extends ParserRuleContext {
		public List<ViewportStatContext> viewportStat() {
			return getRuleContexts(ViewportStatContext.class);
		}
		public ViewportStatContext viewportStat(int i) {
			return getRuleContext(ViewportStatContext.class,i);
		}
		public ViewportElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportElse; }
	}

	public final ViewportElseContext viewportElse() throws RecognitionException {
		ViewportElseContext _localctx = new ViewportElseContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_viewportElse);
		int _la;
		try {
			setState(603);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(592);
				match(T__17);
				setState(593);
				viewportStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(594);
				match(T__17);
				setState(595);
				match(T__8);
				setState(597); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(596);
					viewportStat();
					}
					}
					setState(599); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__39 - 13)) | (1L << (T__40 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(601);
				match(T__9);
				}
				break;
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

	public static class ViewportForContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ViewportStatContext> viewportStat() {
			return getRuleContexts(ViewportStatContext.class);
		}
		public ViewportStatContext viewportStat(int i) {
			return getRuleContext(ViewportStatContext.class,i);
		}
		public ViewportForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportFor; }
	}

	public final ViewportForContext viewportFor() throws RecognitionException {
		ViewportForContext _localctx = new ViewportForContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_viewportFor);
		int _la;
		try {
			setState(623);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(605);
				match(T__12);
				setState(606);
				match(ID);
				setState(607);
				match(T__13);
				setState(608);
				expr(0);
				setState(609);
				viewportStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(611);
				match(T__12);
				setState(612);
				match(ID);
				setState(613);
				match(T__13);
				setState(614);
				expr(0);
				setState(615);
				match(T__8);
				setState(617); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(616);
					viewportStat();
					}
					}
					setState(619); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 13)) & ~0x3f) == 0 && ((1L << (_la - 13)) & ((1L << (T__12 - 13)) | (1L << (T__14 - 13)) | (1L << (T__16 - 13)) | (1L << (T__18 - 13)) | (1L << (T__39 - 13)) | (1L << (T__40 - 13)) | (1L << (T__60 - 13)) | (1L << (T__61 - 13)) | (1L << (T__62 - 13)) | (1L << (T__63 - 13)) | (1L << (T__64 - 13)) | (1L << (ID - 13)))) != 0) );
				setState(621);
				match(T__9);
				}
				break;
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

	public static class ViewportInstructionsContext extends ParserRuleContext {
		public ViewportInstructionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportInstructions; }
	 
		public ViewportInstructionsContext() { }
		public void copyFrom(ViewportInstructionsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SimpleContext extends ViewportInstructionsContext {
		public Token op;
		public SimpleContext(ViewportInstructionsContext ctx) { copyFrom(ctx); }
	}
	public static class CompoundContext extends ViewportInstructionsContext {
		public List<ViewportInstructionsShowElementContext> viewportInstructionsShowElement() {
			return getRuleContexts(ViewportInstructionsShowElementContext.class);
		}
		public ViewportInstructionsShowElementContext viewportInstructionsShowElement(int i) {
			return getRuleContext(ViewportInstructionsShowElementContext.class,i);
		}
		public CompoundContext(ViewportInstructionsContext ctx) { copyFrom(ctx); }
	}

	public final ViewportInstructionsContext viewportInstructions() throws RecognitionException {
		ViewportInstructionsContext _localctx = new ViewportInstructionsContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_viewportInstructions);
		int _la;
		try {
			int _alt;
			setState(639);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				_localctx = new CompoundContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(625);
				match(T__39);
				setState(631);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(626);
						viewportInstructionsShowElement();
						setState(627);
						match(T__4);
						}
						} 
					}
					setState(633);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				}
				setState(634);
				viewportInstructionsShowElement();
				setState(635);
				match(T__19);
				}
				break;
			case 2:
				_localctx = new SimpleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(637);
				((SimpleContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
					((SimpleContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(638);
				match(T__19);
				}
				break;
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

	public static class ViewportInstructionsShowElementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public List<PropertyElementContext> propertyElement() {
			return getRuleContexts(PropertyElementContext.class);
		}
		public PropertyElementContext propertyElement(int i) {
			return getRuleContext(PropertyElementContext.class,i);
		}
		public TransitionContext transition() {
			return getRuleContext(TransitionContext.class,0);
		}
		public ViewportInstructionsShowElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewportInstructionsShowElement; }
	}

	public final ViewportInstructionsShowElementContext viewportInstructionsShowElement() throws RecognitionException {
		ViewportInstructionsShowElementContext _localctx = new ViewportInstructionsShowElementContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_viewportInstructionsShowElement);
		int _la;
		try {
			setState(649);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(641);
				match(ID);
				setState(645);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__20) {
					{
					{
					setState(642);
					propertyElement();
					}
					}
					setState(647);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 2);
				{
				setState(648);
				transition();
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

	public static class PlayDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public PlayDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_playDef; }
	}

	public final PlayDefContext playDef() throws RecognitionException {
		PlayDefContext _localctx = new PlayDefContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_playDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
			match(T__41);
			setState(652);
			match(ID);
			setState(653);
			match(T__19);
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

	public static class DeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<AssignContext> assign() {
			return getRuleContexts(AssignContext.class);
		}
		public AssignContext assign(int i) {
			return getRuleContext(AssignContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_decl);
		try {
			int _alt;
			setState(676);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(655);
				type();
				setState(661);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(656);
						assign();
						setState(657);
						match(T__4);
						}
						} 
					}
					setState(663);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
				}
				setState(664);
				assign();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(666);
				type();
				setState(671);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(667);
						match(ID);
						setState(668);
						match(T__4);
						}
						} 
					}
					setState(673);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				}
				setState(674);
				match(ID);
				}
				break;
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

	public static class AlgebricOPContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public AlgebricOPContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_algebricOP; }
	}

	public final AlgebricOPContext algebricOP() throws RecognitionException {
		AlgebricOPContext _localctx = new AlgebricOPContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_algebricOP);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
				{
				setState(678);
				decl();
				}
				break;
			case ID:
				{
				setState(679);
				assign();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(682);
			match(T__19);
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
	public static class MultDivExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultDivExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class IDExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public IDExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class PointExprContext extends ExprContext {
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public PointExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ParanthesisExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParanthesisExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class EqualsExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualsExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ParanthesisIDExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ParanthesisIDExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class NumberExprContext extends ExprContext {
		public TerminalNode Number() { return getToken(advParser.Number, 0); }
		public NumberExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ReadExprContext extends ExprContext {
		public TerminalNode STRING() { return getToken(advParser.STRING, 0); }
		public ReadExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class CompareExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompareExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class NotExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ListExprContext extends ExprContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ListExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class AddSubExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubExprContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 92;
		enterRecursionRule(_localctx, 92, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(685);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__42) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(686);
				expr(15);
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(687);
				match(T__43);
				setState(688);
				expr(14);
				}
				break;
			case 3:
				{
				_localctx = new ParanthesisIDExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(689);
				match(T__53);
				setState(690);
				match(ID);
				setState(691);
				match(T__54);
				}
				break;
			case 4:
				{
				_localctx = new ParanthesisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(692);
				match(T__53);
				setState(693);
				expr(0);
				setState(694);
				match(T__54);
				}
				break;
			case 5:
				{
				_localctx = new PointExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(696);
				point();
				}
				break;
			case 6:
				{
				_localctx = new ListExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(697);
				list();
				}
				break;
			case 7:
				{
				_localctx = new NumberExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(698);
				match(Number);
				}
				break;
			case 8:
				{
				_localctx = new IDExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(699);
				match(ID);
				}
				break;
			case 9:
				{
				_localctx = new ReadExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(700);
				match(T__55);
				setState(704);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(701);
					match(T__56);
					setState(702);
					match(STRING);
					setState(703);
					match(T__22);
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(728);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(726);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(708);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(709);
						((MultDivExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__44) | (1L << T__45) | (1L << T__46))) != 0)) ) {
							((MultDivExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(710);
						expr(14);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(711);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(712);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__42) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(713);
						expr(13);
						}
						break;
					case 3:
						{
						_localctx = new CompareExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(714);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(715);
						((CompareExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__47) | (1L << T__48))) != 0)) ) {
							((CompareExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(716);
						expr(12);
						}
						break;
					case 4:
						{
						_localctx = new EqualsExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(717);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(718);
						((EqualsExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__49 || _la==T__50) ) {
							((EqualsExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(719);
						expr(11);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(720);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(721);
						match(T__51);
						setState(722);
						expr(10);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(723);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(724);
						match(T__52);
						setState(725);
						expr(9);
						}
						break;
					}
					} 
				}
				setState(730);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
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

	public static class AssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(advParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731);
			match(ID);
			setState(732);
			match(T__21);
			setState(733);
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

	public static class ListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(advParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(advParser.ID, i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(735);
			match(T__57);
			setState(740);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(736);
					match(ID);
					setState(737);
					match(T__4);
					}
					} 
				}
				setState(742);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			}
			setState(743);
			match(ID);
			setState(744);
			match(T__58);
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

	public static class PointContext extends ParserRuleContext {
		public PointRectContext pointRect() {
			return getRuleContext(PointRectContext.class,0);
		}
		public PointPolContext pointPol() {
			return getRuleContext(PointPolContext.class,0);
		}
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_point);
		try {
			setState(748);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(746);
				pointRect();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(747);
				pointPol();
				}
				break;
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

	public static class PointRectContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PointRectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointRect; }
	}

	public final PointRectContext pointRect() throws RecognitionException {
		PointRectContext _localctx = new PointRectContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_pointRect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(750);
			match(T__53);
			setState(751);
			expr(0);
			setState(752);
			match(T__4);
			setState(753);
			expr(0);
			setState(754);
			match(T__54);
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

	public static class PointPolContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PointPolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointPol; }
	}

	public final PointPolContext pointPol() throws RecognitionException {
		PointPolContext _localctx = new PointPolContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_pointPol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(756);
			match(T__53);
			setState(757);
			expr(0);
			setState(758);
			match(T__59);
			setState(759);
			expr(0);
			setState(760);
			match(T__54);
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

	public static class TypeContext extends ParserRuleContext {
		public Token t;
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(762);
			((TypeContext)_localctx).t = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 19)) & ~0x3f) == 0 && ((1L << (_la - 19)) & ((1L << (T__18 - 19)) | (1L << (T__60 - 19)) | (1L << (T__61 - 19)) | (1L << (T__62 - 19)) | (1L << (T__63 - 19)) | (1L << (T__64 - 19)))) != 0)) ) {
				((TypeContext)_localctx).t = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class GridPropertiesContext extends ParserRuleContext {
		public Token prop;
		public GridPropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gridProperties; }
	}

	public final GridPropertiesContext gridProperties() throws RecognitionException {
		GridPropertiesContext _localctx = new GridPropertiesContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_gridProperties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(764);
			((GridPropertiesContext)_localctx).prop = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (T__65 - 66)) | (1L << (T__66 - 66)) | (1L << (T__67 - 66)) | (1L << (T__68 - 66)))) != 0)) ) {
				((GridPropertiesContext)_localctx).prop = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class PropertiesKeysContext extends ParserRuleContext {
		public Token prop;
		public PropertiesKeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertiesKeys; }
	}

	public final PropertiesKeysContext propertiesKeys() throws RecognitionException {
		PropertiesKeysContext _localctx = new PropertiesKeysContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_propertiesKeys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(766);
			((PropertiesKeysContext)_localctx).prop = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (T__69 - 70)) | (1L << (T__70 - 70)) | (1L << (T__71 - 70)) | (1L << (T__72 - 70)) | (1L << (T__73 - 70)))) != 0)) ) {
				((PropertiesKeysContext)_localctx).prop = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 46:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3U\u0303\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\7\2s\n\2\f\2\16\2v\13"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3\177\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\7\5\u008a\n\5\f\5\16\5\u008d\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5\u0096\n\5\3\6\3\6\3\6\3\6\5\6\u009c\n\6\3\7\3\7\5\7\u00a0\n\7\3\b"+
		"\3\b\3\b\3\b\6\b\u00a6\n\b\r\b\16\b\u00a7\3\b\6\b\u00ab\n\b\r\b\16\b\u00ac"+
		"\3\b\3\b\3\b\3\t\5\t\u00b3\n\t\3\t\3\t\3\t\3\t\6\t\u00b9\n\t\r\t\16\t"+
		"\u00ba\3\t\6\t\u00be\n\t\r\t\16\t\u00bf\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\5\n\u00ca\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\6\13\u00d8\n\13\r\13\16\13\u00d9\3\13\3\13\5\13\u00de\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00ea\n\f\r\f\16\f\u00eb\3\f\3"+
		"\f\5\f\u00f0\n\f\3\r\3\r\3\r\3\r\3\r\5\r\u00f7\n\r\3\r\3\r\3\r\3\r\3\r"+
		"\6\r\u00fe\n\r\r\r\16\r\u00ff\3\r\3\r\5\r\u0104\n\r\5\r\u0106\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\6\16\u010d\n\16\r\16\16\16\u010e\3\16\3\16\5\16\u0113"+
		"\n\16\3\17\3\17\3\17\7\17\u0118\n\17\f\17\16\17\u011b\13\17\3\17\3\17"+
		"\3\17\3\20\3\20\6\20\u0122\n\20\r\20\16\20\u0123\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\6\21\u012c\n\21\r\21\16\21\u012d\3\21\5\21\u0131\n\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\7\22\u0139\n\22\f\22\16\22\u013c\13\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\7\23\u0145\n\23\f\23\16\23\u0148\13\23\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0154\n\24\f\24"+
		"\16\24\u0157\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5"+
		"\25\u0163\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\6\26\u0171\n\26\r\26\16\26\u0172\3\26\3\26\5\26\u0177\n\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\6\27\u0183\n\27\r\27\16"+
		"\27\u0184\3\27\3\27\5\27\u0189\n\27\3\30\3\30\3\30\3\30\3\30\5\30\u0190"+
		"\n\30\3\30\3\30\3\30\3\30\3\30\6\30\u0197\n\30\r\30\16\30\u0198\3\30\3"+
		"\30\5\30\u019d\n\30\5\30\u019f\n\30\3\31\3\31\3\31\3\31\3\31\6\31\u01a6"+
		"\n\31\r\31\16\31\u01a7\3\31\3\31\5\31\u01ac\n\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\7\32\u01b5\n\32\f\32\16\32\u01b8\13\32\3\32\3\32\3\32\3"+
		"\33\3\33\7\33\u01bf\n\33\f\33\16\33\u01c2\13\33\3\34\3\34\3\34\7\34\u01c7"+
		"\n\34\f\34\16\34\u01ca\13\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\7\37\u01d9\n\37\f\37\16\37\u01dc\13\37\3\37\3"+
		"\37\3\37\3 \3 \3 \3 \3 \3 \3 \5 \u01e8\n \3!\3!\3!\3!\3!\3!\3!\7!\u01f1"+
		"\n!\f!\16!\u01f4\13!\3!\3!\3!\3!\3\"\3\"\3\"\6\"\u01fd\n\"\r\"\16\"\u01fe"+
		"\3\"\5\"\u0202\n\"\3#\3#\3#\3#\3#\6#\u0209\n#\r#\16#\u020a\3#\3#\3$\3"+
		"$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\6%\u021e\n%\r%\16%\u021f\3%\3"+
		"%\3&\3&\3&\3&\3&\3&\5&\u022a\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3"+
		"\'\6\'\u0236\n\'\r\'\16\'\u0237\3\'\3\'\5\'\u023c\n\'\3(\3(\3(\3(\3(\5"+
		"(\u0243\n(\3(\3(\3(\3(\3(\6(\u024a\n(\r(\16(\u024b\3(\3(\3(\5(\u0251\n"+
		"(\3)\3)\3)\3)\3)\6)\u0258\n)\r)\16)\u0259\3)\3)\5)\u025e\n)\3*\3*\3*\3"+
		"*\3*\3*\3*\3*\3*\3*\3*\3*\6*\u026c\n*\r*\16*\u026d\3*\3*\5*\u0272\n*\3"+
		"+\3+\3+\3+\7+\u0278\n+\f+\16+\u027b\13+\3+\3+\3+\3+\3+\5+\u0282\n+\3,"+
		"\3,\7,\u0286\n,\f,\16,\u0289\13,\3,\5,\u028c\n,\3-\3-\3-\3-\3.\3.\3.\3"+
		".\7.\u0296\n.\f.\16.\u0299\13.\3.\3.\3.\3.\3.\7.\u02a0\n.\f.\16.\u02a3"+
		"\13.\3.\3.\5.\u02a7\n.\3/\3/\5/\u02ab\n/\3/\3/\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\5\60\u02c3\n\60\5\60\u02c5\n\60\3\60\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\7\60\u02d9"+
		"\n\60\f\60\16\60\u02dc\13\60\3\61\3\61\3\61\3\61\3\62\3\62\3\62\7\62\u02e5"+
		"\n\62\f\62\16\62\u02e8\13\62\3\62\3\62\3\62\3\63\3\63\5\63\u02ef\n\63"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66"+
		"\3\67\3\67\38\38\38\2\3^9\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjln\2\n\3\2*+\4\2\t\t--\3\2"+
		"/\61\4\2!\"\62\63\3\2\64\65\4\2\25\25?C\3\2DG\3\2HL\2\u0331\2p\3\2\2\2"+
		"\4~\3\2\2\2\6\u0080\3\2\2\2\b\u0095\3\2\2\2\n\u009b\3\2\2\2\f\u009f\3"+
		"\2\2\2\16\u00a1\3\2\2\2\20\u00b2\3\2\2\2\22\u00c9\3\2\2\2\24\u00dd\3\2"+
		"\2\2\26\u00ef\3\2\2\2\30\u0105\3\2\2\2\32\u0112\3\2\2\2\34\u0114\3\2\2"+
		"\2\36\u011f\3\2\2\2 \u0127\3\2\2\2\"\u0134\3\2\2\2$\u0140\3\2\2\2&\u014d"+
		"\3\2\2\2(\u0162\3\2\2\2*\u0176\3\2\2\2,\u0188\3\2\2\2.\u019e\3\2\2\2\60"+
		"\u01ab\3\2\2\2\62\u01ad\3\2\2\2\64\u01bc\3\2\2\2\66\u01c3\3\2\2\28\u01cb"+
		"\3\2\2\2:\u01ce\3\2\2\2<\u01d4\3\2\2\2>\u01e7\3\2\2\2@\u01e9\3\2\2\2B"+
		"\u01f9\3\2\2\2D\u0203\3\2\2\2F\u020e\3\2\2\2H\u0219\3\2\2\2J\u0229\3\2"+
		"\2\2L\u023b\3\2\2\2N\u0250\3\2\2\2P\u025d\3\2\2\2R\u0271\3\2\2\2T\u0281"+
		"\3\2\2\2V\u028b\3\2\2\2X\u028d\3\2\2\2Z\u02a6\3\2\2\2\\\u02aa\3\2\2\2"+
		"^\u02c4\3\2\2\2`\u02dd\3\2\2\2b\u02e1\3\2\2\2d\u02ee\3\2\2\2f\u02f0\3"+
		"\2\2\2h\u02f6\3\2\2\2j\u02fc\3\2\2\2l\u02fe\3\2\2\2n\u0300\3\2\2\2pt\5"+
		"\b\5\2qs\5\4\3\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3"+
		"\2\2\2wx\7\2\2\3x\3\3\2\2\2y\177\5\6\4\2z\177\5\f\7\2{\177\5&\24\2|\177"+
		"\5D#\2}\177\5X-\2~y\3\2\2\2~z\3\2\2\2~{\3\2\2\2~|\3\2\2\2~}\3\2\2\2\177"+
		"\5\3\2\2\2\u0080\u0081\7\3\2\2\u0081\u0082\7N\2\2\u0082\u0083\7\4\2\2"+
		"\u0083\7\3\2\2\2\u0084\u0085\7\5\2\2\u0085\u008b\7\6\2\2\u0086\u0087\5"+
		"\n\6\2\u0087\u0088\7\7\2\2\u0088\u008a\3\2\2\2\u0089\u0086\3\2\2\2\u008a"+
		"\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2"+
		"\2\2\u008d\u008b\3\2\2\2\u008e\u008f\5\n\6\2\u008f\u0090\7\b\2\2\u0090"+
		"\u0096\3\2\2\2\u0091\u0092\7\5\2\2\u0092\u0093\7N\2\2\u0093\u0094\7\t"+
		"\2\2\u0094\u0096\7N\2\2\u0095\u0084\3\2\2\2\u0095\u0091\3\2\2\2\u0096"+
		"\t\3\2\2\2\u0097\u0098\7O\2\2\u0098\u0099\7\t\2\2\u0099\u009c\7O\2\2\u009a"+
		"\u009c\7O\2\2\u009b\u0097\3\2\2\2\u009b\u009a\3\2\2\2\u009c\13\3\2\2\2"+
		"\u009d\u00a0\5\16\b\2\u009e\u00a0\5\20\t\2\u009f\u009d\3\2\2\2\u009f\u009e"+
		"\3\2\2\2\u00a0\r\3\2\2\2\u00a1\u00a2\7\n\2\2\u00a2\u00a3\7N\2\2\u00a3"+
		"\u00a5\7\13\2\2\u00a4\u00a6\5\34\17\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00ab\5\22\n\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3"+
		"\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\5\"\22\2\u00af"+
		"\u00b0\7\f\2\2\u00b0\17\3\2\2\2\u00b1\u00b3\7\r\2\2\u00b2\u00b1\3\2\2"+
		"\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\7\16\2\2\u00b5"+
		"\u00b6\7N\2\2\u00b6\u00b8\7\13\2\2\u00b7\u00b9\5\34\17\2\u00b8\u00b7\3"+
		"\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bd\3\2\2\2\u00bc\u00be\5\22\n\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3"+
		"\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\5\"\22\2\u00c2\u00c3\7\f\2\2\u00c3\21\3\2\2\2\u00c4\u00ca\5\24"+
		"\13\2\u00c5\u00ca\5\30\r\2\u00c6\u00ca\5\26\f\2\u00c7\u00ca\5\36\20\2"+
		"\u00c8\u00ca\5\\/\2\u00c9\u00c4\3\2\2\2\u00c9\u00c5\3\2\2\2\u00c9\u00c6"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00c8\3\2\2\2\u00ca\23\3\2\2\2\u00cb"+
		"\u00cc\7\17\2\2\u00cc\u00cd\7N\2\2\u00cd\u00ce\7\20\2\2\u00ce\u00cf\5"+
		"^\60\2\u00cf\u00d0\5\22\n\2\u00d0\u00de\3\2\2\2\u00d1\u00d2\7\17\2\2\u00d2"+
		"\u00d3\7N\2\2\u00d3\u00d4\7\20\2\2\u00d4\u00d5\5^\60\2\u00d5\u00d7\7\13"+
		"\2\2\u00d6\u00d8\5\22\n\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\7\f"+
		"\2\2\u00dc\u00de\3\2\2\2\u00dd\u00cb\3\2\2\2\u00dd\u00d1\3\2\2\2\u00de"+
		"\25\3\2\2\2\u00df\u00e0\7\21\2\2\u00e0\u00e1\5^\60\2\u00e1\u00e2\7\22"+
		"\2\2\u00e2\u00e3\5\22\n\2\u00e3\u00f0\3\2\2\2\u00e4\u00e5\7\21\2\2\u00e5"+
		"\u00e6\5^\60\2\u00e6\u00e7\7\22\2\2\u00e7\u00e9\7\13\2\2\u00e8\u00ea\5"+
		"\22\n\2\u00e9\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\7\f\2\2\u00ee\u00f0\3\2"+
		"\2\2\u00ef\u00df\3\2\2\2\u00ef\u00e4\3\2\2\2\u00f0\27\3\2\2\2\u00f1\u00f2"+
		"\7\23\2\2\u00f2\u00f3\5^\60\2\u00f3\u00f4\7\22\2\2\u00f4\u00f6\5\22\n"+
		"\2\u00f5\u00f7\5\32\16\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u0106\3\2\2\2\u00f8\u00f9\7\23\2\2\u00f9\u00fa\5^\60\2\u00fa\u00fb\7"+
		"\22\2\2\u00fb\u00fd\7\13\2\2\u00fc\u00fe\5\22\n\2\u00fd\u00fc\3\2\2\2"+
		"\u00fe\u00ff\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101"+
		"\3\2\2\2\u0101\u0103\7\f\2\2\u0102\u0104\5\32\16\2\u0103\u0102\3\2\2\2"+
		"\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u00f1\3\2\2\2\u0105\u00f8"+
		"\3\2\2\2\u0106\31\3\2\2\2\u0107\u0108\7\24\2\2\u0108\u0113\5\22\n\2\u0109"+
		"\u010a\7\24\2\2\u010a\u010c\7\13\2\2\u010b\u010d\5\22\n\2\u010c\u010b"+
		"\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0111\7\f\2\2\u0111\u0113\3\2\2\2\u0112\u0107\3\2"+
		"\2\2\u0112\u0109\3\2\2\2\u0113\33\3\2\2\2\u0114\u0119\7\25\2\2\u0115\u0116"+
		"\7N\2\2\u0116\u0118\7\7\2\2\u0117\u0115\3\2\2\2\u0118\u011b\3\2\2\2\u0119"+
		"\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2"+
		"\2\2\u011c\u011d\7N\2\2\u011d\u011e\7\26\2\2\u011e\35\3\2\2\2\u011f\u0121"+
		"\7N\2\2\u0120\u0122\5 \21\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123"+
		"\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\7\26"+
		"\2\2\u0126\37\3\2\2\2\u0127\u0128\7\27\2\2\u0128\u0129\5n8\2\u0129\u0130"+
		"\7\30\2\2\u012a\u012c\7N\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u0131\7M"+
		"\2\2\u0130\u012b\3\2\2\2\u0130\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"\u0133\7\31\2\2\u0133!\3\2\2\2\u0134\u013a\7\32\2\2\u0135\u0136\5$\23"+
		"\2\u0136\u0137\7\7\2\2\u0137\u0139\3\2\2\2\u0138\u0135\3\2\2\2\u0139\u013c"+
		"\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013d\3\2\2\2\u013c"+
		"\u013a\3\2\2\2\u013d\u013e\5$\23\2\u013e\u013f\7\26\2\2\u013f#\3\2\2\2"+
		"\u0140\u0141\7N\2\2\u0141\u0146\7\33\2\2\u0142\u0143\7O\2\2\u0143\u0145"+
		"\7\7\2\2\u0144\u0142\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146"+
		"\u0147\3\2\2\2\u0147\u0149\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014a\7O"+
		"\2\2\u014a\u014b\7\33\2\2\u014b\u014c\7N\2\2\u014c%\3\2\2\2\u014d\u014e"+
		"\7\34\2\2\u014e\u014f\7N\2\2\u014f\u0150\7\35\2\2\u0150\u0151\7N\2\2\u0151"+
		"\u0155\7\13\2\2\u0152\u0154\5(\25\2\u0153\u0152\3\2\2\2\u0154\u0157\3"+
		"\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157"+
		"\u0155\3\2\2\2\u0158\u0159\7\f\2\2\u0159\'\3\2\2\2\u015a\u0163\5\\/\2"+
		"\u015b\u0163\5*\26\2\u015c\u0163\5<\37\2\u015d\u0163\5\62\32\2\u015e\u0163"+
		"\58\35\2\u015f\u0163\5@!\2\u0160\u0163\5.\30\2\u0161\u0163\5,\27\2\u0162"+
		"\u015a\3\2\2\2\u0162\u015b\3\2\2\2\u0162\u015c\3\2\2\2\u0162\u015d\3\2"+
		"\2\2\u0162\u015e\3\2\2\2\u0162\u015f\3\2\2\2\u0162\u0160\3\2\2\2\u0162"+
		"\u0161\3\2\2\2\u0163)\3\2\2\2\u0164\u0165\7\17\2\2\u0165\u0166\7N\2\2"+
		"\u0166\u0167\7\20\2\2\u0167\u0168\5^\60\2\u0168\u0169\5(\25\2\u0169\u0177"+
		"\3\2\2\2\u016a\u016b\7\17\2\2\u016b\u016c\7N\2\2\u016c\u016d\7\20\2\2"+
		"\u016d\u016e\5^\60\2\u016e\u0170\7\13\2\2\u016f\u0171\5(\25\2\u0170\u016f"+
		"\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0174\3\2\2\2\u0174\u0175\7\f\2\2\u0175\u0177\3\2\2\2\u0176\u0164\3\2"+
		"\2\2\u0176\u016a\3\2\2\2\u0177+\3\2\2\2\u0178\u0179\7\21\2\2\u0179\u017a"+
		"\5^\60\2\u017a\u017b\7\22\2\2\u017b\u017c\5(\25\2\u017c\u0189\3\2\2\2"+
		"\u017d\u017e\7\21\2\2\u017e\u017f\5^\60\2\u017f\u0180\7\22\2\2\u0180\u0182"+
		"\7\13\2\2\u0181\u0183\5(\25\2\u0182\u0181\3\2\2\2\u0183\u0184\3\2\2\2"+
		"\u0184\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187"+
		"\7\f\2\2\u0187\u0189\3\2\2\2\u0188\u0178\3\2\2\2\u0188\u017d\3\2\2\2\u0189"+
		"-\3\2\2\2\u018a\u018b\7\23\2\2\u018b\u018c\5^\60\2\u018c\u018d\7\22\2"+
		"\2\u018d\u018f\5(\25\2\u018e\u0190\5\60\31\2\u018f\u018e\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u019f\3\2\2\2\u0191\u0192\7\23\2\2\u0192\u0193\5"+
		"^\60\2\u0193\u0194\7\22\2\2\u0194\u0196\7\13\2\2\u0195\u0197\5(\25\2\u0196"+
		"\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2"+
		"\2\2\u0199\u019a\3\2\2\2\u019a\u019c\7\f\2\2\u019b\u019d\5\60\31\2\u019c"+
		"\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f\3\2\2\2\u019e\u018a\3\2"+
		"\2\2\u019e\u0191\3\2\2\2\u019f/\3\2\2\2\u01a0\u01a1\7\24\2\2\u01a1\u01ac"+
		"\5(\25\2\u01a2\u01a3\7\24\2\2\u01a3\u01a5\7\13\2\2\u01a4\u01a6\5(\25\2"+
		"\u01a5\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8"+
		"\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01aa\7\f\2\2\u01aa\u01ac\3\2\2\2\u01ab"+
		"\u01a0\3\2\2\2\u01ab\u01a2\3\2\2\2\u01ac\61\3\2\2\2\u01ad\u01ae\5:\36"+
		"\2\u01ae\u01af\7\36\2\2\u01af\u01b0\5\64\33\2\u01b0\u01b6\7\37\2\2\u01b1"+
		"\u01b2\5\64\33\2\u01b2\u01b3\7\37\2\2\u01b3\u01b5\3\2\2\2\u01b4\u01b1"+
		"\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7"+
		"\u01b9\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01ba\5\64\33\2\u01ba\u01bb\7"+
		"\26\2\2\u01bb\63\3\2\2\2\u01bc\u01c0\5^\60\2\u01bd\u01bf\5 \21\2\u01be"+
		"\u01bd\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0\u01be\3\2\2\2\u01c0\u01c1\3\2"+
		"\2\2\u01c1\65\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3\u01c4\5:\36\2\u01c4\u01c8"+
		"\7 \2\2\u01c5\u01c7\5 \21\2\u01c6\u01c5\3\2\2\2\u01c7\u01ca\3\2\2\2\u01c8"+
		"\u01c6\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\67\3\2\2\2\u01ca\u01c8\3\2\2"+
		"\2\u01cb\u01cc\5\66\34\2\u01cc\u01cd\7\26\2\2\u01cd9\3\2\2\2\u01ce\u01cf"+
		"\7!\2\2\u01cf\u01d0\7N\2\2\u01d0\u01d1\7\7\2\2\u01d1\u01d2\7N\2\2\u01d2"+
		"\u01d3\7\"\2\2\u01d3;\3\2\2\2\u01d4\u01da\7#\2\2\u01d5\u01d6\5> \2\u01d6"+
		"\u01d7\7\7\2\2\u01d7\u01d9\3\2\2\2\u01d8\u01d5\3\2\2\2\u01d9\u01dc\3\2"+
		"\2\2\u01da\u01d8\3\2\2\2\u01da\u01db\3\2\2\2\u01db\u01dd\3\2\2\2\u01dc"+
		"\u01da\3\2\2\2\u01dd\u01de\5> \2\u01de\u01df\7\26\2\2\u01df=\3\2\2\2\u01e0"+
		"\u01e1\7N\2\2\u01e1\u01e2\7$\2\2\u01e2\u01e8\5^\60\2\u01e3\u01e4\5\66"+
		"\34\2\u01e4\u01e5\7$\2\2\u01e5\u01e6\5^\60\2\u01e6\u01e8\3\2\2\2\u01e7"+
		"\u01e0\3\2\2\2\u01e7\u01e3\3\2\2\2\u01e8?\3\2\2\2\u01e9\u01ea\7%\2\2\u01ea"+
		"\u01eb\7N\2\2\u01eb\u01ec\5^\60\2\u01ec\u01f2\7\27\2\2\u01ed\u01ee\5B"+
		"\"\2\u01ee\u01ef\7\7\2\2\u01ef\u01f1\3\2\2\2\u01f0\u01ed\3\2\2\2\u01f1"+
		"\u01f4\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f5\3\2"+
		"\2\2\u01f4\u01f2\3\2\2\2\u01f5\u01f6\5B\"\2\u01f6\u01f7\7\31\2\2\u01f7"+
		"\u01f8\7\26\2\2\u01f8A\3\2\2\2\u01f9\u01fa\5l\67\2\u01fa\u0201\7\30\2"+
		"\2\u01fb\u01fd\7N\2\2\u01fc\u01fb\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01fc"+
		"\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u0202\7M\2\2\u0201"+
		"\u01fc\3\2\2\2\u0201\u0200\3\2\2\2\u0202C\3\2\2\2\u0203\u0204\7&\2\2\u0204"+
		"\u0205\7N\2\2\u0205\u0208\7\13\2\2\u0206\u0209\5F$\2\u0207\u0209\5H%\2"+
		"\u0208\u0206\3\2\2\2\u0208\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u0208"+
		"\3\2\2\2\u020a\u020b\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020d\7\f\2\2\u020d"+
		"E\3\2\2\2\u020e\u020f\7\'\2\2\u020f\u0210\7N\2\2\u0210\u0211\7\17\2\2"+
		"\u0211\u0212\7N\2\2\u0212\u0213\7$\2\2\u0213\u0214\5^\60\2\u0214\u0215"+
		"\7\37\2\2\u0215\u0216\7(\2\2\u0216\u0217\5^\60\2\u0217\u0218\7\26\2\2"+
		"\u0218G\3\2\2\2\u0219\u021a\7)\2\2\u021a\u021b\7N\2\2\u021b\u021d\7\13"+
		"\2\2\u021c\u021e\5J&\2\u021d\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u021d"+
		"\3\2\2\2\u021f\u0220\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u0222\7\f\2\2\u0222"+
		"I\3\2\2\2\u0223\u022a\5\36\20\2\u0224\u022a\5R*\2\u0225\u022a\5T+\2\u0226"+
		"\u022a\5\\/\2\u0227\u022a\5N(\2\u0228\u022a\5L\'\2\u0229\u0223\3\2\2\2"+
		"\u0229\u0224\3\2\2\2\u0229\u0225\3\2\2\2\u0229\u0226\3\2\2\2\u0229\u0227"+
		"\3\2\2\2\u0229\u0228\3\2\2\2\u022aK\3\2\2\2\u022b\u022c\7\21\2\2\u022c"+
		"\u022d\5^\60\2\u022d\u022e\7\22\2\2\u022e\u022f\5J&\2\u022f\u023c\3\2"+
		"\2\2\u0230\u0231\7\21\2\2\u0231\u0232\5^\60\2\u0232\u0233\7\22\2\2\u0233"+
		"\u0235\7\13\2\2\u0234\u0236\5J&\2\u0235\u0234\3\2\2\2\u0236\u0237\3\2"+
		"\2\2\u0237\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u0239\3\2\2\2\u0239"+
		"\u023a\7\f\2\2\u023a\u023c\3\2\2\2\u023b\u022b\3\2\2\2\u023b\u0230\3\2"+
		"\2\2\u023cM\3\2\2\2\u023d\u023e\7\23\2\2\u023e\u023f\5^\60\2\u023f\u0240"+
		"\7\22\2\2\u0240\u0242\5J&\2\u0241\u0243\5P)\2\u0242\u0241\3\2\2\2\u0242"+
		"\u0243\3\2\2\2\u0243\u0251\3\2\2\2\u0244\u0245\7\23\2\2\u0245\u0246\5"+
		"^\60\2\u0246\u0247\7\22\2\2\u0247\u0249\7\13\2\2\u0248\u024a\5J&\2\u0249"+
		"\u0248\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u0249\3\2\2\2\u024b\u024c\3\2"+
		"\2\2\u024c\u024d\3\2\2\2\u024d\u024e\7\f\2\2\u024e\u024f\5P)\2\u024f\u0251"+
		"\3\2\2\2\u0250\u023d\3\2\2\2\u0250\u0244\3\2\2\2\u0251O\3\2\2\2\u0252"+
		"\u0253\7\24\2\2\u0253\u025e\5J&\2\u0254\u0255\7\24\2\2\u0255\u0257\7\13"+
		"\2\2\u0256\u0258\5J&\2\u0257\u0256\3\2\2\2\u0258\u0259\3\2\2\2\u0259\u0257"+
		"\3\2\2\2\u0259\u025a\3\2\2\2\u025a\u025b\3\2\2\2\u025b\u025c\7\f\2\2\u025c"+
		"\u025e\3\2\2\2\u025d\u0252\3\2\2\2\u025d\u0254\3\2\2\2\u025eQ\3\2\2\2"+
		"\u025f\u0260\7\17\2\2\u0260\u0261\7N\2\2\u0261\u0262\7\20\2\2\u0262\u0263"+
		"\5^\60\2\u0263\u0264\5J&\2\u0264\u0272\3\2\2\2\u0265\u0266\7\17\2\2\u0266"+
		"\u0267\7N\2\2\u0267\u0268\7\20\2\2\u0268\u0269\5^\60\2\u0269\u026b\7\13"+
		"\2\2\u026a\u026c\5J&\2\u026b\u026a\3\2\2\2\u026c\u026d\3\2\2\2\u026d\u026b"+
		"\3\2\2\2\u026d\u026e\3\2\2\2\u026e\u026f\3\2\2\2\u026f\u0270\7\f\2\2\u0270"+
		"\u0272\3\2\2\2\u0271\u025f\3\2\2\2\u0271\u0265\3\2\2\2\u0272S\3\2\2\2"+
		"\u0273\u0279\7*\2\2\u0274\u0275\5V,\2\u0275\u0276\7\7\2\2\u0276\u0278"+
		"\3\2\2\2\u0277\u0274\3\2\2\2\u0278\u027b\3\2\2\2\u0279\u0277\3\2\2\2\u0279"+
		"\u027a\3\2\2\2\u027a\u027c\3\2\2\2\u027b\u0279\3\2\2\2\u027c\u027d\5V"+
		",\2\u027d\u027e\7\26\2\2\u027e\u0282\3\2\2\2\u027f\u0280\t\2\2\2\u0280"+
		"\u0282\7\26\2\2\u0281\u0273\3\2\2\2\u0281\u027f\3\2\2\2\u0282U\3\2\2\2"+
		"\u0283\u0287\7N\2\2\u0284\u0286\5 \21\2\u0285\u0284\3\2\2\2\u0286\u0289"+
		"\3\2\2\2\u0287\u0285\3\2\2\2\u0287\u0288\3\2\2\2\u0288\u028c\3\2\2\2\u0289"+
		"\u0287\3\2\2\2\u028a\u028c\5:\36\2\u028b\u0283\3\2\2\2\u028b\u028a\3\2"+
		"\2\2\u028cW\3\2\2\2\u028d\u028e\7,\2\2\u028e\u028f\7N\2\2\u028f\u0290"+
		"\7\26\2\2\u0290Y\3\2\2\2\u0291\u0297\5j\66\2\u0292\u0293\5`\61\2\u0293"+
		"\u0294\7\7\2\2\u0294\u0296\3\2\2\2\u0295\u0292\3\2\2\2\u0296\u0299\3\2"+
		"\2\2\u0297\u0295\3\2\2\2\u0297\u0298\3\2\2\2\u0298\u029a\3\2\2\2\u0299"+
		"\u0297\3\2\2\2\u029a\u029b\5`\61\2\u029b\u02a7\3\2\2\2\u029c\u02a1\5j"+
		"\66\2\u029d\u029e\7N\2\2\u029e\u02a0\7\7\2\2\u029f\u029d\3\2\2\2\u02a0"+
		"\u02a3\3\2\2\2\u02a1\u029f\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2\u02a4\3\2"+
		"\2\2\u02a3\u02a1\3\2\2\2\u02a4\u02a5\7N\2\2\u02a5\u02a7\3\2\2\2\u02a6"+
		"\u0291\3\2\2\2\u02a6\u029c\3\2\2\2\u02a7[\3\2\2\2\u02a8\u02ab\5Z.\2\u02a9"+
		"\u02ab\5`\61\2\u02aa\u02a8\3\2\2\2\u02aa\u02a9\3\2\2\2\u02ab\u02ac\3\2"+
		"\2\2\u02ac\u02ad\7\26\2\2\u02ad]\3\2\2\2\u02ae\u02af\b\60\1\2\u02af\u02b0"+
		"\t\3\2\2\u02b0\u02c5\5^\60\21\u02b1\u02b2\7.\2\2\u02b2\u02c5\5^\60\20"+
		"\u02b3\u02b4\78\2\2\u02b4\u02b5\7N\2\2\u02b5\u02c5\79\2\2\u02b6\u02b7"+
		"\78\2\2\u02b7\u02b8\5^\60\2\u02b8\u02b9\79\2\2\u02b9\u02c5\3\2\2\2\u02ba"+
		"\u02c5\5d\63\2\u02bb\u02c5\5b\62\2\u02bc\u02c5\7M\2\2\u02bd\u02c5\7N\2"+
		"\2\u02be\u02c2\7:\2\2\u02bf\u02c0\7;\2\2\u02c0\u02c1\7P\2\2\u02c1\u02c3"+
		"\7\31\2\2\u02c2\u02bf\3\2\2\2\u02c2\u02c3\3\2\2\2\u02c3\u02c5\3\2\2\2"+
		"\u02c4\u02ae\3\2\2\2\u02c4\u02b1\3\2\2\2\u02c4\u02b3\3\2\2\2\u02c4\u02b6"+
		"\3\2\2\2\u02c4\u02ba\3\2\2\2\u02c4\u02bb\3\2\2\2\u02c4\u02bc\3\2\2\2\u02c4"+
		"\u02bd\3\2\2\2\u02c4\u02be\3\2\2\2\u02c5\u02da\3\2\2\2\u02c6\u02c7\f\17"+
		"\2\2\u02c7\u02c8\t\4\2\2\u02c8\u02d9\5^\60\20\u02c9\u02ca\f\16\2\2\u02ca"+
		"\u02cb\t\3\2\2\u02cb\u02d9\5^\60\17\u02cc\u02cd\f\r\2\2\u02cd\u02ce\t"+
		"\5\2\2\u02ce\u02d9\5^\60\16\u02cf\u02d0\f\f\2\2\u02d0\u02d1\t\6\2\2\u02d1"+
		"\u02d9\5^\60\r\u02d2\u02d3\f\13\2\2\u02d3\u02d4\7\66\2\2\u02d4\u02d9\5"+
		"^\60\f\u02d5\u02d6\f\n\2\2\u02d6\u02d7\7\67\2\2\u02d7\u02d9\5^\60\13\u02d8"+
		"\u02c6\3\2\2\2\u02d8\u02c9\3\2\2\2\u02d8\u02cc\3\2\2\2\u02d8\u02cf\3\2"+
		"\2\2\u02d8\u02d2\3\2\2\2\u02d8\u02d5\3\2\2\2\u02d9\u02dc\3\2\2\2\u02da"+
		"\u02d8\3\2\2\2\u02da\u02db\3\2\2\2\u02db_\3\2\2\2\u02dc\u02da\3\2\2\2"+
		"\u02dd\u02de\7N\2\2\u02de\u02df\7\30\2\2\u02df\u02e0\5^\60\2\u02e0a\3"+
		"\2\2\2\u02e1\u02e6\7<\2\2\u02e2\u02e3\7N\2\2\u02e3\u02e5\7\7\2\2\u02e4"+
		"\u02e2\3\2\2\2\u02e5\u02e8\3\2\2\2\u02e6\u02e4\3\2\2\2\u02e6\u02e7\3\2"+
		"\2\2\u02e7\u02e9\3\2\2\2\u02e8\u02e6\3\2\2\2\u02e9\u02ea\7N\2\2\u02ea"+
		"\u02eb\7=\2\2\u02ebc\3\2\2\2\u02ec\u02ef\5f\64\2\u02ed\u02ef\5h\65\2\u02ee"+
		"\u02ec\3\2\2\2\u02ee\u02ed\3\2\2\2\u02efe\3\2\2\2\u02f0\u02f1\78\2\2\u02f1"+
		"\u02f2\5^\60\2\u02f2\u02f3\7\7\2\2\u02f3\u02f4\5^\60\2\u02f4\u02f5\79"+
		"\2\2\u02f5g\3\2\2\2\u02f6\u02f7\78\2\2\u02f7\u02f8\5^\60\2\u02f8\u02f9"+
		"\7>\2\2\u02f9\u02fa\5^\60\2\u02fa\u02fb\79\2\2\u02fbi\3\2\2\2\u02fc\u02fd"+
		"\t\7\2\2\u02fdk\3\2\2\2\u02fe\u02ff\t\b\2\2\u02ffm\3\2\2\2\u0300\u0301"+
		"\t\t\2\2\u0301o\3\2\2\2Mt~\u008b\u0095\u009b\u009f\u00a7\u00ac\u00b2\u00ba"+
		"\u00bf\u00c9\u00d9\u00dd\u00eb\u00ef\u00f6\u00ff\u0103\u0105\u010e\u0112"+
		"\u0119\u0123\u012d\u0130\u013a\u0146\u0155\u0162\u0172\u0176\u0184\u0188"+
		"\u018f\u0198\u019c\u019e\u01a7\u01ab\u01b6\u01c0\u01c8\u01da\u01e7\u01f2"+
		"\u01fe\u0201\u0208\u020a\u021f\u0229\u0237\u023b\u0242\u024b\u0250\u0259"+
		"\u025d\u026d\u0271\u0279\u0281\u0287\u028b\u0297\u02a1\u02a6\u02aa\u02c2"+
		"\u02c4\u02d8\u02da\u02e6\u02ee";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}