// See LICENSE.txt for license details.
package examples

import chisel3._
import chisel3.iotesters.ChiselFlatSpec
import chiffre.{InjectorInfo, ChiffreController, ChiffreInjector, ChiffreInjectee}
import chiffre.passes.{ScanChainAnnotation, ScanChainDescriptionAnnotation, FaultInjectionAnnotation}
import chiffre.inject.{Injector, LfsrInjector32}

import chisel3.core.Bits
import chisel3.{iotesters}
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

import scala.language.reflectiveCalls

class ShiftRegisterTests(c: ShiftRegister) extends PeekPokeTester(c) {
  val reg = Array.fill(4){ 0 }
  for (t <- 0 until 64) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step(1)
    for (i <- 3 to 1 by -1)
      reg(i) = reg(i-1)
    reg(0) = in
    if (t >= 4) expect(c.io.out, reg(3))
  }
}




class ShiftRegisterTester extends ChiselFlatSpec {

  behavior of "ShiftRegister"
  backends foreach {backend =>
    it should s"shift a number through a series of registers in $backend" in {
      Driver(() => new ShiftRegister, backend)((c) => new ShiftRegisterTests(c)) should be (true)
    }
  }
}

class MyCounterTests(c: MyCounter)extends PeekPokeTester(c) {
  poke(c.io.in, 0.U)
  step(4)  
  expect(c.io.out, 4.U)
}

class MyCounterTester extends ChiselFlatSpec {

  behavior of "MyCounter"
  backends foreach {backend =>
    it should s"increase counter in $backend" in {
      Driver(() => new MyCounter, backend)((c) => new MyCounterTests(c)) should be (true)
    }
  }
}
