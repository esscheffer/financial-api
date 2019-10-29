package com.scheffer.erik.financial.api.model

import javax.persistence.*

@Entity
@Table(name = "contact")
data class Contact(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        val name: String,
        val email: String,
        val phoneNumber: String,
        @ManyToOne @JoinColumn(name = "id_person") var person: Person?)
