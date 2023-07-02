N = 1e6;
p = 20;

for x = 0:20
    P(x+1) = probLancamentoMoeda(p,x,N);

end

P

stem(0:20,P)


