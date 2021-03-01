cmake_minimum_required(VERSION 3.4.1)

include_directories(src/main/cpp/include)
add_library( curl
        SHARED
        IMPORTED )

set_target_properties( curl
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libcurl.so )

add_library(
             native-lib

             SHARED

             src/main/cpp/native-lib.cpp )




find_library( log-lib
              log )

target_link_libraries(
                       native-lib
                        ${log-lib}
                        curl
                       )