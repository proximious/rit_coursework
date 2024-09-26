function image_inversion()
    im_db = im2double(imread('cameraman.tif'));

    imagesc(im_db);
    colormap(gray(256));
    axis image;
    drawnow;
    pause(3);

    imagesc(1 - im_db);
    axis image;
end