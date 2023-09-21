package com.HRMS.repository;

import com.HRMS.repository.entity.Comment;
import com.HRMS.repository.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository extends MongoRepository<Comment, String> {
    Optional<List<Comment>> findAllByStatus(EStatus status);
    Optional<Comment> findOptionalById(String id);
    Optional<List<Comment>> findAllByCompanyName(String companyName);
    Optional<List<Comment>> findAllByCompanyId(String companyId);
}
