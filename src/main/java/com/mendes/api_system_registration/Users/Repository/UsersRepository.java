package com.mendes.api_system_registration.Users.Repository;

import com.mendes.api_system_registration.Users.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository <UserModel, Long> {

}
