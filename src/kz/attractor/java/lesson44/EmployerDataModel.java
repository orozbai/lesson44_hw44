package kz.attractor.java.lesson44;

public class EmployerDataModel {
    private final Employer employer;
    static EmployersDataModel employers = new EmployersDataModel();

    public EmployerDataModel() {
        this.employer = new Employer(employers.getEmployers().get(0).getName(),
                employers.getEmployers().get(0).getSurname(),
                employers.getEmployers().get(0).getReadingBooks(),
                employers.getEmployers().get(0).getCurrentBooks(), employers.getEmployers().get(0).getEmail(),
                employers.getEmployers().get(0).getUser(), employers.getEmployers().get(0).getPassword());
    }

    public Employer getEmployer() {
        return employer;
    }
}