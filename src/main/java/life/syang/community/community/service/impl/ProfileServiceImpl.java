package life.syang.community.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.syang.community.community.mapper.ProfileMapper;
import life.syang.community.community.model.Question;
import life.syang.community.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileMapper profileMapper;
    @Value("${myconfig.page-size}")
    private int pageSize;

    @Override
    public PageInfo queryQuestionById(int id,int num) {
        if (!"".equals(id)){
            PageHelper.startPage(num,pageSize);
            List<Question> list= profileMapper.queryById(id);
            PageInfo page = new PageInfo(list);
            return page;
        }
        return null;
    }
}
