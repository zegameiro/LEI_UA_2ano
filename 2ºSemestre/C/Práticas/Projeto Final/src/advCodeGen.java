import org.stringtemplate.v4.*;
import java.util.HashMap;
import java.util.HashSet;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.LinkedList;
import java.util.List;

import javax.smartcardio.TerminalFactory;

import org.antlr.v4.runtime.tree.*;

@SuppressWarnings("CheckReturnValue")
public class advCodeGen extends advBaseVisitor<ST> {

   // load templates from python.stg
   private STGroup templates = new STGroupFile("python.stg");
   // ParseTreeProperty to store on each node of the tree the variables that they use
   private ParseTreeProperty<LinkedList<String>> decl = new ParseTreeProperty();

   // Corresponding variable in source code to variables in compiled code
   private HashMap<String, String> var = new HashMap();
   // Number of variables used in compiled code
   private int numVar = 0;

   // Function to get new variable for compiled code 
   private String newVar() {
      return "v" + numVar++;
   }

   // Function to get corresponding variable in compiled code
   private String getVar(String v) {
      if (var.containsKey(v))
         return var.get(v);
      return "";
   }

   // Function to set variable the source variable to compiled variable in var HashMap
   private void setVar(String v1, String v2) {
      var.put(v1, v2);
   }

   // Current Element that is being changed by propreties
   private String curElementProp = "";

   // Current view that is being define
   private String curView;

   // Set for variables declared on viewport , since this ones generate diferent code
   private HashSet<String> newVarViewport = new HashSet<>();

   // Current grid being defined 
   private String curGrid = "";

   // If we are on viewport definition since some variable on viewport generate diferent different code
   private boolean onViewport = false;

   // Entry point visit all stats 
   // Alphabet definition doest need to be visited since it doesnt generate code
   @Override
   public ST visitProgram(advParser.ProgramContext ctx) {
      ST res = templates.getInstanceOf("module");
      for (advParser.StatContext c : ctx.stat()) {
         ST st = visit(c);
         if (st != null)
            res.add("stat", st.render());
      }
      return res;
   }

   // Generate import stat
   @Override
   public ST visitImportstat(advParser.ImportstatContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST imp=templates.getInstanceOf("importS");
      imp.add("file",ctx.ID().getText());
      res.add("stat", imp.render());
      return res;
   }

   // Generate Automaton
   // Get a variable , visit children , put the variables that the children use on automaton
   @Override
   public ST visitAutomatonNFADef(advParser.AutomatonNFADefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST ass = templates.getInstanceOf("assign");
      ST aut = templates.getInstanceOf("automaton");
      aut.add("name", ctx.ID().getText());

      String var = newVar();
      setVar(ctx.ID().getText(), var);
      ass.add("var", var);

      for (advParser.StateDefContext c : ctx.stateDef()) {
         res.add("stat", visit(c).render());
         for (String s : decl.get(c))
            aut.add("state", s);
      }

      for (advParser.AutomatonStatContext c : ctx.automatonStat()) {
         res.add("stat", visit(c).render());
      }

      res.add("stat", visit(ctx.transitionDef()).render());
      for (String s : decl.get(ctx.transitionDef()))
         aut.add("transition", s);

      ass.add("value", aut.render());
      res.add("stat", ass.render());
      return res;
   }

   // Generate Automaton
   // Get a variable , visit children , put the variables that the children use on automaton
   // Same as last , it may seem bad code but this helps in the semantic part 
   @Override
   public ST visitAutomatonDFADef(advParser.AutomatonDFADefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST ass = templates.getInstanceOf("assign");
      ST aut = templates.getInstanceOf("automaton");
      aut.add("name", ctx.ID().getText());

      String var = newVar();
      setVar(ctx.ID().getText(), var);
      ass.add("var", var);

      for (advParser.StateDefContext c : ctx.stateDef()) {
         res.add("stat", visit(c).render());
         for (String s : decl.get(c))
            aut.add("state", s);
      }

      for (advParser.AutomatonStatContext c : ctx.automatonStat()) {
         res.add("stat", visit(c).render());
      }

      res.add("stat", visit(ctx.transitionDef()).render());
      for (String s : decl.get(ctx.transitionDef()))
         aut.add("transition", s);

      ass.add("value", aut.render());
      res.add("stat", ass.render());
      return res;
   }

