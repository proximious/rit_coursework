function HW_03_Iacob_Alex(filename)
    read_image_info();

    Show_Color_Separations_rgby(filename);
end

function read_image_info()
    im_in = imread('IMG_2523_Matilda_STROOP_EFFECT.JPG');
    imshow(im_in);
    info = imfinfo('IMG_2523_Matilda_STROOP_EFFECT.JPG');
    disp(info);

    disp(info.DigitalCamera);
end

function Show_Color_Separations_rgby(filename)
    im_in = imread(filename);
    im_in = im2double(im_in);

%     top_left
    subplot(2, 2, 1);
    % getting the red channel
    imshow(im_in(:,:,1));
    title('PIXELS WITH HIGH RED VALUE:');

%     top_right
    subplot(2, 2, 2);
    % getting the green channel
    imshow(im_in(:,:,2));
    title('PIXELS WITH HIGH GREEN VALUE:');

%     bottom_left
    subplot(2, 2, 3);
    % getting the blue channel
    imshow(im_in(:,:,3));
    title('PIXELS WITH HIGH BLUE VALUE:');

%     bottom_right
    subplot(2, 2, 4);
    % using formula from Prof. Kinsman to calculate the yellow values
    im_yellow = (im_in(:,:,1) + im_in(:,:,2) - 2 * im_in(:,:,3 )) / 2;
    % setting negative values to zero
    im_yellow = max(im_yellow, 0);
    imshow(im_yellow);
    title('PIXELS WITH HIGH YELLOW VALUE:');

end