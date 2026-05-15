package org.example.multithreadeddata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoading implements Runnable{
    private final List<Person> personList;
    private final File file;
    private final int number;

    public DataLoading(int number){
        personList = new ArrayList<>();
        this.number = number;
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
