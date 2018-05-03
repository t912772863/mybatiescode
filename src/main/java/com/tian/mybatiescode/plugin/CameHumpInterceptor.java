package com.tian.mybatiescode.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.*;

/**
 * map类型下划线, 转小写驼峰形式
 * Created by Administrator on 2018/4/20 0020.
 */
//@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class CameHumpInterceptor implements Interceptor{
    public Object intercept(Invocation invocation) throws Throwable {
        // 先得到执行结果, 这里的强制转换, 是因为所拦截的方法的返回类型为这个类型
        List<Object> list = (List<Object>)invocation.proceed();
        for(Object o: list){
            if(o instanceof Map){
                processMap((Map)o);
            }else {
                break;
            }
        }
        return list;
    }


    public Object plugin(Object target) {
        return null;
    }

    public void setProperties(Properties properties) {

    }


    private void processMap(Map<String, Object> o) {
        Set<String> keySet = new HashSet<String>(o.keySet());
        for(String s : keySet){
            // 将"_"替换掉, 第二个单词开始, 首字母大写


        }
    }
}
