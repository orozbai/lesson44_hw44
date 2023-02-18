package kz.attractor.java.lesson44;

import kz.attractor.java.server.FileService;

import java.util.ArrayList;
import java.util.List;

public class EmployersDataModel {
    private List<Employer> employers = new ArrayList<>();

    public EmployersDataModel() {
        employers.addAll(FileService.readFileEmployers());
    }

    public List<Employer> getEmployers() {
        return employers;
    }
}
