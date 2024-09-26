
function Example_Hough_Transforms_Horizontal_lines_Through_NO_XForm_v45( filename_in )
FS = 24;
N_LINES_TO_FIND                     = 200;

SHOW_EDGE_COMPONENTS                = true;
SHOW_HISTOGRAM_OF_EDGE_MAGNITUDES   = true;
SHOW_EDGE_ANGLES                    = true;
SHOW_HOUGH_PEAKS                    = true;

PAUSE_TIME                          = 1;

    if ( nargin < 1 )
        filename_in = 'IMG_20221015_Cottage_for_Hough_Lines.jpg';
    end

    %
    %  The secret to getting good results from hough() 
    %  is to give it only meaningful edges.
    %
    workspace();        % What does this do?
    
    im_original     = im2double( imread( filename_in ) );
    
    zoom_figure( [1800 1200] );     % or figure('Position', [10 10 1800 1400] );
    imagesc( im_original );
    colormap(gray);
    title('Original Image', 'FontSize', FS );
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_01_Full_Color.png','PNG',0,'w',0,0);
    disp('Paused'); 
    pause( PAUSE_TIME );
    
    
    %  HERE ARE DIFFERENT METHODS FOR GETTING A GRAYSCALE VALUE:
    %
    %
    im_white_ish    = im_original(:,:,2);
    imagesc( im_white_ish );
    colormap(gray);
    title('Simply using ONLY the Green Channel:', 'FontSize', FS );
    disp('Paused'); 
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_02_Gray_From_Green.png','PNG',0,'w',0,0);
    pause( PAUSE_TIME );
    
    % rgb2gray() works using 0.299 * Red   + 0.587 * Green   + 0.114 * Blue  
    %
    % Why are these values the default values?
    % What do they say about the human vision system?
    %
    im_white_ish    = rgb2gray( im_original );
    imagesc( im_white_ish );
    colormap(gray);
    title('Grayscale from RGB2GRAY( )', 'FontSize', FS );
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_03_Gray_from_rgb2gray.png','PNG',0,'w',0,0);
    disp('Paused'); 
    pause( PAUSE_TIME );

    % Or we might want to try this:
    im_white_ish    = sum( im_original, 3 ) / 3;
    imagesc( im_white_ish );
    colormap(gray);
    title('Average of Red, Green, and Blue', 'FontSize', FS );
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_04_Gray_from_averaging_the_colors.png','PNG',0,'w',0,0);
    disp('Paused'); pause( PAUSE_TIME );


    %
    %  You can create your own "white" mixture:
    %
    rgb_components = [ ...
       0.6104981 ;
       0.5896869 ;
       0.5287356 ;
    ];

    % Checking that it is a unit vector -- that the sum adds up to 1.0:
    length_of_vector = norm( rgb_components )

    im_white_ish    = im_original(:,:,1) * rgb_components(1) + ...
                      im_original(:,:,2) * rgb_components(2) + ...
                      im_original(:,:,3) * rgb_components(3);
                  

    imagesc( im_white_ish );
    colormap(gray);
    title('Dr. Kinsman''s Own Custom Value of Gray', 'FontSize', FS );
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_05_Gray_from_Principal_Components_Analysis.png','PNG',0,'w',0,0);
    disp('Paused'); pause( PAUSE_TIME );



    im_gray          = imfilter( im_white_ish, fspecial('Gauss', 9, 1.0), 'same', 'repl' );
    %     im_g2           = im_g.^(2);   % Contrast enhancement?
    %     im_g2           = im_g.^(1/2);   % Contrast enhancement?
    
    
    imagesc( im_gray );
    colormap(gray);
    title('Image Softened Using a Local Gaussian Filter', 'FontSize', FS );
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_06_Edges_Softened_to_avoid_aliasing_and_Morie_Patterns.png','PNG',0,'w',0,0);
    disp('Paused'); pause( PAUSE_TIME );
    
    %
    %  DO NOT DO THIS!!  (Why not?)
    %
    %     im_edges = edge( im_g, 'Canny', [0.08 0.1] );
    
    %
    %  Instead do this:  (Why?)
    %
    %  This is kind of like a Sobel Edge Detector, but different:
    %
    fltr_dIdy       = [ -1  -2  -3  -2  -1 ;
                         0   0   0   0   0 ;
                         0   0   0   0   0 ;
                         0   0   0   0   0 ;
                        +1  +2  +3  +2  +1 ] / (4*9);
    fltr_dIdx    = fltr_dIdy.';
    
    dIdy            = imfilter( im_gray, fltr_dIdy, 'same', 'repl' );
    dIdx            = imfilter( im_gray, fltr_dIdx, 'same', 'repl' );
    dImag           = sqrt( dIdy.^2  + dIdx.^2 );
    

    % Visualize what you have done:
    if ( SHOW_EDGE_COMPONENTS )
        zoom_figure( [1800 1200] );     % or figure('Position', [10 10 1800 1400] );
        subplot( 2, 2, 1 );
        imagesc( im_gray ); colormap( gray ); axis image; title('Imput Image', 'FontSize', FS );

        subplot( 2, 2, 2 );
        imagesc( dIdy ); colormap( gray ); axis image;  title('dIdy', 'FontSize', FS );

        subplot( 2, 2, 3 );
        imagesc( dIdx ); colormap( gray ); axis image; title('dIdx', 'FontSize', FS );

        subplot( 2, 2, 4 );
        imagesc( dImag ); 
        colormap( gray ); 
        axis image;
        title('Edge Magnitude -- In Any Direction', 'FontSize', FS );
        colorbar;

        capture_graph( 'Fig_07_Edge_Decomposition.png','PNG',0,'w',0,0);
        pause(2);
    end
    
    %
    % A histogram of the edge magnitudes:
    %
    if ( SHOW_HISTOGRAM_OF_EDGE_MAGNITUDES )
        histogram_bin_edges = [0:0.0005:0.10];
        [freq,bins]         = histc( dImag(:), histogram_bin_edges );
        zoom_figure( [1800 1200]  );     % or whatever
        bar( histogram_bin_edges, freq );
        title( 'Edge Strength Bar Graph -- The Rayleigh Distribution', 'FontSize', FS );
        pause(2);
        set(gca,'Position',[0.05 0.05 0.9 0.9]);
        capture_graph( 'Fig_08_Rayleigh_Distribution.png','PNG',0,'w',0,0);
    end
    
    
    %
    % Visualize Angles:
    %
    % You should look at this so see that the angles may come in pairs.
    %
    dIangle         = atan2( -dIdy, dIdx ) * 180 / pi;
    if ( SHOW_EDGE_ANGLES )
        zoom_figure( [1800 1200]  );     % or whatever

        imagesc( dIangle );
        axis image;
        colormap( hsv(256) );
        colorbar;
        title( 'Edge Direction', 'FontSize', FS );
        
        set(gca,'Position',[0.05 0.05 0.9 0.9]);
        capture_graph( 'Fig_09_Edge_Direction__use_HSV_Colormap.png','PNG',0,'w',0,0);
        pause(2);
    end

    %
    %  Find strong HORIZONTAL gradients --> vertical edges.
    %  Here we ignore negative edges, to avoid getting two 
    %  lines next to each other.
    %
    %  Notice that the single '&' operator is used for vector operations.
    %
    b_im_edges_horiz     = ( dIdy         > 0.06 )  & ...
                           (( dIangle      >= -100 ) & ...
                            ( dIangle      <= -80 ) );
                    
    disp('Break Here');
    %
    %  This is an example of using Ordinal Filtering.
    %
    %  You know median filtering.  Ordinal filtering is similar.
    %  The image is sorted, and the Nth value is used in a region.
    %
    %         m5 = magic(5)
    % 
    %     m5 =
    % 
    %         17    24     1     8    15
    %         23     5     7    14    16
    %          4     6    13    20    22
    %         10    12    19    21     3
    %         11    18    25     2     9
    % 
    %     a = ordfilt2( m5, 9, ones(3,3) )
    %
    %
    
    weird_region = [ 1 1 1 1 1 ;
                     0 1 1 1 0 ; 
                     0 1 1 1 0 ;
                     0 0 1 0 0 ;
                     0 1 1 1 0 ;
                     0 1 1 1 0 ;
                     1 1 1 1 1 ].';
    num_ones    = sum( weird_region(:) );
    im_cleaned_horiz = ordfilt2( b_im_edges_horiz, num_ones-size(weird_region,2)-1, weird_region );
    
    vert_bar    = strel( [ 0 0 0 ; 
                           1 1 1 ;
                           0 0 0 ] );
    im_cleaned_horiz = imopen( im_cleaned_horiz, vert_bar );
    
    
    zoom_figure( [1800 1200] );
    imagesc( im_cleaned_horiz );
    colormap(gray);
    
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_10_Edges_Put_into_the_Hough_Line_Detector.png','PNG',0,'w',0,0);
    
    hough_angles = -90:2:89;
    [HoughSpace,Thetas,Rhos] = hough( im_cleaned_horiz, ...
                                      'RhoResolution',  35, ...
                                      'Theta',          hough_angles );
    peaks = houghpeaks( HoughSpace, N_LINES_TO_FIND, ...
                        'NHoodSize', [3 3], ...
                        'Theta', Thetas);
    if SHOW_HOUGH_PEAKS
        last_fig = gcf();               % get the current figure;
        
        zoom_figure([1800 1400] );      % Make a new figure;
        imagesc( HoughSpace );
        xticks = get(gca,'XTick');
        set(gca,'XTickLabels',      Thetas(xticks)    );
        yticks = get(gca,'YTick');
        set(gca,'YTickLabels',      Rhos(yticks)    );
        xlabel('Angle (Degrees),    \Theta', 'FontSize', FS   );
        ylabel('Distance (Pixels),  \rho',   'FontSize', FS   );
        set(gca,'FontSize', FS-4);
        hold on;
        plot( peaks(:,2), peaks(:,1),'ko', ...
                       'MarkerSize', 30,   ...
                       'LineWidth', 2);
        colorbar();

        set(gca,'Position',[0.05 0.05 0.9 0.9]);
        capture_graph( 'Fig_11_Hough_Peaks_Found.png','PNG',0,'w',0,0);
    
        % Restore the original figure:
        figure( last_fig );
    end

    % lines = houghlines( im_edges_vertical, Thetas, Rhos, peaks, 'FillGap', 10, 'MinLength', 30 );
    lines = houghlines( im_cleaned_horiz, Thetas, Rhos, peaks, 'MinLength', 100 );
    
    hold on;
    for line_idx = 1 : min( [N_LINES_TO_FIND, length(lines) ] )
        p1 = lines(line_idx).point1;
        p2 = lines(line_idx).point2;
        hold on;
        plot( [p1(1), p2(1)], [p1(2), p2(2) ], 'r-', 'LineWidth', 3 );
    end

    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_12_Hough_Lines_Found.png','PNG',0,'w',0,0);

    %
    %  Show these lines on the original image:
    %
    zoom_figure( [1800 1400] );
    imagesc( im_original );
    axis image;
    dims = size( im_original );
    hold on;
    
    n_lines_to_display = min( [N_LINES_TO_FIND, length(lines) ] );
    
    fprintf('Number of lines found = %d\n', n_lines_to_display );
    
    for line_idx = 1 : n_lines_to_display
        p1 = lines(line_idx).point1;
        p2 = lines(line_idx).point2;
        hold on;
        plot( [p1(1), p2(1)], [p1(2), p2(2) ], 'c-', 'LineWidth', 3 );
    end

    title('Original Image with Hough Lines Found on it.', 'FontSize', FS );
    set(gca,'Position',[0.05 0.05 0.9 0.9]);
    capture_graph( 'Fig_13_Hough_Lines_Found.png','PNG',0,'w',0,0);
    
end
