function save_curr_fig_to_file( fn_out )
%  Save the current figure to a *.png filename.
%  Assume that the given filename is a *.png file.
%
%  This ability to save a file to a figure for inclusion in your project report
%  or thesis is INSANELY EFFICIENT.
%
%  I use it all the time to create images for classes.
%
%  -Dr. Thomas B. Kinsman
%

    %  Get a handle to the frame structure.  
    %  This is a structure with a few fields.
    %  1.  cdata        is the color data.
    %  2.  colormap     is the optional colormap.
    fr          = getframe( gcf );
    
    %  Save the image and colormap to the given PNG filename.
    %
    %  Let this routine figure out a colormap for the PNG file
    %  if it needs one.
    imwrite( fr.cdata, fn_out, 'PNG' );
end

