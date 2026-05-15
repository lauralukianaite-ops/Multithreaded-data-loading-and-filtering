package org.example.multithreadeddata;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

public class DataLoading implements Runnable {
    private final List<Person> personList;
    private final ObservableList<Person> observableList;
    private final File file;
    private final int number;

    public DataLoading(int number, List<Person> personList, ObservableList<Person> observableList) {
        this.number = number;
        this.personList = personList;
        this.observableList = observableList;
        this.file = new File("data/MOCK_DATA" + number + ".csv");
    }

    @Override
    public void run() {
        System.out.println("Thread number " + number + " started to load.");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                Person person = parseCsvLine(line);
                if (person != null) {
                    personList.add(person);

                    Platform.runLater(() -> {
                        observableList.add(person);
                    });

                    Thread.sleep(20);
                }
            }
            System.out.println("Thread number " + number + " have finished loading.");
        } catch (Exception e) {
            System.err.println("Error in thread " + number + ": " + e.getMessage());
        }
    }

    public Person parseCsvLine(String line) {
        try {
            String[] data = line.split(",");
            return new Person(
                    Long.parseLong(data[0]),
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                    data[5],
                    data[6],
                    LocalDate.parse(data[7])
            );
        } catch (Exception e) {
            return null;
        }
    }
}
