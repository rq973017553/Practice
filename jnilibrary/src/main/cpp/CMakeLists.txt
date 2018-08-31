cmake_minimum_required(VERSION 3.4.1)

# 创建一个动态库 hello-jni 直接引用libhellojni.a
add_library(hello-jni # 生成的so文件
            SHARED # SHARED:动态库 STATIC:静态库
            ${CMAKE_CURRENT_SOURCE_DIR}/hello-jni.c # C/C++源文件
                                                    # CMAKE_CURRENT_SOURCE_DI指向正在被处理的源码目录的路径
            )

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni android log)