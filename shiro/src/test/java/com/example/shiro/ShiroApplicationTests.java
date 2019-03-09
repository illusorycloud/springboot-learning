package com.example.shiro;

import com.example.shiro.controller.UserController;
import com.example.shiro.tools.MailService;
import com.example.shiro.tools.MailServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void contextLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Autowired
    private MailServiceImpl mailService;

    @Test
    public void testMail() {
        mailService.sendMail("xueduanli@163.com", "SpringBoot Email Sender", "Hello Email Server");
    }

    private Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("192.168.5.212", 6379);
    }

    @After
    public void after() {
        jedis.close();
    }

    @Test
    public void testRedis() {
        String set = jedis.set("address", "重庆");
        String address = jedis.get("address");
        System.out.println(address);

    }

    @Test
    public void Chinnel() {
        try {
            RandomAccessFile raf = new RandomAccessFile("D:/testttt.txt", "rw");
            FileChannel channel = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = channel.read(buffer);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            buffer2.put("this is nio test".getBytes());
            buffer2.flip();
            int write = channel.write(buffer2);
            while (read != -1) {
                System.out.println("read" + read);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    buffer.mark();
                    System.out.println((char) buffer.get());
                    buffer.reset();
                }
                buffer.clear();
                read = channel.read(buffer);
            }
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void clientChannel() {
        try {
            //连接
            SocketChannel sc = SocketChannel.open();
            Selector selector = Selector.open();
            sc.configureBlocking(false);
            SelectionKey key = sc.register(selector, SelectionKey.OP_CONNECT);
            int i = key.interestOps();
            SelectableChannel channel = key.channel();
            int i1 = key.readyOps();
            key.cancel();
            key.selector();
            Object attachment = key.attachment();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            int select = selector.select();
            selector.wakeup();
            selector.close();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isConnectable()) {

                }
                iterator.remove();
            }
            sc.connect(new InetSocketAddress("127.0.0.1", 33333));
            //读数据
            ByteBuffer writeBuffer = ByteBuffer.allocate(100);
            writeBuffer.put("this is message from client".getBytes());
            writeBuffer.flip();
            sc.write(writeBuffer);
            //写数据
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            sc.read(readBuffer);
            StringBuilder sb = new StringBuilder();
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                sb.append((char) readBuffer.get());
            }
            System.out.println("from service:" + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ServiceChannel() {
        try {
            Class<?>[] interfaces = this.getClass().getInterfaces();
            Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return null;
                }
            });
            //连接
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ServerSocket socket = ssc.socket();
            socket.bind(new InetSocketAddress("127.0.0.1", 33333));
            SocketChannel socketChannel = ssc.accept();
            //读数据
            ByteBuffer writeBuffer = ByteBuffer.allocate(100);
            writeBuffer.put("this is message from service".getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            //写数据
            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            socketChannel.read(readBuffer);
            StringBuilder sb = new StringBuilder();
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                sb.append((char) readBuffer.get());
            }
            System.out.println("from client:" + sb);
            socketChannel.close();
            ssc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestFile() {
        Path path = Paths.get("D:\\lillusory");
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            forFile(paths);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void forFile(DirectoryStream<Path> paths) {
        for (Path p : paths
        ) {
            System.out.println(p.getFileName());
            try {
                forFile(Files.newDirectoryStream(p));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void threadLocalTest() throws InterruptedException {
        ThreadLocal<Long> threadLocal = new ThreadLocal<>();
        threadLocal.set(System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis()-threadLocal.get());
    }

}

