function HW07_Iacob_Alex_MAIN()
%     HW07_Email1_Cluster_Colors()
%     pause();
    HW07_Email1_FIND_RASPBERRIES();
    pause();
    HW07_Email1_FIND_ORANGES();
    pause();
end

function HW07_Email1_Cluster_Colors()
    % reading in the image and turning it into a double
    fn_in = 'Img431_SCAN275__Secret_Mess.jpg';
    % turning the image into a double and decreasing number of pixels
    % in the image to make kmeans run faster
    im = im2double( imread( fn_in ));

    im = im( 2:2:end, 2:2:end, : );
    
    % separating the image's channels
    im_red  = im( :, :, 1 );
    im_grn  = im( :, :, 2 );
    im_blu  = im( :, :, 3 );
    
    data = [ im_red(:) , im_grn(:) , im_blu(:) ];
    
    % getting the starting points for the 12 color namespaces
    Seed_Pts.Color_Starting_Point = [ ...
       0.0000 0.0000 0.0000 ;   % Black
       1.0000 0.0000 0.0000 ;   % Red
       0.0000 0.9000 0.0000 ;   % Green
       0.0000 0.0000 1.0000 ;   % Blue
       1.0000 0.0000 1.0000 ;   % Magenta
       0.2000 0.4000 0.4000 ;   % Cyan
       1.0000 1.0000 0.0000 ;   % Yellow
       1.0000 1.0000 1.0000 ;   % White
       0.7000 0.7000 0.0000 ;   % Brown -- Dark Yellow
       0.7000 0.4000 0.0000 ;   % Pinkish or maybe orangish
       0.4000 0.4000 0.4000 ;   % Dark gray
       0.8000 0.8000 0.8000 ;   % Light gray
    ];
    Seed_Pts.Color_name = { 
        'Black', ...
        'Red', ...
        'Green', ...
        'Blue', ...
        'Magenta', ...
        'Cyan', ...
        'Yellow', ...
        'White', ...
        'Brown', ...
        'Pinkish', ...
        'Dark Gray', ...
        'Light Gray' };
    
    % Check for stupid mistakes:
    %
    % Assertions are checked once, and then ignored later.
    % They are used for good code development, and checks when debugging.
    %
    % The released code ignores these.
    %
    assert( length( Seed_Pts.Color_Starting_Point ) == length( Seed_Pts.Color_name ) );
    
    % getting the number of clusters
    n_seeds     = size( Seed_Pts.Color_Starting_Point, 1 );

    % Lengths returns the longest dimension of an array, so this would work too,
    % as long as there are more then 3 seed points:
    n_seeds     = length( Seed_Pts.Color_Starting_Point );

    % running the kmeans algorithm
    [cluster_id, kmeans_center_found] = kmeans( data, n_seeds, 'start', Seed_Pts.Color_Starting_Point );
    
    %  Convert the results of k-means into an image:
    image_by_id     = reshape( cluster_id, size(im,1), size(im,2) );
    
    % Build my own custom colormap:
    my_cmap = kmeans_center_found;
    
    % does the same as Prof. Kinsman's zoom_figure();
    figure('Position', [10 10 1400 1200] );
    imagesc( image_by_id );
    axis image;
    colormap( my_cmap );
    colorbar;
    
    pause(3);

    %
    % Count the number of each color found:
    %
    for cluster_number = 1 : size( kmeans_center_found, 1 )
        binary_mask = (image_by_id == cluster_number);
        
        % Count the number of pixels found of this color.
        n_pix_per_cluster(cluster_number) = sum(binary_mask(:));
    end

    [~ ,sort_key] = sort( n_pix_per_cluster, 'Ascend' );

    for cluster_number = sort_key
        
        binary_mask = (image_by_id == cluster_number);
        
        imagesc( binary_mask );
        axis image;
        colormap( gray(2) );
        
        % Count the number of pixels found of this color.
        n_pixels_found = sum(binary_mask(:));
        
        tmp_str = sprintf('Cluster %d, "%s"', cluster_number, Seed_Pts.Color_name{ cluster_number } );
        title( tmp_str, 'FontSize', 32 );
        
        fprintf('Found %6d pixels of   color number %3d, [%4.2f,%4.2f,%4.2f], "%s", \n', ...
            n_pixels_found, ...
            cluster_number, ...
            kmeans_center_found(cluster_number,:), ...
            Seed_Pts.Color_name{ cluster_number } );
        
        pause(6);
    end
