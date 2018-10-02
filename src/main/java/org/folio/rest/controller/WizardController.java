package org.folio.rest.controller;

import org.folio.rest.model.Wizard;
import org.folio.rest.model.repo.WizardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class WizardController implements ResourceProcessor<Resource<Wizard>> {

  @Autowired
  private WizardRepo wizardRepo;

  @GetMapping("/wizards/{id}/magic/increment")
  public @ResponseBody ResponseEntity<String> incrementMagic(@PathVariable String id) {
    wizardRepo.incrementMagic(id);
    return ResponseEntity.ok("Success");
  }

  @GetMapping("/wizards/{id}/magic/decrement")
  public @ResponseBody ResponseEntity<String> decrementMagic(@PathVariable String id) {
    wizardRepo.decrementMagic(id);
    return ResponseEntity.ok("Success");
  }

  @Override
  public Resource<Wizard> process(Resource<Wizard> resource) {
    // @formatter:off
    resource.add(
      ControllerLinkBuilder.linkTo(
        ControllerLinkBuilder
          .methodOn(WizardController.class)
          .incrementMagic(resource.getContent().getId())
      ).withRel("incrementMagic")
    );
    resource.add(
      ControllerLinkBuilder.linkTo(
        ControllerLinkBuilder
          .methodOn(WizardController.class)
          .decrementMagic(resource.getContent().getId())
      ).withRel("decrementMagic")
    );
    // @formatter:on
    return resource;
  }

}
