function hash_lst = minHash(data,k)
    p = primes(10000);
    hash_lst = zeros(k,width(data));
    k_lst = p(randperm(length(p),k));

    for func= 1:length(k_lst)
        for d= 1:width(data)
            hash_lst(func,d) = min(mod(find(data(:,d)==1),k_lst(func)));
        end
    end
end

if