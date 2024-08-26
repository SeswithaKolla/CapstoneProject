package com.asc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asc.dto.LineItem;
import com.asc.repo.LineItemRepo;
@Service
public class LineItemService {
	@Autowired
    private LineItemRepo lineItemRepo;

    public LineItem save(LineItem lineItem) {
        return lineItemRepo.save(lineItem);
    }

    public LineItem findById(int id) {
        return lineItemRepo.findById(id).orElse(null);
    }

    public List<LineItem> findAll() {
        return lineItemRepo.findAll();
    }

    public void deleteById(int id) {
        lineItemRepo.deleteById(id);
    }
    public void delete(LineItem lineItem) {
        lineItemRepo.delete(lineItem);
    }
}
