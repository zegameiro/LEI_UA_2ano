import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

@SuppressWarnings("CheckReturnValue")
public class AdvSemCheck extends advBaseVisitor<Boolean> {
   /* TYPES */   
   protected static final Type NFA_TYPE = new NFAType();
   protected static final Type DFA_TYPE = new DFAType();
   protected static final Type COMPLETE_DFA_TYPE = new CompleteDFAType();
   protected static final Type AUTOMATON_TYPE = new AutomatonType();
   protected static final Type VIEW_TYPE = new ViewType();
   protected static final Type ANIMATION_TYPE = new AnimationType();
   protected static final Type VIEWPORT_TYPE = new ViewportType();
   protected static final Type STATE_TYPE = new StateType();
   protected static final Type STRING_TYPE = new StringType();
   protected static final Type POINT_TYPE = new PointType();
   protected static final Type NUMBER_TYPE = new NumberType();
   protected static final Type BOOLEAN_TYPE = new BooleanType();
   protected static final Type LIST_TYPE = new ListType();
   protected static final Type GRID_TYPE = new GridType();

   // usados para saber se certos elementos foram bem definidos, por exemplo, um automato
   // isto serve para quando por exemplo, se define uma view, ver se o automato foi bem definido
   private List<String> validElements = new ArrayList<>();

   // ParseTreeProperty usada para passar os simbolos de alphabetElement para alphabetDef,
   // é capaz de ser boa ideia mudar isto, está ineficiente (mas funciona)
   private ParseTreeProperty<ArrayList<Character>> alphabetValues = new ParseTreeProperty<>();
   private ParseTreeProperty<String> valuesToString = new ParseTreeProperty<>();
   // alphabetChars vai ter os carateres do alfabeto
   private ArrayList<Character> alphabetChars;
   private SymbolTable globalSymbolTable = new SymbolTable(null);
   private SymbolTable currentSymbolTable = globalSymbolTable;
   private int numInitialStates = 0;
   private int numAcceptingStates = 0;
   // type do automato a ser definido (preciso disto nas transições por causa das condições DFA/NFA,etc)
   private Type currAutomatonType;     
   // para cada automato, ter a lista das transições
   private Map <String, Transitions> automatonsTransitions = new HashMap<>();
   // estados dos automatos
   private Map<String, AutomatonContainer> automatonStates = new HashMap<>();
   // que state está a ser usado agora (para guardar numero de initial/accepting)
   private String currentStateString;
   // estados dentro da lista (usado por causa de definir propriedades no AutomatonFor, e garantir que só ha 1 initial e pelo menos 1 acceptingState)
   private List<String> loopStates = new ArrayList<>();
   // que automato é que está a ser usado agora
   private String currentAutomatonString;
   // view a ser definida agora (usado para guardar as grids desta view)
   private String currentViewString;
   // view -> automato
   private Map <String, String> viewAutomaton = new HashMap<>();
   // List para garantir que variaveis nao inicializadas não podem ser usadas
   private List<String> declared_not_initialized = new ArrayList<>();
   // mapa view -> grid
   private Map<String, String> viewGrids = new HashMap<>();


   @Override public Boolean visitProgram(advParser.ProgramContext ctx) {
      Boolean res = null;
      visitChildren(ctx);
      if (declared_not_initialized.size() > 0) {
         String not_initialized = String.join(", ", declared_not_initialized);
         ErrorHandling.printWarning(String.format("Variable(s) '%s' declared but weren't initialized and were never used.", not_initialized));
      }
      return res;
   }

   @Override public Boolean visitStat(advParser.StatContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
   }

