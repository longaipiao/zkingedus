package com.zking.zkingedu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;

/**
 * 上传文件配置类
 */
@Configuration
public class UploadSpringMvcConfig {

    @Bean(name = "multipartReslover")
    public CommonsMultipartResolver multipartReslover(){
        CommonsMultipartResolver multipartReslover = new CommonsMultipartResolver();
        multipartReslover.setDefaultEncoding("UTF-8");//设置编码方式
        multipartReslover.setMaxUploadSize(1024*1024*1024*10);//设置上传文件最大字节
        multipartReslover.setMaxInMemorySize(1024*1024*1024*1);//设定文件上传时写入内存的最大值，小于这最大值将不会生成临时文件。默认10240
        multipartReslover.setResolveLazily(true);//设置延迟解析文件，以便捕获文件大小异常

        try {
            multipartReslover.setUploadTempDir(new FileSystemResource("F:\\git\\zkingedu2\\zkingedu\\src\\main\\resources\\static\\user\\img"));
        } catch (IOException e) {
            System.out.println("上传文件IO异常");
            e.printStackTrace();
        }
        return multipartReslover;
    }
}
