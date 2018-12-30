package playtest;

/**
 * I assume all files are in the same folder
 */

import java.util.ArrayList;

public class SeaList {
    ArrayList<?> list;
    Common.XMLType type;
    String fileName;
    static String folderName;

    public SeaList(ArrayList<?> list, Common.XMLType type, String fileName) {
        this.list = list;
        this.type = type;
        this.fileName = fileName;
    }

    public ArrayList<?> getList() {
        return list;
    }

    public Common.XMLType getType() {
        return type;
    }

    public String getFile() {
        return fileName;
    }

    public static String getFolder() {
        return folderName;
    }
}
