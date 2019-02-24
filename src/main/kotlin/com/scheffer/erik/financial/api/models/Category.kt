package com.scheffer.erik.financial.api.models

import javax.persistence.*

@Entity
@Table(name = "category")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String)
