clear
clc
      
exec_temp_1 = load('time_execution_1.txt');
exec_temp_2 = load('time_execution_2.txt');
exec_temp_3 = load('time_execution_3.txt');
exec_temp_4 = load('time_execution_4.txt');
exec_temp_5 = load('time_execution_5.txt');
n = load('n.txt');
n_long = zeros(1,length(exec_temp_1));

for i = 1:length(exec_temp_1)
    n_long(i) = n(i);
end

figure(1)
plot(n_long,log(exec_temp_1),"LineWidth",2,"Color",[0 0.4470 0.7410])
title("Execution time graph")
legend("Teacher's slow soolution")
xlabel("n")
ylabel("log(t (s))")
grid on

figure(2)
hold on
plot(n_long,log(exec_temp_1),"LineWidth",2,"Color",[0 0.4470 0.7410])
plot(n,log(exec_temp_2),"LineWidth",2,"Color",[0.8500 0.3250 0.0980])
title("Execution time graph")
legend("Teacher's slow solution","Branch and Bound solution recursive")
xlabel("n")
ylabel("log(t (s))")
grid on
hold off

figure(3)
hold on
plot(n_long,log(exec_temp_1),"LineWidth",2,"Color",[0 0.4470 0.7410])
plot(n,log(exec_temp_2),"LineWidth",2,"Color",[0.8500 0.3250 0.0980])
plot(n,log(exec_temp_3),"LineWidth",2,"Color",[0.9290 0.6940 0.1250])
title("Execution time graph")
legend("Teacher's slow solution","Branch and Bound solution recursive","Teacher's solution improved")
xlabel("n")
ylabel("log(t (s))")
grid on
hold off

figure(4)
hold on
plot(n_long,log(exec_temp_1),"LineWidth",2,"Color",[0 0.4470 0.7410])
plot(n,log(exec_temp_2),"LineWidth",2,"Color",[0.8500 0.3250 0.0980])
plot(n,log(exec_temp_3),"LineWidth",2,"Color",[0.9290 0.6940 0.1250])
plot(n,log(exec_temp_4),"LineWidth",2,"Color",[0.4940 0.1840 0.5560])
title("Executing time graph")
legend("Teacher's slow solution","Branch and Bound solution recursive","Teacher's solution improved","Memoization Iterative Solution")
xlabel("n")
ylabel("log(t (s))")
grid on

figure(5)
hold on
plot(n_long,log(exec_temp_1),"LineWidth",2,"Color",[0 0.4470 0.7410])
plot(n,log(exec_temp_2),"LineWidth",2,"Color",[0.8500 0.3250 0.0980])
plot(n,log(exec_temp_3),"LineWidth",2,"Color",[0.9290 0.6940 0.1250])
plot(n,log(exec_temp_4),"LineWidth",2,"Color",[0.4940 0.1840 0.5560])
plot(n,log(exec_temp_5),"LineWidth",2,"Color",[0.4660 0.6740 0.1880])
legend("Teacher's slow solution","Branch and Bound solution recursive","Teacher's solution improved","Memoization Iterative Solution","Dynamic Iterative solution")
grid on
xlabel("n")
ylabel("log(t (s))")
title("Executing time graph")
hold off
