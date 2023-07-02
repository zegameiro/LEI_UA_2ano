%% Aula 2 29/09/2022
% Exercício 6

x = linspace(-1,1,51);
y = linspace(-1,1,51);

[XX,YY] = meshgrid(x,y);

fxy = cos(4 * pi .*(XX + YY)) .* exp(-abs(XX + YY));

surf(XX,YY,fxy)
shading interp
grid on
xlabel('x')
ylabel('y')
zlabel('z')
title('f(x,y) = cos(4 * pi .*(XX + YY)) .* exp(-abs(XX + YY))')


