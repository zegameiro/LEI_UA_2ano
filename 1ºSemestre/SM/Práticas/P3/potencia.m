function [pot] = potencia(x,Ta,T)
    N = T / Ta;
    pot = x(1:N)*x(1:N)'/ N;
end