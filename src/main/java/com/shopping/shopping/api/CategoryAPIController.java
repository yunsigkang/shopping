package com.shopping.shopping.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.shopping.data.category.CategoryInfoVO;
import com.shopping.shopping.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryAPIController {
    @Autowired CategoryService cate_service;
    //카테고리 추가 기능
    @PutMapping("/add")
    public ResponseEntity<Map<String, Object>> insertCategoryInfo(@RequestBody CategoryInfoVO data){
        return cate_service.insertCategoryinfo(data);
    }
    //카테고리 삭제 기능
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteCategoryInfo(@RequestParam Integer seq){
        return cate_service.deleteCategoryinfo(seq);
    }
    //카테고리 불러오는 기능
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> selectCategoryInfo(){
        return cate_service.selectCategoryinfo();
    }
    //카테고리 수정하는 기능
    @PatchMapping("/update")
    public ResponseEntity<Map<String, Object>> updateCategoryInfo(@RequestBody CategoryInfoVO data){
        return cate_service.updateCategoryinfo(data);
    }
}
