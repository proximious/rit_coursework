function FINAL_Alex_Iacob
    im_in = imread("Crn4761C.jpg");
    
    im_in = im2double(im_in);
    im_lab = rgb2lab(im_in);

    blue = im_lab(:,:,3) <= 0;

    no_blue = im_in - blue;

    faded_red = no_blue - (0.5 * im_in(:,:,1));

    [centers, radii] = imfindcircles(faded_red, [10, 500]);

    imshow(im_in);
    viscircles(centers, radii,'EdgeColor','g');
    
    frame = getframe(gcf());
    im_out = frame.cdata;
    fn_out = sprintf('Results_%s.jpg', getenv('USER') );
    imwrite(im_out, fn_out,'JPEG', 'Quality', 98);
end