grammar Vector;

program: (stat? ';')* EOF;

stat: show | assign;

show: 'show' expression;

assign: expression '->' ID;

expression:
	op = ('+' | '-') expression					# ExprUnary
	| expression op = ('*' | '.') expression	# ExprMults
	| expression op = ('+' | '-') expression	# ExprAddSub
	| '(' expression ')'						# ExprParenteses
	| VECTOR									# ExprVector
	| NUMBER									# ExprNumber
	| ID										# ExprID;

NUMBER: [0-9]+ ('.' [0-9]+)?;
ID: [a-z0-9]+;
VECTOR: '[' NUMBER (',' NUMBER)? ']';
COMMENT: '#'+ .*? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;
Error: .;
