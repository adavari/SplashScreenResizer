package com.adavari;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Profile> profiles = getProfiles();

        String portPath = args[0];
        String landPath = args[1];

        try {
            Image port = ImageIO.read(new File(portPath));
            Image land = ImageIO.read(new File(landPath));

            String s = "/home/alireza/Downloads/output";


            for (Profile profile : profiles) {

                // portrait
                File portFolder = new File(s + "/drawable-" + profile.dpiName);
                portFolder.mkdirs();

                BufferedImage temp = resizeImage(port, profile.width, profile.height);
                File targetPort = new File(portFolder.getAbsolutePath() + "/background.png");
                ImageIO.write(temp, "png", targetPort);

                // landscape
                File landFolder = new File(s + "/drawable-land-" + profile.dpiName);
                landFolder.mkdirs();

                BufferedImage templand = resizeImage(land, profile.height, profile.width);
                File targetland = new File(landFolder.getAbsolutePath() + "/background.png");
                ImageIO.write(templand, "png", targetland);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static BufferedImage resizeImage(Image image, int width, int height) {
        BufferedImage tThumbImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

        Graphics2D tGraphics2D = tThumbImage.createGraphics();
        tGraphics2D.setBackground( Color.WHITE );
        tGraphics2D.setPaint( Color.WHITE );
        tGraphics2D.fillRect( 0, 0, width, height );
        tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        tGraphics2D.drawImage( image, 0, 0, width, height, null );

        return tThumbImage;
    }

    private static List<Profile> getProfiles() {

        List<Profile> profiles = new ArrayList<>();

        profiles.add(new Profile("ldpi", 200, 320));
        profiles.add(new Profile("mdpi", 320, 480));
        profiles.add(new Profile("hdpi", 480, 800));
        profiles.add(new Profile("xhdpi", 720, 1280));
        profiles.add(new Profile("xxhdpi", 960, 1600));
        profiles.add(new Profile("xxxhdpi", 1280, 1920));

        return profiles;

    }


    private static class Profile {

        private String dpiName;
        private int width, height;

        Profile(String dpiName, int width, int height) {
            this.dpiName = dpiName;
            this.width = width;
            this.height = height;
        }

        public String getDpiName() {
            return dpiName;
        }

        public void setDpiName(String dpiName) {
            this.dpiName = dpiName;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}

