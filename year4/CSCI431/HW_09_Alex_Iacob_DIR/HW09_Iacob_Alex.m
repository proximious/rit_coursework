function HW09_Iacob_Alex()
    im_in = imread("IMG_20221015_TO_RECTIFY_2221.jpg");

    % Figure for input image:  
    figure('Position',[ 400  5  1001  792] );
    imagesc( im_in );
    colormap( gray );
    axis image;

    xs_target_points_to_get_to  = [   230       233     1143    1140 ] - (230-1);
    ys_target_points_to_get_to  = [   980       170      176     988 ] - (170-1);

    % These are the original corner points
    % Used ginput to get corner locations
    xy_source_points_to_move      = [  1761   1782   2244   2223 ;
                                       1340   750   823   1484 ;
                                         1     1     1     1 ];


    % Sub-sample by a factor of two:
    xs_target_points_to_get_to  = xs_target_points_to_get_to / 2;
    ys_target_points_to_get_to  = ys_target_points_to_get_to / 2;
    xy_target_points_to_get_to  = [ xs_target_points_to_get_to ;
                                    ys_target_points_to_get_to ;
                                    ones( 1, size(xs_target_points_to_get_to,2) ) ]; 

    %
    %  Form the matlab transformation:
    %
    fit_xform   = fitgeotrans( xy_source_points_to_move(1:2,:).', ...
                               xy_target_points_to_get_to(1:2,:).', ...
                               'projective' );
    
    %  Warp the image:
    im_unmorphed  = imwarp( im_in, fit_xform );
    
    % Cropping the image
    cropped = imcrop(im_unmorphed, [3600 600 500 500]);

    % showing image then saving it  
    imshow(cropped);
    imwrite(cropped, 'Cropped_sign.png');
end