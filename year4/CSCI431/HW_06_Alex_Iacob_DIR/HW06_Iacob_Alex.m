function HW06_Iacob_Alex() 
    addpath( '../TEST_IMAGES' );
    addpath( '../../TEST_IMAGES' );

    im_rgb  = im2double( imread( 'peppers.png' ) );
    im_in   = rgb2gray( im_rgb );
    
%     fltr = [-1, 0, 1; 
%             -3, 0, 3; 
%             -1, 0, 1] / 10;
            
   %  OR 
   
%     fltr = [-2, -5, -2; 
%             0 , 0 , 0 ; 
%             2 , 5 , 2 ] / 18;
            
   % OR 
   
    fltr = fspecial('Gauss', 5, 2 );
   
     
    % Run my version of the filter:
    expect_ans = imfilter( im_in, fltr, 'same', 'repl' );
    
    % Display my results:
    figure('Position', [300 100 600 600]);
    imagesc( expect_ans );
    colormap(gray);         % Show in gray
    axis image;             % Make the axes have square pixels.
    title('My Results','FontSize', 24);
    colorbar;
    
    disp('Showing for 5 seconds:');
    pause(5);
    
    
    my_ans      = my_very_own_image_filter( im_in, fltr );
    
    im_diff     = imabsdiff( expect_ans, my_ans );
    
    imagesc( im_diff );
    axis image;
    colorbar;  
end


% 
%  NOTEs:  
%  1.  outputs must be named.
%
%  2.  Outputs are not returned with 'return...'.
%
%  3.  Functions start with the word "function".
%
function im_out = my_very_own_image_filter( im_in, fltr )

    im_out              = zeros( size(im_in) );   % Make the output the same SIZE by default.
    
    image_dimensions    = size( im_in );
    filter_dims         = size( fltr );

    for row = (1+floor(filter_dims(1)/2)) : (image_dimensions(1)-floor(filter_dims(1)/2))
        for col = (1+floor(filter_dims(2)/2)) : (image_dimensions(2)-floor(filter_dims(2)/2))
            % setting the result to insert into im_out to 0
            result = 0;

            % adjusting the current row and column to account for the
            % filter
            temp_row = row - floor(filter_dims(1)/2);
            temp_col = col - floor(filter_dims(2)/2);

            % iterating over the filter
            for inner_row = 1 : filter_dims(1)
                for inner_col = 1 : filter_dims(2)
                       % calculate the 
                    result = result + (im_in(temp_row, temp_col) * fltr(inner_row, inner_col));
                    % increment the column
                    temp_col = temp_col + 1;
                end
                % when we update the row, we have to reset the column
                temp_row = temp_row + 1;
                temp_col = col - floor(filter_dims(2)/2);
            end
            % setting the calculated result to the row and column of im_out
            im_out(row, col) = result;
        end
    end
    
    % Notice -- no "return" statement.
end 
