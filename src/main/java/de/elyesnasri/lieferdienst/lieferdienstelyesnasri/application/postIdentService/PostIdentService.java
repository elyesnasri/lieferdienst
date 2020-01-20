package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.postIdentService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PostIdentEntry;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.repositories.IPostIdentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostIdentService implements IPostIdentService {

    @Autowired
    private final IPostIdentRepository postIdentRepository;

    public PostIdentService(IPostIdentRepository postIdentRepository) {
        this.postIdentRepository = postIdentRepository;
    }

    @Override
    public boolean verifyCustomer(VerifyPostIdent verifyPostIdent) {
        Date postIdentBirthDate = verifyPostIdent.getPersonalData().getBirthDate();
        Optional<PostIdentEntry> checkPostIdent = postIdentRepository.findPostIdentByBirthDate(postIdentBirthDate);
        if (checkPostIdent.isPresent()) {
            String checkLastName = checkPostIdent.get().getPersonalData().getLastName();
            String postIdentLastName = verifyPostIdent.getPersonalData().getLastName();
            if (checkLastName.equals(postIdentLastName)) {
                return true;
            }
        }
        return false;
    }
}
