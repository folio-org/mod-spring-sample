package org.folio.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.domain.model.AbstractBaseEntity;

@Entity
public class Wand extends AbstractBaseEntity {

  @Column
  @NotNull
  @Enumerated(EnumType.STRING)
  private Wood wood;

  @Column
  @NotNull
  @Size(min = 4, max = 100)
  private String core;

  @Column
  @NotNull
  @Min(0)
  @Max(100)
  private int strength;

  public Wand() {
    super();
    this.strength = 0;
  }

  public Wand(Wood wood, String core) {
    this();
    this.wood = wood;
    this.core = core;
  }

  public Wood getWood() {
    return wood;
  }

  public void setWood(Wood wood) {
    this.wood = wood;
  }

  public String getCore() {
    return core;
  }

  public void setCore(String core) {
    this.core = core;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

}
