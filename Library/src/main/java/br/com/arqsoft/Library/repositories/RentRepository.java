package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.models.RentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentModel, Integer> {
}
