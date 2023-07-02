w = linspace(0,2*pi,200);

yx = sin(4*w) .* (cos(w) + sin(w) .* i);
gx = sin(8 * w) .* (cos(w) + sin(w) .* i);

subplot(2,1,1)
plot(yx,'g')
legend('sen(4*w) * exp(iw)')
title('Parte Superior')
grid on
xlabel('Re(z)')
ylabel('Im(z)')

subplot(2,1,2)
plot(gx,'r')
legend('sen(8*w) * exp(iw)')
title('Parte Inferior')
grid on
xlabel('Re(z)')
ylabel('Im(z)')


