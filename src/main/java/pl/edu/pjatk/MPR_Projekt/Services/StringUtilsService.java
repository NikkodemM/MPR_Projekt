package pl.edu.pjatk.MPR_Projekt.Services;

import org.springframework.stereotype.Service;

@Service
public class StringUtilsService {

    public String toUpperCase(String name) {
        return name.toUpperCase();
    }

    public String toLowerCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
