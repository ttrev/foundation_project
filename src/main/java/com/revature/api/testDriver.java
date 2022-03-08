package com.revature.api;

import com.revature.api.daos.UserDAO;
import com.revature.api.models.Users;
import java.sql.*;

public class testDriver {
    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();

        Users user = userDao.getById("user3");

        System.out.println(user);

    }
}

