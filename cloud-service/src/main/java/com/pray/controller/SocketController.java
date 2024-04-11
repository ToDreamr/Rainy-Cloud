package com.pray.controller;

import com.pray.aop.SocketServer;
import com.pray.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SocketController
 * NIO通过Select方案搭建的服务器Socket
 * @author 春江花朝秋月夜
 * @since 2024/2/17 16:35
 */
@RestController()
@RequestMapping(path = "/socket")
public class SocketController {
    /**
     * @return 写出数据
     */
    @PostMapping("/write")
    @SocketServer
    public Result writeServer(@RequestParam String param, HttpServletRequest servletRequest){
        //创建一个新的SocketChannel，一会通过通道进行通信
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8000))){
            System.out.println("已连接到服务端！");
                channel.write(ByteBuffer.wrap(param.getBytes()));
                ByteBuffer buffer = ByteBuffer.allocate(128);
                channel.read(buffer);   //直接从通道中读取数据
                buffer.flip();
                String msg = "收到服务器返回：" + new String(buffer.array(), 0, buffer.remaining());
                return Result.ok(param,msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
