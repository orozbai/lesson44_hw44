package kz.attractor.java.lesson44;

public class ProfileDataModel {
    private Employer profile;
    static ProfilesDataModel profiles = new ProfilesDataModel();

    public ProfileDataModel() {
        this.profile = new Employer(profiles.getProfile().get(0).getName(), profiles.getProfile().get(0).getSurname(),
                profiles.getProfile().get(0).getReadingBooks(), profiles.getProfile().get(0).getCurrentBooks(),
                profiles.getProfile().get(0).getEmail(), profiles.getProfile().get(0).getUser(),
                profiles.getProfile().get(0).getPassword());
    }

    public Employer getProfile() {
        return profile;
    }
}