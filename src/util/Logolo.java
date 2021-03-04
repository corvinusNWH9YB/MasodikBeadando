package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logolo {
    /**
     * Log fájl útvonala.
     */
    private String outputFilePath;

    public Logolo(){
        Path currentRelativePath = Paths.get("");
        String currentFullPath = currentRelativePath.toAbsolutePath().toString();
        outputFilePath = currentFullPath+"/log.txt";
    }

    /**
     * Logot készít a log.txt mappába.
     * @param szoveg
     * @throws IOException
     */
    public void log(String szoveg) throws IOException {
        FileWriter fw = new FileWriter(outputFilePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(szoveg + "\n");
        bw.close();
    }
}
