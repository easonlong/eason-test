package com.eason.coding.life.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by longyaokun on 2017/5/23.
 */
public class ImageMerge {

    public static void main(String[] args) throws Exception {
        BufferedImage bg = new BufferedImage(2000,2000, BufferedImage.TYPE_INT_RGB);
        BufferedImage pic1 = ImageIO.read(new FileInputStream("/Users/longyaokun/temp/pic/1.jpg"));
        Graphics2D g = bg.createGraphics();
        g.drawImage(pic1, 0, 0, 2000, 2000,null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.6f));
        File dir = new File("/Users/longyaokun/temp/pic");
        int x=0,y=0;
        for(File file : dir.listFiles()){
            BufferedImage image = ImageIO.read(new FileInputStream(file));
            g.drawImage(image, x, y, 200, 200, null );

            y += 100;
        }
        ImageIO.write(bg, "JPG", new File("/Users/longyaokun/temp/pic/3.jpg"));
        g.dispose();
    }
}
