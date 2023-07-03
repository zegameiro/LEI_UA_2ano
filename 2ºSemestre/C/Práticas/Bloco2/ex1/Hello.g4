grammar Hello;  // Define the grammar 
top: greetings | bye;
greetings : 'hello' ID+; // match hello keyword followed by an identifier
bye : 'bye' ID+; 
ID : [a-zA-Z]+;           // match lower case identifiers
WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines