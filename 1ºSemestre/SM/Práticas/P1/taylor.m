function [value] = taylor(N,x)
% Function taylor
%   This function calculates the values
%   of the series of Taylor of sin(x)
%   sin(x) = sum(N, n=1) x^n / n! .* sin(n * pi / 2)
%   Input arguments:
%     - Value N 
%     - Vector x
%   Outup argument:
%     - value 
    value = 0;
    for n = 1:N
        value = value + ((x .^ n) ./ factorial(n)) .* sin(n * pi / 2);
    end

end