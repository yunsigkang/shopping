package com.shopping.shopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.shopping.data.category.CategoryInfoVO;

@Mapper
public interface CategoryMapper {
    public void insertCategoryinfo(CategoryInfoVO data);
    public void deleteCategoryinfo(Integer seq);
    public Boolean isExistCategoryinfo(String namem);
    public Boolean isExistCategoryBySeq(Integer seq);
    public List<CategoryInfoVO> selectCategoryinfo();
    public void updateCategoryinfo(CategoryInfoVO data);
}
