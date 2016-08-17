package homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by art
 */
public class FileUtils {
    /** path to the resources directory */
    private static final String RESOURCES_DIRECTORY = "src/main/resources/";

    /** empty constructor */
    public FileUtils() {
    }

    /**
     * Find and return file from path
     *
     * @param path full path to the file in system
     * @return File instance
     */
    public File getSystemFile(String path) {
        return new File(path);
    }

    /**
     * Find and return file from resources
     *
     * @param path full path to the file located in resource directory
     * @return File instance or null
     */
    public File getResourceFile(String path) {
        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new NullPointerException("no resources presented by this url");
        }
        return new File(resource.getPath());
    }

    /**
     * Reading a system file to String
     *
     * @param path full path to the file in system
     * @return file's content or empty String
     */
    public String readSystemFile(String path) throws FileNotFoundException {
        File file = getSystemFile(path);
        return filesContent(file);
    }

    /**
     * Reading a resource file to String
     *
     * @param path full path to the file located in resource directory
     * @return file's content or empty String
     */
    public String readResourceFile(String path) throws FileNotFoundException {
        File file = getResourceFile(path);
        return filesContent(file);
    }

    /**
     * Reading a given file to String. Replace new lines with \n
     *
     * @param file File instance
     * @return file's content or empty String
     */
    public String filesContent(File file) throws FileNotFoundException {
        if (file == null) {
            throw new NullPointerException("file can't be null");
        }
        // файл может быть не нуль, но не представлен в системе, тогда мы его прочитать не сможем
        if (!file.exists()) {
            throw new FileNotFoundException("no such file or directory found");
        }
        // или файл может быть директорией, тогда мы не будем его читать
        if (file.isDirectory()) {
            throw new FileNotFoundException("it is a directory. content can not be read");
        }
        StringBuilder result = new StringBuilder();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * Write String to the File in system
     *
     * @param path full path to the file in system
     * @param text String to write
     */
    public void writeSystemFile(String path, String text) {
        File file = getSystemFile(path);
        write(file, text);
    }

    /**
     * Write String to the resource File
     *
     * @param path full path to the file located in resource directory
     * @param text String to write
     */
    public void writeResourceFile(String path, String text) {
        File file = new File(RESOURCES_DIRECTORY + path);
        write(file, text);
    }

    /**
     * Write String to the File
     *
     * @param file File instance
     * @param text String to write
     */
    private void write(File file, String text) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                out.print(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check that file exists and file is a normal file (not directory)
     *
     * @param file File instance
     * @return true if it's exists and normal
     */
    public boolean isFile(File file) {
        return file.exists() && file.isFile();
    }

    /**
     * Check that file exists and file is directory
     *
     * @param file File instance
     * @return true if it's exists and directory
     */
    public boolean isDirectory(File file) {
        return file.exists() && file.isDirectory();
    }
}