   @Override public Boolean visitImportstat(advParser.ImportstatContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   /* Verificar se há simbolos repetidos no alfabeto */
   @Override public Boolean visitAlphabetDef(advParser.AlphabetDefContext ctx) {
      Boolean res = true;
      ArrayList<Character> alphabetChars = new ArrayList<>(), currentChars;
      for (int i = 0; i < ctx.alphabetElement().size(); i++)
      {
         if (visit(ctx.alphabetElement(i))) {
            currentChars = alphabetValues.get(ctx.alphabetElement(i));
            for (int j = 0; j < currentChars.size(); j++) {
               if (alphabetChars.contains(currentChars.get(j))) {
                  ErrorHandling.printError(ctx, String.format("Duplicate symbol in alphabet -  \"%s\"", currentChars.get(j)));
                  
               } else
                  alphabetChars.add(currentChars.get(j));
            }
         }
      }
      this.alphabetChars = alphabetChars;
      return res;
   }

   /* Só verifica se, no caso de ser uma lista, o primeiro elemento vem antes do 2º em termos de ASCII */
   @Override public Boolean visitAlphabetElement(advParser.AlphabetElementContext ctx) {
      Boolean res = true;
      char first, second;
      Character[] tempCharArray;
      ArrayList<Character> temp;
      if (ctx.SYMBOL().size() == 2) {     // se for do tipo 'SYMBOL' - 'SYMBOL'
         first = ctx.SYMBOL(0).getText().charAt(1);      // o charAt(0) é ', por isso é preciso meter charAt(1)
         second = ctx.SYMBOL(1).getText().charAt(1);
         if (first > second) 
         {

            
            res = false;
         } else {
            // guardar todos os carateres
            tempCharArray = new Character[second - first + 1];
            for (int i = 0; i < second - first + 1; i++)
            {
               tempCharArray[i] = (char) (first + i);
            }
            alphabetValues.put(ctx, new ArrayList<Character>(Arrays.asList(tempCharArray)));
         }
      } else {       // se for do tipo SYMBOL 
         alphabetValues.put(ctx, new ArrayList<Character>(Arrays.asList(ctx.SYMBOL(0).getText().charAt(1))));
      }
      return res;
   }

   @Override public Boolean visitAutomatonDef(advParser.AutomatonDefContext ctx) {
      Boolean res = true;
      if (ctx.automatonDFADef() != null) {
         if (!visit(ctx.automatonDFADef())) {
            ErrorHandling.printError(ctx,"Couldn't define automaton. Variable name taken.");
            res = false;
         }
      } else if (ctx.automatonNFADef() != null) {
         if (!visit(ctx.automatonNFADef())) {
            ErrorHandling.printError(ctx,"Couldn't define automaton. Variable name taken.");
            res = false;
         }
      }
      if (ErrorHandling.errorCount() == 0) {
         validElements.add(currentAutomatonString);
      }
      return res;
   }

   @Override public Boolean visitAutomatonNFADef(advParser.AutomatonNFADefContext ctx) {
      Boolean res = true;
      numInitialStates = 0;
      numAcceptingStates = 0;
      int i;
      Type automatonType = NFA_TYPE;
      currAutomatonType = NFA_TYPE;
      String automaton_symbolName = ctx.ID().getText();
      // Verificar se automato com este ID já foi definido, se foi, então houve erro
      if (globalSymbolTable.containsSymbol(automaton_symbolName)) {
         
         res = false;
      } else {
         globalSymbolTable.putSymbol(automaton_symbolName, new Symbol(automatonType));
         automatonStates.put(automaton_symbolName, new AutomatonContainer()); // guarda os estados de cada automato
      }
      // Agora, verificar stateDefs
      currentSymbolTable = new SymbolTable(globalSymbolTable);
      automatonsTransitions.put(automaton_symbolName, new Transitions());
      currentAutomatonString = automaton_symbolName;
      // Visitar o stateDef, que vai verificar se há simbolos repetidos e po-los 
      // na currentSymbolTable
      for (i = 0; i < ctx.stateDef().size(); i++)
      {
         visit(ctx.stateDef(i));
      }

      for (i = 0; i < ctx.automatonStat().size(); i++)
      {
         visit(ctx.automatonStat(i));

      }
      // Adicionar objeto transitions a automatonsTransitions para este automato antes de visitar transitionDef
      
      
      visit(ctx.transitionDef());
      AutomatonContainer states = automatonStates.get(currentAutomatonString);
      if (states.AcceptingStatesCount() == 0) {
         ErrorHandling.printError(ctx,"Automaton should have at least 1 accepting state.");
         
      }
      if (states.InitialStatesCount() != 1) {
         ErrorHandling.printError(ctx,"Automaton should have one and only one initial state.");
         
      }

      return res;
   }

   @Override public Boolean visitAutomatonDFADef(advParser.AutomatonDFADefContext ctx) {
      Boolean res = true;
      numInitialStates = 0;
      numAcceptingStates = 0;
      int i;
      Type automatonType;
      if (ctx.complete != null) {
         automatonType = COMPLETE_DFA_TYPE;
         currAutomatonType = COMPLETE_DFA_TYPE;
      } else {
         automatonType = DFA_TYPE;
         currAutomatonType = DFA_TYPE;
      }

      String automaton_symbolName = ctx.ID().getText();
      // Verificar se automato com este ID já foi definido, se foi, então houve erro
      if (globalSymbolTable.containsSymbol(automaton_symbolName)) {
         
         res = false;
      } else {
         globalSymbolTable.putSymbol(automaton_symbolName, new Symbol(automatonType));
         automatonStates.put(automaton_symbolName, new AutomatonContainer()); // guarda os estados de cada automato
      }
      // Agora, verificar stateDefs
      currentSymbolTable = new SymbolTable(globalSymbolTable);
      // Adicionar objeto transitions a automatonsTransitions para este automato antes de visitar transitionDef
      automatonsTransitions.put(automaton_symbolName, new Transitions());
      currentAutomatonString = automaton_symbolName;

      // Visitar o stateDef, que vai verificar se há simbolos repetidos e po-los 
      // na currentSymbolTable
      for (i = 0; i < ctx.stateDef().size(); i++)
      {
         visit(ctx.stateDef(i));
      }

      for (i = 0; i < ctx.automatonStat().size(); i++)
      {
         visit(ctx.automatonStat(i));

      }
      // Adicionar objeto transitions a automatonsTransitions para este automato antes de visitar transitionDef
      automatonsTransitions.put(automaton_symbolName, new Transitions());
      currentAutomatonString = automaton_symbolName;

      visit(ctx.transitionDef());
      AutomatonContainer aut_container = automatonStates.get(currentAutomatonString);
      if (aut_container.AcceptingStatesCount() == 0) {
         ErrorHandling.printError(ctx,"Automaton should have at least 1 accepting state.");
         
      }

      if (aut_container.InitialStatesCount() != 1) {
         ErrorHandling.printError(ctx,"Automaton should have one and only one initial state.");
         
      }

      return res;
   }

   @Override public Boolean visitAutomatonStat(advParser.AutomatonStatContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitAutomatonFor(advParser.AutomatonForContext ctx) {
      Boolean res = null;
      // O ID do for apenas existe enquanto o for existir
      String forVarID = ctx.ID().getText();
      String[] statesInLoopExpr;
      currentSymbolTable.putSymbol(forVarID, new Symbol(STATE_TYPE));
      visit(ctx.expr());
      // Verificar se o expr devolve uma lista
      String typeExpr = valuesToString.get(ctx.expr());
      if (typeExpr != null && !typeExpr.equals("list"))    // expressão inválida para o loop
      {
         ErrorHandling.printError(ctx, String.format("Invalid type of expression in AutomatonFor. Correct use -> for [id] in [list]"));
         
      } else {       // loop valido
         /*
         statesInLoopExpr = ctx.expr().getText().replace("{", "").replace("}", "").split(",");
         for (String curr_state_from_loopExpr : statesInLoopExpr) {
            loopStates.add(curr_state_from_loopExpr);
         }
         */
         visitChildren(ctx);           // so verificar as expressões dentro do loop se for um loop valido
      }
      currentSymbolTable.removeSymbol(forVarID);
      loopStates.clear();
      currentStateString = "";

      return res;
   }

   @Override public Boolean visitAutomatonWhile(advParser.AutomatonWhileContext ctx) {
      Boolean res =  visit(ctx.expr()); // true se for booleana
      if (!res) ErrorHandling.printError(ctx,"Invalid boolean expression in the \"while\" statement inside the automaton definition.");
      for (int i = 0; i < ctx.automatonStat().size(); i++) {
         visit(ctx.automatonStat(i));
      }
      
      return res;
   }

   @Override public Boolean visitAutomatonIf(advParser.AutomatonIfContext ctx) {
      Boolean res =  visit(ctx.expr()); // true se for booleana
      if (!res) ErrorHandling.printError(ctx,"Invalid boolean expression in the \"if\" statement inside the automaton definition.");
      for (int i = 0; i < ctx.automatonStat().size(); i++) {
         visit(ctx.automatonStat(i));
      }
      
      return res;
   }

   @Override public Boolean visitAutomatonElse(advParser.AutomatonElseContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitStateDef(advParser.StateDefContext ctx) {
      Boolean res = true;
      String currState;
      AutomatonContainer states = automatonStates.get(currentAutomatonString);
      for (int i = 0; i < ctx.ID().size(); i++)
      {
         currState = ctx.ID(i).getText();
         if (currentSymbolTable.containsSymbol(currState)) {
            res = false;
            
            ErrorHandling.printError(ctx,String.format("Variable name taken -  \"%s\"", currState));
         } else {
            currentSymbolTable.putSymbol(currState, new Symbol(STATE_TYPE));
            states.addState(currState);
         }
      }
      return res;
   }


   @Override public Boolean visitPropertiesDef(advParser.PropertiesDefContext ctx) {
      Boolean res = null;
      String stateID;
      String propertyKey;
      Symbol stateSymbol;
      if (loopStates.size() != 0) {    // no caso de haver uma definição de propriedade dentro de um loop. ver testing_files/CDFAError.txt linha 9 para referência
         for (String curr_state_in_loop : loopStates) {
            stateID = curr_state_in_loop;
            currentStateString = stateID;
            stateSymbol = currentSymbolTable.findSymbol(stateID);
            if (stateSymbol == null) {
               ErrorHandling.printError(ctx,String.format("Can't define property for state \"%s\". Symbol not found.", stateID));
               
            } else {
               if (stateSymbol.type() != STATE_TYPE) {
                  ErrorHandling.printError(ctx,String.format("Can't define property for symbol \"%s\". Symbol of invalid type '%s'. Must be a state.", stateID, stateSymbol.type().name()));
                  
               } else {
                  for (int i = 0; i < ctx.propertyElement().size(); i++) {
                     visit(ctx.propertyElement(i));
                     propertyKey = valuesToString.get(ctx.propertyElement(i));
                     if (!propertyKey.equals("initial") && !propertyKey.equals("accepting"))
                     {
                        
                        ErrorHandling.printError(ctx,String.format("Invalid property key for state \"%s\", must be either 'accepting' or 'initial'.", stateID));
                     }
                  }
               }
            }
         }
      } else {       // no caso de não ser dentro de um loop
         stateID = ctx.ID().getText();
         currentStateString = stateID;
         stateSymbol = currentSymbolTable.findSymbol(stateID);
         if (stateSymbol == null) {
            ErrorHandling.printError(ctx,String.format("Can't define property for state \"%s\". Symbol not found.", stateID));
            
         } else {
            if (stateSymbol.type() != STATE_TYPE) {
               ErrorHandling.printError(ctx,String.format("Can't define property for symbol \"%s\". Symbol of invalid type '%s'. Must be a state.", stateID, stateSymbol.type().name()));
               
            } else {
               for (int i = 0; i < ctx.propertyElement().size(); i++) {
                  visit(ctx.propertyElement(i));
                  propertyKey = valuesToString.get(ctx.propertyElement(i));
                  if (!propertyKey.equals("initial") && !propertyKey.equals("accepting"))
                  {
                     
                     ErrorHandling.printError(ctx,String.format("Invalid property key for state \"%s\", must be either 'accepting' or 'initial'.", stateID));
                  }
               }
            }
         }
      }
      
      
      return res;
   }

   @Override public Boolean visitPropertyElement(advParser.PropertyElementContext ctx) {
      Boolean res = true;
      String propertyKey = ctx.propertiesKeys().getText();
      String propertyValue = "";
      // para ir contando o numero de initial/accepting
      AutomatonContainer states = automatonStates.get(currentAutomatonString);
      if (ctx.Number() == null) {
         for (int i = 0; i < ctx.ID().size(); i++)
         { 
            propertyValue = propertyValue + ctx.ID(i).getText() + " ";
         }
      } else {
         propertyValue = propertyValue + ctx.Number().getText();
      }
      propertyValue = propertyValue.strip();
      if (propertyKey.equals("initial"))  // true ou false
      {
         if (!propertyValue.equals("true") && !propertyValue.equals("false")) {
            ErrorHandling.printError(ctx,"Invalid property value for \"" + propertyKey + "\" as propertyKey");
            
         } else {
            if (!currentStateString.isEmpty()) {
               if (propertyValue.equals("true")) {
                  states.addProperty(currentStateString, propertyKey);
               } else {
                  states.removeProperty(currentStateString, propertyKey);
               }
            }
         }
         valuesToString.put(ctx, "initial");
      } else if (propertyKey.equals("accepting")) {
         if (!propertyValue.equals("true") && !propertyValue.equals("false")) {
            ErrorHandling.printError(ctx,String.format("Invalid property value for \"%s\" as propertyKey - \"%s\"", propertyKey, propertyValue));
            
         } else {
            if (!currentStateString.isEmpty()) {
               if (propertyValue.equals("true")) {
                  states.addProperty(currentStateString, propertyKey);
               } else {
                  states.removeProperty(currentStateString, propertyKey);
               }
            }
         }
         valuesToString.put(ctx, "accepting");

      } else if (propertyKey.equals("align")) {
         if (!validAlignProperty(propertyValue)) {
            ErrorHandling.printError(ctx,String.format("Invalid property value for \"%s\" as propertyKey - \"%s\"", propertyKey, propertyValue));
            
         } 
         valuesToString.put(ctx, "align");
      } else if (propertyKey.equals("slope")) {
         // Tem de ser um valor numerico (mas não sei se inclui 0)
         if (!propertyValue.matches("[0-9]+")) {
            ErrorHandling.printError(ctx,String.format("Invalid property value for \"%s\" as propertyKey - \"%s\"", propertyKey, propertyValue));
            
         } 
         valuesToString.put(ctx, "slope");

      } else if (propertyKey.equals("highlighted")) {
         if (!propertyValue.equals("true") && !propertyValue.equals("false")) {
            ErrorHandling.printError(ctx,String.format("Invalid property value for \"%s\" as propertyKey - \"%s\"", propertyKey, propertyValue));
            
         } 
         valuesToString.put(ctx, "highlighted");
      }
      return res;
   }

   @Override public Boolean visitTransitionDef(advParser.TransitionDefContext ctx) {
      Boolean res = null;

      for (int i = 0; i < ctx.transitionElement().size(); i++)
      {
         visit(ctx.transitionElement(i));
      }
      // Depois de adicionar todas as transições definidas, verificar se cumprem condições do type
      Transitions transitions = automatonsTransitions.get(currentAutomatonString);
      if (currAutomatonType == DFA_TYPE) {
         if (!transitions.isDFAValid())
         {
            
            ErrorHandling.printError(ctx,"Invalid DFA Transitions - Either multiple transitions for a symbol, used a empty string for transition or have duplicate transitions.");
         }

      } else if (currAutomatonType == NFA_TYPE) {
         if (!transitions.isNFAValid())
         {
            
            ErrorHandling.printError(ctx,"Invalid NFA Transitions - Duplicate transitions are not allowed.");
         }

      } else if (currAutomatonType == COMPLETE_DFA_TYPE) {
         if (!transitions.isCompleteDFAValid(alphabetChars.size()))
         {
            
            ErrorHandling.printError(ctx,"Invalid Complete DFA Transitions - Either multiple transitions for a symbol, used a empty string for transition or have duplicate transitions.");
         }
      }
      
      return res;
   }

   @Override public Boolean visitTransitionElement(advParser.TransitionElementContext ctx) {
      Boolean res = null;
      Transitions transitions = automatonsTransitions.get(currentAutomatonString);

      String fromId = ctx.ID(0).getText();
      String toId = ctx.ID(1).getText();
      Character curr_alph_symbol;
      Symbol fromSymbol, toSymbol;
      fromSymbol = currentSymbolTable.findSymbol(fromId);
      toSymbol = currentSymbolTable.findSymbol(toId);
      // Verificar se os IDs da transição são estados existentes
      if (fromSymbol == null || fromSymbol.type() != STATE_TYPE)
      {
         ErrorHandling.printError(ctx,"Invalid \"from\" symbol in transitions definition: " + fromId);
         
      }
      if (toSymbol == null || toSymbol.type() != STATE_TYPE)
      {
         ErrorHandling.printError(ctx,"Invalid \"to\" symbol in transitions definition: " + toId);
         
      }
      // Verificar se os símbolos que causam a transição estão no alfabeto
      for (int i = 0; i < ctx.SYMBOL().size(); i++) {
         curr_alph_symbol = ctx.SYMBOL(i).getText().charAt(1);
         if (curr_alph_symbol == '\'') {
            if (!transitions.addTransition(fromId, toId, curr_alph_symbol)) {
               ErrorHandling.printError(ctx,String.format("Duplicate transition: %s -> '' -> %s.", fromId, toId));
            }
         } else if (!alphabetChars.contains(curr_alph_symbol)) {
            ErrorHandling.printError(ctx,String.format("\"%s\" not found in alphabet.", curr_alph_symbol));
         } else {
            if (!transitions.addTransition(fromId, toId, curr_alph_symbol)) {
               ErrorHandling.printError(ctx,String.format("Duplicate transition: %s -> '%c' -> %s.", fromId, curr_alph_symbol, toId));
            }
         }  
      }
      
      return res;
   }

   @Override public Boolean visitViewDef(advParser.ViewDefContext ctx) {
      Boolean res = true;
      String viewID = ctx.ID(0).getText();
      String automatonID = ctx.ID(1).getText();
      Symbol automatonSymbol = globalSymbolTable.findSymbol(automatonID);
      AutomatonContainer currentStates;
      // Verificar se há algum simbolo com o ID definido para esta view
      if (globalSymbolTable.containsSymbol(viewID)) {
         ErrorHandling.printError(ctx,String.format("Invalid ID for view -  Already taken."));
         
         res = false;
      } else {
         // Verificar se ID de automato dado existe e está associado a um automato
         if (automatonSymbol == null) {
            ErrorHandling.printError(ctx, String.format("Automaton ID not found - \"%s\"", automatonID));
            currentAutomatonString = "";
            
            res = false;
            
         } else if (!automatonSymbol.type().subtype(AUTOMATON_TYPE)) {
            ErrorHandling.printError(ctx,String.format("Invalid type for automaton given."));
            currentAutomatonString = "";
            res = false;
         } else {
            // se houve algum erro durante a definição de automato, não irá correr a análise da definição desta view
            if (!validElements.contains(automatonID)) {  
               return res;
            }
            globalSymbolTable.putSymbol(viewID, new Symbol(VIEW_TYPE));
            // novo scope para a view
            currentSymbolTable = new SymbolTable(globalSymbolTable);
            currentStates = automatonStates.get(automatonID);
            currentViewString = viewID;
            // meter estados do automato sobre o qual a view foi definida na symbol table para serem usados livremente
            for (String state : currentStates.getStates()) {
               currentSymbolTable.putSymbol(state, new Symbol(STATE_TYPE));
            }
            currentAutomatonString = automatonID;
            viewAutomaton.put(viewID, automatonID);
            visitChildren(ctx);
            // se depois de visitar todos os filhos não houverem erros, é um validElement
            if (ErrorHandling.errorCount() == 0) {
               validElements.add(viewID);
            }
         }
      }
      return res;
   }

   @Override public Boolean visitViewStat(advParser.ViewStatContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitViewFor(advParser.ViewForContext ctx) {
      Boolean res = null;

      // O ID do for apenas existe enquanto o for existir
      String forVarID = ctx.ID().getText();
      currentSymbolTable.putSymbol(forVarID, new Symbol(STATE_TYPE));
      visit(ctx.expr());
      // Verificar se o expr devolve uma lista
      String typeExpr = valuesToString.get(ctx.expr());
      if (!typeExpr.equals("list"))
      {
         ErrorHandling.printError(ctx,"Invalid type of expression in viewFor. Correct use -> for [id] in [list]");
         
      }
      // Depois disto, chamar os automatonStats
      if (ctx.viewStat().size() == 1)
      {
         visit(ctx.viewStat(0));
      } else
         for (int i = 0; i < ctx.viewStat().size(); i++) {
            visit(ctx.viewStat(i));
      }
      currentSymbolTable.removeSymbol(forVarID);
      loopStates.clear();
      currentStateString = "";
      return res;
   }

   @Override public Boolean visitViewWhile(advParser.ViewWhileContext ctx) {
      Boolean res =  visit(ctx.expr()); // true se for booleana
      if (!res) {
         ErrorHandling.printError(ctx,"Invalid boolean expression in the \"while\" statement inside the view definition.");
         
      } 
      for (int i = 0; i < ctx.viewStat().size(); i++) {
         visit(ctx.viewStat(i));
      }
      
      return res;
   }

   @Override public Boolean visitViewIf(advParser.ViewIfContext ctx) {
      Boolean res =  visit(ctx.expr()); // true se for booleana
      if (!res) {
         ErrorHandling.printError(ctx,"Invalid boolean expression in the \"if\" statement inside the view definition.");
         
      }
         for (int i = 0; i < ctx.viewStat().size(); i++) {
         visit(ctx.viewStat(i));
      }
      
      return res;
   }

   @Override public Boolean visitViewElse(advParser.ViewElseContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitTransitionRedefine(advParser.TransitionRedefineContext ctx) {
      Boolean res = true;
      String transitionText = ctx.transition().getText();
      if (!visit(ctx.transition())) {
         ErrorHandling.printError(ctx,String.format("Invalid transition '%s' on transition redefinition statement. It doesn't exist for automaton \"%s\".", transitionText, currentAutomatonString));
         
         res = false;
      } else {
         for (int i = 0; i < ctx.transitionPoint().size() ; i++) {
            if (!visit(ctx.transitionPoint(i)))
               res = false;
         }
      }
      
      return res;
   }

   @Override public Boolean visitTransitionPoint(advParser.TransitionPointContext ctx) {
      Boolean res = true;
      visit(ctx.expr());
      String exprValue, propertyKey;
      exprValue = valuesToString.get(ctx.expr());
      if (exprValue == null || !exprValue.equals("point")) {
         
         ErrorHandling.printError(ctx,String.format("Invalid point in transition redefinition. Must be of 'point' type."));
         res = false;
      }
      if (ctx.propertyElement() != null)
         for (int i = 0; i < ctx.propertyElement().size() ; i++) {
            visit(ctx.propertyElement(i));
            propertyKey = valuesToString.get(ctx.propertyElement(i));
            if (!propertyKey.equals("slope")) {
               
               ErrorHandling.printError(ctx,String.format("Invalid property key \"%s\" for show statement. Must be 'slope'.", propertyKey));
               res = false;
            }
         }
      return res;
   }

   @Override public Boolean visitTransitionLabelAlter(advParser.TransitionLabelAlterContext ctx) {
      Boolean res = true;
      String transitionText = ctx.transition().getText();
      if (!visit(ctx.transition())) {
         ErrorHandling.printError(ctx,String.format("Invalid transition '%s' on transition label placement. It doesn't exist for automaton \"%s\".", transitionText, currentAutomatonString));
         
         res = false;
      }
      if (ctx.propertyElement() != null)
      {
         for (int i = 0; i < ctx.propertyElement().size(); i++) {
            visit(ctx.propertyElement(i));
            if (!valuesToString.get(ctx.propertyElement(i)).equals("align"))
            {
               
               ErrorHandling.printError(ctx,String.format("Invalid property key for transition \"%s\", must be 'align'.", transitionText));
               res = false;
            }
         }
         
      }
      return res;
   }

   @Override public Boolean visitTransitionLabelAlterWithComma(advParser.TransitionLabelAlterWithCommaContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitTransition(advParser.TransitionContext ctx) {
      Boolean res = false;
      String fromID = ctx.ID(0).getText();
      String toID = ctx.ID(1).getText();
      Transitions current_aut_transitions = automatonsTransitions.get(currentAutomatonString);
      if (current_aut_transitions.containsTransition(fromID, toID))
         res = true;
      return res;
   }

   @Override public Boolean visitPlaceDef(advParser.PlaceDefContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitIDplaceElement(advParser.IDplaceElementContext ctx) {
      Boolean res = null;
      // Verificar que ID existe e é um state
      String exprType;

      String stateID = ctx.ID().getText();
      Symbol stateSymbol = currentSymbolTable.findSymbol(stateID);
      if (stateSymbol == null || stateSymbol.type() != STATE_TYPE)
      {
         
         ErrorHandling.printError(ctx,String.format("Invalid state \"%s\" on the 'place' statement inside the view definition.", stateID));
      }

      visit(ctx.expr());
      // quando eu visito points/numbers/strings, meto-los na 
      // valuesToString, para saber que type visitei
      exprType = valuesToString.get(ctx.expr());
      if (exprType != null && !exprType.equals("point"))
      {
         
         ErrorHandling.printError(ctx,String.format("Invalid expression while trying to place state \"%s\": Must be a point.", stateID));
      }
   

      return res;
   }

   @Override public Boolean visitTransitionplaceElement(advParser.TransitionplaceElementContext ctx) {
      Boolean res = null;
      String exprType;
      if (visit(ctx.transitionLabelAlter())) {
         visit(ctx.expr());
         exprType = valuesToString.get(ctx.expr());
         if (exprType == null || !exprType.equals("point"))
         {
            
            ErrorHandling.printError(ctx,String.format("Invalid expression while trying to place the label of a state."));
         }
      }
      return res;
   }

   @Override public Boolean visitGridDef(advParser.GridDefContext ctx) {
      Boolean res = null;
      String gridID = ctx.ID().getText();
      String exprType;
      if (currentSymbolTable.findSymbol(gridID) != null)
      {
         ErrorHandling.printError(ctx,String.format("Cannot define grid '%s'. Variable name taken.", gridID));
         
      } else {
         currentSymbolTable.putSymbol(gridID, new Symbol(GRID_TYPE));
         viewGrids.put(currentViewString, gridID);
         visit(ctx.expr());
         exprType = valuesToString.get(ctx.expr());
         if (exprType == null || !exprType.equals("point")) {
            ErrorHandling.printError(ctx,String.format("Cannot define grid '%s'. Expression given \"%s\" is the wrong type, must be a point.", gridID, ctx.expr().getText()));
         }
      }
      for (int i = 0; i < ctx.gridOptions().size(); i++) {
         visit(ctx.gridOptions(i));
      }
      return res;
   }

   @Override public Boolean visitGridOptions(advParser.GridOptionsContext ctx) {
      Boolean res = true;
      String gridProperty = ctx.gridProperties().getText();
      String gridValue = "";
      if (ctx.Number() == null) {
         for (int i = 0; i < ctx.ID().size(); i++)
         { 
            gridValue = gridValue + ctx.ID(i).getText() + " ";
         }
      } else {
         gridValue = gridValue + ctx.Number().getText();
      }
      /*
      if (ctx.ID() != null) {
         for (int i = 0; i < ctx.ID().size(); i++)
         { 
            gridValue = gridValue + ctx.ID(i).getText() + " ";
         }
      } else if (ctx.Number() != null) {
         gridValue = gridValue + ctx.Number().getText();
      }
      */
      gridValue = gridValue.strip();
      if (gridProperty.equals("step")) {
         if (ctx.Number() == null) { // tem de ser um numero
            ErrorHandling.printError(ctx,String.format("Invalid grid property value for \"%s\" as propertyKey - \"%s\". Must be a number.", gridProperty, gridValue));
            
         }
      } else if (gridProperty.equals("margin")) {
         if (ctx.Number() == null) { // tem de ser um numero
            ErrorHandling.printError(ctx,String.format("Invalid grid property value for \"%s\" as propertyKey - \"%s\". Must be a number.", gridProperty, gridValue));
            
         }

      } else if (gridProperty.equals("color")) {
         if (ctx.ID() == null) { // tem de ser um ID+, que identifica a cor
            ErrorHandling.printError(ctx,String.format("Invalid grid property value for \"%s\" as propertyKey - \"%s\". Must be a color.", gridProperty, gridValue));
            
         }
         
      } else if (gridProperty.equals("line")) {
         if (!gridValue.equals("solid") && !gridValue.equals("dotted") && !gridValue.equals("dashed")) { // tem de ser um ID+, que identifica a propriedade
            ErrorHandling.printError(ctx,String.format("Invalid grid property value for \"%s\" as propertyKey - \"%s\". Must be a 'dotted', 'dashed' or 'solid'.", gridProperty, gridValue));
            
         }
         
         
      }

      return res;
   }

   @Override public Boolean visitAnimationDef(advParser.AnimationDefContext ctx) {
      Boolean res = true;
      String animationID = ctx.ID().getText();
      if (globalSymbolTable.containsSymbol(animationID))
      {
         ErrorHandling.printError(ctx,String.format("Variable name taken on animation definition - \"%s\"", animationID));
         
         res = false;
      } else {
         globalSymbolTable.putSymbol(animationID, new Symbol(ANIMATION_TYPE));
         currentSymbolTable = new SymbolTable(globalSymbolTable);
      }
      visitChildren(ctx);
      return res;
   }

   @Override public Boolean visitViewportDef(advParser.ViewportDefContext ctx) {
      Boolean res = true;
      String viewportID = ctx.ID(0).getText();
      String viewID = ctx.ID(1).getText();
      Symbol viewSymbol;
      AutomatonContainer currentStates;
      String exprType1, exprType2;
      if (globalSymbolTable.containsSymbol(viewportID))
      {
         ErrorHandling.printError(ctx,String.format("Variable name taken on viewport definition - \"%s\"", viewportID));
         
      } else {
         if (globalSymbolTable.containsSymbol(viewID)) {
            viewSymbol = globalSymbolTable.findSymbol(viewID);
            if (viewSymbol.type() != VIEW_TYPE) {
               ErrorHandling.printError(ctx,String.format("Wrong type for variable \"%s\" in viewport definition. Must be a view.", viewID));
            } else {
               if (!validElements.contains(viewID)) {
                  res = false;
                  return res;
               }
               currentViewString = viewID;
               visit(ctx.expr(0));
               visit(ctx.expr(1));
               exprType1 = valuesToString.get(ctx.expr(0));
               exprType2 = valuesToString.get(ctx.expr(1));
               if (exprType1 == null || exprType2 == null || !exprType1.equals("point") || !exprType2.equals("point")) {
                  ErrorHandling.printError(ctx,String.format("Viewport definition must include 2 points."));
               } else {
                  currentAutomatonString = viewAutomaton.get(viewID);
                  currentStates = automatonStates.get(currentAutomatonString);
                  for (String state : currentStates.getStates()) {
                     currentSymbolTable.putSymbol(state, new Symbol(STATE_TYPE));
                  }
                  globalSymbolTable.putSymbol(viewportID, new Symbol(VIEWPORT_TYPE));  // só inserir na tabela de simbolos caso não hajam erros na definição
               }
            }
   
         } else {
            ErrorHandling.printError(ctx,String.format("View \"%s\" not found in viewport definition.", viewID));
            res = false;
         }
      }
      return res;
   }

   @Override public Boolean visitViewportOn(advParser.ViewportOnContext ctx) {
      Boolean res = null;
      String viewportID = ctx.ID().getText();
      Symbol viewportSymbol;
      if (globalSymbolTable.containsSymbol(viewportID)) {
         viewportSymbol = globalSymbolTable.findSymbol(viewportID);
         if (viewportSymbol.type() != VIEWPORT_TYPE) {
            ErrorHandling.printError(ctx,String.format("Wrong type for variable \"%s\". Must be a viewport.", viewportID));
            
         } else {
            currentSymbolTable.putSymbol(viewGrids.get(currentViewString), new Symbol(GRID_TYPE));
            return visitChildren(ctx);
         }
      } else {
         ErrorHandling.printError(ctx,String.format("Viewport \"%s\" not found.", viewportID));
         
      }
      return res;
   }

   @Override public Boolean visitViewportStat(advParser.ViewportStatContext ctx) {
      Boolean res = null;

      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitViewportWhile(advParser.ViewportWhileContext ctx) {
      Boolean res =  visit(ctx.expr()); // true se for booleana
      if (!res) {
         ErrorHandling.printError(ctx,"Invalid boolean expression in the \"while\" statement inside the viewport definition.");
         
      } 
      for (int i = 0; i < ctx.viewportStat().size(); i++) {
         visit(ctx.viewportStat(i));
      }
      
      return res;
   }

   @Override public Boolean visitViewportIf(advParser.ViewportIfContext ctx) {
      Boolean res =  visit(ctx.expr()); // true se for booleana
      if (!res) {
         ErrorHandling.printError(ctx,"Invalid boolean expression in the \"if\" statement inside the viewport definition.");
         
      }
         for (int i = 0; i < ctx.viewportStat().size(); i++) {
         visit(ctx.viewportStat(i));
      }
      
      return res;
   }

   @Override public Boolean visitViewportElse(advParser.ViewportElseContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitViewportFor(advParser.ViewportForContext ctx) {
      Boolean res = null;
      // O ID do for apenas existe enquanto o for existir
      String forVarID = ctx.ID().getText();
      // para já, vou assumir que os elementos da lista são states
      currentSymbolTable.putSymbol(forVarID, new Symbol(STATE_TYPE));
      visit(ctx.expr());
      // Verificar se o expr devolve uma lista
      String typeExpr = valuesToString.get(ctx.expr());

      if (!(typeExpr.equals("list") || typeExpr.equals("string")))
      {
         ErrorHandling.printError(ctx,String.format("Invalid type of expression in viewportFor. Correct use -> for [id] in [list/string]"));
         
      }
      // Depois disto, chamar os automatonStats
      if (ctx.viewportStat().size() == 1)
      {
         visit(ctx.viewportStat(0));
      } else
         for (int i = 0; i < ctx.viewportStat().size(); i++) {
            visit(ctx.viewportStat(i));
      }
      currentSymbolTable.removeSymbol(forVarID);
      loopStates.clear();
      currentStateString = "";
      return res;
   }


   @Override public Boolean visitCompound(advParser.CompoundContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitSimple(advParser.SimpleContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitViewportInstructionsShowElement(advParser.ViewportInstructionsShowElementContext ctx) {
      Boolean res = null;
      String id, propertyKey, transitionText;
      Symbol id_Symbol;
      Transitions current_transitions;
      if (ctx.transition() != null) {
         transitionText = ctx.transition().getText();
         if (!visit(ctx.transition())) {
            ErrorHandling.printError(ctx,String.format("Invalid transition '%s' on viewport 'show' instruction. It doesn't exist for automaton \"%s\".", transitionText, currentAutomatonString));
            
         }
      } else {       // ID propertyElement*
         id = ctx.ID().getText();
         id_Symbol = currentSymbolTable.findSymbol(id);
         if (id_Symbol == null)
         {
            ErrorHandling.printError(ctx,String.format("State '%s' not found in the 'show' statement inside viewport.", id));
            
         } else {
            if (id_Symbol.type() != STATE_TYPE && id_Symbol.type() != GRID_TYPE) {
               ErrorHandling.printError(ctx,String.format("Symbol in 'show' clause is of an invalid type. Must be a state, transition or grid.", id));
            } else {
               for (int i = 0; i < ctx.propertyElement().size(); i++) {
               visit(ctx.propertyElement(i));
               propertyKey = valuesToString.get(ctx.propertyElement(i));
               if (!propertyKey.equals("accepting") && !propertyKey.equals("highlighted")) {
                  
                  ErrorHandling.printError(ctx,String.format("Invalid property key for show statement. Must be either 'accepting' or 'highlighted'."));
               }
            }
            }
            
         }
      }


      return res;
   }

   @Override public Boolean visitPlayDef(advParser.PlayDefContext ctx) {
      Boolean res = null;
      String animationID = ctx.ID().getText();
      Symbol animationSymbol = globalSymbolTable.findSymbol(animationID);
      if (globalSymbolTable.containsSymbol(animationID)) {
         if (animationSymbol.type() != ANIMATION_TYPE) {
            ErrorHandling.printError(ctx,String.format("Wrong type for variable \"%s\" in 'play' statement. Must be an animation.", animationID));
            
         }
      } else {
         ErrorHandling.printError(ctx,String.format("Animation \"%s\" not found in 'play' statement.", animationID));
         
      }
      return res;
   }

   @Override public Boolean visitDecl(advParser.DeclContext ctx) {
      Boolean res = null;
      Type var_type = null;
      String curr_id, type_given;
      type_given = ctx.type().getText();
      if (type_given.equals("number"))
         var_type = NUMBER_TYPE;
      else if (type_given.equals("point"))
         var_type = POINT_TYPE;
      else if (type_given.equals("list"))
         var_type = LIST_TYPE;
      else if (type_given.equals("string"))
         var_type = STRING_TYPE;
      else if (type_given.equals("state"))
         var_type = STATE_TYPE;
      else if (type_given.equals("boolean"))
         var_type = BOOLEAN_TYPE;

      // TODO: Testar isto
      // Se houver assignment logo após declaração:
      if (ctx.assign().size() > 0) {
         for (int i = 0; i < ctx.assign().size(); i++) {
            curr_id = ctx.assign(i).ID().getText();
            // verificar se o ID a declarar já existe 
            if (currentSymbolTable.findSymbol(curr_id) == null) {
               declared_not_initialized.add(curr_id);
               currentSymbolTable.putSymbol(curr_id, new Symbol(var_type));
            }
            if (!visit(ctx.assign(i))) {   // retorna false se for um assignment invalido
               ErrorHandling.printError(ctx,String.format("Invalid assignment for variable \"%s\".", curr_id));
               
            }
         }

      } else {    // se não houver assignment logo
         // declarar todos os IDs. exemplo: point p1, p2, p3;
         for (int i = 0; i < ctx.ID().size(); i++) {
            curr_id = ctx.ID(i).getText();
            // verificar se o ID a declarar já existe 
            if (currentSymbolTable.findSymbol(curr_id) == null) {
               declared_not_initialized.add(curr_id);
               currentSymbolTable.putSymbol(curr_id, new Symbol(var_type));
            }
         }
      }
      return res;
   }

   @Override public Boolean visitAlgebricOP(advParser.AlgebricOPContext ctx) {
      Boolean res = null;
      // só fiz isto porque temos expr na gramatica
      if (ctx.decl() != null) {
         visit(ctx.decl());
      } else if (ctx.assign() != null) {
         visit(ctx.assign());
      }
      return res;
   }

   @Override public Boolean visitMultDivExpr(advParser.MultDivExprContext ctx) {
      Boolean res = null;
      visit(ctx.expr(0));     // visita a expressão da esquerda
      visit(ctx.expr(1));     // visita a expressão da direita
      String leftExprType, rightExprType, leftExpr, rightExpr;
      String op = ctx.op.getText();
      leftExprType = valuesToString.get(ctx.expr(0));
      rightExprType = valuesToString.get(ctx.expr(1));
      leftExpr = ctx.expr(0).getText();
      rightExpr = ctx.expr(1).getText();
      if (op.equals('*')) { // multiplicação
         if (validMultiplication(leftExprType, rightExprType)) {
            valuesToString.put(ctx, leftExprType); // leftexprtype ou rightexprtype é a mesma coisa
         } else {
            ErrorHandling.printError(ctx,String.format("Incompatible types in multiplication/division operation. Left expression is '%s' with type '%s' and right is '%s' with type '%s'.", leftExpr, leftExprType, rightExpr, rightExprType));
            
         }
      } else { // divisão e resto
         if (validDivision(leftExprType, rightExprType)) {
            valuesToString.put(ctx, leftExprType); // leftexprtype ou rightexprtype é a mesma coisa
         } else {
            ErrorHandling.printError(ctx,String.format("Incompatible types in multiplication/division operation. Left expression is '%s' with type '%s' and right is '%s' with type '%s'.", leftExpr, leftExprType, rightExpr, rightExprType));
            
         }
      }
      
      return res;
   }

   @Override public Boolean visitAndExpr(advParser.AndExprContext ctx) {
      Boolean res = false;
      Boolean expr0 = visit(ctx.expr(0));
      Boolean expr1 = visit(ctx.expr(1));
      // devolve true se for uma expressão "OR" válida, com 2 expressões booleanas
      if (expr0 != null && expr1 != null && expr0 && expr1) res = true;
      valuesToString.put(ctx, "boolean");
      
      return res;
   }

   @Override public Boolean visitIDExpr(advParser.IDExprContext ctx) {
      Boolean res = null;
      String id = ctx.ID().getText();
      Symbol idSymbol = currentSymbolTable.findSymbol(id);
      if (idSymbol != null)
      {
         if (idSymbol.type() == POINT_TYPE)
            valuesToString.put(ctx, "point");
         else if (idSymbol.type() == NUMBER_TYPE)
            valuesToString.put(ctx, "number");
         else if (idSymbol.type() == STRING_TYPE)
            valuesToString.put(ctx, "string");
         else if (idSymbol.type() == LIST_TYPE)
            valuesToString.put(ctx, "list");
      } else {
         ErrorHandling.printError(ctx, String.format("Symbol \"%s\" not found in current symbol table.", id));
      }
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitPointExpr(advParser.PointExprContext ctx) {
      Boolean res = null;
      valuesToString.put(ctx, "point");
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitUnaryExpr(advParser.UnaryExprContext ctx) {
      Boolean res = null;
      // este nó devolve o mesmo type que o type da expressão que este contém
      visit(ctx.expr());
      String exprType;
      exprType = valuesToString.get(ctx.expr());
      if (exprType != null) {
         valuesToString.put(ctx, exprType);
      }

      return res;
   }

   @Override public Boolean visitParanthesisExpr(advParser.ParanthesisExprContext ctx) {
      /*  
         Há dois casos em que se entra aqui: expressão booleana e expressão aritmetica com parentesis (ex: (10 > 20) ou (1 + 1))
         - se for booleana, vai devolver true/false;
         - se for aritmetica, vai meter no ParseTreeProperty valuesToString, 
         o tipo do valor de dentro, para a analise semantica nos nós pais;

         Para o primeiro caso, basta devolver o valor que visit(ctx.expr()) devolve, pois eu desenhei as expressões booleanas dessa forma,
         basicamente devolvem uma cadeira de true/false's para saber se são expressões booleanas válidas ou não

         No caso de ser aritmetica, uso o ParseTreeProperty
      */
      Boolean res = visit(ctx.expr());
      String exprType;
      // o valor guardado pelo ctx.expr em valuesToString 
      // vai ser passado pelo ctx atual, pois apenas adiciona parentesis à volta
      exprType = valuesToString.get(ctx.expr());
      if (exprType != null) {
         valuesToString.put(ctx, exprType);
      }
      return res;
   }

   @Override public Boolean visitOrExpr(advParser.OrExprContext ctx) {
      Boolean res = false;
      Boolean expr0 = visit(ctx.expr(0));
      Boolean expr1 = visit(ctx.expr(1));
      // devolve true se for uma expressão "OR" válida, com 2 expressões booleanas
      if (expr0 != null && expr1 != null && expr0 && expr1) res = true;
      valuesToString.put(ctx, "boolean");
      return res;
   }

   @Override public Boolean visitEqualsExpr(advParser.EqualsExprContext ctx) {
      Boolean res = false;
      visit(ctx.expr(0));
      visit(ctx.expr(1));
      String leftExpr = valuesToString.get(ctx.expr(0));
      String rightExpr = valuesToString.get(ctx.expr(1));
      // nesta linha verifico se a expr da esquerda e a expr da direita são validas,
      // e caso sejam, vejo se são do mesmo type, que é o que valuesToString tem
      // ou seja, apenas é valido fazer, por exemplo, POINT > POINT, ou NUMBER > NUMBER
      if (leftExpr != null && rightExpr != null && leftExpr.equals(rightExpr)) res = true;
      valuesToString.put(ctx, "boolean");
      return res;
   }

   // (ID) irá representar o ponto em que se encontra um state
   @Override public Boolean visitParanthesisIDExpr(advParser.ParanthesisIDExprContext ctx) {
      Boolean res = null;
      String stateID = ctx.ID().getText();
      Symbol state_symbol = currentSymbolTable.findSymbol(stateID);
      /*if (state_symbol != null && state_symbol.type() == STATE_TYPE) {
         valuesToString.put(ctx, "point");
      }*/
      if (state_symbol != null) {
         if (state_symbol.type() == STATE_TYPE) {
            valuesToString.put(ctx, "point");
         } else if (state_symbol.type() == BOOLEAN_TYPE) {
            res = true;
         } else {
            valuesToString.put(ctx, state_symbol.type().name().toLowerCase());
         }
      }
      return res;
      //return res;
   }

   @Override public Boolean visitNumberExpr(advParser.NumberExprContext ctx) {
      Boolean res = null;
      valuesToString.put(ctx, "number");
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitReadExpr(advParser.ReadExprContext ctx) {
      Boolean res = null;
      valuesToString.put(ctx, "string");
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitCompareExpr(advParser.CompareExprContext ctx) {
      Boolean res = false;
      visit(ctx.expr(0));
      visit(ctx.expr(1));
      String leftExpr = valuesToString.get(ctx.expr(0));
      String rightExpr = valuesToString.get(ctx.expr(1));
      // nesta linha verifico se a expr da esquerda e a expr da direita são validas,
      // e caso sejam, vejo se são do mesmo type, que é o que valuesToString tem
      // ou seja, apenas é valido fazer, por exemplo, POINT > POINT, ou NUMBER > NUMBER
      if (leftExpr != null && rightExpr != null && leftExpr.equals(rightExpr)) res = true;
      valuesToString.put(ctx, "boolean");
      return res;
   }

   // Devolve true se for tiver uma expressão booleana e false se não
   @Override public Boolean visitNotExpr(advParser.NotExprContext ctx) {
      Boolean res = false; // devolve true se for booleana (porque eu decidi assim)
      Boolean expr = visit(ctx.expr());
      if (expr != null && expr) res = true;
      valuesToString.put(ctx, "boolean");
      return res;
   }

   @Override public Boolean visitListExpr(advParser.ListExprContext ctx) {
      Boolean res = null;
      // USADO NO AUTOMATONFOR para verificar se Expr é uma lista
      valuesToString.put(ctx, "list");
      String[] IDs_from_list = ctx.list().getText().replace("{", "").replace("}", "").split(",");
      // verificar se os valores definidos na lista são válidos para o scope atual
      Symbol curr_symbol;
      // ver o type do primeiro elemento da lista. todos têm de ser do mesmo senão é incompativel
      Type values_type = currentSymbolTable.findSymbol(IDs_from_list[0]).type();
      for (String current_id : IDs_from_list) {
         curr_symbol = currentSymbolTable.findSymbol(current_id);
         if (curr_symbol == null) {
            ErrorHandling.printError(ctx, String.format("Symbol \"%s\" not found in current symbol table.", current_id));
            
         } else if (curr_symbol.type() != values_type) {
            ErrorHandling.printError(ctx, String.format("Type \"%s\" invalid for list with type \"%s\".", curr_symbol.type().name(), values_type.name()));
            
         }
      }
      return visitChildren(ctx);
   }

   @Override public Boolean visitAddSubExpr(advParser.AddSubExprContext ctx) {
      Boolean res = null;
      visit(ctx.expr(0));     // visita a expressão da esquerda
      visit(ctx.expr(1));     // visita a expressão da direita

      String leftExprType, rightExprType;
      leftExprType = valuesToString.get(ctx.expr(0));
      rightExprType = valuesToString.get(ctx.expr(1));

      // Se forem do mesmo type, é valido
      if (leftExprType != null && rightExprType != null && leftExprType.equals(rightExprType)) {
         valuesToString.put(ctx, leftExprType); // leftexprtype ou rightexprtype é a mesma coisa
      } else {
         ErrorHandling.printError(ctx,String.format("Incompatible types in addition/subtraction operation. Left expression is '%s' and right is '%s'.", leftExprType, rightExprType));
         
      }
      // remover de valuesToString (para poupar memoria)
      valuesToString.removeFrom(ctx.expr(0));
      valuesToString.removeFrom(ctx.expr(1));
      return res;
   }

   @Override public Boolean visitAssign(advParser.AssignContext ctx) {
      Boolean res = true;
      Boolean exprReturnValue = visit(ctx.expr());
      String exprType, id_to_assign;
      id_to_assign = ctx.ID().getText();
      exprType = valuesToString.get(ctx.expr());
      Symbol id_symbol = currentSymbolTable.findSymbol(id_to_assign);
      if (id_symbol != null && exprType != null) {
         // Verificar se 'expr' é consistente com o type declarado
         if (id_symbol.type() == POINT_TYPE) {
            if (!exprType.equals("point")) {
               ErrorHandling.printError(ctx,String.format("Invalid type '%s' for variable \"%s\". Expected \"%s\".", exprType, id_to_assign, id_symbol.type().name()));
               
               res = false;
            }
         } else if (id_symbol.type() == NUMBER_TYPE) {
            if (!exprType.equals("number")) {
               ErrorHandling.printError(ctx,String.format("Invalid type '%s' for variable \"%s\". Expected \"%s\".", exprType, id_to_assign, id_symbol.type().name()));
               res = false;
               
            }
         } else if (id_symbol.type() == LIST_TYPE) {
            if (!exprType.equals("list")) {
               ErrorHandling.printError(ctx,String.format("Invalid type '%s' for variable \"%s\". Expected \"%s\".", exprType, id_to_assign, id_symbol.type().name()));
               res = false;
               
            }
         } else if (id_symbol.type() == STRING_TYPE) {
            if (!exprType.equals("string")) {
               ErrorHandling.printError(ctx,String.format("Invalid type '%s' for variable \"%s\". Expected \"%s\".", exprType, id_to_assign, id_symbol.type().name()));
               res = false;
               
            }
         } else if (id_symbol.type() == STATE_TYPE) {
            if (!exprType.equals("string")) {
               ErrorHandling.printError(ctx,String.format("Invalid type '%s' for variable \"%s\". Expected \"%s\".", exprType, id_to_assign, id_symbol.type().name()));
               res = false;
               
            }
         } else if (id_symbol.type() == BOOLEAN_TYPE) {
            if (exprReturnValue != null) {   // o returnValue só é true para boolean!! (ver visitCompareExpr, e por exempl visitNumberExpr)
               if (exprReturnValue == true) {
                  if (!exprType.equals("boolean")) {
                     ErrorHandling.printError(ctx,String.format("Invalid boolean expression for variable \"%s\".", id_to_assign));
                     res = false;
                  }
               }
            } else {
               ErrorHandling.printError(ctx,String.format("Invalid type '%s' for variable \"%s\". Expected \"%s\".", exprType, id_to_assign, id_symbol.type().name()));
               res = false;
            }
         } 

      }
      // depois do assign, mesmo que seja inválido, remover dos declared_not_initialized
      if (declared_not_initialized.contains(id_to_assign)) {
            declared_not_initialized.remove(id_to_assign);
      }
      return res;
   }

   @Override public Boolean visitList(advParser.ListContext ctx) {
      Boolean res = null;
      String[] statesInLoopExpr = ctx.getText().replace("{", "").replace("}", "").split(",");
      for (String curr_state_from_loopExpr : statesInLoopExpr) {
         loopStates.add(curr_state_from_loopExpr);
      }
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitPoint(advParser.PointContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   // TODO: fazer isto 
   @Override public Boolean visitPointRect(advParser.PointRectContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   // TODO: fazer isto 
   @Override public Boolean visitPointPol(advParser.PointPolContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitType(advParser.TypeContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitGridProperties(advParser.GridPropertiesContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Boolean visitPropertiesKeys(advParser.PropertiesKeysContext ctx) {
      Boolean res = null;
      return visitChildren(ctx);
      //return res;
   }

   public static boolean validAlignProperty(String property)
   {
      String valueToCheck = property.strip();
      List<String> possibleProperties = new ArrayList<>(Arrays.asList(
         "below",
         "above",
         "right",
         "left",
         "above left",
         "above right",
         "below left",
         "below right"
         ));
      return possibleProperties.contains(valueToCheck);
   }

   // É valido se forem 2 numbers, ou 1 number e um point
   public static boolean validMultiplication(String leftExprType, String rightExprType) {
      boolean res = false;
      if (leftExprType == null || rightExprType == null) {
         return res;
      }
      if (leftExprType.equals("number") && rightExprType.equals("number"))
         res = true;
      else if (leftExprType.equals("number") && rightExprType.equals("point"))
         res = true;
      else if (leftExprType.equals("point") && rightExprType.equals("number"))
         res = true;
      return res;
   }

   // É valido se forem 2 numbers, ou 1 number e um point
   public static boolean validDivision(String leftExprType, String rightExprType) {
      boolean res = false;
      if (leftExprType.equals("number") && rightExprType.equals("number"))
         res = true;
      else if (leftExprType.equals("point") && rightExprType.equals("number"))
         res = true;
      return res;
   }
}