   // Generate for 
   // Get variable for the loop , visit children
   @Override
   public ST visitAutomatonFor(advParser.AutomatonForContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST forV = templates.getInstanceOf("forIn");

      res.add("stat", visit(ctx.expr()).render());

      String loopVar = newVar();
      setVar(ctx.ID().getText(), loopVar);
      forV.add("var", loopVar);
      forV.add("list", decl.get(ctx.expr()).get(0));

      for (advParser.AutomatonStatContext c : ctx.automatonStat())
         forV.add("stat", visit(c).render());

      res.add("stat", forV.render());
      return res;
   }

   // Generate While
   // Set variable of expr as the condition in while , visit children , recalculate expr at the end of the loop
   @Override
   public ST visitAutomatonWhile(advParser.AutomatonWhileContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST wh = templates.getInstanceOf("while");
      String expr = visit(ctx.expr()).render();
      res.add("stat", expr);
      wh.add("expr", decl.get(ctx.expr()).get(0));

      for (advParser.AutomatonStatContext c : ctx.automatonStat())
         wh.add("stat", visit(c).render());

      wh.add("stat", expr);
      res.add("stat", wh.render());
      return res;
   }

   // Generate If
   // Set variable of expr as the condition , visit children , if else exist visit else
   @Override
   public ST visitAutomatonIf(advParser.AutomatonIfContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST xif = templates.getInstanceOf("if");
      res.add("stat", visit(ctx.expr()).render());
      xif.add("expr", decl.get(ctx.expr()).get(0));
      for (advParser.AutomatonStatContext c : ctx.automatonStat())
         xif.add("stat", visit(c).render());

      res.add("stat", xif.render());
      if (ctx.automatonElse() != null)
         res.add("stat", visit(ctx.automatonElse()).render());

      return res;
   }

   // Generate Else
   // visit children 
   @Override
   public ST visitAutomatonElse(advParser.AutomatonElseContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST el = templates.getInstanceOf("else");
      for (advParser.AutomatonStatContext c : ctx.automatonStat())
         el.add("stat", visit(c).render());

      res.add("stat", el.render());
      return res;
   }

   // Generate states
   // For each state
   //    Get new var , add to list of used variables by this node , make State
   @Override
   public ST visitStateDef(advParser.StateDefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      LinkedList<String> states = new LinkedList<>();
      for (TerminalNode c : ctx.ID()) {
         ST ass = templates.getInstanceOf("assign");
         ST state = templates.getInstanceOf("state");
         String var = newVar();
         ass.add("var", var);
         setVar(c.getText(), var);
         states.add(var);
         state.add("label", c.getText());
         ass.add("value", state.render());
         res.add("stat", ass.render());
      }
      decl.put(ctx, states);
      return res;
   }

   // Generate setting propreties of element
   // Get variable associated with ID and set curElementProp to that , visit children
   @Override
   public ST visitPropertiesDef(advParser.PropertiesDefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      String var = getVar(ctx.ID().getText());
      for (advParser.PropertyElementContext c : ctx.propertyElement()) {
         curElementProp = var;
         res.add("stat", visit(c).render());
      }
      return res;
   }

   // Generate setting propreties of element
   // get curElementProp , set the key , get the corresponding value
   @Override
   public ST visitPropertyElement(advParser.PropertyElementContext ctx) {
      ST res = templates.getInstanceOf("setP");

      res.add("var", curElementProp);
      res.add("prop", ctx.propertiesKeys().prop.getText());

      if (ctx.ID().size() != 0) {
         String r = "'";
         for (TerminalNode n : ctx.ID())
            r += n.getText() + " ";
         r += "'";
         res.add("value", r);
      } else
         res.add("value", ctx.Number().getText());

      return res;
   }

   // Generate transiton definition
   // visit children
   @Override
   public ST visitTransitionDef(advParser.TransitionDefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      LinkedList<String> l = new LinkedList<>();
      for (advParser.TransitionElementContext c : ctx.transitionElement()) {
         res.add("stat", visit(c).render());
         l.add(decl.get(c).get(0));
      }
      decl.put(ctx, l);

      return res;
   }

   // Generate transition definition
   // Get new var to make transition , add symbols to transition , get variable of the states
   @Override
   public ST visitTransitionElement(advParser.TransitionElementContext ctx) {
      ST res = templates.getInstanceOf("assign");
      ST transition = templates.getInstanceOf("transition");
      String var = newVar();
      LinkedList l = new LinkedList<String>();
      l.add(var);
      decl.put(ctx, l);
      res.add("var", var);
      for (TerminalNode n : ctx.SYMBOL())
         transition.add("label", n.getText().replace("'", ""));
      String startSt = getVar(ctx.ID(0).getText());
      String endSt = getVar(ctx.ID(1).getText());
      transition.add("stateStart", startSt);
      transition.add("stateEnd", endSt);
      res.add("value", transition.render());
      return res;
   }

