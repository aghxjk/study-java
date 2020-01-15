package com.jeremy.study.dao;

import com.jeremy.study.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    /**
     * 使用了 JPA，无需手动构建 SQL 语句，而只需要按照规范提供方法的名字即可实现对数据库的增删改查。
     * 如 findByUsername，就是通过 username 字段查询到对应的行，并返回给 User 类
     */
    User findByUsername(String username);
    User getByUsernameAndPassword(String username, String password);
}
//        -- ----------------------------
//        -- Table structure for user
//        -- ----------------------------
//        DROP TABLE IF EXISTS `user`;
//        CREATE TABLE `user` (
//        `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
//        `username` varchar(255) DEFAULT NULL,
//        `password` varchar(255) DEFAULT NULL,
//        PRIMARY KEY (`id`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
//
//        -- ----------------------------
//        -- Records of user
//        -- ----------------------------
//        INSERT INTO `user` VALUES ('1', 'admin', '123');
