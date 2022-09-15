package com.shopping.shopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shopping.shopping.data.member.MemberGradeVO;
import com.shopping.shopping.data.member.MemberInfoVO;

@Mapper
public interface MemberMapper {
    public void insertMemberGrade(MemberGradeVO data);
    public void insertMemberInfo(MemberInfoVO data);
    public boolean isExistMemberBySeq(Integer member_seq);
    public boolean isExistMemberInfo(String name);
    public void deleteMemberInfo(Integer member_seq);
    public void deleteMemberGrade(Integer member_seq);
    public MemberInfoVO selectMemberInfo(Integer member_seq);
    public List<MemberInfoVO> selectMemberList();
    public void updateMemberInfo(MemberInfoVO data);
    public MemberInfoVO selectLoginMember(String id, String pwd);
}