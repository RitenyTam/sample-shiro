package com.riteny;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroQuickStart {

    public static void main(String[] args) {

        test("root","123456");
        System.out.println("====================================================================================");
        test("user","1234567");
        System.out.println("====================================================================================");
        test("guest","12345678");
        System.out.println("====================================================================================");
    }

    public static void test(String username, String password) {

        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        Realm iniRealm = new IniRealm("classpath:shiro.ini");

        securityManager.setRealm(iniRealm);

//        Factory<SecurityManager> factory = new IniSecurityManagerFactory();
//
//        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(subject.isAuthenticated());
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        System.out.println("hasRole admin : " + subject.hasRole("admin"));
        System.out.println("hasRole operator : " + subject.hasRole("operator"));
        System.out.println("hasRole viewer : " + subject.hasRole("viewer"));
        System.out.println("isPermittedAll delete add : " + subject.isPermittedAll("delete", "add"));
        System.out.println("isPermitted search : " + subject.isPermitted("search"));
    }
}
