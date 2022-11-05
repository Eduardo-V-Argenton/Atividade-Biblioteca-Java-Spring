package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);
}