   // Generate view
   // Get new var , get var associated with automaton , visit children
   @Override
   public ST visitViewDef(advParser.ViewDefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST ass = templates.getInstanceOf("assign");
      ST view = templates.getInstanceOf("view");

      String var = newVar();
      setVar(ctx.ID(0).getText(), var);
      ass.add("var", var);
      view.add("name", ctx.ID(0).getText());
      view.add("automaton", getVar(ctx.ID(1).getText()));
      ass.add("value", view.render());
      res.add("stat", ass.render());

      curView = var;

      for (advParser.ViewStatContext c : ctx.viewStat()) {
         ST s = visit(c);
         if (s != null)
            res.add("stat", s.render());
      }

      return res;
   }

   // Generate for 
   // Same as last
   @Override
   public ST visitViewFor(advParser.ViewForContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST forV = templates.getInstanceOf("forIn");

      res.add("stat", visit(ctx.expr()));

      String loopVar = newVar();
      setVar(ctx.ID().getText(), loopVar);
      forV.add("var", loopVar);
      forV.add("list", decl.get(ctx.expr()).get(0));

      for (advParser.ViewStatContext c : ctx.viewStat())
         forV.add("stat", visit(c).render());

      res.add("stat", forV.render());
      return res;
   }

   // Generate while 
   // Same as last
   @Override
   public ST visitViewWhile(advParser.ViewWhileContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST wh = templates.getInstanceOf("while");
      String expr = visit(ctx.expr()).render();
      res.add("stat", expr);
      wh.add("expr", decl.get(ctx.expr()).get(0));

      for (advParser.ViewStatContext c : ctx.viewStat())
         wh.add("stat", visit(c).render());

      wh.add("stat", expr);
      res.add("stat", wh.render());
      return res;
   }

   // Generate if 
   // Same as last
   @Override
   public ST visitViewIf(advParser.ViewIfContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST xif = templates.getInstanceOf("if");
      res.add("stat", visit(ctx.expr()).render());
      xif.add("expr", decl.get(ctx.expr()).get(0));
      for (advParser.ViewStatContext c : ctx.viewStat())
         xif.add("stat", visit(c).render());

      res.add("stat", xif.render());
      if (ctx.viewElse() != null)
         res.add("stat", visit(ctx.viewElse()).render());
      return res;
   }

   // Generate else 
   // Same as last
   @Override
   public ST visitViewElse(advParser.ViewElseContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST el = templates.getInstanceOf("else");
      for (advParser.ViewStatContext c : ctx.viewStat())
         el.add("stat", visit(c).render());

      res.add("stat", el.render());
      return res;
   }

   // Generate update
   // return the String Update to signal to loop
   @Override
   public ST visitPath(advParser.PathContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat","Update");
      return res;
   }

   // Generate for , Generate update
   // Almost same as last one , if if the visit of stats is equal to Update genrate update
   @Override
   public ST visitViewportFor(advParser.ViewportForContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST forV = templates.getInstanceOf("forIn");

      res.add("stat", visit(ctx.expr()));

      String loopVar = newVar();
      setVar(ctx.ID().getText(), loopVar);
      newVarViewport.add(ctx.ID().getText());
      forV.add("var", loopVar);
      forV.add("list", decl.get(ctx.expr()).get(0));

      for (advParser.ViewportStatContext c : ctx.viewportStat()){ 
         String ret= visit(c).render();
         if (ret.equals("Update")){
            ST path=templates.getInstanceOf("path");
            path.add("loopVar",loopVar);
            forV.add("stat",path.render());
         }
         else
            forV.add("stat",ret);
      }
      res.add("stat", forV.render());
      return res;
   }

   // Generate while
   // Same as last one
   @Override
   public ST visitViewportWhile(advParser.ViewportWhileContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST wh = templates.getInstanceOf("while");
      String expr = visit(ctx.expr()).render();
      res.add("stat", expr);
      wh.add("expr", decl.get(ctx.expr()).get(0));

      for (advParser.ViewportStatContext c : ctx.viewportStat())
         wh.add("stat", visit(c).render());

      wh.add("stat", expr);
      res.add("stat", wh.render());
      return res;
   }

