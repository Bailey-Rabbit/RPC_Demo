package zpy.rpc.core.registry;

public interface ServiceRegistry {
    <T> void register(T object);
    Object getService(String serviceName);
}
