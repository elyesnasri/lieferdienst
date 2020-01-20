package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.config;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.Address;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PersonalData;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PostIdentEntry;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.enums.Salutation;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IPostIdentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostIdentCustomers implements ApplicationRunner {

    @Autowired
    private final IPostIdentRepository postIdentRepository;

    public PostIdentCustomers(IPostIdentRepository postIdentRepository) {
        this.postIdentRepository = postIdentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<PostIdentEntry> customers = new ArrayList<>();

        PostIdentEntry customer1 = new PostIdentEntry();
        PersonalData customer1Data = new PersonalData();
        customer1Data.setLastName("Mehrfachvererbung");
        customer1Data.setFirstName("Manuel");
        String customer1Date = "01-12-1964";
        Date customer1BirthDate = new SimpleDateFormat("dd-MM-yyyy").parse(customer1Date);
        customer1Data.setBirthDate(customer1BirthDate);
        customer1Data.setBirthPlace("Regensburg");
        customer1Data.setSalutation(Salutation.MAN);
        Address customer1Address = new Address();
        customer1Address.setStreet("Galgenbergstraße 32");
        customer1Address.setPostalCode(93053);
        customer1Address.setCity("Regensburg");
        customer1Address.setCountry("Deutschland");
        customer1Data.setAddress(customer1Address);
        customer1.setPersonalData(customer1Data);
        customers.add(customer1);

        PostIdentEntry customer2 = new PostIdentEntry();
        PersonalData customer2Data = new PersonalData();
        customer2Data.setLastName("Polymorphie");
        customer2Data.setFirstName("Paula");
        String customer2Date = "24-07-1988";
        Date customer2BirthDate = new SimpleDateFormat("dd-MM-yyyy").parse(customer2Date);
        customer2Data.setBirthDate(customer2BirthDate);
        customer2Data.setBirthPlace("Regensburg");
        customer2Data.setSalutation(Salutation.WOMEN);
        Address customer2Address = new Address();
        customer2Address.setStreet("Seybothstraße 2");
        customer2Address.setPostalCode(93053);
        customer2Address.setCity("Regensburg");
        customer2Address.setCountry("Deutschland");
        customer2Data.setAddress(customer2Address);
        customer2.setPersonalData(customer2Data);
        customers.add(customer2);

        for (PostIdentEntry customer: customers) {
            this.postIdentRepository.save(customer);
        }

    }
}
