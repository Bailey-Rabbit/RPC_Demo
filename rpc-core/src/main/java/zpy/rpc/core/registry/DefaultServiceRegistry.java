package zpy.rpc.core.registry;

import zpy.rpc.common.enumeration.RpcError;
import zpy.rpc.common.exception.RpcException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultServiceRegistry implements ServiceRegistry{
    private final Map<String,Object> serviceMap = new ConcurrentHashMap<>();
    private final Set<String> registeredService = ConcurrentHashMap.newKeySet();

    @Override
    public synchronized <T> void register(T object) {
        String serviceImpleName = object.getClass().getCanonicalName();
        if(registeredService.contains(serviceImpleName)){
            return;
        }
        registeredService.add(serviceImpleName);
        Class<?>[] interfaces = object.getClass().getInterfaces();
        if(interfaces.length == 0){
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        for(Class<?> i : interfaces){
            serviceMap.put(i.getCanonicalName(),object);
        }
        System.out.println("向接口“ + ”注册服务" + serviceImpleName);
    }

    @Override
    public synchronized Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null){
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
