import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class ImageConversion {

    public static BufferedImage convertToCIEXYZ(BufferedImage image) {
        ColorSpace csXYZ = ColorSpace.getInstance(ColorSpace.CS_CIEXYZ);
        ColorSpace csRGB = ColorSpace.getInstance(ColorSpace.CS_sRGB);

        ColorConvertOp convertOp = new ColorConvertOp(csRGB, csXYZ, null);
        BufferedImage convertedImage = convertOp.filter(image, null);

        return convertedImage;
    }

    public static BufferedImage convertToRGB(BufferedImage image) {
        ColorSpace csXYZ = ColorSpace.getInstance(ColorSpace.CS_CIEXYZ);
        ColorSpace csRGB = ColorSpace.getInstance(ColorSpace.CS_sRGB);

        ColorConvertOp convertOp = new ColorConvertOp(csXYZ, csRGB, null);
        BufferedImage convertedImage = convertOp.filter(image, null);

        return convertedImage;
    }

}
