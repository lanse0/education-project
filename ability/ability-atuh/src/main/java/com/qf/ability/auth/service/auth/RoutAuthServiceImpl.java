package com.qf.ability.auth.service.auth;

/**
 * 路由实现类
 * 策略模式 - 根据登录的客户端，匹配相应的IAuthService实现类
 * author 14526
 * create_time 2023/2/28
 */
//@Service
//@Primary //某个接口有多个实现类时 打了这个注解的实现类为自动注入的主要实现类 防止报错
//public class RoutAuthServiceImpl implements IAuthService {
//
//    @Autowired
//    private SysAuthServiceImpl sysAuthService;
//
//    @Autowired
//    private TeacherAuthServiceImpl teacherAuthService;
//
//    @Override
//    public BaseUser login(AuthInfo authInfo) {
//        switch (authInfo.getFromType()) {
//            case 0:
//                return sysAuthService.login(authInfo);
//            case 1:
//                return teacherAuthService.login(authInfo);
//        }
//        return null;
//    }
//}
