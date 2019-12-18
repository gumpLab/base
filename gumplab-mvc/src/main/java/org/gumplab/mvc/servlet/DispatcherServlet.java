package org.gumplab.mvc.servlet;

import org.gumplab.mvc.annotation.Controller;
import org.gumplab.mvc.annotation.RequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList();

    private Map<String, Object> ioc = new HashMap<>();

    private Map<String, Method> handlerMapping = new HashMap<>();

    private Map<String, Object> controllerMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) {

        // 1. 加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2. 扫描类
        doScanner(properties.getProperty("scanPackage"));

        // 3. 拿到扫描的类，通过反射机制实例化后放到 IOC 容器<key value>中
        doInstance();

        // 4. 初始化 HandlerMapping
        initHandlerMapping();

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

    private void doScanner(String packageName) {
        // 把所有 . 替换成 /
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            // 递归读取包
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
                System.out.println("Spring容器扫描到的类有： " + packageName + "." + file.getName());
            }
        }
    }

    private void doInstance() {

        classNames.forEach(className -> {
            try {

                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    ioc.put(toLowerFirstWord(clazz.getSimpleName()), clazz.newInstance());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void initHandlerMapping() {

        try {

            for (Map.Entry entry : ioc.entrySet()) {
                Class<? extends Object> clazz = entry.getValue().getClass();
                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }
                // 拼 URL 时， 是 Controller 头的 URL 拼上方法的 URL
                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                    baseUrl = annotation.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(RequestMapping.class)) {
                        continue;
                    }
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    String url = annotation.value();
                    url = (baseUrl + "/" + url).replaceAll("/+", "/");
                    handlerMapping.put(url, method);
                    controllerMap.put(url, clazz.newInstance());
                    System.out.println(url + "," + method);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 把字符串的首字母小写
    private String toLowerFirstWord(String simpleName) {
        char[] charArray = simpleName.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
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
