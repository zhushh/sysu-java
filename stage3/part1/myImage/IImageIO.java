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
        //static final int[] BITSETS = {32, 24, 16, 8}

        byte[] header = new byte[HEADERSIZE];
        byte[] info = new byte[IMAGEINFO];

        finput.read(header, 0, HEADERSIZE);
        finput.read(info, 0, IMAGEINFO);

        int imgfileSize = changeInt(header, 5);
        int imgWidth = changeInt(info, 7);
        int imgHeight = changeInt(info, 11);

        int imgBitCount = (((int) info[15] & 0xff) << 8) | (int) info[14] & 0xff;
        int imgDataSize = changeInt(info, 23);
        if (imgDataSize == 0) {     // calculate the image size
            imgDataSize = ((((imgWidth * imgBitCount) + 31) & ~31) >> 3);
            imgDataSize *= imgHeight;
        }

        int imgPad = (imgDataSize / imgHeight) - imgWidth * (imgBitCount / 8);
        int[] imgData = new int[imgWidth * imgHeight];
        byte[] imgBrgbs = new byte[(imgWidth + imgPad) * (imgBitCount / 8) * imgHeight];

        finput.read(imgBrgbs, 0, (imgWidth + imgPad) * (imgBitCount/8) * imgHeight);

        int nindex = 0;
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                imgData[imgWidth * (imgHeight - j - 1) + i] = ((255 & 0xff) << 24) 
                                | (((int) imgBrgbs[nindex + 2] & 0xff) << 16) 
                                | (((int) imgBrgbs[nindex + 1] & 0xff) << 8) 
                                | ((int) imgBrgbs[nindex] & 0xff);
                nindex += (imgBitCount / 8);
            }
            nindex += imgPad;
        }
 
        // finish reading
        finput.close();
        image = Toolkit.getDefaultToolkit().createImage(
                        new MemoryImageSource(imgWidth, imgHeight, imgData, 0, imgWidth));
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