package com.muhammet.manager;

import com.muhammet.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.muhammet.constants.EndPoints.SAVE;
@FeignClient(name = "elastic-service-manager",
url = "http://localhost:9100/elastic/user",decode404 = true)
public interface IElasticServisManager {

    @PostMapping(SAVE)
    ResponseEntity<Void> addUser(@RequestBody UserProfile dto);
}
