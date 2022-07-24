package zpy.rpc.core.server;

import lombok.AllArgsConstructor;
import zpy.rpc.common.entity.RpcRequest;
import zpy.rpc.common.entity.RpcResponse;
import zpy.rpc.common.enumeration.ResponseCode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class RequestHandler{
    public Object handle(RpcRequest rpcRequest,Object service) {
        Object res = null;
        try {
            res = invokeTargetMethod(rpcRequest, service);
            System.out.println("服务" + rpcRequest.getInterfaceName() + "成功调用方法" + rpcRequest.getMethodName());
        } catch (Exception e) {
            System.out.println("调用或发送时有错误发生" + e);
        }
        return res;
    }
    private Object invokeTargetMethod(RpcRequest rpcRequest,Object service) throws InvocationTargetException, IllegalAccessException{
        Method method;
        try{
            method = service.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamTypes());
        } catch (NoSuchMethodException e) {
            return RpcResponse.fail(ResponseCode.METHOD_NOT_FOUND);
        }
        return method.invoke(service,rpcRequest.getParameters());
    }
}
