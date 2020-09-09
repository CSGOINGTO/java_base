package springcodeanalysis.ioc.impl;

import springcodeanalysis.ioc.MessageService;

public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
