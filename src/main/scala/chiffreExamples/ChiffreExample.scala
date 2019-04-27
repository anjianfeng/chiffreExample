// See LICENSE.txt for license details.
package chiffreExamples

import chisel3._
import chiffre._

import chisel3.iotesters.ChiselFlatSpec
import chiffre.{InjectorInfo, ChiffreController, ChiffreInjector, ChiffreInjectee}
import chiffre.passes.{ScanChainAnnotation, ScanChainDescriptionAnnotation, FaultInjectionAnnotation}
import chiffre.inject.{Injector, LfsrInjector32, StuckAt, CycleInjector32, StuckAtInjectorWithId}
import chiffre.inject.IdentityInjector

import scala.language.reflectiveCalls

class MyController(val scanId: String) extends Module with ChiffreController {
  val io = IO(new Bundle{})
  val clk = Reg(UInt(1.W))
  clk := ~clk
  scan.clk := clk
  scan.en := ~clk
  scan.out := clk

  scan.out := clk
  //assert(scan.in === scan.out)
}


class ShiftRegister extends Module with ChiffreInjectee {
  val io = IO(new Bundle {
    val in  = Input(UInt(1.W))
    val out = Output(UInt(1.W))
  })


  val r0 = RegNext(io.in)
  val r1 = RegNext(r0)
  val r2 = RegNext(r1)
  val r3 = RegNext(r2)
    
  io.out := r3  
  
  val scanId = "shifter"
  //isFaulty(r3, scanId, classOf[LfsrInjector32])
  //val controller = Module(new MyController(scanId))

}

class MyCounter extends Module with ChiffreInjectee {
  val io = IO(new Bundle {
    val in  = Input(UInt(4.W))
    val out = Output(UInt(4.W))
  })

  val rCounter = RegInit(0.U(4.W)) 
  rCounter := rCounter + 1.U
  io.out := rCounter

  val scanId = "counter"
  isFaulty(rCounter, scanId, classOf[StuckAtInjectorWithId])
  val controller = Module(new MyController(scanId))

}
