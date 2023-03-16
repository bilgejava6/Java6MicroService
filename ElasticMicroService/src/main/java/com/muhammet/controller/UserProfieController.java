package com.muhammet.controller;

import com.muhammet.dto.request.AddUserRequestDto;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.constants.EndPoints.*;
@RestController
@RequestMapping(ELASTIC+USER)
@RequiredArgsConstructor
public class UserProfieController {
    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequestDto dto){
        userProfileService.saveDto(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
     return  ResponseEntity.ok(userProfileService.findAll());
    }
}
