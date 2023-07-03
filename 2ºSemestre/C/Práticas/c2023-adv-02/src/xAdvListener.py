# Generated from xAdv.g4 by ANTLR 4.12.0
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .xAdvParser import xAdvParser
else:
    from xAdvParser import xAdvParser

# This class defines a complete listener for a parse tree produced by xAdvParser.
class xAdvListener(ParseTreeListener):

    # Enter a parse tree produced by xAdvParser#program.
    def enterProgram(self, ctx:xAdvParser.ProgramContext):
        pass

    # Exit a parse tree produced by xAdvParser#program.
    def exitProgram(self, ctx:xAdvParser.ProgramContext):
        pass


    # Enter a parse tree produced by xAdvParser#definitions.
    def enterDefinitions(self, ctx:xAdvParser.DefinitionsContext):
        pass

    # Exit a parse tree produced by xAdvParser#definitions.
    def exitDefinitions(self, ctx:xAdvParser.DefinitionsContext):
        pass


    # Enter a parse tree produced by xAdvParser#automatonStyle.
    def enterAutomatonStyle(self, ctx:xAdvParser.AutomatonStyleContext):
        pass

    # Exit a parse tree produced by xAdvParser#automatonStyle.
    def exitAutomatonStyle(self, ctx:xAdvParser.AutomatonStyleContext):
        pass



del xAdvParser