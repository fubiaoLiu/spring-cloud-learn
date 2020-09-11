package com.xiaoliu.learn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @description: 请求缓存Command
 * @author: liufb
 * @create: 2020/8/5 10:06
 **/
public abstract class CacheCommand extends HystrixCommand<String> {
    private String cacheKay;

    protected CacheCommand(HystrixCommandGroupKey group, String cacheKay) {
        super(group);
        this.cacheKay = cacheKay;
    }

    @Override
    protected String getCacheKey() {
        return cacheKay;
    }
}
