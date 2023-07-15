grammar FractionsCalculator;

program: 
        stat* EOF
    ;
stat:
        expr ? NEWLINE
    |   print ? NEWLINE
    |   assignment ? NEWLINE
    ;

assignment:
        expr '->' ID ';'
    ;

print: 
        'print ' expr ';'
    ;

reduce:
        'reduce ' expr ';'?
    ;

read:
        'read' '"' ID '"'
    ;

expr:
        expr op=('*'|':') expr                                                  #ExprMultDiv
    |   expr op=('+'|'-') expr                                                  #ExprAddSub
    |   expr signal0=('+'|'-')? '(' expr ')' ('^' signal1=('+'|'-')? INT)?      #ExprParent
    |   signal=('+'|'-')? INT '/' INT                                           #ExprFr
    |   reduce                                                                  #ExprReduce
    |   read                                                                    #ExprRead
    |   INT                                                                     #ExprInt
    |   ID                                                                      #ExprId
    ;

INT: [0-9]+;
ID: [a-zA-Z]+ ([a-zA-Z] | [0-9])*;
NEWLINE: '\r'? '\n' | LINE_COMMENT;
WS: [ \t]+ -> skip;
LINE_COMMENT: '//' . *? '\n' -> skip;




    

