package com.scheffer.erik.financial.api.security

import com.scheffer.erik.financial.api.model.ApiUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SystemUser(val user: ApiUser,
                 authorities: Collection<GrantedAuthority>) : User(user.email, user.password, authorities)