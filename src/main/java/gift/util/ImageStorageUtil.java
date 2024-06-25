package gift.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ImageStorageUtil {
    private static final String STORAGE_PATH = "/Users/hansol/Desktop/temp/kakao_step2_1/spring-gift-product/src/main/java/gift/imageStorage/";

    public static String saveImage(MultipartFile imageFile, Long productId) throws IOException {
        // Generate unique image file name using product ID and current timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String imageName = productId + "_" + timestamp + ".jpg"; // assuming image format is jpg

        // Save the image to storage
        byte[] bytes = imageFile.getBytes();
        String filePath = STORAGE_PATH + imageName;
        File outputFile = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(bytes);
        }
        return filePath;
    }

    public static String encodeImagePathToBase64(String imagePath) {
        // Encode the image path to Base64
        String encodedImagePath = Base64.getEncoder().encodeToString(imagePath.getBytes());
        return encodedImagePath;
    }

    public static String decodeBase64ImagePath(String base64ImagePath) {
        // Decode the Base64 encoded image path
        byte[] decodedBytes = Base64.getDecoder().decode(base64ImagePath);
        String decodedPath = new String(decodedBytes);
        return decodedPath;
    }

    public static void deleteImage(String imagePath) {
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }
}