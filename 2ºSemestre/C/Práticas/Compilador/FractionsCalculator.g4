grammar FractionsCalculator;

program: 
        (instruction ? ';')* EOF
    ;

instruction:
        print
    |   assign
    ;

print: 
        'print ' expr 
    ;

assign:
        expr '->' ID 
    ;

expr:
        op=('-'|'+') expr              #ExprUnary
    |   expr op=('*'|':') expr         #ExprMultDiv
    |   expr op=('+'|'-') expr         #ExprAddSub
    |   '(' expr ')' '^' expr         #ExprPower
    |   '(' expr ')'                   #ExprParent
    |   'reduce' expr                  #ReduceExpr
    |   LITERAL                        #ExprLiteral                                                        
    |   ID                             #ExprId
    ;

LITERAL: INT ('/' INT)?;
INT: [0-9]+;
ID: [a-zA-Z]+ ([a-zA-Z] | [0-9])*;
NEWLINE: '\r'? '\n' | LINE_COMMENT;
WS: [ \t]+ -> skip;
LINE_COMMENT: '//' . *? '\n' -> skip;




    

