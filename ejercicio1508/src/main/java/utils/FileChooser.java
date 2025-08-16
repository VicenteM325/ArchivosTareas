package utils;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    private final static JFileChooser fileChooser = new JFileChooser();

    static {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select a Folder");
    }

    public static String selectPath(){
        final int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            final File folder = fileChooser.getSelectedFile();
            final String path = folder.getAbsolutePath();
            System.out.println("Se selecciono la carpetac " + path);
            return path;
        } else {
            return null;
        }
    }
}
