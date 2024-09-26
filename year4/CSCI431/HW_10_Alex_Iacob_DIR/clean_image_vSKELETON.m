function im_cleaned = clean_image_vSKELETON( im_gray )
%  This encapsulates the noise cleaning part of the imaging chain.
%  
%  Add any noise cleaning here you want to do.
%
%  Noise cleaning, background removal, ...
%
%  This process is applied to all input images, and could shrink the image,
%  or delete the outside margin.
%

    % taking the histogram of the image
    hst = imhist(im_gray);

    % finding the cumulative histogram from the histogram's cumulative sum
    cumhst = cumsum(hst);
    frchst = cumhst(:) / cumhst(end);

    % taking the indeces from the top 94th percentile
    hist_index = find( frchst >= 0.94, 1, 'First' );
    
    % setting up the threshold
    threshold = hist_index+1;

    % getting the values above the threshold
    im_bright = im_gray > threshold;

    % This was determined empirically for a few images:
    octStrelA = strel( 'Disk', 9 );
    octStrelB = strel( 'Disk', 12 );
    im_cleaned = imerode( im_bright, octStrelA );
    im_cleaned = imdilate( im_cleaned, octStrelB );
end

