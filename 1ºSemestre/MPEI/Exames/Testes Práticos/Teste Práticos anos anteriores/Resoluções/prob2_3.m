%% Problema 2 alínea 1 MPEI exercícios Práticos sobre cadeias de Markov

T = [     0 , 0.5000 ,      0 ,      0 ;
     1.0000 ,      0 , 0.6000 , 0.6000 ;
          0 ,      0 ,      0 , 0.4000 ;
          0 , 0.5000 , 0.4000 ,      0 ];

x0 = [0.25 ;
      0.25 ;
      0.25 ;
      0.25 ];

v_estaci = markov_estadoestacionario(T,x0);

%% Problema 2 alínea 2 MPEI exercícios Práticos sobre cadeias de Markov

x = [0.25 ;
     0.25 ; 
     0.25 ;
     0.25 ;
        1 ];

M = [T-eye(4); ones(1,4)];
u = M\x;

%% Problema 3 alínea 1 MPEI exercícios Práticos sobre cadeias de Markov

x0_1 = [1 ; 0 ; 0 ; 0];
v_estaci_1 = markov_estadoestacionario(T,x0_1);

x0_2 = [0 ; 1 ; 0 ; 0];
v_estaci_2 = markov_estadoestacionario(T,x0_2);

x0_3 = [0 ; 0 ; 1 ; 0];
v_estaci_3 = markov_estadoestacionario(T,x0_3);

x0_4 = [0 ; 0 ; 0 ; 1];
v_estaci_4 = markov_estadoestacionario(T,x0_4);
