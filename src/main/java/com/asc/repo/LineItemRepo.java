package com.asc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asc.dto.LineItem;
@Repository
public interface LineItemRepo extends JpaRepository<LineItem, Integer> {

}
