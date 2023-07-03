grammar NumberTranslator;

file : (line|empty_line)* EOF;

line : NUM '-'  WORD NEWLINE;
empty_line : NEWLINE;

NUM : [0-9]+;
WORD : [A-Za-z]+;
NEWLINE : '\r'? '\n';
WS : [ \t]+ -> skip;
