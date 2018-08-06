package com.octopus.affiliate.admin.service;

import com.github.pagehelper.PageHelper;
import com.octopus.affiliate.admin.db.dao.AffiliateUserMapper;
import com.octopus.affiliate.admin.db.model.AffiliateUser;
import com.octopus.affiliate.admin.db.model.AffiliateUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AffiliateUserMapper affiliateUserMapper;

    public List<AffiliateUser> querySelective(Integer page, Integer size) {
        AffiliateUserExample example = new AffiliateUserExample().createCriteria().example().page(page, size).orderBy(AffiliateUser.Column.id.asc());
        return affiliateUserMapper.selectByExample(example);
    }

    public int countSelective() {
        AffiliateUserExample example = new AffiliateUserExample();
        return (int) affiliateUserMapper.countByExample(example);
    }
}
