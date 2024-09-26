function HW05_Alex_Iacob_MAIN()

% first we have to remove the red text from the image
rgbImage = imread('domino.jpg');

% redChannel = rgbImage(:, :, 1);
% greenChannel = rgbImage(:, :, 2);
% blueChannel = rgbImage(:, :, 3);
% 
% % using a mask to catch red values that are above 200
% redMask = redChannel >= 200;
% redChannel(redMask) = 255;
% greenChannel(redMask) = 255;
% blueChannel(redMask) = 255;
% 
% % concatenating everything back
% rgbImage = cat(3, redChannel, greenChannel, blueChannel);

rgbImage = imcomplement(rgbImage);

% Read image
Igray = rgb2gray(rgbImage);
% Extract dots on all dice
% BW = Igray > 100;
% BW = imclearborder(BW);
% BW = imfill(BW,'holes');
% BW = imopen(BW,strel('disk',7));
% % Calculate centroid for each dot
% stats = struct2table(regionprops(BW,'Centroid'));
% % Clustering dots by k-means
% idx = kmeans(stats.Centroid,5,'Replicates',16);
% % Show the result
% figure
% imshow(imcomplement(rgbImage))
% hold on
% gscatter(stats.Centroid(:,1),stats.Centroid(:,2),idx)

imfindcircles(Igray, 15);

end

function show_all_files_in_dir( )
 % Find all files that match this regular expression:
 file_list = dir('*.jpg');
 for counter = 1 : length( file_list )
 fn = file_list(counter).name;
 fprintf('file %3d = %s\n', counter, fn );
 end

end