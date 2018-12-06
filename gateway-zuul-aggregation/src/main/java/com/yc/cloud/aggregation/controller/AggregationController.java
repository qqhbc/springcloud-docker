package com.yc.cloud.aggregation.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.collect.Maps;
import com.yc.cloud.aggregation.GatewayZuulApplication;
import com.yc.cloud.aggregation.entity.User;
import com.yc.cloud.aggregation.service.AggregationService;

import rx.Observable;
import rx.Observer;

@RestController
public class AggregationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(GatewayZuulApplication.class);

    @Autowired
    private AggregationService aggregationService;
    
    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String,User>> aggregate(@PathVariable long id){
        Observable<HashMap<String,User>> details = this.aggregateObservable(id);
        return this.toDeferredResult(details);
    }

    // 聚合多个observable发射出的数据项
    public Observable<HashMap<String, User>> aggregateObservable(long id) {
        return Observable.zip(this.aggregationService.getUserById(id),this.aggregationService.getMovieUserByUserId(id), (user, movieUser) -> {
            HashMap<String, User> map = Maps.newHashMap();
            map.put("user", user);
            map.put("movieUser", movieUser);
            return map;
        });
    }
    
    public DeferredResult<HashMap<String,User>> toDeferredResult(Observable<HashMap<String,User>> details){
        DeferredResult<HashMap<String,User>> result = new DeferredResult<>();
        details.subscribe(new Observer<HashMap<String,User>>(){ //订阅

            @Override
            public void onCompleted() {
                LOGGER.info("完成。。。");
            }

            @Override
            public void onError(Throwable e) {
                LOGGER.error("发生错误。。。"+e);
            }

            @Override
            public void onNext(HashMap<String, User> t) {
                result.setResult(t);
            }
            
        });
        return result;
    }
}
