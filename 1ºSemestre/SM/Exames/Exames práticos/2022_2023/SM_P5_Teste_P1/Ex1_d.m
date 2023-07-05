%% Teste Prático 1 - Diogo Falcão(108712) e José Gameiro(108840) - P5

Vfreq = [1/2 , 5/2 , 7/2];
T = 2;
Vamp = [5 , 10, 5];
Vfas = [pi/3 , -2*pi/3 , 0];
Ta = 0.01;
Fo = 1/2;
Np = 3;


% Teste da funcão tempo_frequencia para T_F = 1
T_F = 1;

[Y_t_f1,t_f1] = tempo_frequencia(T_F,Ta,Fo,Np,Vamp,Vfreq,Vfas);

figure(1)
plot(t_f1,Y_t_f1)
xlabel('Tempo (s)')
ylabel('Sinal')
title('x(t) = 5sin(pi*t + pi/3) + 10sin(5pi*t - 2pi/3) + 5sin(7pi * t)')
grid on


% Teste da funcão tempo_frequencia para T_F = 2
T_F = 2;

[Y_t_f2,t_f2] = tempo_frequencia(T_F,Ta,Fo,Np,Vamp,Vfreq,Vfas);

figure(2)
plot(t_f2,Y_t_f2)
xlabel('Frequência (Hz)')
ylabel('Magnitude')
title("Espectograma")
grid on


% Teste da funcão tempo_frequencia para T_F = 3
T_F = 3;
tempo_frequencia(T_F,Ta,Fo,Np,Vamp,Vfreq,Vfas);
