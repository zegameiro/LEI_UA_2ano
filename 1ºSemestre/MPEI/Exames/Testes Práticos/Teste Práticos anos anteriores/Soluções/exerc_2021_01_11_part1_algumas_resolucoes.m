% exercício #7
e) P(X >= 20)
P(N(16,16) >) 20);

Z= (X - E[x])/ sqrt(var(X)) % Z é aprox. normal com média 0 e desv padrão 1

P(X >= 20)= P((X -16)/sqrt(16) >= (20 - 16)/sqrt(16) 

P( Z >= 4/4) = P(Z >= 1)= Q(1) ~= 0.1587- 

% exercício #8

A - o programa é do André
X - o programa tem 3 ou mais erros
B - o programa é do Bernardo
C - o programa é do Carlos

a) P(A | X)= P( AX )/P(x)= P(X|A)* P(A)/P(X)= 0.12*0.5/0.0775
           = 0.7742

P(A)= P(B) + P(C),  P(B)= P(C), P(A) + P(B) + P(C) = 1 =>
P(A) = 0.5, P(B) = 0.25, P(C) = 0.25 
P(X|A)= 0.12
P(X)= P(X|A)*P(A) + P(X|B)*P(B) + P(X|C)*P(C)
    = 0.12*0.5 + (0.03 + 0.02)*0.25 + (0.01 + 0.01)*0.25
    = 0.0775

b) P(B|X)
   P(C|X)
   
% exercício #9
Nº médio de errros por 1000 linhas de código = 10
X_100  VA correspondente ao número de erros num programa com 100 linhas(Poisson)
X_200  VA correspondente ao número de erros num programa com 200 linhas(Poisson)
X_500  VA correspondente ao número de erros num programa com 500 linhas(Poisson)

P(X_100= 0 ^ X_200= 0 ^ X_500= 0)= P(X_100=0)*P(X_200=0)*P(X_500=0)

lambda_100= 10*100/1000=1
lambda_200= 10*200/1000= 2
lambda_500= 5

Resposta: 1^0*exp(-1)/0! * 2^0*exp(-2)/0! * 5^0*exp(-5)/0!= exp(-8)






   
