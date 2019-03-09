package com.example.shiro.shiro;


import net.sf.ehcache.CacheManager;

import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.LinkedHashMap;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro的配置类
 *
 * @author Administrator
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("403");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/login.*", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/logout*", "anon");
        filterChainDefinitionMap.put("/hello", "anon");
        filterChainDefinitionMap.put("/defaultKaptcha", "anon");
        filterChainDefinitionMap.put("/index.*", "authc");
//        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
//        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
//        filterChainDefinitionMap.put("/*.*", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    //url配置
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */
//    @Bean
//    public EhCacheManager ehCacheManager(CacheManager cacheManager) {
//        EhCacheManager em = new EhCacheManager();
//        //将ehcacheManager转换成shiro包装后的ehcacheManager对象
//        em.setCacheManager(cacheManager);
//        em.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return em;
//    }


    //配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm, @Qualifier("authRealm2") AuthRealm authRealm2, @Qualifier("authRealm3") AuthRealm authRealm3) {
        System.err.println("----------------------------shiro已经加载---------------------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //配置两个测试一下认证策略AllSuccessfulStrategy
//        manager.setRealm(authRealm);
//        manager.setRealm(authRealm2);

        //测试一下密码加密
        manager.setRealm(authRealm3);

        manager.setSessionManager(sessionManager());
//        manager.setCacheManager(ehCacheManager);
        return manager;
    }

    /**
     * 认证策略配置
     *
     * @return modularRealmAuthenticator
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        //AllSuccessfulStrategy 多个realm都成功才认证成功
        AuthenticationStrategy atLeastOneSuccessfulStrategy = new AllSuccessfulStrategy();
        modularRealmAuthenticator.setAuthenticationStrategy(atLeastOneSuccessfulStrategy);
        return modularRealmAuthenticator;
    }

    //配置自定义的权限登录器
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    //配置自定义的权限登录器
    @Bean(name = "authRealm3")
    public AuthRealm authRealmhash(@Qualifier("credentialsMatcher3") CredentialsMatcherHash matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     * 配置自定义的权限登录器2 第二个real其中的credentialsMatcher2限制了密码必须是realpwd才让登录
     * 测试的时候把张三的密码改成了realpwd
     * 这样AllSuccessfulStrategy 策略下就只有张三能登录 李四不能  起到多重认证效果
     * AtLeastOneSuccessfulStrategy策略下都能登录
     *
     * @param matcher
     * @return
     */
    @Bean(name = "authRealm2")
    public AuthRealm authRealm2(@Qualifier("credentialsMatcher2") CredentialsMatcher2 matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200); //30天
        return simpleCookie;
    }

//    @Bean
//    public RememberMeManager rememberMeManager() {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        cookieRememberMeManager.setCipherKey(Base64.getDecoder().decode("xasdasdasx123dadsada48sdd"));
//        return cookieRememberMeManager;
//    }

    @Bean
    public SessionManager sessionManager() {
        return new DefaultSessionManager();
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    //配置自定义的密码比较器2 限制了密码必须是realpwd才让登录
    @Bean(name = "credentialsMatcher2")
    public CredentialsMatcher2 credentialsMatcher2() {
        return new CredentialsMatcher2();
    }

    //配置自定义的密码比较器3 对密码进行加密
    @Bean(name = "credentialsMatcher3")
    public CredentialsMatcherHash credentialsMatcher3() {
        return new CredentialsMatcherHash();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }


}

