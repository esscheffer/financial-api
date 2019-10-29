package com.scheffer.erik.financial.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(@Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long? = null,
                  var name: String,
                  @Embedded var address: Address?,
                  var active: Boolean,
                  @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true)
                  @JsonIgnoreProperties("person")
                  var contacts: MutableList<Contact> = ArrayList())
