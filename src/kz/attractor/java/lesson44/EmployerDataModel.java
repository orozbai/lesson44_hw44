package kz.attractor.java.lesson44;

public class EmployerDataModel {
    private Employer employer;
    static EmployersDataModel employers = new EmployersDataModel();

    public EmployerDataModel() {
        this.employer = new Employer(employers.getEmployers().get(0).getName(),
                employers.getEmployers().get(0).getSurname(),
                employers.getEmployers().get(0).getReadingBooks(),
                employers.getEmployers().get(0).getCurrentBooks());
    }

    public Employer getEmployer() {
        return employer;
    }
}
