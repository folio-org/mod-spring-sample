package org.folio.rest.model.repo;

import org.folio.rest.model.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface WizardRepo extends JpaRepository<Wizard, String> {

  @Modifying
  @Transactional
  @Query("UPDATE Wizard w SET w.magic = w.magic + 1 WHERE w.id = :id")
  public int incrementMagic(@Param("id") String id);

  @Modifying
  @Transactional
  @Query("UPDATE Wizard w SET w.magic = w.magic - 1 WHERE w.id = :id")
  public int decrementMagic(@Param("id") String id);

}
