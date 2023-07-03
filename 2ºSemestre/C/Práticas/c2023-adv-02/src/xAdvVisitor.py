# Generated from xAdv.g4 by ANTLR 4.12.0
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .xAdvParser import xAdvParser
else:
    from xAdvParser import xAdvParser

# This class defines a complete generic visitor for a parse tree produced by xAdvParser.

class xAdvVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by xAdvParser#program.
    def visitProgram(self, ctx:xAdvParser.ProgramContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by xAdvParser#definitions.
    def visitDefinitions(self, ctx:xAdvParser.DefinitionsContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by xAdvParser#automatonStyle.
    def visitAutomatonStyle(self, ctx:xAdvParser.AutomatonStyleContext):
        return self.visitChildren(ctx)



del xAdvParser