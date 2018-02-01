package com.lamdevops.lambda.Functional._2PixelFunction;

import java.awt.*;

/**
 * Created by lam.nm on 7/17/2017.
 */
@FunctionalInterface
public interface PixelFunction {
    Color apply(int x, int y);
}
