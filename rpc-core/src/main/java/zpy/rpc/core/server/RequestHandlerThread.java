package zpy.rpc.core.server;

import lombok.AllArgsConstructor;
import zpy.rpc.common.entity.RpcRequest;
import zpy.rpc.common.entity.RpcResponse;
import zpy.rpc.core.registry.DefaultServiceRegistry;
import zpy.rpc.core.registry.ServiceRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class RequestHandlerThread implements Runnable{
    Socket socket;
    RequestHandler requestHandler;
    ServiceRegistry serviceRegistry;
    @Override
    public void run() {
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream((socket.getOutputStream()));
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object service = serviceRegistry.getService(rpcRequest.getInterfaceName());
            Object res = requestHandler.handle(rpcRequest,service);
            objectOutputStream.writeObject(RpcResponse.success(res));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("调用或发送时发生错误" + e);
        }
    }
}
