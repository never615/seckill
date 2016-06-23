package org.seckill.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 测试数据库连接
 * Created by never615 on 6/15/16.
 */
public class DbTest {


    @Test
    public void test() {
        System.out.println("test");
    }

    @Test
    public void testGetConnection() {
        String url = "jdbc:postgresql://api.mall-to.com/seaworld_app?useUnicode=true&characterEncoding=utf-8";
        String driver = "org.postgresql.Driver";
        String user = "dbuser";
        String passwd = "dbuser";

        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Get Connection failed!!!");
        }

        try {
            Connection con = DriverManager.getConnection(url, user, passwd);

            System.out.println("Get Connection Success!!!");

            Properties properties = con.getClientInfo();
            Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Object, Object> entry = it.next();
                System.out.println(entry.getKey() + "---------------" + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testComboPooledDataSource() throws Exception {
        String url = "jdbc:postgresql://api.mall-to.com/seaworld_app?useUnicode=true&characterEncoding=utf-8";
        String driver = "org.postgresql.Driver";
        String user = "dbuser";
        String passwd = "dbuser";

        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(passwd);

        druidDataSource.setDriverClassName(driver);


        Connection con = druidDataSource.getConnection();

        System.out.println("Get Connection Success!!!   " + con);

    }
}
