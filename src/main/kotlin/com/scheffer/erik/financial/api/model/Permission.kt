package com.scheffer.erik.financial.api.model

import javax.persistence.*

@Entity
@Table(name = "permission")
data class Permission(@Id
                      @GeneratedValue(strategy = GenerationType.IDENTITY)
                      var id: Long? = null,
                      var description: String = "")