/*
 * Copyright 2013-2016 Tsukasa Kitachi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package configs.instance

import configs.Configs
import configs.util._
import java.{math => jm, util => ju}
import scala.collection.convert.decorateAsJava._
import scalaprops.Property.forAll
import scalaprops.Scalaprops
import scalaz.Equal
import scalaz.std.java.math.bigInteger._
import scalaz.std.math.bigDecimal._
import scalaz.std.math.bigInt._
import scalaz.syntax.equal._

object BigNumberConfigsTest extends Scalaprops {

  val bigInt = check[BigInt]
  val bigIntFromDecimal =
    forAll { d: BigDecimal =>
      Configs[BigInt].extract(d.toConfigValue).exists(_ === d.toBigInt())
    }

  val bigIntList = {
    implicit val h = hideConfigs[BigInt]
    check[ju.List[BigInt]]
  }
  val bigIntListFromDecimal = {
    implicit val h = hideConfigs[BigInt]
    forAll { ds: List[BigDecimal] =>
      Configs[ju.List[BigInt]].extract(ds.toConfigValue).exists(_ === ds.map(_.toBigInt()).asJava)
    }
  }

  val bigInteger = check[jm.BigInteger]
  val bigIntegerFromDecimal =
    forAll { d: jm.BigDecimal =>
      Configs[jm.BigInteger].extract(d.toConfigValue).exists(_ === d.toBigInteger)
    }

  val bigIntegerList = {
    implicit val h = hideConfigs[jm.BigInteger]
    check[ju.List[jm.BigInteger]]
  }
  val bigIntegerListFromDecimal = {
    implicit val h = hideConfigs[jm.BigInteger]
    forAll { ds: List[jm.BigDecimal] =>
      Configs[ju.List[jm.BigInteger]].extract(ds.toConfigValue).exists(_ === ds.map(_.toBigInteger).asJava)
    }
  }

  val bigDecimal = check[BigDecimal]

  val bigDecimalList = {
    implicit val h = hideConfigs[BigDecimal]
    check[ju.List[BigDecimal]]
  }

  val javaBigDecimal = check[jm.BigDecimal]

  val javaBigDecimalList = {
    implicit val h = hideConfigs[jm.BigDecimal]
    check[ju.List[jm.BigDecimal]]
  }

  implicit lazy val javaBigDecimalEqual: Equal[jm.BigDecimal] =
    Equal.equalA[jm.BigDecimal]

}
