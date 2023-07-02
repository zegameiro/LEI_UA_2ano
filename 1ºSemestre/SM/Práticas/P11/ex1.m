%% Exercício 1 Guião 10

clear
clc

[Image, ColorMap] = imread('Parede_8bit.bmp');

figure(1)
image(Image)
colormap(ColorMap)

%% Exercício 2 Guião 10

Save8bitImage('ex2.raw',Image);

%% Exercício 3 Guião 10

[N, M, Im] = Load8bitImage('ex2.raw');
Im = uint8(Im);

figure(2)
image(Im)
colormap(ColorMap)

%% Exercício 4 Guião 10

stream = EncodeImage_RLE(Im);

%% Exercício 5 Guião 10

Save8bitStream("ex5.raw",N,M,stream)

%% Exercício 6 Guião 10

[N, M, stream1] = Load8bitStream('ex5.raw');
stream1 = uint8(stream1);

%% Exercício 7 Guião 10

image2 = DecodeImage_RLE(N,M,stream1);
figure(3)
image(image2);
colormap(ColorMap)



