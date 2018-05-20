package com.nis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class DataProcessingService {

    private static final String ALGORITHM = "SHA-256";
    private static final String URL_STRING = "https://raw.githubusercontent"
        + ".com/Anastasia-vorontsova/gos-zakupki/master/proccessed_data"
        + ".csv";

    public File downloadFile() throws IOException {
        URL url = new URL(URL_STRING);
        File file = new File("data.csv");
        // delete existing file
        file.delete();

        FileUtils.copyURLToFile(url, file);

        return file;
    }

    public String checksum(File input) {
        try (InputStream in = new FileInputStream(input)) {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] block = new byte[4096];
            int length;
            while ((length = in.read(block)) > 0) {
                digest.update(block, 0, length);
            }
            return toHex(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }
}
