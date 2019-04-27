// See LICENSE.txt for license details.
package examples

import chisel3._
import chiffre._
import chiffre.passes.{ScanChainAnnotation, ScanChainDescriptionAnnotation, FaultInjectionAnnotation}
import chiffre.inject.{IdentityInjector, Injector, LfsrInjector32, StuckAt, CycleInjector32, StuckAtInjectorWithId}

import scala.language.reflectiveCalls

class ChiffreControllerExample(val scanId: String) extends Module with ChiffreController {
  val io = IO(new Bundle{})
  val clk = Reg(UInt(1.W))
  clk := ~clk
  scan.clk := clk
  scan.en := ~clk
  //scan.out := clk
  //assert(scan.in === scan.out)
}


