#include <jni.h>
#include <iostream>
#include <cstdlib>
#include <unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>    //strlen
#include<sys/socket.h>
#include<arpa/inet.h> //inet_addr
#include<netdb.h>
#include <android/log.h>
#include <memory.h>
#include <curl.h>
#include <string>
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,"Chenzhu",__VA_ARGS__)
using  namespace std;
/**
 *
 *
 **/
extern "C" JNIEXPORT jint
JNICALL
Java_com_amt_cip_Identify_getAntennaSum( JNIEnv *env, jobject  jobject_/* this */,jint antennaSum) {
    int baseNumArr[4]  = {9,12,15,18};
    int result = baseNumArr[0];
    for (int i = 0; i <4; i++) {
        int computeResult = antennaSum - baseNumArr[i];
        if (computeResult < 0) {
            result = baseNumArr[i];
            break;
        }
    }

    FILE * file;
    const char * file_path  = "/sdcard/testcz.txt";
    const char * write_content  = "fuck";
    if(0 == access(file_path,0)){
        //只需要以"w"形式打开就可以了,会清空以前的
        //a如果文件存在就添加内容，如果文件不存在就创建新文件
        file = fopen(file_path,"w");
    } else{
        file = fopen(file_path,"a");
    }
    fputs(write_content,file);//写入操作
    fclose(file);//关闭文件流
    return result;
}

size_t   receive_data(void *contents, size_t size, size_t nmemb, void *stream){
    string *str = (string*)stream;
    (*str).append((char*)contents, size*nmemb);
    return size * nmemb;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_amt_cip_Identify_getInfo(JNIEnv *env, jobject instance) {
    CURLcode res;
    string *getResponseStr = new string();
    CURL* pCURL = curl_easy_init();
    if (pCURL == NULL) {
        LOGI("----->curl_easy_init fail");
    }
    curl_easy_setopt(pCURL, CURLOPT_URL, "http://quan.suning.com/getSysTime.do");
    curl_easy_setopt(pCURL, CURLOPT_NOSIGNAL, 1L);
    curl_easy_setopt(pCURL, CURLOPT_TIMEOUT, 5);
    curl_easy_setopt(pCURL, CURLOPT_NOPROGRESS, 1L);
    curl_easy_setopt(pCURL, CURLOPT_WRITEFUNCTION, receive_data);
    curl_easy_setopt(pCURL, CURLOPT_WRITEDATA, (void *)getResponseStr);
    res = curl_easy_perform(pCURL);
    curl_easy_cleanup(pCURL);
    char * response = const_cast<char *>(getResponseStr->c_str());
    LOGI("----->%s---%d",response,res);
    return res;


}