# Generated from xAdv.g4 by ANTLR 4.12.0
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO

def serializedATN():
    return [
        4,1,15,39,2,0,7,0,2,1,7,1,2,2,7,2,1,0,4,0,8,8,0,11,0,12,0,9,1,0,
        1,0,1,1,1,1,1,1,1,2,1,2,1,2,1,2,1,2,4,2,22,8,2,11,2,12,2,23,1,2,
        1,2,1,2,1,2,1,2,1,2,4,2,32,8,2,11,2,12,2,33,1,2,3,2,37,8,2,1,2,0,
        0,3,0,2,4,0,0,39,0,7,1,0,0,0,2,13,1,0,0,0,4,36,1,0,0,0,6,8,3,2,1,
        0,7,6,1,0,0,0,8,9,1,0,0,0,9,7,1,0,0,0,9,10,1,0,0,0,10,11,1,0,0,0,
        11,12,5,0,0,1,12,1,1,0,0,0,13,14,5,1,0,0,14,15,3,4,2,0,15,3,1,0,
        0,0,16,17,5,2,0,0,17,21,5,3,0,0,18,19,5,6,0,0,19,20,5,4,0,0,20,22,
        5,9,0,0,21,18,1,0,0,0,22,23,1,0,0,0,23,21,1,0,0,0,23,24,1,0,0,0,
        24,25,1,0,0,0,25,37,5,5,0,0,26,27,5,9,0,0,27,31,5,3,0,0,28,29,5,
        6,0,0,29,30,5,4,0,0,30,32,5,9,0,0,31,28,1,0,0,0,32,33,1,0,0,0,33,
        31,1,0,0,0,33,34,1,0,0,0,34,35,1,0,0,0,35,37,5,5,0,0,36,16,1,0,0,
        0,36,26,1,0,0,0,37,5,1,0,0,0,4,9,23,33,36
    ]

class xAdvParser ( Parser ):

    grammarFileName = "xAdv.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'define'", "'automaton'", "'{'", "':'", 
                     "'}'", "<INVALID>", "<INVALID>", "';'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "AutomatonProperty", "WS", 
                      "Ignore", "Value", "INT", "ID", "NEWLINE", "SingleLineComment", 
                      "BlockComment", "ERROR" ]

    RULE_program = 0
    RULE_definitions = 1
    RULE_automatonStyle = 2

    ruleNames =  [ "program", "definitions", "automatonStyle" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    AutomatonProperty=6
    WS=7
    Ignore=8
    Value=9
    INT=10
    ID=11
    NEWLINE=12
    SingleLineComment=13
    BlockComment=14
    ERROR=15

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.12.0")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class ProgramContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def EOF(self):
            return self.getToken(xAdvParser.EOF, 0)

        def definitions(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(xAdvParser.DefinitionsContext)
            else:
                return self.getTypedRuleContext(xAdvParser.DefinitionsContext,i)


        def getRuleIndex(self):
            return xAdvParser.RULE_program

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterProgram" ):
                listener.enterProgram(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitProgram" ):
                listener.exitProgram(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitProgram" ):
                return visitor.visitProgram(self)
            else:
                return visitor.visitChildren(self)




    def program(self):

        localctx = xAdvParser.ProgramContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_program)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 7 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 6
                self.definitions()
                self.state = 9 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not (_la==1):
                    break

            self.state = 11
            self.match(xAdvParser.EOF)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class DefinitionsContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def automatonStyle(self):
            return self.getTypedRuleContext(xAdvParser.AutomatonStyleContext,0)


        def getRuleIndex(self):
            return xAdvParser.RULE_definitions

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterDefinitions" ):
                listener.enterDefinitions(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitDefinitions" ):
                listener.exitDefinitions(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitDefinitions" ):
                return visitor.visitDefinitions(self)
            else:
                return visitor.visitChildren(self)




    def definitions(self):

        localctx = xAdvParser.DefinitionsContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_definitions)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 13
            self.match(xAdvParser.T__0)

            self.state = 14
            self.automatonStyle()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class AutomatonStyleContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def AutomatonProperty(self, i:int=None):
            if i is None:
                return self.getTokens(xAdvParser.AutomatonProperty)
            else:
                return self.getToken(xAdvParser.AutomatonProperty, i)

        def Value(self, i:int=None):
            if i is None:
                return self.getTokens(xAdvParser.Value)
            else:
                return self.getToken(xAdvParser.Value, i)

        def getRuleIndex(self):
            return xAdvParser.RULE_automatonStyle

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAutomatonStyle" ):
                listener.enterAutomatonStyle(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAutomatonStyle" ):
                listener.exitAutomatonStyle(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAutomatonStyle" ):
                return visitor.visitAutomatonStyle(self)
            else:
                return visitor.visitChildren(self)




    def automatonStyle(self):

        localctx = xAdvParser.AutomatonStyleContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_automatonStyle)
        self._la = 0 # Token type
        try:
            self.state = 36
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [2]:
                self.enterOuterAlt(localctx, 1)
                self.state = 16
                self.match(xAdvParser.T__1)
                self.state = 17
                self.match(xAdvParser.T__2)
                self.state = 21 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while True:
                    self.state = 18
                    self.match(xAdvParser.AutomatonProperty)
                    self.state = 19
                    self.match(xAdvParser.T__3)
                    self.state = 20
                    self.match(xAdvParser.Value)
                    self.state = 23 
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)
                    if not (_la==6):
                        break

                self.state = 25
                self.match(xAdvParser.T__4)
                pass
            elif token in [9]:
                self.enterOuterAlt(localctx, 2)
                self.state = 26
                self.match(xAdvParser.Value)
                self.state = 27
                self.match(xAdvParser.T__2)
                self.state = 31 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while True:
                    self.state = 28
                    self.match(xAdvParser.AutomatonProperty)
                    self.state = 29
                    self.match(xAdvParser.T__3)
                    self.state = 30
                    self.match(xAdvParser.Value)
                    self.state = 33 
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)
                    if not (_la==6):
                        break

                self.state = 35
                self.match(xAdvParser.T__4)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





