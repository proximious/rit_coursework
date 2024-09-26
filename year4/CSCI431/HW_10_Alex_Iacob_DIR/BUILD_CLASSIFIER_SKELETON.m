function BUILD_CLASSIFIER_SKELETON( )
%  This tests the use of the DecisionTree Classifiers to classify three different screw types.
%
%  "doc Classification Trees" for more information...
%
%  '20-Jul-2020'    Thomas B. Kinsman
%
input_dir      = 'Images_cropped_T5';

    screw_classes                   = 'ABC';

    initialize_the_feature_table    = true;

    for class_id = 1:length(screw_classes)

        this_cls_letter = screw_classes(class_id);
        file_pattrn     = sprintf('%s%cim_type_%c*.jpg', input_dir, filesep(), this_cls_letter );
        training_files  = dir( file_pattrn );   

        for idx = 1 : length( training_files )

            fn_in       = sprintf('%s%c%s', input_dir, filesep(), training_files(idx).name );
            fprintf('%s\n', fn_in );

            im_gray     = rgb2gray( imread( fn_in ) );

            %
            %  DO NOISE CLEANING ON THE IMAGE.
            %
            %  NOTE: Must be done exactly the same way as when BUILDING
            %  the classifier as when USING the classifier.
            %
            %
            %  CHANGE ME:  YOU NEED TO WRITE THIS ROUTINE...
            im_cleaned  = clean_image_vSKELETON( im_gray );

            %
            %  EXTRACT FEATURES FROM THE IMAGE.
            %
            %  AGAIN: Must be done exactly the same way as when BUILDING
            %  the classifier as when USING the classifier.
            %
            %  The feature extraction routine encapsulates the process of 
            %  finding small parts, and collecting features on them.
            %
            %  I don't even know what the features are.
            %
            %  CHANGE ME:  YOU NEED TO WRITE THIS ROUTINE...
            feats       = get_features_SKELETON( im_cleaned );

            n_new       = size( feats, 1 );

            if ( initialize_the_feature_table == true )
                initialize_the_feature_table                     = false;

                collected_feats                     = feats;
                class_list(1:n_new)                 = class_id;
            else
                collected_feats(end+1:end+n_new,:)  = feats;
                class_list(end+1:end+n_new)         = class_id;
            end
            
            imagesc( im_cleaned );
            colormap( copper );
            title( ['Screws of Class  ', this_cls_letter, '  '], ...
                   'FontSize', 32 );
            axis image;
            drawnow;
            print_mat( feats, 1, 'Features', 2 );
        end
    end

    % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    %
    %
    %   Now, build a classifier, based on the collected data:
    %
    %
    % %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    tree_classifier = fitctree( collected_feats, class_list );

    %
    %  Test using the tree -- Create a Confusion Matrix:
    %
    %  setup and fill in a confusion_matrix
    %
    con_mat = zeros(length(screw_classes));
    for instance_id = 1 : size( collected_feats, 1 )
        true_cls                        = class_list( instance_id );
        pred_cls                        = tree_classifier.predict( collected_feats(instance_id,:) );
        con_mat( pred_cls, true_cls )   = con_mat( pred_cls, true_cls ) + 1;
    end

    print_mat( con_mat, 1, 'confusion_matrix_on_training_data', 0 );
    fprintf('\n\n');

    save tree_classifier_634.mat tree_classifier;
        
end

