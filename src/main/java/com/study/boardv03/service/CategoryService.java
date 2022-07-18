package com.study.boardv03.service;

import com.study.boardv03.domain.Category;
import com.study.boardv03.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Category와 관련된 로직을 처리하는 클래스
 */

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     *
     * @return 모든 Category를 가지는 List
     */
    public List<Category> getCategoryList() {

        List<Category> categoryList = categoryRepository.getCategoryList();
        return categoryList;
    }

    /**
     *
     * @param categoryId : 찾을 Category의 categoryId
     * @return 해당 categoryId를 가지는 Category 인스턴스
     */
    public Category getCategoryById(int categoryId) {

        Category category = categoryRepository.getCategoryById(categoryId);
        return category;
    }

}
