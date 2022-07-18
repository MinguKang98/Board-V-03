package com.study.boardv03.repository;

import com.study.boardv03.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DB에 접근하여 Category와 관련된 작업을 하는 interface
 */

@Mapper
@Repository
public interface CategoryRepository {

    /**
     * 모든 Category를 List 형태로 select
     * @return 모든 Category를 가지는 List
     */
    List<Category> getCategoryList();

    /**
     * 입력받은 categoryId를 가지는 Category select
     * @param categoryId : select 할 Category의 categoryId
     * @return 해당 categoryId를 가지는 Category 인스턴스
     */
    Category getCategoryById(int categoryId);

}
