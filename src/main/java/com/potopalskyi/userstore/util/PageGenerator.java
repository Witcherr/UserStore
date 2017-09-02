package com.potopalskyi.userstore.util;

import com.potopalskyi.userstore.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageGenerator {
    private static PageGenerator pageGenerator;
    private Configuration configuration = new Configuration();

    private PageGenerator(){};

    public static PageGenerator getInstance(){
        if (pageGenerator == null){
            return new PageGenerator();
        }
        return pageGenerator;
    }

    public String createPage(String filename, List<User> userList){
        Map<String, Object> data = new HashMap<>();
        StringWriter out = new StringWriter();
        try {
            Template template = configuration.getTemplate(filename);
            data.put("userList", userList);
            template.process(data, out);
            out.flush();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return out.toString();
    }

}
