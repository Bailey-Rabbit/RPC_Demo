package zpy.test;

import zpy.rpc.api.HelloObject;
import zpy.rpc.api.HelloService;
import zpy.rpc.core.client.RpcClientProxy;
import zpy.rpc.core.server.RpcServer;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy(9000,"127.0.0.1");
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12,"this is a test message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
