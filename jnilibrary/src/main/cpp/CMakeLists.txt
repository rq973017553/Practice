cmake_minimum_required(VERSION 3.4.1)

# 创建一个静态库 hello-jni 直接引用libhellojni.a
add_library(hello-jni # 生成的so文件
            SHARED # SHARED:动态库 STATIC:静态库
            hello-jni.c # C/C++源文件
            )

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni android log)