package com.HRMS.repository;

import com.HRMS.repository.entity.Post;
import com.HRMS.repository.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPostRepository extends MongoRepository<Post, String> {

    Optional<Post> findByPostSubjectAndPostContent(String postSubject, String postContent);
    Optional<List<Post>> findAllByStatus(String status);
    Optional<Post> findById(String id);

}
