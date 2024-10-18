function ivy_segmentation()
    im_in = imread('images\IMG_3108.JPG');
    im_lab = rgb2hsv(im_in);

%     binarized = imbinarize(im_lab(:,:,2), "global");
%     removed_noise = medfilt2(binarized);
%     eroded = imerode(removed_noise);
%     imshow(binarized);
%     corners = detectHarrisFeatures(eroded);
%     hold on;
%     plot(corners.selectStrongest(20));
    tryingPart2()
%     trying()

 
end


function trying()
    im = imread("images\IMG_3127_2000_x_3000.jpg");
    im_gray = im2double(im(2:2:end, 2:2:end, 2));
    im_binary = imbinarize(im_gray, 0.3);
    imshow(im);
    pause();
    imshow(im_binary)
    removed_noise = medfilt2(im_binary);
    dilated = imdilate(removed_noise, strel('disk', 10));
    imshow(dilated);

%     corners = detectHarrisFeatures(dilated);
%     hold on;
%     plot(corners.selectStrongest(50));

end

function tryingPart2()
    hThresholds = [0.2, 0.45];
	
    im_in = imread('images/IMG_3127_2000_x_3000.jpg');
    hsv = rgb2hsv(double(im_in));
    hue=hsv(:,:,1);
    binaryH = hue >= hThresholds(1) & hue <= hThresholds(2);
    imshow(binaryH, []);
    axis on;
     
    closed = imclose(binaryH, strel('disk', 15));
    eroded = imerode(closed, strel('disk', 25));
%     imshow(eroded);

%     corners = detectHarrisFeatures(eroded);
%     hold on;
%     plot(corners.selectStrongest(50));

    vert = [-1 -2 -1;
             0 0 0;
             1 2 1]/ 4;
    
    hor = [-1 0 1;
           -2 0 2;
           -1 0 1]/ 4;
    
    OutputV = conv2(rgb2gray(double(im_in)), vert);
    OutputH = conv2(rgb2gray(double(im_in)), hor); 

    Output = sqrt((OutputV .^ 2) + (OutputH .^ 2));
    imshow(Output);

end
