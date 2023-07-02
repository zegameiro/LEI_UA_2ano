function y = chorus(x,fs,MaxDelay,NumComp)
  % NumComp -> réplicas do sinal de x
  % x -> Vetor de amostras do sinal
  % fa -> Frequência de amostragem
  % MaxDelay -> Máximo número de réplicas

    N = length(x);
    y = zeros(N,1);

    for n = 1:NumComp
        CurDelay = rand(1,1) * MaxDelay;
        dn = max([1 round(CurDelay*fs)]);
        y(dn : end) = y(dn : end) + x(1 : end-dn+1);
    end

    Px = x' * x/N; % Potência do sinal x
    Py = y' * y/N; % Potência do sinal y
    y = y * sqrt(Px/Py);
end