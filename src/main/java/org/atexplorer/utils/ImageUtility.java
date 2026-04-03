package org.atexplorer.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtility {

    public static BufferedImage scaledImage (BufferedImage original, int width, int height, boolean hasTransparency){

        int imageType = hasTransparency ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;

        BufferedImage scaledImage = new BufferedImage(width, height, imageType);

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
