package com.example.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.petshop.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    

    @Query(value = "Select * from Post where id != :id order by id desc limit 5",nativeQuery = true)
    List<Post> getListNewest(long id);

}
