from antlr4 import *
from xAdvParser import xAdvParser
from xAdvVisitor import xAdvVisitor

class xAdvInterpreter(xAdvVisitor):

   colors = {
            'black': (0, 0, 0),
            'white': (255, 255, 255),
            'red': (0, 0, 255),
            'green': (0, 255, 0),
            'cyan': (255, 255, 0),
            'magenta': (255, 0, 255),
            'yellow': (0, 255, 255),
            'gray': (128, 128, 128),
            'light gray': (192, 192, 192),
            'dark gray': (64, 64, 64),
            'maroon': (0, 0, 128),
            'olive': (0, 128, 128),
            'navy': (128, 0, 0),
            'purple': (128, 0, 128),
            'teal': (128, 128, 0),
            'aqua': (255, 255, 0),
            'lime': (0, 255, 0),
            'fuchsia': (255, 0, 255),
            'silver': (192, 192, 192),
            'olive drab': (35, 142, 107),
            'sky blue': (235, 206, 135),
            'salmon': (128, 148, 114),
            'indigo': (130, 0, 75),
            'coral': (80, 127, 255),
            'lavender': (250, 230, 230),
            'goldenrod': (32, 165, 218),
            'turquoise': (208, 224, 64),
            'tomato': (71, 99, 255),
            'orchid': (214, 112, 218),
            'slate blue': (205, 90, 106),
            'chocolate': (30, 105, 210),
            'dark olive green': (47, 107, 85),
            'medium purple': (219, 112, 147),
            'light coral': (128, 128, 240),
            'dark slate gray': (79, 128, 128)
        }
   
   positions=["centered", "left", "right", "above", "below", "left_above", "left_below", "right_above", "right_below"]

   def visitProgram(self, ctx:xAdvParser.ProgramContext):
      dic={"num":len(ctx.definitions())}
      for c in range (len(ctx.definitions())):
         dic[c]=(self.visit(ctx.definitions(c)))
      print(dic)
      return dic

   def visitDefinitions (self, ctx:xAdvParser.DefinitionsContext):
      thisCtx=None
      dic=dict()
      dic.update(self.visit(ctx.automatonStyle()))
      return dic
  
  
   def visitAutomatonStyle(self, ctx:xAdvParser.AutomatonStyleContext):
      dic={"type":"automaton"}
      val=0
      if len(ctx.Value())>len(ctx.AutomatonProperty()):
         dic.update({"ID:":ctx.Value(0).getText()})
         val=val+1
      for c in range (len(ctx.AutomatonProperty())):
         if(ctx.AutomatonProperty(c).getText()=="color" or ctx.AutomatonProperty(c).getText()=="linecolor"):
            if(ctx.Value(val).getText() in self.colors):
               dic.update({ctx.AutomatonProperty(c).getText():ctx.Value(val).getText()})
            else:
               print(ctx.Value(val).getText()," is not a Valid Color")
               break
         if(ctx.AutomatonProperty(c).getText()=="label"):
            if(ctx.Value(val).getText().lower() in self.positions):
               dic.update({ctx.AutomatonProperty(c).getText():ctx.Value(val).getText().upper()})
            else:
               print(ctx.Value(val).getText()," is not a Valid Label position")
               break
         val=val+1   

      return dic


  

