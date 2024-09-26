function HW08_Iacob_Alex_MAIN()
    HW08_Iacob_Alex_Part_A();
    pause();
end

function HW08_Iacob_Alex_Part_A()
    % read in the file and take in every other pixel to make the functions
    % run faster
    im_in = imread('IMG_HW08_20221025_House_Side.jpg');
    im = im_in( 2:2:end, 2:2:end, : );

    % turning the 
    im_white_ish = rgb2gray(im);

    im_gray = imfilter( im_white_ish, fspecial('Gauss', 9, 1.0), 'same', 'repl' );

    % making the 
    fltr_dIdy       = [ -1  -2  -3  -2  -1 ;
                         0   0   0   0   0 ;
                         0   0   0   0   0 ;
                         0   0   0   0   0 ;
                        +1  +2  +3  +2  +1 ] / (4*9);

    fltr_dIdx    = fltr_dIdy.';
    
    dIdy            = imfilter( im_gray, fltr_dIdy, 'same', 'repl' );
    dIdx            = imfilter( im_gray, fltr_dIdx, 'same', 'repl' );
    dImag           = sqrt( double(dIdy.^2)  + double(dIdx.^2) );
    
    imagesc(dImag);
    colormap(gray);
end

function HW08_Iacob_Alex_Part_B()
end
