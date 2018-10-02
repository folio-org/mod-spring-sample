package org.folio.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.folio.rest.domain.model.AbstractBaseEntity;

@Entity
public class Wizard extends AbstractBaseEntity {

  @Column
  @NotNull
  @Size(min = 5, max = 100)
  private String name;

  @Column
  @NotNull
  @Min(0)
  @Max(100)
  private int magic;

  @OneToOne(fetch = FetchType.EAGER, optional = true)
  private Wand wand;

  public Wizard() {
    super();
    this.magic = 0;
  }

  public Wizard(String name, Wand wand) {
    this();
    this.name = name;
    this.wand = wand;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Wand getWand() {
    return wand;
  }

  public void setWand(Wand wand) {
    this.wand = wand;
  }

  public int getMagic() {
    return magic;
  }

  public void setMagic(int magic) {
    this.magic = magic;
  }

}
