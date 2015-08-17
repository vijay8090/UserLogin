package com.vijay.userlogin;

/**
 * Created by abi on 8/16/2015.
 */
public interface GetUserCallback {

    public abstract void done(boolean result, User returnedUser);
}
