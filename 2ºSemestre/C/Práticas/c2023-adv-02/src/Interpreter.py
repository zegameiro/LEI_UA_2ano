import os
import sys
from antlr4 import *
from xAdvLexer import xAdvLexer
from xAdvParser import xAdvParser
from xAdvInterpreter import xAdvInterpreter
from io import StringIO

def Interpreter(path):
   style=""
   with open(path+".xadv","r")as file:
      style=file.read();
   print(style)
   visitor0 = xAdvInterpreter()
   input_stream = InputStream(style)
   lexer = xAdvLexer(input_stream)
   stream = CommonTokenStream(lexer)
   parser = xAdvParser(stream)
   tree = parser.program()
   if parser.getNumberOfSyntaxErrors() == 0:
      return visitor0.visit(tree)
   


      