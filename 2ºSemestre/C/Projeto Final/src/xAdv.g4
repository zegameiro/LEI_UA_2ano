grammar xAdv;

program: definitions+ EOF;

definitions:'define' (automatonStyle);

automatonStyle: 'automaton''{'(AutomatonProperty ':' Value)+ '}'//todos os autómatos
|Value '{'(AutomatonProperty ':' Value)+ '}'; //um autómato em especifico


AutomatonProperty:'linecolor'|'color' | 'label';


WS: [ \t]+ ->skip;
Ignore:';' ->skip;
Value: '"'ID (WS ID)+'"'|ID| INT;
INT:[1-9][0-9]*;
ID: [a-zA-Z][a-zA-Z0-9]*;
NEWLINE:'\r'? '\n' -> skip;
SingleLineComment: [/][/].*?NEWLINE -> skip;
BlockComment :'/*' .*? '*/' -> skip;

ERROR: . ;