import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public final class IImageIORunner
{
    private IImageIORunner() {}
    public static void main(String[] args)
    {
        IImageIO img = new IImageIO();
        Image image;
        try {
            image = img.myRead("../bmptest/2.bmp");
            img.myWrite(image, "ttt2.bmp");
        } catch (IOException e) {
            System.out.println("Failed!");
            return ;
        }
        System.out.println("Successfully!");
    }
}