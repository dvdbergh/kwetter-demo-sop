package org.jea6.mocking;

import org.jea6.dao.UserDao;
import org.jea6.dao.UserDaoImplJPA;
import org.jea6.domain.Role;
import org.jea6.domain.User;
import org.jea6.qualifiers.JPA;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class DaoMock {

    @Inject
    UserDao userDao;


    @PostConstruct
    public void init(){
        userDao.create(new User("dennis", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Administrator));
        userDao.create(new User("nick", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.Moderator));
        userDao.create(new User("rick", "pass", "mijnbio", "Eindhoven", "www.test.nl","dennis@test.nl", Role.User));
    }
}
