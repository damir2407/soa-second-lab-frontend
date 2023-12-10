package ru.itmo.soa.spacemarine.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itmo.soa.spacemarine.domain.SpaceMarine
import ru.itmo.soa.spacemarine.domain.StarShip

@Repository
interface StarShipRepository : JpaRepository<StarShip, Long> {

    fun findBySpaceMarine(spaceMarine: SpaceMarine): StarShip?
}