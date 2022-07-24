package zpy.test;





import zpy.rpc.api.HelloObject;
import zpy.rpc.api.HelloService;

public class HelloServiceImple implements HelloService {

    @Override
    public String hello(HelloObject object) {
        System.out.println("接收到：{}" + object.getMessage());
        return "id=" + object.getId();
    }
}
