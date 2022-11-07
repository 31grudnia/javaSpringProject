package com.grymas.projectJava.audit;

import com.grymas.projectJava.constants.SchoolConstants;
import com.grymas.projectJava.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SchoolInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> mapa = new HashMap<String, String>();
        mapa.put("App Name", "School");
        mapa.put("App Description", "School Web app");
        mapa.put("App Version", "1.0.0");
        mapa.put("Contact Email", "info@school.com");
        mapa.put("Contact Mobile", "+69 420 69 69 69");
        builder.withDetail("school-info", mapa);
    }

}