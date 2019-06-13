package com.restwebservice.synethesis.repository;

import com.restwebservice.synethesis.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepository extends JpaRepository<User,Integer> {

}
