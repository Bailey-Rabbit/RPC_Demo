package zpy.rpc.core.server;

import zpy.rpc.core.registry.ServiceRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.concurrent.*;

public class RpcServer {
    private static final int corePoolSize = 5;
    private static final int maxiumPoolSize = 50;
    private static final long keepAliveTime = 60;
    private final ExecutorService threadPool;
    private final ServiceRegistry serviceRegistry;
    private RequestHandler requestHandler = new RequestHandler();
    public RpcServer(ServiceRegistry serviceRegistry){
        this.serviceRegistry = serviceRegistry;

        BlockingQueue<Runnable> workingqueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize,maxiumPoolSize,keepAliveTime,TimeUnit.SECONDS,workingqueue,threadFactory);
    }
    public void start(int port){
        Socket socket;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while((socket = serverSocket.accept()) != null){
                System.out.println("客户端连接，IP：" + socket.getInetAddress() + socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket,requestHandler,serviceRegistry));
            }
        } catch (IOException e) {
            System.out.println("连接时发生错误" + e);
        }
    }
}
