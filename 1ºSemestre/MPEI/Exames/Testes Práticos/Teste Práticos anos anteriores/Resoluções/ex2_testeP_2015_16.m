%% Exercício 2a) Miniteste Prático 2015/16

clear
clc

fprintf("2a)-----------------------------------------------\n")

%     Angola   Brasil   Chile  Dinamarca
T = [   0.84 ,   0.10 ,     0 ,      0.5 ;   % Angola
        0.10 ,   0.70 ,     0 ,      0.1 ;   % Brasil
        0.05 ,   0.10 ,  0.80 ,      0.1 ;   % Chile
        0.01 ,   0.10 ,  0.20 ,      0.3 ];  % Dinamarca

v0 = [ 1 ; 
       2 ; 
      10 ;
       5 ];

toneladas_agosto = T^7 * v0;

fprintf("Toneladas em Angola: %.4f toneladas\n",toneladas_agosto(1))
fprintf("Toneladas em Brasil: %.4f toneladas\n",toneladas_agosto(2))
fprintf("Toneladas em Chile: %.4f toneladas \n",toneladas_agosto(3))
fprintf("Toneladas em Dinamarca: %.4f toneladas \n",toneladas_agosto(4))

%% Exercício 2b) Miniteste Prático 2015/16

fprintf("2b)-----------------------------------------------\n")

v0 = [ 0 ; 
       0 ; 
       0 ;
       0 ;
       1 ];

M = [T-eye(4) ; ones(1,4)];
u = M\v0;
disp(u)

%% Exercício 2c) Miniteste Prático 2015/16

fprintf("2c)-----------------------------------------------\n")
v0 = [ 1 ; 
       2 ; 
      10 ;
       5 ];
diff = 2; % limite de decisão
iter=1;
  
while 1
    xn = T^iter*v0;  
    if(xn(4) < diff)
      break;
    end
    iter= iter+1;
end

disp(iter)