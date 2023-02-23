package kz.attractor.java.lesson44;

import kz.attractor.java.server.FileService;

import java.util.ArrayList;
import java.util.List;

public class ProfilesDataModel {
    private List<Employer> profile = new ArrayList<>();

    public ProfilesDataModel() {
        profile.addAll(FileService.readFileProfile());
    }

    public List<Employer> getProfile() {
        return profile;
    }
}
