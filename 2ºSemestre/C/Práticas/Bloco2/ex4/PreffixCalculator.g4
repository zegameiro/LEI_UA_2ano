grammar PreffixCalculator;
program:
        stat* EOF
    ;
stat:
        expr? NEWLINE
    ;
expr:
        expr expr op = ('*'|'/'|'+'|'-') #ExprPreffix
    |   Number                           #ExprNumber
    ;

Number: [0-9]+('.'[0-9]+)?;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;