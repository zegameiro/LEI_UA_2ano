grammar adv;

program: alphabetDef stat* EOF
;
stat:(importstat| automatonDef | viewDef | animationDef |playDef);

importstat:'import' ID;

alphabetDef : 'alphabet' '{' (alphabetElement',')*alphabetElement '}' |
'alphabet' ID '-' ID ;

alphabetElement: SYMBOL'-'SYMBOL
               | SYMBOL
;    

automatonDef: automatonNFADef | automatonDFADef;
automatonNFADef: 'NFA' ID '<<<' stateDef+ automatonStat+ transitionDef '>>>';   
automatonDFADef: complete='complete'? 'DFA' ID '<<<' stateDef+ automatonStat+ transitionDef '>>>';   

automatonStat: (automatonFor |automatonIf |automatonWhile |propertiesDef | algebricOP);

automatonFor: 'for' ID 'in' expr automatonStat 
            |'for' ID 'in' expr '<<<' automatonStat+ '>>>';
automatonWhile:'while' expr 'do' automatonStat
            |'while' expr 'do' '<<<'automatonStat+'>>>';
automatonIf:'if' expr 'do' automatonStat automatonElse?
            |'if' expr 'do' '<<<'automatonStat+'>>>' automatonElse?;
automatonElse:'else' automatonStat
            | 'else' '<<<' automatonStat+ '>>>';

stateDef: 'state' (ID',')*ID ';'; 

propertiesDef: ID propertyElement+ ';';         
propertyElement: '[' propertiesKeys '=' (ID+ |Number) ']' ; // This requires more attention

transitionDef:     'transition' (transitionElement',')*transitionElement ';';
transitionElement: ID '->' (SYMBOL',')*SYMBOL '->' ID ;

viewDef: 'view' ID 'of' ID '<<<' (viewStat)* '>>>'; 
viewStat: (algebricOP| viewFor| placeDef| transitionRedefine|transitionLabelAlterWithComma| gridDef|viewIf|viewWhile);
viewFor: 'for' ID 'in' expr viewStat 
       |'for' ID 'in' expr '<<<' viewStat+ '>>>';
viewWhile:'while' expr 'do' viewStat
               |'while' expr 'do' '<<<'viewStat+'>>>'; 
viewIf:'if' expr 'do' viewStat viewElse?
          |'if' expr 'do' '<<<'viewStat+'>>>' viewElse?;
viewElse:'else' viewStat
            | 'else' '<<<' viewStat+ '>>>';

transitionRedefine: transition 'as' transitionPoint '--' (transitionPoint '--')* transitionPoint';';       
transitionPoint: expr propertyElement*;

transitionLabelAlter: transition '#label' propertyElement*;
transitionLabelAlterWithComma: transitionLabelAlter ';';

transition: '<'ID','ID'>' ;

placeDef: 'place' (placeElement',')*placeElement ';';
placeElement: ID 'at' expr #IDplaceElement
            | transitionLabelAlter 'at' expr #transitionplaceElement
;

gridDef: 'grid' ID expr '[' (gridOptions',')*gridOptions ']' ';';
gridOptions: gridProperties '=' (ID+ |Number) ;

animationDef: 'animation' ID '<<<' ( viewportDef | viewportOn )+  '>>>';


viewportDef: 'viewport' ID 'for' ID 'at' expr '--' '++' expr ';';
viewportOn: 'on' ID '<<<' viewportStat+ '>>>';
viewportStat: (propertiesDef| viewportFor| viewportInstructions| algebricOP | viewportIf | viewportWhile | path);

path:  'update' ';';

viewportWhile:'while' expr 'do' viewportStat
               |'while' expr 'do' '<<<'viewportStat+'>>>'; 
viewportIf:'if' expr 'do' viewportStat viewportElse?
          |'if' expr 'do' '<<<'viewportStat+'>>>' viewportElse;
viewportElse:'else' viewportStat
            | 'else' '<<<' viewportStat+ '>>>';
viewportFor: 'for' ID 'in' expr viewportStat 
          |'for' ID 'in' expr '<<<' viewportStat+ '>>>';

viewportInstructions: 'show' (viewportInstructionsShowElement',')*viewportInstructionsShowElement ';' #compound
                    | op=('show'|'pause') ';' #simple
;
viewportInstructionsShowElement:ID propertyElement* | transition; 

playDef: 'play' ID ';';

decl: type (assign ',')*assign  | type (ID',')*ID  
;

algebricOP: ( decl | assign ) ';' ;

expr:     op=('+'|'-') expr #UnaryExpr
         |'not' expr #NotExpr
         | expr op=('*'|'/'|'%') expr #MultDivExpr
         | expr op=('+'|'-') expr #AddSubExpr
         | expr op=('>'|'<'|'>='|'<=') expr #CompareExpr
         | expr op=('=='|'!=') expr #EqualsExpr
         | expr 'and' expr #AndExpr
         | expr 'or' expr #OrExpr
         | '(' ID ')' #ParanthesisIDExpr
         | '(' expr ')' #ParanthesisExpr
         | point #PointExpr 
         | list #ListExpr
         | Number #NumberExpr
         | ID #IDExpr
         | 'read' ('[prompt=' STRING ']')? #ReadExpr
;
assign: ID '=' expr ;

list: '{{' (ID',')*ID '}}' ;

point: pointRect 
     | pointPol 
; 
pointRect: '(' expr ',' expr ')' ;
pointPol:  '(' expr ':' expr ')' ;

type: t=('number' | 'point' | 'list' | 'string' | 'state'| 'boolean') ; 

gridProperties: prop=('step'|'margin'|'color'|'line');
propertiesKeys: prop=('initial'|'accepting'|'align'|'slope'|'highlighted');

Number:  ('+'|'-')?[0-9]+('.'[0-9]+)?; 
ID:      [a-zA-Z][a-zA-Z0-9]*;
SYMBOL:  ['][a-zA-Z0-9]*['] ;
STRING: '"' .*? '"' ;

WS:                [ \t]+ -> skip ;
NEWLINE:           '\r'? '\n' -> skip;
SingleLineComment: [/][/].*?NEWLINE -> skip;
BlockComment :     '/*' .*? '*/' -> skip;

ERROR: . ;