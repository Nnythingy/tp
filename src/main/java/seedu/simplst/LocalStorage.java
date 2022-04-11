package seedu.simplst;

import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Storage class that handles save file IO for task list.
 */
public class LocalStorage {
    private static final String OUT_DIR = "output";    // Honestly create a config file or smth
    private static final String SAVE_PATH = String.format("%s/%s", OUT_DIR, "STORAGE.json");
    public static final String WAREHOUSE_PATH = String.format("%s/%s", OUT_DIR, "WAREHOUSE.json");

    /**
     * Opens and reads file at SAVE_PATH.
     *
     * @return saveStr the JSON string from save file
     */
    public String readSaveFile() {
        String saveStr = "";
        try {
            FileReader fr = new FileReader(SAVE_PATH);
            int i;
            while ((i = fr.read()) != -1) {
                saveStr += ((char) i);
            }
            fr.close();
        } catch (IOException e) {
            System.err.println("Failed to open save file! " + e.getMessage() + "\n");
        }
        return saveStr;
    }

    public static String readSaveFile(String filePath) {
        String saveStr = "";
        try {
            FileReader fr = new FileReader(filePath);
            int i;
            while ((i = fr.read()) != -1) {
                char newChar = ((char) i);
                saveStr += (newChar);
            }
            fr.close();
        } catch (IOException e) {
            System.err.println("Failed to open save file! " + e.getMessage() + "\n");
        }
        return saveStr;
    }

    /**
     * Opens and writes serialised string versino of tasklist to file at SAVE_PATH.
     */
    public void writeSaveFile(String storeStr) {
        Path dir = Paths.get(OUT_DIR);
        //        File f = new File(OUT_DIR);
        if (!Files.exists(dir)) {   //createTempDirectory
            try {
                Files.createDirectory(Path.of(OUT_DIR));
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
        //        String filePath = String.format("%s/%s", OUT_DIR, "SAVE.json");
        try {
            FileWriter fw = new FileWriter(SAVE_PATH);
            fw.write(storeStr);
            fw.close();
        } catch (IOException e) {
            System.err.println("Failed to write to save file!" + e.getMessage());
        }

    }

    public static Boolean writeSaveFile(String storeStr, String filePath) throws IOException {
//        System.out.println("filePath: " + filePath);
        Path dir = Paths.get(filePath).toAbsolutePath().normalize().getParent();
//        System.out.println("dir: ");
//        System.out.println(dir);
        if (!Files.exists(dir)) {   //createTempDirectory
            try {
                Files.createDirectory(dir);
                System.out.printf("Output Directory created at %s!\n", dir.toString());
            } catch (IOException e) {
                System.err.println("Failed to create directory! " + e.getMessage());
                return false;
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(storeStr);
            fw.close();
        } catch (IOException e) {
            System.err.println("Failed to write to save file!" + e.getMessage());
            return false;
        }
        return true;
    }

    public static String json2str(JSONObject jo) {
        return jo.toString();
    }

}
