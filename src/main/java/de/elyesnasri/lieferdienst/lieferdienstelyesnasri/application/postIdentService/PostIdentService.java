package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.postIdentService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PostIdentEntry;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IPostIdentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostIdentService implements IPostIdentService {

    @Autowired
    private final IPostIdentRepository postIdentRepository;

    public PostIdentService(IPostIdentRepository postIdentRepository) {
        this.postIdentRepository = postIdentRepository;
    }

    @Override
    public boolean verifyCustomer(VerifyPostIdent verifyPostIdent) {

        Iterable<PostIdentEntry> verifiedCustomers = postIdentRepository.findAll();
        for (PostIdentEntry customer: verifiedCustomers         ) {
            if (customer.equals(verifyPostIdent.getPersonalData().getLastName())) {
                // check birthday
            }
        }
        return false;
    }
}
