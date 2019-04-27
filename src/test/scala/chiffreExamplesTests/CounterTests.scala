// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

import chiffre._
import chiffre.passes._
import chiffre.inject._

import scala.language.reflectiveCalls

class CounterTests(c: Counter)extends PeekPokeTester(c) {
  poke(c.io.in, 0.U)
  step(4)  
  expect(c.io.out, 4.U)
}

class CounterTester extends ChiselFlatSpec {

  behavior of "Counter"
  backends foreach {backend =>
    it should s"increase counter in $backend" in {
      Driver(() => new Counter, backend)((c) => new CounterTests(c)) should be (true)
    }
  }
}
