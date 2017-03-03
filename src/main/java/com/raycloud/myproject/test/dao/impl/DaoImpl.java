package com.raycloud.myproject.test.dao.impl;

import com.alibaba.cobar.client.CobarSqlMapClientDaoSupport;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.raycloud.myproject.test.dao.IUserDAO;
import com.raycloud.myproject.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 001117020012 on 2017/2/17.
2 */
/*@Component("demoService")*/
public class DaoImpl extends CobarSqlMapClientDaoSupport implements IUserDAO {
     @Autowired
    private SqlMapClient sqlMapClient;
        @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }


    @Override
    public User selectUsersByName(String name) throws SQLException {
        User u = (User)super.getSqlMapClientTemplate().queryForObject("selectUserByName",name);
        System.out.print(u);
        return u;
    }

    @Override
    public List<User> selectUsersByPage(int page) {
        /*int totleNum=selectNumber();
        if(page<=0){
            page=totleNum;
        }else if(page>totleNum){
            page=1;
        }*/
        List<User> u = super.getSqlMapClientTemplate().queryForList("selectUserByPage",(page-1)*2);
        System.out.print(u);
        return u;
    }

    @Override
    public int selectNumber() {
        int number=(Integer)super.getSqlMapClientTemplate().queryForObject("selectNumber");
        int totleNum=0;
        if(number!=0&&number%2==0){
            totleNum=number/2;
        }else {
            totleNum=number/2+1;
        }
        System.out.println(totleNum);
        return totleNum;
    }
}
