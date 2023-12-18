package com.example.rentingcars.Repository;


import com.example.rentingcars.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    @Query("select c from User c where c.username=?1 and c.password=?2")
    User logIn(String username, String password);
}
