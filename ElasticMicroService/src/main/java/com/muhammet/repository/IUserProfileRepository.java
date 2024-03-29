package com.muhammet.repository;

import com.muhammet.repository.entity.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends ElasticsearchRepository<UserProfile,String> {

    boolean existsByUserprofileid(Long id);

    Optional<UserProfile> findOptionalByAuthid(Long authid);
}
