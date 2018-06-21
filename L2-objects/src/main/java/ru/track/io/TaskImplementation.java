package ru.track.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.track.io.vendor.Bootstrapper;
import ru.track.io.vendor.FileEncoder;
import ru.track.io.vendor.ReferenceTaskImplementation;

import java.io.*;
import java.net.InetSocketAddress;

public final class TaskImplementation implements FileEncoder {

    /**
     * @param finPath  where to read binary data from
     * @param foutPath where to write encoded data. if null, please create and use temporary file.
     * @return file to read encoded data from
     * @throws IOException is case of input/output errors
     */
    @NotNull
    public File encodeFile(@NotNull String finPath, @Nullable String foutPath) throws IOException {
        File input = new File(finPath);
        File output;
        if (foutPath == null) {
            output = File.createTempFile("encoded_data", ".tmp");
            output.deleteOnExit();
        }
        else output = new File(foutPath);

        try(
                BufferedInputStream reader = new BufferedInputStream(new FileInputStream(input));
                FileWriter writer  = new FileWriter(output);) {

            byte[] bytes = new byte[3];

            int n = 0;
            while ((n = reader.read(bytes, 0, 3)) > 0) {

                int buf = 0;
                for (int i = 0; i < n; i++){
                    buf |= ((bytes[i] & 0xff) << (8 * (2 - i)));
                    // в buf лежат все байты на своих позициях
                }

                for (int i = 0; i < n + 1; i++) {
                    writer.write(toBase64[0x3f & (buf >> 6 * (3 - i))]);
                    //накладываем маску 111111 = 63, соответствующую 6 битам
                }

                if (n == 1) {
                    writer.write('=');
                    writer.write('=');
                }
                if (n == 2){
                    writer.write('=');
                }
            }

        }

        return output;


    }

    private static final char[] toBase64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static void main(String[] args) throws Exception {
        final FileEncoder encoder = new TaskImplementation();
        // NOTE: open http://localhost:9000/ in your web browser
        (new Bootstrapper(args, encoder))
                .bootstrap("", new InetSocketAddress("127.0.0.1", 9000));
    }

}
