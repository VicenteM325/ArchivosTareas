package services;

import models.VideoGame;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class VideoGameService {
    private List<VideoGame> videoGames;
    private final String filePath;

    public VideoGameService(String filePath) {
        this.videoGames = new ArrayList<>();
        this.filePath = filePath + File.separator + "list.vm";
        loadData();
    }

    private void saveInFile() {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))){
            objectOutputStream.writeObject(this.videoGames);

        } catch (IOException e) {
            System.out.println("Error saving video games. \n");
        }
    }

    private  List<VideoGame> returnVideoGameList(Object object){
        if(!(object instanceof List<?>)) return null;
        final List<?> list = (List<?>) object;
        for(Object item : list){
            if(!(item instanceof VideoGame))return null;
        }
        return (List<VideoGame>) list;
    }

    private void loadData(){
        final File file = new File(filePath);
        if(file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))){
                final Object object = objectInputStream.readObject();
                final List<VideoGame> list = returnVideoGameList(object);
                if(list == null){
                    System.out.println("VideoGame list is null");
                    return;
                }
                this.videoGames = list;
                System.out.println("VideoGame list loaded");
            } catch (Exception e) {
                System.out.println("VideoGame list loading failed");
            }
        }
    }
}
