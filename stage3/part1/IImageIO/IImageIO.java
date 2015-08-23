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
    }

    public Image myWrite(Image image, String s) throws IOException
    {
        // to-do
    }

    public int ChangeInt(byte[] bi,int start){
        return (((int)bi[start]&0xff)<<24)
                | (((int)bi[start-1]&0xff)<<16)
                | (((int)bi[start-2]&0xff)<<8)
                | (int)bi[start-3]&0xff;
    }
}