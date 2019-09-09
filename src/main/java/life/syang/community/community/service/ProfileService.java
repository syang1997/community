package life.syang.community.community.service;


import com.github.pagehelper.PageInfo;
import life.syang.community.community.model.Question;

import java.util.List;

public interface ProfileService {
    PageInfo queryQuestionById(Integer id,Integer num);
}
