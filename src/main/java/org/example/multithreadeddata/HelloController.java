package org.example.multithreadeddata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, Long> idCol;
    @FXML
    private TableColumn<Person, String> nameCol, surnameCol, emailCol, genderCol, countryCol, domainCol;
    @FXML
    private TableColumn<Person, LocalDate> birthDateCol;
    @FXML
    private DatePicker dateFrom, dateTo;
    @FXML
    private ComboBox<String> sortPicker;

    @FXML private Label status1, status2, status3;

    private final List<Person> allData = Collections.synchronizedList(new ArrayList<>());
    private final ObservableList<Person> observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        domainCol.setCellValueFactory(new PropertyValueFactory<>("domain"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        tableView.setItems(observableList);

        sortPicker.getItems().addAll("Name A-Z", "Name Z-A", "ID increasingly", "ID decreasingly", "Birth Date");
    }

    @FXML
    private void handleStartLoading() {
        allData.clear();
        observableList.clear();

        Label[] statuses = {status1, status2, status3};

        for (int i = 1; i <= 3; i++) {
            DataLoading task = new DataLoading(i, allData, observableList, statuses[i-1]);
            new Thread(task).start();
        }
    }

    @FXML
    private void handleFilterByDate() {
        LocalDate from = dateFrom.getValue();
        LocalDate to = dateTo.getValue();

        if (from == null || to == null) return;

        List<Person> filtered = allData.stream()
                .filter(p -> !p.getBirthDate().isBefore(from) && !p.getBirthDate().isAfter(to))
                .collect(Collectors.toList());

        observableList.setAll(filtered);
    }

    @FXML
    private void handleSort() {
        String selection = sortPicker.getValue();
        if (selection == null) return;

        List<Person> sorted;
        switch (selection) {
            case "Name A-Z":
                sorted = observableList.stream()
                        .sorted(Comparator.comparing(Person::getFirstName))
                        .collect(Collectors.toList());
                break;
            case "Name Z-A":
                sorted = observableList.stream()
                        .sorted(Comparator.comparing(Person::getFirstName).reversed())
                        .collect(Collectors.toList());
                break;
            case "ID increasingly":
                sorted = observableList.stream()
                        .sorted(Comparator.comparingLong(Person::getId))
                        .collect(Collectors.toList());
                break;
            case "ID decreasingly":
                sorted = observableList.stream()
                        .sorted(Comparator.comparingLong(Person::getId).reversed())
                        .collect(Collectors.toList());
                break;
            case "Birth Date":
                sorted = observableList.stream()
                        .sorted(Comparator.comparing(Person::getBirthDate))
                        .collect(Collectors.toList());
                break;
            default:
                sorted = new ArrayList<>(observableList);
        }
        observableList.setAll(sorted);
    }
}
