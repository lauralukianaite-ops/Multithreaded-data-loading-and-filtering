package org.example.multithreadeddata;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoading implements Runnable{
    private final List<Person> personList;
    private final ObservableList<Person> observableList;
    private final File file;
    private final int number;

    public DataLoading(int number, List<Person> personList, ObservableList<Person> observableList){
        this.number = number;
        this.personList = personList;
        this.observableList = observableList;
        this.file = new File("data/MOCK_DATA"+number+".csv");
    }

    @Override
    public void run(){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            boolean isFirstLine = true;
            int count = 0;

            while ((line = br.readLine()) != null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }

                Person = parseCsvLine(line);
            }
        }


    }


}
