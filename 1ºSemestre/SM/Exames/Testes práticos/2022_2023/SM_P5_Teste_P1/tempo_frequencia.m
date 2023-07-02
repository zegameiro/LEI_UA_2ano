function [y_t_f,t_f] = tempo_frequencia(T_F,Ta,Fo,Np,Vamp,Vfreq,Vfas)
% T_F -> Parâmetro que seleciona geração de sinal no tempo (T_F = 1) ou na
% frequência (T_F = 2)
% Ta -> Tempo de amostragem
% Fo -> Frequência do sinal
% Np -> Número de períodos
% Vamp, Vfreq, Vfas -> Vetores com Nt amplitudes, Nt frequências e Nt
% fases, respetivamente
% y_t_f -> sinal no tempo ou frequência dependendo do valor de T_F
% t_f -> Vetor dos tempos(s) ou frequência(Hz)
    
    To = 1 / Fo;
    fa = 1 / Ta;
    Nt = (To / Ta) * Np;
    t = (0:Nt-1) * Ta;
    xt = 0; 

    for i= 1:length(Vamp)
        xt =  xt + Vamp(i) * sin(2 * pi * Vfreq(i) * t + Vfas(i));
    end

    if T_F == 1

        t_f = t;
        y_t_f = xt;

    elseif T_F == 2

        df = fa/Nt;
        t_f = (0:Nt-1) * df - fa/2;

        X = fft(xt);
        X = fftshift(X) / Nt;
        X = abs(X);
        
        y_t_f = X;

    else
        disp("T_F deverá ter valores de 1 ou 2")
    end

        

end