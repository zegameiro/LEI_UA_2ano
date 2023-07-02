function m = makeMatrix(data)
    sSet = [];

    for n= 1:length(data)
        sSet = cat(1,sSet,data{n});
    end    
    sSet = unique(sSet);

    m = zeros(length(sSet),length(data));
    for s= 1:length(sSet)
        for d= 1:length(data)
            m(s,d) = any(data{d}==sSet(s));
        end
    end
end