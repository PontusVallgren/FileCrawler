import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileCrawler {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for:");
        String searchWord = scanner.next();
        scanner.close();

        File startFolder = new File (System.getProperty("user.dir"));

        searchFiles(startFolder, searchWord);
    }

    private static void searchFiles(File file, String searchWord) {
        if(file.isFile()) {
            try {
                FileReader filereader = new FileReader(file.getPath());
                Scanner sc = new Scanner(filereader);

                while(sc.hasNext()) {
                    if (sc.nextLine().contains(searchWord)) {
                        System.out.println("Found it!" + file.getPath());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error" + file.getPath());
            }
        } else if(file.isDirectory()) {
            try {
                File[] folderFiles = file.listFiles();
                for(File filePath : folderFiles) {
                    searchFiles(filePath.getCanonicalFile(), searchWord);
                }
            } catch (IOException e) {
                System.out.println("Error" + file.getPath());
            }
        }
    }
}
