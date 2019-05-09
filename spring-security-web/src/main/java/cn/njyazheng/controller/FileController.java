package cn.njyazheng.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    @PostMapping("/upload3")
    @ResponseBody
    public Map<String,Object> upload3(Part file3){
        Map<String,Object>map=new HashMap<>();
        String fileName=file3.getSubmittedFileName();
        LOGGER.info("-----------------------------------file1 SubmittedFileName:"+fileName);
        try {
            file3.write("upload/"+fileName);
        } catch (IOException e) {
            LOGGER.error("----------------------------------- IOException"+e.getLocalizedMessage());
            e.printStackTrace();
            map.put("message","上传失败");
            return map;
        }
        map.put("message","上传成功");
        return map;
    }
    
    @GetMapping("/file1")
    public ResponseEntity<byte[]> testRespnseEntity() {
        
        byte[] body = null;
        try (InputStream in = new FileInputStream("D:\\image\\kg.jpg")){
            body = new byte[in.available()];
            in.read(body);
        } catch (Exception e) {
            LOGGER.error("------------------------Exception"+e);
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        //下载时会显示的文件名称
        headers.add("Content-Disposition", "attachment;filename=kg.jpg");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }
}
