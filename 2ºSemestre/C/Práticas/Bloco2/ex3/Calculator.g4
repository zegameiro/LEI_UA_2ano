grammar Calculator;

program:
        stat * EOF
    ;
stat:
        expr? NEWLINE
    ;
expr:
        expr op=('*'|'/'|'%') expr      #ExprMulDivMod
    |   expr op=('+'|'-') expr          #ExprAddSub
    |   signal=('+'|'-')? Integer       #ExprInteger
    |   signal=('+'|'-')? '(' expr ')'  #ExprParent
    ;
    
Integer: [0-9]+; 
NEWLINE: '\r' ? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;

