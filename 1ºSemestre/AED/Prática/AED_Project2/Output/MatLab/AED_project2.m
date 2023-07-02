clear
clc

four = load("four_letters.txt");
five = load("five_letters.txt");
six = load("six_letters.txt");
big = load("big_letters.txt");

figure(1)
subplot(2,2,1)
histogram(four, 'FaceColor', [0.4660 0.6740 0.1880]);
xlim([0 18])
title("Four letters")
xlabel("Número colisões")
ylabel("Número de vezes que ocorrem x colisões")
grid on

subplot(2,2,2)
histogram(five, 'FaceColor', [0.4660 0.6740 0.1880]);
xlim([0 18])
title("Five letters")
xlabel("Número colisões")
ylabel("Número de vezes que ocorrem x colisões")
grid on

subplot(2,2,3)
histogram(six, 'FaceColor', [0.4660 0.6740 0.1880]);
xlim([0 18])
title("Six letters")
xlabel("Número colisões")
ylabel("Número de vezes que ocorrem x colisões")
grid on

subplot(2,2,4)
histogram(big, 'FaceColor', [0.4660 0.6740 0.1880]);
xlim([0 18])
title("Big letters")
xlabel("Número colisões")
ylabel("Número de vezes que ocorrem x colisões")
grid on
