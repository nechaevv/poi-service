package com.github.nechaevv.poi.core

import java.sql.{PreparedStatement, ResultSet}

import com.github.tminglei.slickpg.{ExPostgresDriver, PgDate2Support}
import org.postgresql.geometric.PGpoint
import slick.ast.FieldSymbol

/**
  * Created by sgl on 20.03.16.
  */
object PgDriver extends ExPostgresDriver with PgDate2Support {
  class LocationJdbcType extends DriverJdbcType[Location] {
    override def sqlType: Int = java.sql.Types.OTHER
    override def sqlTypeName(sym: Option[FieldSymbol]): String = "POINT"
    override def getValue(r: ResultSet, idx: Int): Location = {
      val point = r.getObject(idx).asInstanceOf[PGpoint]
      Location(point.x, point.y)
    }
    override def setValue(v: Location, p: PreparedStatement, idx: Int): Unit = p.setObject(idx, new PGpoint(v.lat, v.lng))
    override def updateValue(v: Location, r: ResultSet, idx: Int): Unit = r.updateObject(idx, new PGpoint(v.lat, v.lng))
  }

  override val api = new API with DateTimeImplicits {
    implicit val locationJdbcType = new LocationJdbcType
  }
}

case class Location(lat: Double, lng: Double)