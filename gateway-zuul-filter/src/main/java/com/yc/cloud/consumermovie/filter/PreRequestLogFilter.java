package com.yc.cloud.consumermovie.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreRequestLogFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreRequestLogFilter.class);
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        PreRequestLogFilter.LOGGER.info(String.format("send %s request to %s", request.getMethod(),request.getRequestURL().toString()));
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
