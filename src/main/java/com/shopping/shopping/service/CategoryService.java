package com.shopping.shopping.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.shopping.data.category.CategoryInfoVO;
import com.shopping.shopping.mapper.CategoryMapper;

@Service
public class CategoryService {
    @Autowired CategoryMapper cate_mapper;
    //카테고리 추가 기능
    public ResponseEntity<Map<String, Object>> insertCategoryinfo(CategoryInfoVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        HttpStatus stat = null;

        if(cate_mapper.isExistCategoryinfo(data.getCate_name())){
            resultMap.put("status", false);
            resultMap.put("message", "이미 사용중인 카테고리입니다.");
            stat = HttpStatus.BAD_REQUEST;
        }
        else{
            cate_mapper.insertCategoryinfo(data);
            resultMap.put("status", true);
            resultMap.put("message", "카테고리가 추가 되었습니다.");
            stat = HttpStatus.CREATED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap,stat);
    }
    //카테고리 삭제 기능
    public ResponseEntity<Map<String, Object>> deleteCategoryinfo(Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        HttpStatus stat = null;
        if(cate_mapper.isExistCategoryBySeq(seq)){
            cate_mapper.deleteCategoryinfo(seq);
            resultMap.put("status", true);
            resultMap.put("message", "카테고리가 삭제 되었습니다.");
            stat = HttpStatus.CREATED;
        }
        else{
            resultMap.put("status", false);
            resultMap.put("message", "카테고리가 없습니다.");
            stat = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap,stat);
    }
    //카테고리 불러오는 기능
    public ResponseEntity<Map<String, Object>> selectCategoryinfo(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        HttpStatus stat = HttpStatus.OK;
        
        resultMap.put("list",cate_mapper.selectCategoryinfo());

        return new ResponseEntity<Map<String, Object>>(resultMap,stat);
    }
    //카테고리 수정하는 기능
    public ResponseEntity<Map<String, Object>> updateCategoryinfo(CategoryInfoVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        HttpStatus stat = null;

        if(cate_mapper.isExistCategoryBySeq(data.getCate_seq())){
            cate_mapper.updateCategoryinfo(data);
            resultMap.put("status", true);
            resultMap.put("message", "카테고리가 수정되었습니다.");
            stat = HttpStatus.CREATED;
        }
        else{
            resultMap.put("status", false);
            resultMap.put("message", "존재하지 않은 카테고리입니다.");
            stat = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap,stat);
    }
}