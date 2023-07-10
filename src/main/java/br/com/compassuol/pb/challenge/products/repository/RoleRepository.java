package br.com.compassuol.pb.challenge.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.pb.challenge.users.model.RoleModel;


@Repository
public interface RoleRepository extends JpaRepository<RoleModel, String> {

    Optional<RoleModel> findByName(String name);
    
}
