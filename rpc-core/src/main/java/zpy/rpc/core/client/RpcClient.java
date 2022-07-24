package zpy.rpc.core.client;

import zpy.rpc.common.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClient {
    public Object sendRpcRequest(RpcRequest rpcRequest,int port,String host){
        try(Socket socket = new Socket(host,port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream((socket.getOutputStream()));
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("调用时发生错误" + e);
            return null;
        }
    }
}
