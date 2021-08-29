package com.example.hpc.controller;

import com.example.hpc.model.domain.AdminDomain;
import com.example.hpc.model.dto.AdminDto;
import com.example.hpc.service.AdminService;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.filtering.criteria.AdminCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * just sys-admin can call this apis
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    /*
     * TODO add checking access for all admin controller apis
     *  just sys-admin can use this apis
     */

    @GetMapping("/{id}")
    public ResponseEntity<AdminDomain> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }

    @GetMapping
    public ResponseEntity<PagedResult<AdminDomain>> getAll(AdminCriteria criteria, Pageable pageable) {
        return ResponseEntity.ok().body(service.getAll(criteria, pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AdminDomain> create(@Valid @RequestBody AdminDto dto) {
        return ResponseEntity.ok().body(service.createAndUpdate(dto));
    }


    @PutMapping
    public ResponseEntity<AdminDomain> update(@Valid @RequestBody AdminDto dto) {
        return ResponseEntity.ok().body(service.createAndUpdate(dto));
    }

}
