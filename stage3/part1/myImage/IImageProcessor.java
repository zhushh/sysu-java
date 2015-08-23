import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.MemoryImageSource;
import java.awt.image.RGBImageFilter;

public class IImageProcessor implements imagereader.IImageProcessor
{
    public class RedFilter extends RGBImageFilter
    {
        public RedFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xffff0000;
        }
    }

    public class GreenFilter extends RGBImageFilter
    {
        public GreenFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xff00ff00;
        }
    }

    public class BlueFilter extends RGBImageFilter
    {
        public BlueFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xff0000ff;
        }
    }

    public class GrayFilter extends RGBImageFilter
    {
        public GrayFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            int blue = rgb & 0xff;
            rgb >>= 8;
            int green = rgb & 0xff;
            rgb >>= 8;
            int red = rgb & 0xff;
            int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
            return (0xff000000 | (gray << 16) | (gray << 8) | gray);
        }
    }

    public Image showChanelR(Image image)
    {
        return Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(image.getSource(), new RedFilter()));
    }

    public Image showChanelG(Image image)
    {
        return Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(image.getSource(), new GreenFilter()));
    }

    public Image showChanelB(Image image)
    {
        return Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(image.getSource(), new BlueFilter()));
    }

    public Image showGray(Image image)
    {
        return Toolkit.getDefaultToolkit().createImage(
            new FilteredImageSource(image.getSource(), new GrayFilter()));
    }
}