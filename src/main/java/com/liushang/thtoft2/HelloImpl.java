package com.liushang.thtoft2;

import org.apache.thrift.TException;
import thrift.generated.service.demo.Hello;

public class HelloImpl  implements Hello.Iface {
    @Override
    public String helloString(String word) throws TException {
        System.out.println("get " + word);
        return "hello " + word;
    }
}
