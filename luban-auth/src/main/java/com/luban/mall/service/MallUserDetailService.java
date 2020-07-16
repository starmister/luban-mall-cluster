package com.luban.mall.service;

import com.luban.mapper.UmsMemberMapper;
import com.luban.model.UmsMember;
import com.luban.model.UmsMemberExample;
import com.luban.mall.domain.MemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Component
public class MallUserDetailService implements UserDetailsService {

    @Autowired
    private UmsMemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if(StringUtils.isEmpty(userName)) {
            log.warn("用户登陆用户名为空:{}",userName);
            throw new UsernameNotFoundException("用户名不能为空");
        }

        UmsMember umsMember = getByUsername(userName);

        if(null == umsMember) {
            log.warn("根据用户名没有查询到对应的用户信息:{}",userName);
        }

        log.info("根据用户名:{}获取用户登陆信息:{}",userName,umsMember);

        MemberDetails memberDetails = new MemberDetails(umsMember);

        return memberDetails;
    }


    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }
}
