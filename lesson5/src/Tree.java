import java.io.File;
import java.io.IOException;

public class Tree {
    public static void main(String[] args) throws IOException {
        File file = new File(".");
        showFiles(file);
    }

    public static void showFiles(File file) {
        showFiles(file, "", true);

    }

    private static void showFiles(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└──");
            indent += "\t";
        } else {
            System.out.print("├──");
            indent += "│\t";
        }

        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                subDirTotal++;
        }
        int fileCount = 0;
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory())
                fileCount++;
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                showFiles(files[i], indent, subDirCounter == subDirTotal);
            } else {
                subFileCounter++;
                showFiles(files[i], indent, subFileCounter == fileCount);
            }
        }
    }
}