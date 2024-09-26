function HW_02_Alex_Iacob()
%      bin_segmentation_Virginia_Creeper();
%      contrast_enhancement_Hidden_Sign();
%      contrast_enhancement_Grape_Vines();
     contrast_Deer();
end

% question 4
function bin_segmentation_Virginia_Creeper()
    % reading and showing the given image of the Virginia Creeper
    im_in = imread('IMG_2742_Virginia_Creeper.jpg');
    im_gray = im2double( im_in( 2:2:end, 2:2:end, 2) );
    imshow(im_gray);

    % using graythresh to get an appropriate level
    level = graythresh(im_gray);
    disp(level);

    % using imbinarize to binarize the image to get a black and white image
    black_and_white = imbinarize(im_gray, level);
    imshow(black_and_white);
    imwrite(black_and_white, 'normal_threshold.png');
    pause();

    % going through more values for the ideal combination
    disp(0.4);
    imshow(imbinarize(im_gray, 0.4));
    pause();

    disp(0.45);
    imshow(imbinarize(im_gray, 0.45));
    pause();

    % this is a good one
    disp(0.5);
    imshow(imbinarize(im_gray, 0.5));
    imwrite(imbinarize(im_gray, 0.5), 'point5_threshold.png');
    pause();
    
    disp(0.55);
    imshow(imbinarize(im_gray, 0.55));
    pause();
    
    % this is a good one
    disp(0.6);
    imshow(imbinarize(im_gray, 0.6));
    imwrite(imbinarize(im_gray, 0.6), 'point6_threshold.png');
    pause();
end

% question 5
function contrast_enhancement_Hidden_Sign()
    % reading and showing the given image of the Virginia Creeper
    im_in = imread('IMG_2548__Needs_Contrast_Enhancement.jpg');
    im_gray = im2double( im_in( 2:2:end, 2:2:end, 2) );

    % using histogram equalization
    equalized = histeq(im_gray);
    imshow(equalized);
    imwrite(equalized, 'color_equalized.png');
end

% question 6
function contrast_enhancement_Grape_Vines()
    % reading and showing the given image of the Grape Vines
    im_in = imread('IMG_2653_IVY_Against_Wild_Grape_Vines.jpg');
    im_gray = im2double( im_in( 2:2:end, 2:2:end, 2) );

    % using imbinarize to binarize the image to get a black and white image
    black_and_white = imbinarize(im_gray, 0.5);
    imshow(black_and_white);
    imwrite(black_and_white, 'noised_ivy.png');
    pause();
    
    % removing some of the noise from the image
    denoised = medfilt2(black_and_white, [15,15]);
    imshow(denoised);
    imwrite(denoised, 'denoised_ivy.png');
    pause();
end

% question 7
function contrast_Deer()
    im_in = imread('IMG_2663_DEER_with_Ears_small.jpg');
    im_db = im2double( im_in );

    % part a
    disp('a');
    imshow( im_db(:,:,1) );
    colormap(gray(256));
    axis image;
    pause();

    % part b
    disp('b');
    imshow( im_db(:,:,2) );
    colormap(gray(256));
    axis image;
    imwrite(im_db(:,:,2), 'partB.png');
    pause();

    % part c
    disp('c');
    imshow( im_db(:,:,3) );
    colormap(gray(256));
    axis image;
    pause();

    % part d
    disp('d');
    im_gray = rgb2gray( im_db );
    imshow(im_gray);
    imwrite(im_gray,'partD.png');
    pause();

    % part e
    disp('e');
    im_yellow = im_db(:,:,1) + im_db(:,:,2) - 2 * im_db(:,:,3) / 2;
    imshow(im_yellow);
    pause();
    
    % part f
    disp('f');
    im_other = im_db(:,:,1) + im_db(:,:,3) - 2 * im_db(:,:,2) / 2;
    imshow(im_other);
    imwrite(im_other, 'partF.png');
    pause();

    % part g
    disp('g');
    im_other2 = im_db(:,:,2) + im_db(:,:,3) - 2 * im_db(:,:,1) / 2;
    imshow(im_other2);
    pause();
end
