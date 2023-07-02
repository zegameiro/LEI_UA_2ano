function state = discrete_rnd(states, probVector)
    % Generate randomly the next state.
    % Inputs:
    % states = vector with state values
    % probVector = probability vector
    U=rand();
    i = 1 + sum(U > cumsum(probVector));
    state= states(i);
end