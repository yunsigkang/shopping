package com.shopping.shopping.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopping.shopping.data.member.MemberGradeVO;
import com.shopping.shopping.data.member.MemberInfoVO;
import com.shopping.shopping.data.member.MemberLoginVO;
import com.shopping.shopping.data.member.MemberTotalVO;
import com.shopping.shopping.mapper.MemberMapper;
import com.shopping.shopping.util.AESAlgorithm;

@Service
public class MemberService {
    @Autowired MemberMapper mem_mapper;
    //멤버 추가 service
    public ResponseEntity<Map<String, Object>> insertMemberInfo(MemberTotalVO data)throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        HttpStatus stat = null;
        String id = data.getMember_Info().getMi_id();

        if(mem_mapper.isExistMemberInfo(id)){
            resultMap.put("status",false);
            resultMap.put("status",id + " 사용중인 아이디입니다.");
            stat = HttpStatus.BAD_REQUEST;
        }
        else{
            MemberGradeVO grade = data.getMember_grade();
            MemberInfoVO info = data.getMember_Info();
            mem_mapper.insertMemberGrade(grade);
            info.setMi_mg_seq(grade.getMg_seq());
            // data.getMember_Info().setMi_mg_seq(data.getMember_grade().getMg_seq());
            info.setMi_pwd(AESAlgorithm.Encrypt(info.getMi_pwd()));
            mem_mapper.insertMemberInfo(info);
            resultMap.put("status",true);
            resultMap.put("status","회원 가입이 완료 되었습니다.");
            stat = HttpStatus.CREATED;
        }
        
        return new ResponseEntity<Map<String, Object>>(resultMap,stat);
    }
    //멤버 삭제 service
    public ResponseEntity<Map<String, Object>> deleteMemberInfo(Integer member_seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        HttpStatus stat = null;
        MemberInfoVO info = mem_mapper.selectMemberInfo(member_seq);
        if(info != null){
            if(mem_mapper.isExistMemberBySeq(member_seq)){
                mem_mapper.deleteMemberInfo(member_seq);
                mem_mapper.deleteMemberGrade(member_seq);
                resultMap.put("status",true);
                resultMap.put("status",info.getMi_id()+" 을/를 삭제하였습니다.");
                stat = HttpStatus.OK;
            }
        }
        else{
            resultMap.put("status",false);
            resultMap.put("status","존재하지 않은 아이디입니다.");
            stat = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap,stat);
    }
    //멤버 리스트 service
    public ResponseEntity<Map<String, Object>> selectMemberInfo(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("list",mem_mapper.selectMemberList());
        return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
    }
    //멤버 수정 service
    public ResponseEntity<Map<String, Object>> updateMemberInfo(MemberTotalVO data)throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
            Integer seq = (data.getMember_Info().getMi_mg_seq());
            data.getMember_Info().setMi_pwd(AESAlgorithm.Encrypt(data.getMember_Info().getMi_pwd()));
            data.getMember_Info().setMi_mg_seq(seq);
            mem_mapper.updateMemberInfo(data.getMember_Info());
            resultMap.put("status",true);
            resultMap.put("message","멤버 정보를 수정하였습니다.");

        return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
    }
    //로그인 조금 이상함
    public ResponseEntity<Map<String, Object>> loginMember(MemberLoginVO data, HttpSession session)throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        data.setPwd(AESAlgorithm.Decrypt(data.getPwd()));
        MemberInfoVO info = mem_mapper.selectLoginMember(data.getId(), data.getPwd());
        System.out.println(info);
        if(info != null){
            session.setAttribute("login", info);
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되셨습니다.");
        }
        else{
            resultMap.put("status", false);
            resultMap.put("message", "아이디/비밀번호가 잘못되었습니다.");
        }
        return new ResponseEntity<Map<String, Object>>(resultMap,HttpStatus.OK);
    }
}
