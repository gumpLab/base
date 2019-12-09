package org.gumplab.mvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {

    Properties properties = new Properties();

    @Override
    public void init(ServletConfig config) throws ServletException {


        // 1. 加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2. 扫描类

        // 3. 拿到扫描的类，通过反射机制实例化后放到 IOC 容器<key value>中

        // 4. 初始化 HandlerMapping

    }

    private void doLoadConfig(String location) {
        // 把 web.xml 中的 contextConfigLocation 对应的 value 文件加载到流里面
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
