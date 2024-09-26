function HW_01_Alex_Iacob()
    im_in = imread('Jairaj_for_HW_01_handout.jpg');
    imshow(im_in);
    [column, row] = ginput();
    disp(column);
    disp(row);

    red_channel = im_in(:,:,1);
    grn_channel = im_in(:,:,2);
    blu_channel = im_in(:,:,3);

    disp('red channel');
    imshow(red_channel);
    pause();

    disp('grn channel');
    imshow(grn_channel);
    pause();

    disp('blu channel');
    imshow(blu_channel);
    pause();

    display_tilted_image();
    pause();

    display_sin_wave();
    pause();
end
   
function display_tilted_image()
    % reading in the file and display the green channel
    im_in = imread('me.jpg');
    grn_channel = im_in(:,:,2);

    % rotating the image and showing it to get the ginput
    rotated = imrotate(grn_channel, -15);
    imshow(rotated);

    % getting the user's end points for the dynamic image
    [col, row] = ginput(2);
    col = round(col);
    row = round(row);

    % slicing the rotated image with the user's choice
    sliced = rotated (row(1):row(2), col(1):col(2));
    imshow(sliced);

    % writing the sliced image to the 
    imwrite(sliced, 'HW01_Dynamic_Alex_Iacob.jpg');
end

function display_sin_wave()
    x = 0 : 1 : 1080;
    y = sind(x);
    plot(x, y);
    axis tight;
    xlabel('Degrees', 'FontSize', 18);
    ylabel('Sine of Theta', 'FontSize', 18);
end