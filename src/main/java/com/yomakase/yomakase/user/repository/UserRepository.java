package com.yomakase.yomakase.user.repository;

import com.yomakase.yomakase.user.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByAccount(String account);

    boolean existsByEmail(String email);
}
