package kz.attractor.java.lesson44;

import kz.attractor.java.server.FileService;

public class ProfileDataModel {
    private final Employer profile;
    private HistoryBooks history;

    public ProfileDataModel() {
        ProfilesDataModel profiles = new ProfilesDataModel();
        this.profile = new Employer(profiles.getProfile().get(0).getName(), profiles.getProfile().get(0).getSurname(),
                profiles.getProfile().get(0).getReadingBooks(), profiles.getProfile().get(0).getCurrentBooks(),
                profiles.getProfile().get(0).getEmail(), profiles.getProfile().get(0).getUser(),
                profiles.getProfile().get(0).getPassword());
        for (int i = 0; i < FileService.readFileHistoryBooks().size(); i++) {
            this.history = new HistoryBooks(FileService.readFileHistoryBooks().get(i).getBook(),
                    FileService.readFileHistoryBooks().get(i).getEmail());
        }
    }

    public HistoryBooks getHistory() {
        return history;
    }

    public Employer getProfile() {
        return profile;
    }
}