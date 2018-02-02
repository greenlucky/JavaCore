package com.lamdevops.lambda.Functional._2PixelFunction;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by lam.nm on 7/17/2017.
 */
class ImageTest {
    @Test
    void createImage() {
        Image image = new Image();
        BufferedImage frenchFlag = image.createImage(150, 100,
                (x, y) -> x < 50 ? Color.BLUE : x < 100 ? Color.WHITE : Color.RED);
        System.out.println(frenchFlag);
    }

}