package com.lxy.threadDemo;

import com.lxy.threadDemo.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

class UserSendThread implements Runnable {
    private List<UserEntity> listUser;

    UserSendThread(List<UserEntity> listUser) {
        this.listUser = listUser;
    }

    @Override
    public void run() {
        for (UserEntity userEntity : listUser) {
            System.out.println(Thread.currentThread().getName() + "," + userEntity.toString());
        }
        System.out.println();
    }
}

public class BatchSms {

    public static void main(String[] args) {
        //初始化数据
        //定义每个线程分批发送大小
        //计算每个线程分批跑的数据
        //分配发送

        // 1.初始化数据
        List<UserEntity> initUser = initUser();
        // 2.定义每个线程分批发送大小
        int userCount = 2;
        // 3.计算每个线需要分配跑的数据
        List<List<UserEntity>> splitList = splitList(initUser, userCount);
        for (int i = 0; i < splitList.size(); i++) {
            List<UserEntity> list = splitList.get(i);
            UserSendThread userSendThread = new UserSendThread(list);
            // 4.分配发送
            Thread thread = new Thread(userSendThread, "线程" + i);
            thread.start();
        }

    }

    private static List<UserEntity> initUser() {
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new UserEntity("userId-" + i, "userName-" + i));
        }

        return list;
    }

    private static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;
        List<List<T>> listArray = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            List<T> subList = new ArrayList<T>();
            for (int j = 0; j < listSize; j++) {
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
                if (pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if ((j + 1) == ((j + 1) * pageSize)) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }
}