   // Generate if
   // Same as last one
   @Override
   public ST visitViewportIf(advParser.ViewportIfContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST xif = templates.getInstanceOf("if");
      res.add("stat", visit(ctx.expr()).render());
      xif.add("expr", decl.get(ctx.expr()).get(0));
      for (advParser.ViewportStatContext c : ctx.viewportStat())
         xif.add("stat", visit(c).render());

      res.add("stat", xif.render());
      if (ctx.viewportElse() != null)
         res.add("stat", visit(ctx.viewportElse()).render());
      return res;
   }

   // Generate else
   // Same as last one
   @Override
   public ST visitViewportElse(advParser.ViewportElseContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST el = templates.getInstanceOf("else");
      for (advParser.ViewportStatContext c : ctx.viewportStat())
         el.add("stat", visit(c).render());

      res.add("stat", el.render());
      return res;
   }

   // Generate adding points to transition
   // Get transition ( needs a template because its not the var but a intrisic value of automaton )
   // Add each point ( involves getting the point visit(c) )
   // for each point
   //    add key value pair
   // cant visit PropertyElementContext bcs we want to use a diferent template
   // maybe a new rule in grammar ?
   @Override
   public ST visitTransitionRedefine(advParser.TransitionRedefineContext ctx) {
      ST res = templates.getInstanceOf("stats");

      String transition = visit(ctx.transition()).render();

      for (advParser.TransitionPointContext c : ctx.transitionPoint()) {
         ST r = visit(c);
         if (r != null)
            res.add("stat", r.render());
         ST add = templates.getInstanceOf("add");
         add.add("var", transition);
         add.add("prop", "point");
         add.add("value", decl.get(c));
         for (advParser.PropertyElementContext d : c.propertyElement()) {
            String prop = d.propertiesKeys().prop.getText() + "=";
            if (d.ID().size() != 0) {
               prop += "'";
               for (TerminalNode n : d.ID())
                  prop += n.getText() + " ";
               prop += "'";
            } else
               prop += d.Number().getText();
            add.add("value", prop);
         }
         res.add("stat", add.render());
      }
      return res;
   }

   // generate point for transition
   // get variable associated with expr copy value to node
   @Override
   public ST visitTransitionPoint(advParser.TransitionPointContext ctx) {
      ST res = templates.getInstanceOf("stats");

      String re = visit(ctx.expr()).render();
      res.add("stat", re);

      LinkedList<String> l = decl.get(ctx.expr());
      decl.put(ctx, l);

      return (re.length() == 0) ? null : res;
   }

   // Generate label alter for transition
   // set curElementProp to transition
   @Override
   public ST visitTransitionLabelAlter(advParser.TransitionLabelAlterContext ctx) {
      ST res = templates.getInstanceOf("stats");

      String transition = visit(ctx.transition()).render();
      curElementProp = transition;

      for (advParser.PropertyElementContext c : ctx.propertyElement()) {
         res.add("stat", visit(c).render());
      }

      LinkedList<String> l = new LinkedList<>();
      l.add(transition);
      decl.put(ctx, l);

      return res;
   }


   // Generate label alter for transition
   // Just a wrapper to put comma correctly
   @Override
   public ST visitTransitionLabelAlterWithComma(advParser.TransitionLabelAlterWithCommaContext ctx) {
      ST res = visit(ctx.transitionLabelAlter());
      decl.put(ctx, decl.get(ctx.transitionLabelAlter()));
      return res;
   }


   // Generate code to the get instrisic transition
   // use the template 
   @Override
   public ST visitTransition(advParser.TransitionContext ctx) {
      ST res = templates.getInstanceOf("get");

      res.add("var", curView);
      res.add("prop", "transition");
      res.add("value", "'" + ctx.ID(0).getText() + "'");
      res.add("value", "'" + ctx.ID(1).getText() + "'");

      return res;
   }

   // Generate placement
   // visit children
   @Override
   public ST visitPlaceDef(advParser.PlaceDefContext ctx) {
      ST res = templates.getInstanceOf("stats");

      for (advParser.PlaceElementContext c : ctx.placeElement()) {
         res.add("stat", visit(c).render());
      }

      return res;
   }

