grammar CalComplex;

program: (stat? ';')*;

stat: output | assign;

output: 'output' expr;

assign: expr '=>' ID;

expr:
	op = ('+' | '-') expr			# ExprUnary
	| expr op = ('+' | '-') expr	# ExprAddSub
	| expr op = ('*' | ':') expr	# ExprMultDiv
	| '(' expr ')'					# ExprParenteses
	| COMPLEX						# ExprComplex
	| NUMBER						# ExprNumber
	| ID							# ExprID;

COMPLEX: NUMBER? ('+' | '-')? NUMBER? 'i';
NUMBER: [0-9]+;
ID: [a-zA-Z][a-zA-Z0-9]*;
COMMENT: '#' .*? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;
ERROR: .;