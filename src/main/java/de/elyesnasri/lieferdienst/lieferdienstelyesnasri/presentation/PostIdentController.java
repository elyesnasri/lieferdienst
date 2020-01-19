package de.elyesnasri.lieferdienst.lieferdienstelyesnasri.presentation;

import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.postIdentService.IPostIdentService;
import de.elyesnasri.lieferdienst.lieferdienstelyesnasri.application.postIdentService.VerifyPostIdent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostIdentController {
    private IPostIdentService postIdentService;

    public PostIdentController(IPostIdentService postIdentService) {
        this.postIdentService = postIdentService;
    }

    @RequestMapping(value = "apis/postIdent/verifyPostIdent", method = RequestMethod.POST)
    @ResponseBody
    public Boolean verifyPostIdent(@RequestBody VerifyPostIdent verifyPostIdent) {
        return postIdentService.verifyCustomer(verifyPostIdent);
    }
}
