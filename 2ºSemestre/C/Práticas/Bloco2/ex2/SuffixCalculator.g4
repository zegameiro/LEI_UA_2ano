grammar SuffixCalculator;
program:
        stat * EOF // Zero or more repititions of stat
    ;
stat:
        expr ? NEWLINE // Optative expr followed by an end of line
    ;
expr:
        expr expr op = ('*'|'/'|'+'|'-')  #ExprSuffix
    |   Number                            #ExprNumber
    ;
Number: [0-9]+('.'[0-9]+)?; // Fixed point real Number
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;


