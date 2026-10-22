package com.companny.pinponalv.shoptech.service.impl;

import com.companny.pinponalv.shoptech.dto.CategoryRequest;
import com.companny.pinponalv.shoptech.dto.CategoryResponse;
import com.companny.pinponalv.shoptech.model.Categorys;
import com.companny.pinponalv.shoptech.repository.CategorysRepository;
import com.companny.pinponalv.shoptech.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategorysRepository categorysRepository;

    @Override
    public CategoryResponse addCategory(CategoryRequest request) {
        Categorys category = new Categorys();
        category.setName(request.name());
        Categorys saved = categorysRepository.save(category);

        return new CategoryResponse(
                saved.getId(),
                saved.getName()
        );
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Categorys category = categorysRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(request.name());
        Categorys saved = categorysRepository.save(category);
        return new  CategoryResponse(
                saved.getId(),
                saved.getName()
        );
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Categorys> categories = categorysRepository.findAll();
        List<CategoryResponse> response = new ArrayList<>();
        for (Categorys category : categories) {
            CategoryResponse categoryResponse = new  CategoryResponse(
                    category.getId(),
                    category.getName()
            );
            response.add(categoryResponse);
        }
        return response;
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        Categorys category = categorysRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return new  CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public void deleteCategory(Long id) {
        if(!categorysRepository.existsById(id)){
            throw new RuntimeException("Category not found!");
        }
        categorysRepository.deleteById(id);
    }
}
