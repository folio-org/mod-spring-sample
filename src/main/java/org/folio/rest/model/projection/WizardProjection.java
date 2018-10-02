package org.folio.rest.model.projection;

import org.folio.rest.model.Wand;
import org.folio.rest.model.Wizard;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "expanded", types = Wizard.class)
public interface WizardProjection {

  public String getName();

  public int getMagic();

  public Wand getWand();

}
