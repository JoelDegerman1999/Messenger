package se.joeldegerman.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.joeldegerman.messenger.database.DatabaseClass;
import se.joeldegerman.messenger.model.Profile;

public class ProfileService {
private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("joeld", new Profile(1l, "Joel", "Degerman", "joeld"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String name) {
		return profiles.get(name);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getId() <= 0) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String name) {
		return profiles.remove(name);
	}
}
