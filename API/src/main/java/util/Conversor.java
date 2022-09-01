package util;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Conversor {

    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    public static void convertStringToImage(String base64) {
        try {

            byte[] imageByteArray = java.util.Base64.getDecoder()
                    .decode(base64.getBytes(StandardCharsets.UTF_8));

            Path destinationFile = Paths.get("./src/main/resources/imagens/", "imagem1.jpg");
            Files.write(destinationFile, imageByteArray);



        } catch (Exception e) {
            e.getMessage();
        }
    }

}
