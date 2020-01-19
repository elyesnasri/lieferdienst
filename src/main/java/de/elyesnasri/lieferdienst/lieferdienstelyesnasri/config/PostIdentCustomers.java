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
        customer1Data.setLastName("Stadler");
        customer1Data.setFirstName("Flo");
        String customer1Date = "10-10-1997";
        Date customer1BirthDate = new SimpleDateFormat("dd-mm-yyy").parse(customer1Date);
        customer1Data.setBirthDate(customer1BirthDate);
        customer1Data.setBirthPlace("Regensburg");
        customer1Data.setSalutation(Salutation.MAN);
        Address customer1Address = new Address();
        customer1Address.setStreet("StadlerStr");
        customer1Address.setPostalCode(93049);
        customer1Address.setCity("Regensburg");
        customer1Address.setCountry("Deutschland");
        customer1Data.setAddress(customer1Address);
        customer1.setPersonalData(customer1Data);
        customers.add(customer1);

        PostIdentEntry customer2 = new PostIdentEntry();
        PersonalData customer2Data = new PersonalData();
        customer2Data.setLastName("Pongratz");
        customer2Data.setFirstName("Paul");
        String customer2Date = "14-02-1997";
        Date customer2BirthDate = new SimpleDateFormat("dd-mm-yyy").parse(customer2Date);
        customer2Data.setBirthDate(customer2BirthDate);
        customer2Data.setBirthPlace("Cham");
        customer2Data.setSalutation(Salutation.MAN);
        Address customer2Address = new Address();
        customer2Address.setStreet("ChamerStr");
        customer2Address.setPostalCode(93049);
        customer2Address.setCity("Cham");
        customer2Address.setCountry("Deutschland");
        customer2Data.setAddress(customer2Address);
        customer2.setPersonalData(customer2Data);
        customers.add(customer2);

        PostIdentEntry customer3 = new PostIdentEntry();
        PersonalData customer3Data = new PersonalData();
        customer3Data.setLastName("Meierhofer");
        customer3Data.setFirstName("Jannis");
        String customer3Date = "29-07-1997";
        Date customer3BirthDate = new SimpleDateFormat("dd-mm-yyy").parse(customer3Date);
        customer3Data.setBirthDate(customer3BirthDate);
        customer3Data.setBirthPlace("Regensburg");
        customer3Data.setSalutation(Salutation.MAN);
        Address customer3Address = new Address();
        customer3Address.setStreet("DonauStr");
        customer3Address.setPostalCode(93049);
        customer3Address.setCity("Regensburg");
        customer3Address.setCountry("Deutschland");
        customer3Data.setAddress(customer3Address);
        customer3.setPersonalData(customer3Data);
        customers.add(customer3);

        for (PostIdentEntry customer: customers) {
            this.postIdentRepository.save(customer);
        }

    }
}
