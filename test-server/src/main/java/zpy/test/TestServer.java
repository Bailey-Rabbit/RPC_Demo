package zpy.test;

import zpy.rpc.api.HelloService;
import zpy.rpc.core.registry.DefaultServiceRegistry;
import zpy.rpc.core.registry.ServiceRegistry;
import zpy.rpc.core.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImple();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
