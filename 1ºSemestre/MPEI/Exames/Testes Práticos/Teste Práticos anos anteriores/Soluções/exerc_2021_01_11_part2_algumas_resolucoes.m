T= [ 0 0.5 0    0 ;
     1 0   0.6  0.6;
     0 0   0    0.4;
     0 0.5 0.4  0]
 
 x0= ones(4,1)/4;
 
 X= markov_estadoestacionario(T,x0,1e-6)
 
 % método algébrico
 N= length(T);
 M= [T-eye(N); ones(1,N)];
 
 res= zeros(N+1,1);
 res(N+1)= 1;
 
 x_est= M\res
  