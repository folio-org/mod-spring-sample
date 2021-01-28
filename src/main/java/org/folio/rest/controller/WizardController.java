package org.folio.rest.controller;

import org.folio.rest.model.Wizard;
import org.folio.rest.model.repo.WizardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class WizardController implements RepresentationModelProcessor<EntityModel<Wizard>> {

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
  public EntityModel<Wizard> process(EntityModel<Wizard> resource) {
    // @formatter:off
    resource.add(
      WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder
          .methodOn(WizardController.class)
          .incrementMagic(resource.getContent().getId())
      ).withRel("incrementMagic")
    );
    resource.add(
      WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder
          .methodOn(WizardController.class)
          .decrementMagic(resource.getContent().getId())
      ).withRel("decrementMagic")
    );
    // @formatter:on
    return resource;
  }

}
