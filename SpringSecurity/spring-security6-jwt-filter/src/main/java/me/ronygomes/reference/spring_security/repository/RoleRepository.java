package me.ronygomes.reference.spring_security.repository;

import me.ronygomes.reference.spring_security.domain.Role;
import me.ronygomes.reference.spring_security.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
