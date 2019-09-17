package life.syang.community.community.cache;

import com.github.pagehelper.util.StringUtil;
import life.syang.community.community.dto.tagDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<tagDTO> get(){
        List<tagDTO> tagDTOS=new ArrayList<>();
        tagDTO program =new tagDTO();
        program.setCartegoryName("开发语言");
        program.setTags(Arrays.asList("javascript","php","css","Java","html","node.js","python","c","c++"));
        tagDTOS.add(program);
        tagDTO framework =new tagDTO();
        framework.setCartegoryName("平台框架");
        framework.setTags(Arrays.asList("spring","laravel","koa","struts"));
        tagDTOS.add(framework);
        tagDTO db =new tagDTO();
        db.setCartegoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","sqlserver"));
        tagDTOS.add(db);
        return tagDTOS;
    }

    public static  String isValid(String tags){
        String[] split= StringUtils.split(tags,",");
        List<tagDTO> tagDTOS=get();
        List<String> tagList=tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalidList = Arrays.stream(split).filter(t -> tagList.contains(t)).collect(Collectors.joining(","));
        return  invalidList;
    }
}
