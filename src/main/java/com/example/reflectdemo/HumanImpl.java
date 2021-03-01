package com.example.reflectdemo;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HumanImpl extends Human {
    private Logger log = org.slf4j.LoggerFactory.getLogger(HumanImpl.class);
    public HumanImpl(){
        super();
    }

    public void publicMethod(String name) {
        log.info("增强日志");
        super.publicMethod(name);
    }
    public static void main(String[] args) {
        new HumanImpl().publicMethod("test");
    }
}
