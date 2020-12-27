package com.example.training.admin.repository;

import java.util.Optional;

import com.example.training.admin.domain.Admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {

  Optional<Admin> findByName(String name);
}
