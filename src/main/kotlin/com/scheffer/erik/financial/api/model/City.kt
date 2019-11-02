package com.scheffer.erik.financial.api.model

import javax.persistence.*

@Entity
@Table(name = "city")
data class City(@Id var id: Long? = null,
                var cityName: String = "",
                @ManyToOne @JoinColumn(name = "id_state") var state: State = State())
