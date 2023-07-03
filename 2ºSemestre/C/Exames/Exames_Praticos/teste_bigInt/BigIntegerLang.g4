grammar BigIntegerLang;

program : (stat ';')* EOF;

stat:
        condition
    |   assign
    |   show
    |   expr
    ;

show:
        'show' expr 
    ;

assign:
        expr '->' VAR
    ;

condition:
        'if' expr 'then' (trueInst += stat ';')* 'end' ('else' (falseInst += stat ';')* 'end')?
    ;


expr:
        op=('+'|'-') expr                   #ExprUnary
    |   expr op=('*'|'div'|'mod') expr      #ExprMultDivMod
    |   expr op=('+'|'-') expr              #ExprSumSub
    |   '(' expr ')'                        #ExprParent
    |   INT                                 #ExprINT
    |   VAR                                 #ExprVAR
    ;


VAR: (STR|INT)+ ;
STR: [a-zA-Z]+ ;
INT: [0-9]+ ;
WS: [ \t\r\n]+ -> skip ;
COMMENT: '#' .*? ('\n'|'\r') -> skip ;
ERROR: .;
