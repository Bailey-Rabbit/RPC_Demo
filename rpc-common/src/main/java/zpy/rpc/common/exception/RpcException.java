package zpy.rpc.common.exception;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import sun.reflect.generics.tree.SimpleClassTypeSignature;
import zpy.rpc.common.enumeration.RpcError;

public class RpcException extends RuntimeException {
    public RpcException(RpcError err, String detail){
        super(err.getMessage() + ":" + detail);
    }
    public RpcException(RpcError err){
        super(err.getMessage());
    }
    public RpcException(String message,Throwable cause){
        super(message,cause);
    }
}
