package life.syang.community.community.controller;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import life.syang.community.community.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.UUID;

@Controller
@Slf4j
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        FileDTO fileDTO = new FileDTO();

        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis()+"_"+ UUID.randomUUID()+suffix;

        String path = request.getSession().getServletContext().getRealPath("/static/images/upload//");
        System.out.println(path);

        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传图片失败"+e.toString());
        }
        fileDTO.setSuccess(1);
        fileDTO.setMessage("upload success!");
        fileDTO.setUrl("/static/images/upload/"+fileName);
//
//        JSONObject res = new JSONObject();
//        res.put("url", Constant.WEB_ROOT+"assets/msg/upload/"+fileName);
//        res.put("success", 1);
//        res.put("message", "upload success!");
//
//        return res;
        return fileDTO;
    }
}
