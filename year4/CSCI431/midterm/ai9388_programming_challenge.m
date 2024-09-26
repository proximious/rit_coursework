function ai9388_programming_challenge()
    im_in = imread('IMG_4275_ENGLISH_IVY.JPG');

    % turning the image into it's red channel   
    red_channel = im_in(:,:,1);

    % we first want to binarize the image to more easily get the edges
    binarized = imbinarize(red_channel);
    im_dbl = im2double(binarized);

    % using a canny edge detector because you cannot use Hough
    % Hough uses specifically horizontal lines, but we want to get all of
    % the edges, regardless of their angle
    edges = edge(im_dbl, 'canny', [0.15, 0.3]);

    subplot(1, 2, 1);
    imshow(red_channel);
    title('red channel');

    subplot(1, 2, 2);
    imshow(edges);
    title('the edges');

    % writing the image.
    imwrite(edges, 'ai9388_programming_challenge.png');
end