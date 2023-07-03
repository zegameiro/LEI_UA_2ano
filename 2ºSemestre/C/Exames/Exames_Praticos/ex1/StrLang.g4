grammar StrLang;

main : (stat)* EOF
    ;

stat : (print|assign)      
    ;

assign : 
        VAR ':' expr
    ;

print : 'print' expr
    ;

expr :  
        expr op='+' expr        #ExprAddText
    |   expr op='-' expr        #ExprRemoveText
    |   'trim' expr             #ExprTrimText
    |   '(' expr ')'            #ExprParent
    |   expr '/' expr '/' expr  #ExprReplaceText
    |   'input' '(' expr ')'    #ExprInput
    |   STR                     #ExprSTR
    |   VAR                     #ExprVAR
    ;


VAR : (INT|ID)+;
INT : [0-9]+;
ID : [a-zA-Z]+;
STR : '"' .*? '"';
COMMENT : '//' .*? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;

ERROR : .;