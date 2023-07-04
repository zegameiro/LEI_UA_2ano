grammar FracLang ;

program: (command? ';')* EOF
    ;

command: display | assignment
    ;

display: 'display' expr
    ;

assignment: ID '<=' expr
    ;

expr:
        op=('+'|'-') expr       #ExprUnary
    |   expr op=('*'|':') expr  #ExprMultDiv
    |   expr op=('+'|'-') expr  #ExprSumSub
    |   'reduce' expr           #ExprReduce
    |   'read' String           #ExprRead   
    |   '(' expr ')'            #ExprParent
    |   Fraction                #ExprFraction
    |   ID                      #ExprID
    ;

String: '"' .*? '"' ;
Fraction: [0-9]+ ('/' [0-9]+)?;
ID: [a-z]+;
WS: [ \t\r\n]+ -> skip;
Comment: '--' .*? '\n' -> skip;
Error: .;



