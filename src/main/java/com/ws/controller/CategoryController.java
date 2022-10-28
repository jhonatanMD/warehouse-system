package com.ws.controller;

import com.ws.entity.dto.CategoryDto;
import com.ws.entity.dto.HeadquartersDto;
import com.ws.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping()
    public Flux<CategoryDto> getAll(@RequestAttribute("headquarters") Long headquarters){
        return categoryService.findAll(headquarters);
    }

    @PostMapping()
    public Mono<CategoryDto> save(@RequestAttribute("headquarters") Long headquarters, @RequestBody CategoryDto category){
        category.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Mono<CategoryDto> update(@RequestAttribute("headquarters") Long headquarters, @PathVariable("id") Long id, @RequestBody CategoryDto category){
        category.setHeadquarters(HeadquartersDto.builder().id(headquarters).build());
        return categoryService.update(category,id);
    }
}
