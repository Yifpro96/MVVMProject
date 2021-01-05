package com.aoxing.mymvvm

class Requester {
    companion object{

        private fun <T> getService(baseUrl: String, service: Class<T>): T {

            var clien = OkHttpClient.Builder()
                //自定义拦截器用于日志输出
                .addInterceptor(LogInterceptor())
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                //格式转换
                .addConverterFactory(GsonConverterFactory.create())
                //正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或其他类型
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(clien)
                .build()
            return retrofit.create(service)
        }

        //可用于多种不同种类的请求
        fun apiService(): ApiService {
            return getService(ApiService.baseUrl, ApiService::class.java)
        }

    }
}