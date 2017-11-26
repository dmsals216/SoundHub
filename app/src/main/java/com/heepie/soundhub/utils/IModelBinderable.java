package com.heepie.soundhub.utils;

import android.support.annotation.Nullable;

/**
 * Created by Heepie on 2017. 11. 25..
 * 제네릭을 사용하기 위해 생성
 * Binder가 Model를 xml에 Binding 하기 위해 생성
 * ItemBinding에서 구현
 */

public interface IModelBinderable {
    <U> void setModel(@Nullable U Model);
}