   // Generate placement
   // get expr , get state 
   @Override
   public ST visitIDplaceElement(advParser.IDplaceElementContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST set = templates.getInstanceOf("setP");
      ST state = templates.getInstanceOf("get");

      String r = visit(ctx.expr()).render();
      if (r.length() != 0)
         res.add("stat", r);

      state.add("var", curView);
      state.add("prop", "state");
      state.add("value", "'" + ctx.ID().getText() + "'");

      set.add("var", state.render());
      set.add("prop", "pos");
      set.add("value", decl.get(ctx.expr()));

      res.add("stat", set.render());

      return res;
   }

   // Generate placement
   // get transition , get expr
   @Override
   public ST visitTransitionplaceElement(advParser.TransitionplaceElementContext ctx) {
      ST res = templates.getInstanceOf("stats");

      ST set = templates.getInstanceOf("setP");

      res.add("stat", visit(ctx.transitionLabelAlter()).render());
      String r = visit(ctx.expr()).render();
      if (r.length() != 0)
         res.add("stat", r);
      
      set.add("var", decl.get(ctx.transitionLabelAlter()));
      set.add("prop", "pos");
      set.add("value", decl.get(ctx.expr()));

      res.add("stat", set.render());

      return res;
   }

   // Generate grid
   // get var , visit expr , set curGrid to var , visit children
   @Override
   public ST visitGridDef(advParser.GridDefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST ass = templates.getInstanceOf("assign");
      ST set = templates.getInstanceOf("setP");

      String var = newVar();
      curGrid = var;
      setVar(ctx.ID().getText(), var);
      ass.add("var", var);

      ST grid = templates.getInstanceOf("grid");
      res.add("stat",visit(ctx.expr()).render());
      grid.add("widthheigth", decl.get(ctx.expr()));
      grid.add("name","'"+ctx.ID().getText()+"'");
      ass.add("value", grid.render());

      res.add("stat", ass.render());

      set.add("var",curView);
      set.add("prop","grid");
      set.add("value",var);

      res.add("stat",set.render());

      curGrid = var;
      for (advParser.GridOptionsContext c : ctx.gridOptions()) {
         res.add("stat", visit(c).render());
      }

      return res;
   }

   // Generate grid potions
   // change prop of current grid , similar to PropertyElement
   @Override
   public ST visitGridOptions(advParser.GridOptionsContext ctx) {
      ST res = templates.getInstanceOf("setP");

      res.add("var", curGrid);
      res.add("prop", ctx.gridProperties().prop.getText());

      if (ctx.ID().size() != 0)
         for (TerminalNode n : ctx.ID())
            res.add("value", "'" + n.getText() + "'");
      else
         res.add("value", ctx.Number().getText());

      return res;
   }

   // Generate Animation
   // get car , visit viewport defs , assign viewports to animation  , visit viewport on
   @Override
   public ST visitAnimationDef(advParser.AnimationDefContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST ass = templates.getInstanceOf("assign");
      ST anim = templates.getInstanceOf("animation");

      String var = newVar();
      setVar(ctx.ID().getText(), var);
      ass.add("var", var);

      for (advParser.ViewportDefContext vp : ctx.viewportDef()) {
         res.add("stat", visit(vp).render());
         anim.add("viewport", decl.get(vp).get(0));
      }

      ass.add("value", anim.render());
      res.add("stat", ass.render());

      for (advParser.ViewportOnContext vp : ctx.viewportOn()) {
         res.add("stat", visit(vp).render());
         ST addAnim = templates.getInstanceOf("add");
         addAnim.add("var", var);
         addAnim.add("value", decl.get(vp).get(0));
         res.add("stat", addAnim.render());
      }

      return res;
   }

   // Generate viewport 
   // get new var , get exprs
   @Override
   public ST visitViewportDef(advParser.ViewportDefContext ctx) {
      ST ass = templates.getInstanceOf("assign");
      ST viewP = templates.getInstanceOf("viewPort");
      ST res = templates.getInstanceOf("stats");

      String var = newVar();
      setVar(ctx.ID(0).getText(), var);
      ass.add("var", var);
      viewP.add("view", getVar(ctx.ID(1).getText()));
      viewP.add("cornerBottom", ctx.expr(0).getText());
      viewP.add("cornerTop", ctx.expr(1).getText());
      ass.add("value", viewP.render());
      res.add("stat", ass.render());

      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      return res;
   }

