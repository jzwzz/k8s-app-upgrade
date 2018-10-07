测试k8s滚动更新
======================


============
结论：
    通过配置k8s滚动更新。
    可以实现服务不间断升级；用户无感知。
    
    基础工作:
        session共享；
        配置探针readinessProbe



启动项目
---
    
    ./gradlew bootrun


编译
---
    
    ./gradlew build
    
=========================

3e10de95734a

=========================    
    
    docker build .    
    docker images
    
    docker commit 3e10de95734a jzwzz/k8s-app-upgrade
        
    docker tag 65fb3004c71a k8s-app-upgrade:1.0    
    docker images    
    docker tag 1537957027e5 k8s-app-upgrade:1.1

    docker tag c5598a3f107a jzwzz/k8s-app-upgrade:1.3.0   
     
    docker tag bdc8ebee511f jzwzz/k8s-app-upgrade:1.3.1   


    docker build -t k8s-app-upgrade:1.2.0 .
    docker build -t k8s-app-upgrade:1.2.1 .

    docker build -t k8s-app-upgrade:1.3.4 .

    docker save -o k8s-app-upgrade_1.2.1.tar k8s-app-upgrade:1.2.1
   
    scp k8s-app-upgrade_1.2.1.tar root@192.168.56.22:/Developer/k8s-app-upgrade/libs/release/libs
    
    docker load < k8s-app-upgrade_1.2.0.tar
    docker load < k8s-app-upgrade_1.2.1.tar



    docker build -t jzwzz/k8s-app-upgrade:1.3.4 .
    docker push jzwzz/k8s-app-upgrade:1.3.4


upload to docker hub
--
  docker login
  docker push jzwzz/k8s-app-upgrade:1.3.0
  docker push jzwzz/k8s-app-upgrade:1.3.1
  docker push jzwzz/k8s-app-upgrade:1.3.2
  docker push jzwzz/k8s-app-upgrade:1.3.3



url
---
    

========================
不能访问？？？为啥啊？


Svc 创建后不能访问，去掉端口？




问题排查：

Step1 检查POD端口是否能正常访问
===============================

- 查看pods
    
  
    kubectl get pods -o wide

- 登录到一个pod上
    
  
    kubectl exec k8s-app-upgrade-59657c9b6b-lz9xt -ti sh

- 检查是否能成功访问另外一个pods的地址和Pod端口

  
    wget http://10.2.36.24:8200?delay=2
    
    
    
Step2 检查通过Service的Selector是否正常查询到Pod
===============================

- 通过labels查询Pods


    kubectl get pods -l app=k8s-app-upgrade -o wide

    
    注意：在pod查询时，尽量避免使用name标签
    （对吗？）name标签会被加上随机数覆盖： k8s-app-upgrade-59657c9b6b-lz9xt
    
    
Test Case 
===    
   case 1 增加一个POD
   
       kubectl scale --replicas=1 deployment/k8s-app-upgrade 

   case 2 减少一个POD  （2个减少到1个）
                 
       没有负载时，一会就停止了
        
       有运行中的任务未完成；
   
       有负载时，停止了一个POD后，在另外一个POD上重试了。浏览器重试的？还是应用重试的？ 
  
       无运行中的任务，打开页面后，在线重新新请求，
   case 3 滚动升级POD
   
   case 4 POD线程终端，重启；
   
   case 5 有2个POD，异常终止了一个；
   case 6 有1个POD，异常退出了。
   
   
   TODO
    加入session，shiro等等。
    
    
    
   
升级步骤
===
前置条件：
    
    每个服务至少有2个POD在运行
    
    
    未配置健康检查：2000个请求，20个并发，每个并发访问100次，结果：945个请求成功；
    2000 945 / 1055
    
    
    NextAction:
        
    配置健康检查，
        没有失败。
    
1.
    
k8s-app-upgrade-56b7f656dd-2gv9t   1/1       Running   0          5m
k8s-app-upgrade-56b7f656dd-lmq2t   1/1       Running   0          7m
k8s-app-upgrade-5cb6cc5b8c-wc67r   0/1       Running   0          1m
   
使用2个服务，
    登录
    
前面Nginx代理到另外一个节点不丢失session；


定时任务集群化；
    

优雅停服务
  1. 通过springboot-graceful-shutdown 控制进入terminal状态的时间；
  2. 运行定时任务通过查看health状态，判断是否启动定时任务。


========================================================
springboot-graceful-shutdown

https://github.com/SchweizerischeBundesbahnen/springboot-graceful-shutdown



=======================================================-   
   
   
   
Referfance

=================
 
    Spring Boot Session共享2种方式 - https://blog.csdn.net/shuiluobu/article/details/77418896
    
    
    Spring Boot 内嵌容器 Tomcat / Undertow / Jetty 优雅停机实现 - http://www.spring4all.com/article/1022
    
    springboot-graceful-shutdown https://github.com/SchweizerischeBundesbahnen/springboot-graceful-shutdown