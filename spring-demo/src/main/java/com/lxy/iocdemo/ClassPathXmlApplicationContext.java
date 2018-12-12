package com.lxy.iocdemo;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;

public class ClassPathXmlApplicationContext {

    private static String PATH;
    private static String ID;
    private static String CLASS;
    private static String NAME;
    private static String VALUE;

    public void init() {
        ID = "id";
        CLASS = "class";
        NAME = "name";
        VALUE = "value";
    }

    ClassPathXmlApplicationContext(String path) {
        init();
        PATH = path;
    }

    public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        // 1、解析xml
        if (StringUtils.isEmpty(beanId)) {
            return null;
        }

        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(this.getClass().getClassLoader().getResource(PATH));
        Element rootElement = read.getRootElement();
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            //使用beanid查找对应xml节点，获取class节点属性
            String id = element.attributeValue(ID);
            if (!beanId.equals(id)) {
                continue;
            }

            String attClass = element.attributeValue(CLASS);
            Class<?> forName = Class.forName(attClass);
            // 3、使用java的反射机制初始化类
            Object newInstance = forName.newInstance();
            List<Element> sonEle = element.elements();
            for (Element el : sonEle) {
                String name = el.attributeValue(NAME);
                String value = el.attributeValue(VALUE);
                Field nameFild = forName.getDeclaredField(name);
                nameFild.setAccessible(true);
                nameFild.set(newInstance, value);
            }
            return newInstance;
        }

        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, DocumentException, IllegalAccessException {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserEntity user = (UserEntity) app.getBean("userEntity");
        System.out.println(user.toString());
    }
}
