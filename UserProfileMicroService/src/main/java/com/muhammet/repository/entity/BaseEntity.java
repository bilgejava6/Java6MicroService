package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder //BaseEntity miras alındığı için bunu kullanıyoruz.
@MappedSuperclass
public class BaseEntity {
    Long createat;
    Long updateat;
    boolean state;

}
