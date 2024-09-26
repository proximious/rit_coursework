function messing_with_algos()
    % reading in the image
    im_in = imread('IMG_1504.png');

    % resizing to 600x400
%     im_resized = imresize(im_in, [400, 600]);

    %
    % PART B
    %

    % using Matlab's documentation to learn how imsegkmeans works
    [labeled, centers] = imsegkmeans(im_in, 9);
    cartoonized = label2rgb(labeled, im2double(centers));

    % showing the image
    imshow(cartoonized);
    pause();
    imwrite(cartoonized, 'me_cartoonized.jpg');


    % turning the image into a double and grayscaling it for edge detection
    im_dbl = im2double(im_in);
    im_gray = im2gray(im_dbl);

    % using a few different edges to see which one is best
    edge1 = edge(im_gray, 'Sobel');
    % afterwards, it seems that Sobel and Prewitt Edges are the best
%     figure;
%     grid on;
    % turning the cartoonized image into 
    cartoon_dbl = im2double(cartoonized);
    
    % I asked around for how people combined the images and someone said:
    % "yeah i just subtracted the edge from the kmeans-image"
    % and it worked, talking with people is good
%     combined = cartoon_dbl - edge1;
%     imagesc(combined);
%     imwrite(combined, 'Alex_Iacob.png');
end