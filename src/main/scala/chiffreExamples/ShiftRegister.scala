// See LICENSE.txt for license details.
package examples

import chisel3._

import chiffre._
import chiffre.passes._
import chiffre.inject._

import scala.language.reflectiveCalls

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
  
  // Add chiffre scanid and controller
  val scanId = "shifter"
  isFaulty(r3, scanId, classOf[LfsrInjector32])
  val controller = Module(new ChiffreControllerExample(scanId))

}
