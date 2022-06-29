package com.sme.modelapplication

import com.sme.modelapplication.state.AuthorizationPresenter
import junit.framework.Assert.*
import org.junit.Test

class StateTest {

    @Test
    fun login() {
        val authorizationPresenter = AuthorizationPresenter()

        authorizationPresenter.loginUser("admin")

        assertTrue(authorizationPresenter.isAuthorized)
        assertEquals(authorizationPresenter.userName,"admin")

        authorizationPresenter.logoutUser()

        assertFalse(authorizationPresenter.isAuthorized)
        assertEquals(authorizationPresenter.userName,"Unknown")
    }

}