function feature_mat = get_features_SKELETON( im_cleaned )
% Given a grayscale image, which has been cleaned, find features for each region:

    %  Note that you want to be careful about which features you use here.
    %  For example, putting in the Center of Mass would be worthless,
    %  because it returns an (X,Y) location.  That is worthless
    %  unless you are comparing it to something like the Convex Hull...
    %  which is problematic.
    %
    %
    %  YOU MAY ADD OTHER FEATURES HERE:
    %
    feature_tbl = regionprops( 'table', im_cleaned, 'Area', 'MajorAxis', 'MinorAxis' );    

    %  Explicitly toss out small DIRT particles:
    % You should change this.  CHANGE ME!!
    b_too_small = feature_tbl.Area <= 2500;  % 50^2 pixels.  
    
    feature_tbl(b_too_small,:)      = [];

    if ( size(feature_tbl,1) == 0 )
        %  THIS IS DIRT:
        %  warning('No Valid features returned');
        feature_mat = [];
    else 
        % 
        %  Convert table to a matrix:
        %
        feature_mat = feature_tbl{:,:};
    end

end

