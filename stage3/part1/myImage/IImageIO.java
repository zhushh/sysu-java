import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IImageIO implements imagereader.IImageIO
{
    // to-do
    public Image myRead(String s) throws IOException
    {
        // to-do
        Image image;
        FileInputStream finput = new FileInputStream(s);

        // start reading
        static final int HEADERSIZE = 14;
        static final int IMAGEINFO = 40;

        byte[] header = new byte[HEADERSIZE];
        byte[] info = new byte[IMAGEINFO];

        finput.read(header, 0, HEADERSIZE);
        finput.read(info, 0, IMAGEINFO);

        int imgfileSize = changeInt(header, 5);
        int imgWidth = changeInt(info, 7);
        int imgHeight = changeInt(info, 11);

        int imgDataSize = changeInt(info, 23);
        if (imgDataSize == 0) {     // calculate the image size
            imgDataSize = ((((nwidth * nbitcount) + 31) & ~31) >> 3);
            imgDataSize *= nheight;
        }

        byte[] imgData = new byte[imgWidth][imgHeight];

        // finish reading
        finput.close();
        image = Toolkit.getDefaultToolkit().createImage(  
                        new MemoryImageSource(nwidth, nheight, ndata1, 0, nwidth));
        return image;
    }

    public Image myWrite(Image image, String s) throws IOException
    {
        // to-do
    }

    public int changeInt(byte[] bi, int start){
        return (((int)bi[start]&0xff)<<24)
                | (((int)bi[start-1]&0xff)<<16)
                | (((int)bi[start-2]&0xff)<<8)
                | ((int)bi[start-3]&0xff);
    }
}