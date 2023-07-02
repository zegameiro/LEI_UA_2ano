alpha = [0.2 ; 0.3 ; 0.1 ; 0.45];

%     1           2              3              4             5  meta
T = [         0 , 0 ,            0 ,            0 ,            0 , 0 ;
       alpha(1) , 0 ,            0 ,            0 ,            0 , 0 ;
              0 , 0 ,            0 ,     alpha(3) ,     alpha(4) , 0 ;
              0 , 0 ,     alpha(2) ,            0 , 1 - alpha(4) , 0 ;
     1-alpha(1) , 1 , 1 - alpha(2) ,            0 ,            0 , 0 ; 
              0 , 0 ,            0 , 1 - alpha(3) ,            0 , 1 ];

v0 = [ 1 ;
       0 ;
       0 ;
       0 ;
       0 ;
       0 ];

prob_min = T^3 * v0;
fprintf("%.4f\n",prob_min(6))

%     1           3              4             5               2 meta
T_can = [         0 ,            0 ,            0 ,            0 , 0 , 0 ;   % 1
              0 ,            0 ,     alpha(3) ,     alpha(4) , 0 , 0 ;   % 3
              0 ,     alpha(2) ,            0 , 1 - alpha(4) , 0 , 0 ;   % 4
     1-alpha(1) , 1 - alpha(2) ,            0 ,            0 , 0 , 0 ;   % 5
       alpha(1) ,            0 ,            0 ,            0 , 1 , 0 ;   % 2
              0 ,            0 , 1 - alpha(3) ,            0 , 0 , 1 ];  % meta


Q = T_can(1:4,1:4);

I = eye(4);   % Gera a matriz identidade de 3x3

F = (I - Q)^(-1);   % Matriz Fundamental

val_esp = F * ones(4,1);

