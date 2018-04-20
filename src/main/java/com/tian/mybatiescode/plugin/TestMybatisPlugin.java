package com.tian.mybatiescode.plugin;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.Properties;


/**
 * 拦截器签名介绍
 *
 * 除了实现接口外, 还要给拦截器添加如下注解,方能生效.
 * @Intercepts 注解和 @Signature注解.这两个注解用来配置拦截器要拦截的接口方法.
 * Intercepts注解中的属性是一个Signature注解数组, 可以在一个拦截器中同时拦截不同的接口方法.
 * Signature注解中有如下三个属性:
 * type: 设置拦截的接口, 可选值是mybatis中的四种处理器类型
 *        StatementHandler.class,  ResultSetHandler.class, ParameterHandler.class, Executor.class
 * method: 指定type属性中所指定的接口中的某一个方法名
 * args: method方法中所指定的方法的参数列表的类型. 因为java中方法可以重载, 以此确定目标方法
 *
 Created by Administrator on 2018/4/20 002
 */
@Intercepts({@Signature(type = ParameterHandler.class, method = "setParameters",args= {PreparedStatement.class})})
public class TestMybatisPlugin implements Interceptor {
    private Properties properties;

    /**
     * mybatis运行时要执行的拦截方法, 通过该方法的参数invocation可以得到很多有用的信息.
     * 常用方法如下
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取当前被拦截的对象
        Object target = invocation.getTarget();
        // 获取当前被拦截的方法
        Method method = invocation.getMethod();
        // 获取被拦截方法中的参数
        Object[] args = invocation.getArgs();
        // 内部就是被拦截对象执行了invoke方法
        Object result = invocation.proceed();

        return result;
    }

    /**
     *
     * @param o 拦截器要拦截的对象, 该方法会在创建被拦截的接口实现类时
     *          调用. 该方法的实现很简单, 只需要调用MyBatis提供的plugin
     *          类的wrap静态方法就可以通过java的动态代理拦截目标对象.
     * @return
     */
    public Object plugin(Object o) {
        /*
        Plugin.wrap方法会自动判断拦截器的签名和被拦截对象的接口是否匹配, 只有匹配的情况下
        才会使用动态代理拦截目标对象, 因此在上面的实现方法中不必做额外的逻辑判断.
         */
        return Plugin.wrap(o, this);
    }

    /**
     * 该方法用来给自定义的插件注入一些自定义的参数
     * @param properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
