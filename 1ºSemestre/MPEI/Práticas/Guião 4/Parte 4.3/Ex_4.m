d_cell = cell(3,1);
i = 1;

udata = load("u.data");
u = udata(1:end,1:2); clear udata;
users = unique(u(:,1));
%users = users(randperm(length(users),100));

for k= [50,100,200]
    fprintf("\n=============== %d HASH FUNCTIONS ===================\n", k);
    
    tic;
    movies = get_info("u.data");
    fprintf("getMovies(): %7.6es\n",toc);

    tic;
    matrix = makeMatrix(movies);
    fprintf("makeMatrix(): %7.6es\n",toc);

    tic;
    hashMat = minHash(matrix,k);
    fprintf("minHash(): %7.6es\n",toc);

    tic;
    dists = zeros(length(users),length(users));
    for n1= 1:length(users)
        for n2= n1+1:length(users)
            dists(n1,n2) = sum(hashMat(:,n1)==hashMat(:,n2))/k;
        end
    end
    fprintf("calculating distances(): %7.6es\n",toc);
    d_cell{i} = dists;

    tic;
    SimilarUsers = filterSimilar(users,dists,0.4);
    fprintf("filterSimimlar(): %7.6es\n",toc);
    fprintf("Similar users (%d pairs):\n\n",height(SimilarUsers));

    i = i+1;
end