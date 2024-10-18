function poison_ivy_segmentation(in_image)
    im=imread(in_image);
    im= imresize(im,0.75);
    fontSize = 20;
    [nx,ny] = size(im) ;
%     fltr_dIdy = [ -1 -2 -1; 0 0 0 ; +1 +2 +1 ] / 8
%     fltr_dIdx = [ -1 -2 -1; 0 0 0 ; +1 +2 +1 ] / 8
%     dIdy = imfilter( grayscale_image, fltr_dIdy, ‘same’, ‘repl’ );
%     dImag = sqrt( dIdy.^2 + dIdx.^2 );
    im=imgaussfilt(im);
    im_size=size(im);
    r=round(im_size(1)*0.4);
    x0=round(im_size(2)/2);
    y0=round(im_size(1)/2);
    circleImage = false(im_size(1), im_size(2)); 
    [x, y] = meshgrid(1:im_size(2), 1:im_size(1)); 
    circleImage((x - x0).^2 + (y - y0).^2 <= r.^2) = true;
    axis('on', 'image');
    hold on;
    imshow(circleImage, []);
    maskedImage = bsxfun(@times, im, cast(circleImage,class(im)));
    imshow(maskedImage);
    pause();
    lab = rgb2lab(maskedImage);
    hsv=rgb2hsv(maskedImage);
    ab = lab(:,:,2:3);
    ab = im2single(ab);
    numColors=4;
    kmeansgrid=[lab(:,:,2),lab(:,:,3),hsv(:,:,1),hsv(:,:,3),];
    pixel_labels = imsegkmeans(ab,numColors,NumAttempts=3);
    B2 = labeloverlay(lab,pixel_labels);
    imshow(B2);
    title("Labeled Image a*b*");
    pause();
    mask1 = pixel_labels == 1;
    cluster1 = im.*uint8(mask1);
    imshow(cluster1);
    title("Objects in Cluster 1");
    pause();
    mask2 = pixel_labels == 2;
    cluster2 = im.*uint8(mask2);
    imshow(cluster2);
    pause();
    pixel_labels_mask = imsegkmeans(cluster2,2,NumAttempts=3);
    B2_mask = labeloverlay(maskedImage,pixel_labels_mask);
    imshow(B2_mask);
    title("Labeled Image a*b*_mask")
    pause();
    mask1_mask = pixel_labels_mask == 1;
    cluster1_mask = maskedImage.*uint8(mask1_mask);
    imshow(cluster1_mask);
    title("Objects in Cluster 1");
    pause();
    mask2_mask = pixel_labels_mask == 2;
    cluster2_mask = maskedImage.*uint8(mask2_mask);
    imshow(cluster2_mask);
    title('Circle Mask', 'FontSize', fontSize);
    title("Objects in Cluster 2");
    imwrite(cluster2_mask,"good_one.png");
    tryingPart2(cluster2_mask);
    pause();
end
function tryingpart3(im_in)
    im=imread(im_in);
    im= imresize(im,0.75);
    hsvImage=rgb2hsv(im);
    saturationImage = hsvImage(:,:,2);
    leafMask = saturationImage > 0.35;
    leafMask = bwareafilt(leafMask, 1);
    leafMask = imfill(leafMask, 'holes');
    labeledImage = bwlabel(leafMask);
    props = regionprops(labeledImage, 'BoundingBox');
    boundingBox = props.BoundingBox;
    croppedImage = imcrop(im, boundingBox);
    imshow(croppedImage);
    pause()
end

function tryingPart2(im_in)
    hThresholds = [0.2, 0.45];
	
%     im_in = imread('images/IMG_3127_2000_x_3000.jpg');
    hsv = rgb2hsv(double(im_in));
    hue=hsv(:,:,1);
    binaryH = hue >= hThresholds(1) & hue <= hThresholds(2);
    imshow(binaryH, []);
    axis on;
     
    closed = imclose(binaryH, strel('disk', 15));
    eroded = imerode(closed, strel('disk', 25));
    imshow(eroded);

    corners = detectHarrisFeatures(eroded);
    hold on;
    plot(corners.selectStrongest(50));
    C = corner(eroded,15);
    hold on;
    plot(C(:,1), C(:,2), 'r.', 'MarkerSize',30);

end