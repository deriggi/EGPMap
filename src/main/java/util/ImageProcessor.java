/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.dai.tamislogin.TamisLogin;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jDeRiggi
 */
public class ImageProcessor {

    public static final int STANDARD_WIDTH = 390;
    private static String shrunkenImageFolder = "C:\\Users\\jderiggi\\Documents\\NetBeansProjects\\TAMISLogin\\outimages\\";

    public static String getShrunkenImageFolder() {
        return shrunkenImageFolder;
    }

    public static void setShrunkenImageFolder(String outFolder) {

        shrunkenImageFolder = outFolder + "/";
        new File(shrunkenImageFolder).mkdirs();
    }

    public static String getImage(CloseableHttpClient client, String url, String outputPath) {
//        return "bogus.jpg";
//        
        CloseableHttpResponse response = null;
        FileOutputStream fos = null;
        String imageOutPath = null;
        try {
            HttpGet imageGet = new HttpGet(url);

            response = client.execute(imageGet);
            byte[] imageArray = EntityUtils.toByteArray(response.getEntity());

            BufferedImage scaled = new ImageProcessor().writeImage(new ByteArrayInputStream(imageArray), ImageProcessor.STANDARD_WIDTH);
            imageOutPath = outputPath + new Date().getTime() + ".jpg";
            ImageIO.write(scaled, "jpeg", new File(imageOutPath));
            System.out.println(imageOutPath);

        } catch (IOException ex) {
            if (ex != null) {
                ex.printStackTrace();
            }

        } finally {

            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex1) {
                    Logger.getLogger(TamisLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex1) {
                    Logger.getLogger(TamisLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return imageOutPath;
    }

    public BufferedImage writeImage(InputStream is, int width) {
        BufferedImage bi;
        BufferedImage newImage = null;
        try {

            bi = ImageIO.read(is);
            if (bi != null) {
                int originalHeight = bi.getHeight();
                int originalWidth = bi.getWidth();

                int scaledHeight = getScaledHeight(originalWidth, originalHeight, STANDARD_WIDTH);
                newImage = new BufferedImage(STANDARD_WIDTH, scaledHeight, BufferedImage.TYPE_INT_RGB);
                final Graphics2D graphics2D = newImage.createGraphics();

                graphics2D.drawImage(bi, 0, 0, STANDARD_WIDTH, scaledHeight, null);

            }
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newImage;

    }

    private int getScaledHeight(int originalWidth, int originalHeight, int desiredWidth) {
        float sh = desiredWidth * ((float) originalHeight / (float) originalWidth);
        return new Double(Math.floor(sh)).intValue();

    }
}
