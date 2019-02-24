package com.scheffer.erik.financial.api.models

import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(@Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long? = null,
                  var name: String,
                  @Embedded var address: Address?,
                  var active: Boolean)