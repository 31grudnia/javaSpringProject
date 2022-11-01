package com.grymas.projectJava.repository;

import com.grymas.projectJava.model.Contact;
import com.grymas.projectJava.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles getByRoleName(String roleName);
}