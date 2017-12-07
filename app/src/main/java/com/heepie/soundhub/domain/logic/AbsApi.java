package com.heepie.soundhub.domain.logic;

import com.heepie.soundhub.BuildConfig;
import com.heepie.soundhub.Interfaces.ICallback;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Heepie on 2017. 11. 30..
 * 서버와 통신하기 위한 Retrofit 초기 설정
 */

abstract class AbsApi {
    Retrofit retrofit;

    public void createRetrofit(String defaultURL) {
        Retrofit.Builder rBuilder = new Retrofit.Builder();

        // 기본 URL 설정
        rBuilder.baseUrl(defaultURL);

        rBuilder.addConverterFactory(GsonConverterFactory.create(/*new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()*/));
        /*switch (defaultURL) {
            case BuildConfig.SERVER_URL:
                // Gson 팩토리로 JSON 데이터 처리 설정
                rBuilder.addConverterFactory(GsonConverterFactory.create(*//*new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()*//*));
                break;
        }*/

        // RxJava2 어뎁터 사용 설정
        rBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        // 해당 Retrofit 생성
        retrofit = rBuilder.build();
    }


/*    // 전체 데이터를 가져오는 메소드
    public abstract void getData(ICallback callback);*/
}
