import imagereader.Runner;

public final class IImageProcessorRunner
{
    private IImageProcessorRunner() {}
    public static void main(String[] args)
    {
        IImageIO img = new IImageIO();
        IImageProcessor imgPro = new IImageProcessor();
        Runner.run(img, imgPro);
    }
}