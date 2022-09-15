package com.shopping.shopping.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.shopping.data.member.MemberLoginVO;
import com.shopping.shopping.data.member.MemberTotalVO;
import com.shopping.shopping.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberService mem_service;
    //멤버 추가 기능
    @PutMapping("/add")
    public ResponseEntity<Map<String, Object>> insertMemberInfo(@RequestBody MemberTotalVO data)throws Exception{
        return mem_service.insertMemberInfo(data);
    }
    //멤버 삭제 기능
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteMemberInfo(@RequestParam Integer member_seq){
        return mem_service.deleteMemberInfo(member_seq);
    }
    //멤버 불러오는 기능
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> selectMemberInfo(){
        return mem_service.selectMemberInfo();
    }
    //멤버 수정하는 기능
    @PatchMapping("/update")
    public ResponseEntity<Map<String, Object>> updateMemberInfo(@RequestBody MemberTotalVO data)throws Exception{
        return mem_service.updateMemberInfo(data);
    }
    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginMemeber(@RequestBody MemberLoginVO data, HttpSession session)throws Exception{
        return mem_service.loginMember(data,session);
    }
}
