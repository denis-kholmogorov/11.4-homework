import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        int newWidth = 300;

        String srcFolder = "/home/denis/Рабочий стол/fromFolder";
        String dstFolder = "/home/denis/Рабочий стол/toFolder";

        int processorsCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Количество ядер процессора - " + processorsCount);

        long start = System.currentTimeMillis();

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int cursor = 0;
        int middle = files.length/processorsCount;
        int remnant = files.length%processorsCount;

        for(int i = 0; i < processorsCount; i++){
            if (remnant > 0){
                File[] images = Arrays.copyOfRange(files, cursor, (cursor + middle + 1));
                System.out.println(images.length);
                new Thread(new PictureReduce(images, newWidth, dstFolder, start)).start();
                cursor = cursor + middle + 1;
                remnant--;
            }else {
                File[] images = Arrays.copyOfRange(files, cursor, (cursor + middle));
                System.out.println(images.length);
                new Thread(new PictureReduce(images, newWidth, dstFolder, start)).start();
                cursor = cursor + middle;
            }
        }
    }
}
