package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByPhone(String phone);
}
