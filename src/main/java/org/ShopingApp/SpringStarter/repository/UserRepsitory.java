package org.ShopingApp.SpringStarter.repository;

import java.util.Optional;

import org.ShopingApp.SpringStarter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepsitory extends JpaRepository<User, Integer> {

	Optional<User>  findUserByEmail(String email);
}
