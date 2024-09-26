function HW_03_Iacob_Alex_Q2()
    im_in = imread('IMG_20220828_Apples.jpg');
    im_dbl = im2double(im_in);
    
    % turning the double image to a lab image
    im_lab = rgb2lab(im_dbl);

    % after viewing all of the channels, the a* was the ideal channel
    im_a_star = im_lab(:,:,2);

    % updating the colormap to better see things
    colormap("copper");

    % I asked Prof. Kinsman about this during his office hours and he 
    % recommended that I threshold the function.
    im_updated = im_a_star >= 8;

    % using median filtering to to clean up some noise
    im_median_filtering = medfilt2(im_updated, [10, 10]);
    
    % displaying the updated image
    imagesc(im_median_filtering);
end

