package com.g5619.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{username}/{groupId}")
@Slf4j
public class WebSocketServer {
    //实例一个session，这个session是websocket的session
    private Session session;

    //存放当前用户名
    private String userName;

    //存放当前用户所属活动组Group
    private String groupIdd;

    //存放需要接受消息的用户名
    private String toUserName;

    //存放在线的用户数量
    private static Integer userNumber = 0;

    //存放websocket的集合
    private static ConcurrentHashMap<String,Set<WebSocketServer>> webSocketMap = new ConcurrentHashMap<>();

    //前端请求时一个websocket时
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("username") String username,
                       @PathParam("groupId") String groupId) throws IOException {

        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!webSocketMap.containsKey(groupId)){
            // 创建房间不存在时，创建房间
            Set<WebSocketServer> room = new HashSet<>();
            //添加用户
            this.session = session;
            //将当前对象放入set中
            room.add(this);
            webSocketMap.put(groupId, room);
            pvOpen(username,groupId);
        }else {
            // 房间已存在，直接添加用户到相应的房间
            this.session = session;
            webSocketMap.get(groupId).add(this);
            pvOpen(username,groupId);
        }
    }

    //前端关闭时一个websocket时
    @OnClose
    public void onClose() throws IOException {
        //从集合中移除当前对象
        Set<WebSocketServer> serverSet = webSocketMap.get(this.groupIdd);
        serverSet.remove(this);
        //在线用户数减少
        userNumber--;

        //通知下线
        Map<String, Object> map1 = new HashMap();
        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        map1.put("messageType", 2);
        //所有在线用户
        map1.put("onlineUsers", serverSet);
        //下线用户的用户名
        map1.put("username", this.userName);
        //返回在线人数
        map1.put("number", userNumber);
        //发送信息，所有人，通知谁下线了
        sendMessageAll(JSON.toJSONString(map1),this.userName);

        //通知修改用户列表
        // 更新在线人数(给所有人)
        Map<String, Object> map2 = new HashMap();
        //获得所有的用户
        Set<String> userLists = new TreeSet<>();
        for (WebSocketServer webSocket : serverSet) {
            userLists.add(webSocket.userName);
        }
        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        map2.put("messageType", 3);
        //把所有用户放入map2
        map2.put("onlineUsers", userLists);
        //返回在线人数
        map2.put("number", this.userNumber);
        // 消息发送指定人（所有的在线用户信息）
        sendMessageAll(JSON.toJSONString(map2),this.userName);
        log.info("【websocket消息】连接断开, 总数:{}", serverSet.size());
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message) throws IOException {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        Set<WebSocketServer> serverSet = webSocketMap.get(this.groupIdd);
        //将前端传来的数据进行转型
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(message);
        //获取所有数据
        String textMessage = jsonObject.getString("message");
        String username = jsonObject.getString("username");
        String type = jsonObject.getString("type");
        String tousername = jsonObject.getString("tousername");
        //群发
        if(type.equals("群发")){
            Map<String, Object> map3 = new HashMap();
            map3.put("messageType", 4);
            //所有在线用户
            map3.put("onlineUsers", serverSet);
            //发送消息的用户名
            map3.put("username", username);
            //返回在线人数
            map3.put("number", userNumber);
            //发送的消息
            map3.put("textMessage", textMessage);
            //发送信息，所有人，通知谁下线了
            sendMessageAll(JSON.toJSONString(map3),this.userName);
        }
        //私发
        else{
            //发送给对应的私聊用户
            Map<String, Object> map3 = new HashMap();
            map3.put("messageType", 4);
            //所有在线用户
            map3.put("onlineUsers", serverSet);
            //发送消息的用户名
            map3.put("username", username);
            //返回在线人数
            map3.put("number", userNumber);
            //发送的消息
            map3.put("textMessage", textMessage);
            //私聊用户
            map3.put("toUsername", tousername);
            //发送信息，所有人，通知谁下线了
            sendMessageTo(JSON.toJSONString(map3),tousername);

            //发送给自己
            Map<String, Object> map4 = new HashMap();
            map4.put("messageType", 4);
            //所有在线用户
            map4.put("onlineUsers", serverSet);
            //发送消息的用户名
            map4.put("username", username);
            //返回在线人数
            map4.put("number", userNumber);
            //发送的消息
            map4.put("textMessage", textMessage);
            //私聊用户
            map4.put("toUsername", tousername);
            //发送信息，所有人，通知谁下线了
            sendMessageTo(JSON.toJSONString(map4),username);
        }
    }

    /**
     *  消息发送所有人
     */
    public void sendMessageAll(String message, String FromUserName) throws IOException {
        for (WebSocketServer webSocket: webSocketMap.get(this.groupIdd)) {
            //消息发送所有人（同步）getAsyncRemote
            webSocket.session.getBasicRemote().sendText(message);
        }
    }

    /**
     *  消息发送指定人
     */
    public void sendMessageTo(String message, String ToUserName) throws IOException {
        //遍历所有用户
        for (WebSocketServer webSocket : webSocketMap.get(this.groupIdd)) {
            if (webSocket.userName.equals(ToUserName)) {
                //消息发送指定人
                webSocket.session.getBasicRemote().sendText(message);
                log.info("【发送消息】:", this.userName+"向"+ToUserName+"发送消息："+message);
                break;
            }
        }
    }

    /**
     *  对onopen中部分功能的封装
     */
    private void pvOpen(String username,String groupId) throws IOException {
        //增加在线人数
        userNumber++;
        //保存当前用户名
        this.userName = username;
        this.groupIdd = groupId;
        //获得所有的用户
        Set<String> userLists = new TreeSet<>();
        for (WebSocketServer webSocket : webSocketMap.get(groupId)) {
            userLists.add(webSocket.userName);
        }


        //将所有信息包装好传到客户端(给所有用户)
        Map<String, Object> map1 = new HashMap();
        //  把所有用户列表
        map1.put("onlineUsers", userLists);
        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        map1.put("messageType", 1);
        //  返回用户名
        map1.put("username", username);
        //  返回在线人数
        map1.put("number", this.userNumber);
        //发送给所有用户谁上线了，并让他们更新自己的用户菜单
        sendMessageAll(JSON.toJSONString(map1),this.userName);
        log.info("【websocket消息】小组： "+ this.groupIdd +",有新的连接, 总数:{ "+ this.userNumber+" }");

        // 更新在线人数(给所有人)
        Map<String, Object> map2 = new HashMap();
        //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
        map2.put("messageType", 3);
        //把所有用户放入map2
        map2.put("onlineUsers", userLists);
        //返回在线人数
        map2.put("number", this.userNumber);
        // 消息发送指定人（所有的在线用户信息）
        sendMessageAll(JSON.toJSONString(map2),this.userName);
    }

}
