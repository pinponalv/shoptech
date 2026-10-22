package com.companny.pinponalv.shoptech.service;

import com.companny.pinponalv.shoptech.dto.CategoryRequest;
import com.companny.pinponalv.shoptech.dto.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse addCategory(CategoryRequest request);
    CategoryResponse updateCategory(Long id,CategoryRequest request);
    List<CategoryResponse> getCategories();
    CategoryResponse getCategory(Long id);
    void deleteCategory(Long id);

}
