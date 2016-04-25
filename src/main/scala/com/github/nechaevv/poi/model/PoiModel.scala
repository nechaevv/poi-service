package com.github.nechaevv.poi.model

import java.time.LocalTime
import java.util.UUID

import com.github.nechaevv.poi.core.{Location, PgDriver}

/**
  * Created by sgl on 25.04.16.
  */
trait PoiModel {
  import PgDriver.api._
  class PoiTable(t: Tag) extends Table[Poi](t, "POI") {
    def id = column[UUID]("ID", O.PrimaryKey)
    def name = column[String]("NAME")
    def location = column[Location]("LOCATION")
    def floor = column[Int]("FLOOR")
    def category = column[String]("CATEGORY")
    def openFrom = column[LocalTime]("OPEN_FROM")
    def openTill = column[LocalTime]("OPEN_TILL")
    def * = (id, name, location, floor, category, openFrom, openTill) <> (Poi.tupled, Poi.unapply)
  }
}

case class Poi
(
  id: UUID,
  name: String,
  location: Location,
  floor: Int,
  category: String,
  openFrom: LocalTime,
  openTill: LocalTime
)