end

function HW07_Email1_FIND_RASPBERRIES()
    % reading in the image and turning it into a double
    im_in = imread("Img431_Example__Raspberry_Image.jpg");

    im = im2double( im_in ) ;
    
    % displaying every other pixel to make the image smaller
    im  = im( 1:2:end, 1:2:end, : );
    
    % showing the figure
    figure;    
    imagesc( im );
    axis image;

    % Letting the user be able to click for the foreground info
    fprintf('Click on the raspberries\n');
    [rxs,rys] = ginput();

    %  Get location of other pixels from the user.
    fprintf('Click on the NON-raspberries\n');
    [bgxs,bgys] = ginput();

    % rounding the pixels to make them easier to work with
    rxs = round( rxs );
    rys = round( rys );
    for berry_idx = 1 : length( rxs )
        fg_color( berry_idx, 1:3 ) = im( rys(berry_idx), rxs(berry_idx), : );
    end
    
    %  Get other background color values.
    bgxs = round( bgxs );
    bgys = round( bgys );
    for background_idx = 1 : length( bgxs )
        bg_color( background_idx, 1:3 ) = im( bgys(background_idx), bgxs(background_idx), : );
    end
    
    %  For each input pixel, figure out distance to the raspberry colors.
    [im_red, im_grn, im_blu] = imsplit( im );
    
    pixel_data = [ im_red(:), im_grn(:), im_blu(:) ];  
    
    %  For each input pixel, find   distance to the background colors.
    %  If a pixel is closer to the raspberry color than to a the other,
    %       classify it as a raspberry
    %  else
    %       call it a background pixel.
   
    % calculating the mahalanobis distance
    fg_dist = mahal( pixel_data, fg_color );
    bg_dist = mahal( pixel_data, bg_color );
    
    % getting all of the foreground pixels
    b_is_fg = fg_dist < bg_dist;
    
    % reshaping some pixels
    b_is_fg = reshape( b_is_fg, size(im,1), size(im,2) );
    
    % setting the colormap and displaying the image
    colormap("gray");
    im = im2uint8(b_is_fg);
    imagesc( im );
    
    % saving the image
    imwrite(im, 'raspberry_final_image.jpg');
end

function HW07_Email1_FIND_ORANGES()
    % reading in the image and turning it into a double
    im_in = imread("Orange_Tree_Image_3305.jpg");

    
    im = im2double( im_in ) ;
    
    % displaying every other pixel to make the image smaller
    im  = im( 1:2:end, 1:2:end, : );
    
    % showing the figure
    figure;    
    imagesc( im );
    axis image;

    % Letting the user be able to click for the foreground info
    fprintf('Click on the oranges\n');
    beep();
    [rxs,rys] = ginput();

    %  Get location of other pixels from the user.
    fprintf('Click on the NON-oranges\n');
    beep();
    [bgxs,bgys] = ginput();

    % rounding the pixels to make them easier to work with
    rxs = round( rxs );
    rys = round( rys );
    for berry_idx = 1 : length( rxs )
        fg_color( berry_idx, 1:3 ) = im( rys(berry_idx), rxs(berry_idx), : );
    end
    
    %  Get other background color values.
    bgxs = round( bgxs );
    bgys = round( bgys );
    for background_idx = 1 : length( bgxs )
        bg_color( background_idx, 1:3 ) = im( bgys(background_idx), bgxs(background_idx), : );
    end
    
    %  For each input pixel, figure out distance to the raspberry colors.
    [im_red, im_grn, im_blu] = imsplit( im );
    
    pixel_data = [ im_red(:), im_grn(:), im_blu(:) ];  
    
    %  For each input pixel, find   distance to the background colors.
    %  If a pixel is closer to the raspberry color than to a the other,
    %       classify it as a raspberry
    %  else
    %       call it a background pixel.
   
    % calculating the mahalanobis distance
    fg_dist = mahal( pixel_data, fg_color );
    bg_dist = mahal( pixel_data, bg_color );
    
    % getting all of the foreground pixels
    b_is_fg = fg_dist < bg_dist;
    
    % reshaping some pixels
    b_is_fg = reshape( b_is_fg, size(im,1), size(im,2) );
    
    % setting the colormap and displaying the image
    colormap("gray");
    im = im2uint8(b_is_fg);
    imagesc( im );
    
    % saving the image
    imwrite(im, 'orange_final_image.jpg');
end