package kz.attractor.java.lesson44;

import java.util.ArrayList;
import java.util.List;

public class EmployersDataModel {
    private List<Employer> employers = new ArrayList<>();

    public EmployersDataModel() {
        employers.addAll(List.of(new Employer("Орозбай", "Алтынбеков"),
                new Employer("Азидин", "Аманкулов"),
                new Employer("Максим", "Фарафонов"),
                new Employer("Алексей", "Подставин")));
    }

    public List<Employer> getEmployers() {
        return employers;
    }
}
