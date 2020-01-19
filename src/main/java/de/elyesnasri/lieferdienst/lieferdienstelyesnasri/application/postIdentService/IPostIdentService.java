package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.postIdentService;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.persistence.entities.PostIdentEntry;

public interface IPostIdentService {
    boolean verifyCustomer (VerifyPostIdent verifyPostIdent);
}
