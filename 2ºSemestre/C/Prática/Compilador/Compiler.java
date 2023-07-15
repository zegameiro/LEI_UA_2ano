import java.util.HashMap;
import java.util.Map;
import org.stringtemplate.v4.*;

@SuppressWarnings("CheckReturnValue")
public class Compiler extends FractionsCalculatorBaseVisitor<ST> {

   STGroup templates = new STGroupFile("templates.stg");
   Map<String, Boolean> variables = new HashMap<String, Boolean>();
   ST output;

   @Override public ST visitProgram(FractionsCalculatorParser.ProgramContext ctx) {
      output = templates.getInstanceOf("program");

      visitChildren(ctx);

      System.out.println(output.render());

      return null
   }

   @Override public ST visitInstruction(FractionsCalculatorParser.InstructionContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitPrint(FractionsCalculatorParser.PrintContext ctx) {
      ST res = visit(ctx.expr());
      ST print = templates.getInstanceOf("print_expr");

      print.add("expr", res);

      output.add("instructions", print);

      return null;
   }

   @Override public ST visitAssign(FractionsCalculatorParser.AssignContext ctx) {
      ST res = visit(ctx.expr());
      String name = ctx.ID().getText();
      ST var_decl, var_set;

      if( variables.get(name) == null) {
         variables.put(name, true);

         var_decl = templates.getInstanceOf("var_declaration");
         var_decl.add("name",name);
         output.add("variables",var_decl);
      }

      var_set = templates.getInstanceOf("var_set");
      var_set.add("name",name);
      var_set.add("expr",res);
      output.add("instructions",var_set);

      return null
      
   }

   @Override public ST visitExprAddSub(FractionsCalculatorParser.ExprAddSubContext ctx) {
      ST a = visit(ctx.expr(0));
      ST b = visit(ctx.expr(1));
      ST bin_op;

      if(ctx.op.getText().equals("+")) {
         bin_op = templates.getInstanceOf("sum");
      } else {
         bin_op = templates.getInstanceOf("sub");
      }
   }

   @Override public ST visitLiteralExpr(FractionsCalculatorParser.ExprParentContext ctx) {
      ST res = templates.getInstanceOf("literal");
      String fraction[] = ctx.LITERAL().getText().split("/");

      if(fraction.length == 2)
         res.add(fraction[0], fraction[1]);
      else
         res.add(fraction[0], "1");
   }

   @Override public ST visitExprParent(FractionsCalculatorParser.ExprParentContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitExprUnary(FractionsCalculatorParser.ExprUnaryContext ctx) {
      ST res = visit(ctx.expr());
      ST minus;

      if(ctx.op.getText().equals("-")) {
         minus = templates.getInstanceOf("minus");
         minus.add("a",res);
         return minus;
      }

      return res;
   }

   @Override public ST visitPowerExpr(FractionsCalculatorParser.ExprMultDivContext ctx) {
      ST base = visit(ctx.expr(0));
      ST exp = visit(ctx.expr(1));
      ST power = templates.getInstanceOf("power");

      power.add("base",base);
      power.add("exp",exp);

      return power;
   }

   @Override public ST visitExprInt(FractionsCalculatorParser.ExprIntContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprMultDiv(FractionsCalculatorParser.ExprMultDivContext ctx) {
      ST a = visit(ctx.expr(0));
      ST b = visit(ctx.expr(1));
      ST bin_op;

      if(ctx.op.getText().equals("*")) {
         bin_op = templates.getInstanceOf("mult");
      } else {
         bin_op = templates.getInstanceOf("div");
      }
   }
   

   @Override public ST visitExprId(FractionsCalculatorParser.ExprIdContext ctx) {
      ST res = templates.getInstanceOf("variable");
      res.add("name",ctx.ID().getText());

      return res;
   }

   @Override public ST visitReduceExpr(FractionsCalculatorParser.ReduceExprContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }
}
