/**
 * Copyright 2008-2019 Dahai Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.config;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author: yaofeng
 * @create:2019-03-02-11:49
 **/
public class ContextFinalizer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers(); //这里如果Web应用拥有多个数据库的连接，可以一并关闭
           System.exit(0);//结束jvm
        //Thread[] allThreads = findAllThreads();
       // System.out.println(allThreads.length+"5555555555555555");
        //for (Thread thread:allThreads){
       //   thread.interrupt();
       // }
        Driver d = null;
        try {
            while (drivers.hasMoreElements()) {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
            }
        } catch (SQLException e) {
        }finally {
            try {
                AbandonedConnectionCleanupThread.shutdown();
            } catch (InterruptedException e) {
            }
        }
    }

    public Thread[] findAllThreads(){
        ThreadGroup group  = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup  = group;
        // traverse the ThreadGroup tree to the top
        while (group!=null){
            topGroup = group;
            group = group.getParent();

        }
        // Create a destination array that is about
        // twice as big as needed to be very confident
        // that none are clipped.
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] threadsArray = new Thread[estimatedSize];
        // Load the thread references into the oversized
        // array. The actual number of threads loaded
        // is returned.
        int actualSize = topGroup.enumerate(threadsArray);
        // copy into a list that is the exact size
        Thread[] threadList = new Thread[actualSize];
        System.arraycopy(threadsArray,0,threadList,0,actualSize);
        return threadList;
    }
}
