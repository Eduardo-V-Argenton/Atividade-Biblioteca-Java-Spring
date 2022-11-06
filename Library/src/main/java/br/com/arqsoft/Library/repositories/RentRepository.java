package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.models.RentModel;
import br.com.arqsoft.Library.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<RentModel, Integer> {

    Optional<List<RentModel>> findAllByUser(UserModel user);
}
