package br.com.arqsoft.Library.repositories;

import br.com.arqsoft.Library.enums.RoleName;
import br.com.arqsoft.Library.models.RoleModel;
import br.com.arqsoft.Library.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

    Optional<RoleModel> findByRoleName(RoleName roleName);

}
