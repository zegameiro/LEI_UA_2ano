function state = nextState(H, currentState)
    % Returning the next state
    % Inputs:
    % H - state transition matrix
    % currentState - current state
    % find the probabilities of reaching all states starting at the current one:
    
    probVector = H(:,currentState)'; % probVector is a row vector
    n = length(probVector); %n is the number of states
    % generate the next state randomly according to probabilities probVector:
    state = discrete_rnd(1:n, probVector);
end