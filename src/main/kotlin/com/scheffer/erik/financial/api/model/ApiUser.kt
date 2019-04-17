package com.scheffer.erik.financial.api.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class ApiUser(@Id
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   var id: Long? = null,
                   var name: String = "",
                   var email: String = "",
                   var password: String = "",
                   @ManyToMany(fetch = FetchType.EAGER)
                   @JoinTable(name = "user_permission",
                           joinColumns = [JoinColumn(name = "id_user")],
                           inverseJoinColumns = [JoinColumn(name = "id_permission")])
                   var permissions: List<Permission> = ArrayList())