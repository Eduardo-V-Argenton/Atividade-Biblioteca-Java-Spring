package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.models.RentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentRepository extends JpaRepository<RentModel, UUID> {
}
