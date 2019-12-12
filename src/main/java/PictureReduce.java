import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PictureReduce implements Runnable {


    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public PictureReduce(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try
        {
            for(File file : files)
            {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    continue;
                }

                BufferedImage thumbnail = Thumbnails.of(image)
                        .size(300, 300)
                        .asBufferedImage();

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(thumbnail, "jpg", newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