   // Generate viewport animfunc
   // change onViewport , get new var , visit children 
   @Override
   public ST visitViewportOn(advParser.ViewportOnContext ctx) {
      onViewport = true;
      newVarViewport = new HashSet<>();
      ST viewP = templates.getInstanceOf("viewportInstructions");

      String var = newVar();
      setVar(ctx.ID().getText(), var);

      viewP.add("name", var);

      for (advParser.ViewportStatContext vp : ctx.viewportStat()) {
         viewP.add("instruction", visit(vp).render());
      }

      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);
      onViewport = false;
      return viewP;
   }

   // Generate viewport instruction for show of something 
   // Kinda bad code no time to refactor :( ( should have used more visitors )
   // See if transition or id 
   // if transition , get transition 
   // if id , get id ( depending if it was declared on viewportstats as diferent template )
   // for each propreties
   //    get the propretie key value and set value of id to that 
   @Override
   public ST visitCompound(advParser.CompoundContext ctx) {
      ST res = templates.getInstanceOf("stats");

      for (advParser.ViewportInstructionsShowElementContext c : ctx.viewportInstructionsShowElement()) {
         if (c.transition() != null) {
            ST show = templates.getInstanceOf("show");
            ST get = templates.getInstanceOf("get");

            get.add("var", "view");
            get.add("prop", "transition");
            get.add("value", "'" + c.transition().ID(0).getText() + "'");
            get.add("value", "'" + c.transition().ID(1).getText() + "'");
            show.add("element", get.render());
            res.add("stat", show.render());
         } else {
            ST get = templates.getInstanceOf("get");
            ST show = templates.getInstanceOf("show");

            get.add("var", "view");

            if(!newVarViewport.contains(c.ID().getText()))
               get.add("value", "'" + c.ID().getText() + "'");
            else
               get.add("value",getVar( c.ID().getText() ));

            for (advParser.PropertyElementContext d : c.propertyElement()) {
               ST set = templates.getInstanceOf("setP");
               set.add("var", get.render());
               String prop = d.propertiesKeys().prop.getText();
               set.add("prop", prop);
               String value = "";
               if (d.ID().size() != 0) {
                  value += "'";
                  for (TerminalNode n : d.ID())
                     value += n.getText() + " ";
                  value += "'";
               } else
                  value += d.Number().getText();
               set.add("value", value);
               res.add("stat", set.render());
            }

            show.add("element", get.render());
            res.add("stat", show.render());
         }
      }
      return res;
   }

   // Generate show pause
   // Check which one make corresponding template
   @Override
   public ST visitSimple(advParser.SimpleContext ctx) {
      ST res = templates.getInstanceOf("stats");

      if (ctx.op.getText().equals("pause")) {
         ST pause = templates.getInstanceOf("pause");
         res.add("stat", pause.render());
      } else {
         ST show = templates.getInstanceOf("show");
         res.add("stat", show.render());
      }

      return res;
   }

   // Generate play
   // get var of animation
   @Override
   public ST visitPlayDef(advParser.PlayDefContext ctx) {
      if(getVar(ctx.ID().getText()).equals(""))
         return null;
      ST res = templates.getInstanceOf("play");
      res.add("animation",getVar(ctx.ID().getText()));
      return res;
   }

   // Doesnt generate anything :)
   // Python doesnt require decl
   // visit assigns 
   @Override
   public ST visitDecl(advParser.DeclContext ctx) {

      if (ctx.ID().size() != 0) {
         return null;
      }

      ST res = templates.getInstanceOf("stats");

      LinkedList<String> l = new LinkedList<>();

      for (advParser.AssignContext c : ctx.assign()) {
         res.add("stat", visit(c).render());
         l.add(decl.get(c).get(0));
      }

      decl.put(ctx, l);

      return res;
   }


   // Doesnt generate anything
   // wrapper for decl and assign operations
   @Override
   public ST visitAlgebricOP(advParser.AlgebricOPContext ctx) {
      ST res = null;

      LinkedList<String> l = new LinkedList<>();

      if (ctx.decl() != null) {
         res = visit(ctx.decl());
         l = decl.get(ctx.decl());
      }

      if (ctx.assign() != null) {
         res = visit(ctx.assign());
         l = decl.get(ctx.assign());
      }

      decl.put(ctx, l);
      return res;
   }

   // Generate mult div
   // visit children , get var to store result , put var in the node , get var of children , do operation
   @Override
   public ST visitMultDivExpr(advParser.MultDivExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST binOP = templates.getInstanceOf("binaryOperation");

      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      binOP.add("var", var);
      binOP.add("op", ctx.op.getText());

      binOP.add("e1", decl.get(ctx.expr(0)));
      binOP.add("e2", decl.get(ctx.expr(1)));

      res.add("stat", binOP.render());
      return res;
   }

   // Generate and 
   // Same as multdiv 
   @Override
   public ST visitAndExpr(advParser.AndExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST binOP = templates.getInstanceOf("binaryOperation");

      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      binOP.add("var", var);
      binOP.add("op", "&");

      binOP.add("e1", decl.get(ctx.expr(0)));
      binOP.add("e2", decl.get(ctx.expr(1)));

      res.add("stat", binOP.render());
      return res;
   }

   // Generate getting value of id
   // if intrisic value being used in viewport , get correct template
   // else use getVar
   @Override
   public ST visitIDExpr(advParser.IDExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      LinkedList<String> l = new LinkedList<>();
      if(onViewport && !newVarViewport.contains(ctx.ID().getText())){
            ST get = templates.getInstanceOf("get");
            get.add("var","view");
            get.add("value","'"+ctx.ID().getText()+"'");
            l.add(get.render());
      }
      else
         l.add(getVar(ctx.ID().getText()));
      decl.put(ctx, l);
      return res;
   }

   // Doesnt generate anything
   // get variable of point
   @Override
   public ST visitPointExpr(advParser.PointExprContext ctx) {
      ST res = visit(ctx.point());

      decl.put(ctx, decl.get(ctx.point()));

      return res;
   }

   // Generate unray operation
   // visit child , get new var, get var of children , apply operation
   @Override
   public ST visitUnaryExpr(advParser.UnaryExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST unary = templates.getInstanceOf("unaryOperation");

      res.add("stat", visit(ctx.expr()));

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      unary.add("var", var);
      unary.add("op", ctx.op.getText());
      unary.add("value", decl.get(ctx.expr()));

      res.add("stat", unary.render());

      return res;
   }

   // Doesnt generate anything
   // Grammar handles this
   @Override
   public ST visitParanthesisExpr(advParser.ParanthesisExprContext ctx) {
      ST res = visit(ctx.expr());
      decl.put(ctx, decl.get(ctx.expr()));
      return res;
   }

   // Generate or 
   // Same as multdiv 
   @Override
   public ST visitOrExpr(advParser.OrExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST binOP = templates.getInstanceOf("binaryOperation");

      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      binOP.add("var", var);
      binOP.add("op", "|");

      binOP.add("e1", decl.get(ctx.expr(0)));
      binOP.add("e2", decl.get(ctx.expr(1)));

      res.add("stat", binOP.render());
      return res;
   }

   // Generate equals 
   // Same as multdiv 
   @Override
   public ST visitEqualsExpr(advParser.EqualsExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST binOP = templates.getInstanceOf("binaryOperation");

      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      binOP.add("var", var);
      binOP.add("op", ctx.op.getText());

      binOP.add("e1", decl.get(ctx.expr(0)));
      binOP.add("e2", decl.get(ctx.expr(1)));

      res.add("stat", binOP.render());
      return res;
   }

   // Generate getting pos of state
   // get new var , use template 
   @Override
   public ST visitParanthesisIDExpr(advParser.ParanthesisIDExprContext ctx) {
      ST res = templates.getInstanceOf("assign");

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      res.add("var", var);

      ST g1 = templates.getInstanceOf("get");
      ST g2 = templates.getInstanceOf("get");

      g2.add("var", curView);
      g2.add("prop", "state");
      g2.add("value", "'" + ctx.ID().getText() + "'");
      g1.add("var", g2.render());
      g1.add("prop", "pos");

      res.add("value", g1.render());

      return res;
   }

   // Generate number
   // get var , assign number to var
   @Override
   public ST visitNumberExpr(advParser.NumberExprContext ctx) {
      ST res = templates.getInstanceOf("assign");

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      res.add("var", var);
      res.add("value", ctx.Number().getText());

      return res;
   }

   // Generate input
   // get var , assign input to var
   @Override
   public ST visitReadExpr(advParser.ReadExprContext ctx) {
      ST res = templates.getInstanceOf("assign");
      ST in = templates.getInstanceOf("read");

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      res.add("var", var);
      in.add("msg", ctx.STRING().getText());
      res.add("value", in.render());

      return res;
   }

   // Generate compare 
   // Same as multdiv 
   @Override
   public ST visitCompareExpr(advParser.CompareExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST binOP = templates.getInstanceOf("binaryOperation");

      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      binOP.add("var", var);
      binOP.add("op", ctx.op.getText());

      binOP.add("e1", decl.get(ctx.expr(0)));
      binOP.add("e2", decl.get(ctx.expr(1)));

      res.add("stat", binOP.render());
      return res;
   }

   // Generate not
   // same as unary
   @Override
   public ST visitNotExpr(advParser.NotExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST unary = templates.getInstanceOf("unaryOperation");

      res.add("stat", visit(ctx.expr()));

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      unary.add("var", var);
      unary.add("op", "not");
      unary.add("value", decl.get(ctx.expr()));

      res.add("stat", unary.render());

      return res;
   }

   // Generate list
   // get value of list , assign new var to value
   @Override
   public ST visitListExpr(advParser.ListExprContext ctx) {
      ST res = templates.getInstanceOf("assign");

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      res.add("var", var);
      res.add("value", visit(ctx.list()).render());

      return res;
   }

   // Generate add sub 
   // Same as multdiv 
   @Override
   public ST visitAddSubExpr(advParser.AddSubExprContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST binOP = templates.getInstanceOf("binaryOperation");

      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      binOP.add("var", var);
      binOP.add("op", ctx.op.getText());

      binOP.add("e1", decl.get(ctx.expr(0)));
      binOP.add("e2", decl.get(ctx.expr(1)));

      res.add("stat", binOP.render());
      return res;
   }

   // Generate assign 
   // visit child , if var not declared get new var , else get var , assign var to value of expr
   @Override
   public ST visitAssign(advParser.AssignContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST ass = templates.getInstanceOf("assign");
      res.add("stat",visit(ctx.expr()).render());

      String var = "";
      if(getVar(ctx.ID().getText()).equals("")){
         var = newVar();
         LinkedList<String> l = new LinkedList<>();
         l.add(var);
         decl.put(ctx, l);
         setVar(ctx.ID().getText(), var);
         newVarViewport.add(ctx.ID().getText());
      }
      else{
         var = getVar(ctx.ID().getText());
      }

      if(onViewport)
         newVarViewport.add(ctx.ID().getText());

      ass.add("var",var);
      ass.add("value",decl.get(ctx.expr()));
      res.add("stat",ass.render());
      return res;
   }

   // Generate List
   // for each ID
   // if intrisic value being used in viewport , get correct template
   // else getVar
   @Override
   public ST visitList(advParser.ListContext ctx) {
      ST res = templates.getInstanceOf("array");

      for (TerminalNode c : ctx.ID()) {
         if(onViewport && !newVarViewport.contains(c.getText()))
            res.add("elem", "'"+c.getText()+"'" );
         else
            res.add("elem", getVar(c.getText()));
      }

      return res;
   }

   // Doesnt generate anything
   // Wrapper for pointPol pointRect
   @Override
   public ST visitPoint(advParser.PointContext ctx) {
      ST res = visitChildren(ctx);

      if (ctx.pointPol() != null)
         decl.put(ctx, decl.get(ctx.pointPol()));

      if (ctx.pointRect() != null)
         decl.put(ctx, decl.get(ctx.pointRect()));

      return res;
   }

   // Generate point
   // visit children , assign to x and y correct expr , get new var to sotre result
   @Override
   public ST visitPointRect(advParser.PointRectContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST point = templates.getInstanceOf("point");

      for (advParser.ExprContext c : ctx.expr())
         res.add("stat", visit(c).render());

      point.add("x", decl.get(ctx.expr(0)).get(0));
      point.add("y", decl.get(ctx.expr(1)).get(0));

      ST ass = templates.getInstanceOf("assign");

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      ass.add("var", var);
      ass.add("value", point.render());

      res.add("stat", ass.render());
      return res;
   }

   // Generate point
   // visit children , assign to x and y correct expr , do math , get new var to sotre result
   @Override
   public ST visitPointPol(advParser.PointPolContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ST point = templates.getInstanceOf("point");

      for (advParser.ExprContext c : ctx.expr())
         res.add("stat", visit(c).render());

      String degree = decl.get(ctx.expr(0)).get(0);
      String r = decl.get(ctx.expr(1)).get(0);
      point.add("x", r + "*cos(radians(" + degree + "))");
      point.add("y", r + "*sin(radians(" + degree + "))");

      ST ass = templates.getInstanceOf("assign");

      String var = newVar();
      LinkedList<String> l = new LinkedList<>();
      l.add(var);
      decl.put(ctx, l);

      ass.add("var", var);
      ass.add("value", point.render());

      res.add("stat", ass.render());

      return res;
   }

}
