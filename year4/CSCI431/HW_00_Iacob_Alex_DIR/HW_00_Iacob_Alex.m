%{
@author:    Alex Iacob ai9388
@filename:  HW_00_Iacob_Alex.m
%}

function HW_00_Iacob_Alex (filename_in)
    % reading in the image
    image_in = imread(filename_in);
    % PART 4A
    % showing the rotated image
    disp('basic image');
    imshow(image_in);
    % pausing between displaying images
    pause();

    % PART 4B
    % showing the gray scaled image
    disp('gray scaled image');
    display_grayscale_image(filename_in);
    pause();

    % PART 4C
    % showing the shrinked image
    disp('shrunked image');
    display_shrinked_image(filename_in);
    pause();

    % PART 4D
    % showing the red and green channels swapped image
    disp('color swapped image');
    display_color_swapped_image(filename_in);
    pause();

    % PART 4E and 4F
    % showing original image with inverted middle chunk
    disp('inverted middle chunk image');
    display_inverted_center_image(filename_in);
    pause();

    % PART 4G
    % showing image with inverted red channel
    disp('red channel inversion');
    display_inverted_red_channel(filename_in);
    pause();

    % PART 4G
    % showing image with inverted green channel
    disp('green channel inversion');
    display_inverted_green_channel(filename_in);
    pause();

    % PART 4G
    % showing image with inverted blue channel
    disp('blue channel inversion');
    display_inverted_blue_channel(filename_in);
    pause();

    % Part 4H
    % showing image of the difference of two images
    disp('image difference');
    display_image_difference(filename_in);
    pause();

    % Part 4I
    % showing the image resule of rgb to hsv
    disp('hsv image');
    display_hsv_image(filename_in)
    pause();

    % PART 4J
    % showing the warhol style image
    disp('warhol image');
    display_warhol_style_image(filename_in);
    pause();    
end    
    
function display_grayscale_image(filename_in)
    % reading image
    image_in = imread(filename_in);
    % turning the image into grayscale
    grayscale_image = rgb2gray(image_in);
    imshow(grayscale_image);
end

function display_shrinked_image(filename_in)
    % reading image
    image_in = imread(filename_in);
    [rows, cols] = size(image_in);
    disp(max(rows, cols));
    % shrink the image to be a max of 480 rows
    shrinked_image = imresize(image_in, [480, NaN]);
    imshow(shrinked_image);
end

function display_color_swapped_image(filename_in)
    image_in = imread(filename_in);
    % shrink the image to be a max of 480 rows
    shrinked_image = imresize(image_in, [480, NaN]);
    % Swap the Red and Green values
    color_swapped_image = shrinked_image(:,:,[2 1 3]);
    imshow(color_swapped_image);
end

function display_inverted_red_channel(filename_in)
    image_in = imread(filename_in);

    % first we have to extract the individual colors
    red_channel = image_in(:,:,1);
    green_channel = image_in(:,:,2);
    blue_channel = image_in(:,:,3);
    
    % invert the red channel
    red_channel = imcomplement(red_channel);

    % Concatenate the channels back together to make one image
    recombined = cat(3, red_channel, green_channel, blue_channel);
    imshow(recombined);
end

function display_inverted_green_channel(filename_in)
    image_in = imread(filename_in);

    % first we have to extract the individual colors
    red_channel = image_in(:,:,1);
    green_channel = image_in(:,:,2);
    blue_channel = image_in(:,:,3);
    
    % invert the green channel
    green_channel = imcomplement(green_channel);

    % Concatenate the channels back together to make one image
    recombined = cat(3, red_channel, green_channel, blue_channel);
    imshow(recombined);
end

function display_inverted_blue_channel(filename_in)
    image_in = imread(filename_in);

    % first we have to extract the individual colors
    red_channel = image_in(:,:,1);
    green_channel = image_in(:,:,2);
    blue_channel = image_in(:,:,3);
    
    % invert the blue channel
    blue_channel = imcomplement(blue_channel);

    % Concatenate the channels back together to make one image
    recombined = cat(3, red_channel, green_channel, blue_channel);
    imshow(recombined);
end

function display_inverted_center_image(filename_in)
    % reading image
    image_in = imread(filename_in);
    % invert the center half of the image
    % by first getting the bounds for the rorws and columns
    % bounds are : rows / 4 <= good <= 3 * rows / 4 &&
    %              cols / 4 <= good <= 3 * cols / 4
    rows=size(image_in,1);
    cols=size(image_in,2);
    
    lower_row = round(rows/4);
    upper_row = round( rows * 3/4);
    
    lower_col = round(cols/4);
    upper_col = round( cols* 3/4);

    % Iterate over rows and columns 
    for row = 1 : rows
        for col = 1 : cols
            if (row >= lower_row) && (row <= upper_row) 
                if (col >= lower_col) && (col <= upper_col)
                    % getting the complement of each channel in the new image
                    image_in(row, col, 1) = imcomplement(image_in(row, col, 1));   
                    image_in(row, col, 2) = imcomplement(image_in(row, col, 2));   
                    image_in(row, col, 3) = imcomplement(image_in(row, col, 3));   
                end
            end    
        end
    end

    imshow(image_in);

    % Writing the images to two files for comparison later
    imwrite(image_in, 'temp5.jpg','Quality', 5);
    imwrite(image_in, 'temp90.jpg','Quality', 90);
    
end

function display_warhol_style_image(filename_in)
    % Code is from Prof. Kinsman's writeup
    image_in = imread( filename_in );
    im_small = imrotate( image_in( 3:4:end, 3:4:end, : ), 0 );
    im_up_right = zeros( size(im_small) );
    im_up_right(:,:,1) = im_small(:,:,1);
    im_down_left = zeros( size(im_small) );
    im_down_left(:,:,2) = im_small(:,:,2);
    im_down_right = zeros( size(im_small));
    im_down_right(:,:,3) = im_small(:,:, 3 );
    im_composite = [im_small , im_up_right; 
                    im_down_left , im_down_right ];
    figure;
    imagesc( im_composite );
end

function display_image_difference(filename_in)
    % getting both images
    my_image_in = imread(filename_in);
    partner_image_in = imread('Benson.jpg');

    % turning both images into doubles
    my_image_double = im2double(my_image_in);
    partner_image_double = im2double(partner_image_in);

    % resizing both images to the same size
    my_resized = imresize(my_image_double, [800, 600]);
    partner_resized = imresize(partner_image_double, [800, 600]);

    % taking the difference between them
    image_difference = my_resized - partner_resized;

    imagesc(image_difference);
end

function display_hsv_image(filename_in)
    % code is from Prof, Kinsman's writeup
    im = imread( filename_in );
    im_hsv = rgb2hsv( im );
    figure;
    im_hue_only = im_hsv(:,:,3);
    imagesc( im_hue_only );
    colormap( hsv(256) );
end
