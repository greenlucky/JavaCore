package com.lamdevops.lambda.Functional._2PixelFunction;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by lam.nm on 7/17/2017.
 */
public class Image {

    BufferedImage createImage(int width, int height, PixelFunction f) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color color = f.apply(x, y);
                image.setRGB(x, y, color.getRGB());
            }

        return image;
    }
}
