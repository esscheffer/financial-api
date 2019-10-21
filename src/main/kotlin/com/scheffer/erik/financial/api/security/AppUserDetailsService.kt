package com.scheffer.erik.financial.api.security

import com.scheffer.erik.financial.api.model.ApiUser
import com.scheffer.erik.financial.api.repository.UserRepository
import com.scheffer.erik.financial.api.util.getMessage
import org.springframework.context.MessageSource
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserDetailsService(private val userRepository: UserRepository,
                            private val messageSource: MessageSource) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
                ?: throw UsernameNotFoundException(messageSource.getMessage("message.userNameNotFound"))
        return SystemUser(user, getPermissions(user))
    }

    private fun getPermissions(user: ApiUser) =
            HashSet<SimpleGrantedAuthority>().apply {
                addAll(user.permissions.map {
                    SimpleGrantedAuthority(it.description.toUpperCase())
                })
            }
}
