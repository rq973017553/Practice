#用来指定 CMake 最低版本为3.4.1
cmake_minimum_required(VERSION 3.4.1)

#该段代码会在指定的目录中生成好的so文件，但是该项目如果作为library似乎不合适，所以去掉该代码
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

# CMAKE_CURRENT_SOURCE_DI指向正在被处理的源码目录的路径
set(SRC_LIST ${CMAKE_CURRENT_SOURCE_DIR}/hello-jni.c)

# 创建一个动态库 hello-lib 直接引用libhellojni.a
add_library(hello-lib # 生成的so文件
            SHARED # SHARED:动态库 STATIC:静态库
            ${SRC_LIST} # C/C++源文件
            )
MESSAGE(STATUS "This is the binary dir: " ${PROJECT_SOURCE_DIR})

# #指定android和log添加到hello-lib中
target_link_libraries(hello-lib android log)