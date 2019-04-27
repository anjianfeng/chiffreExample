// See LICENSE.txt for license details.
package examples

import chisel3._

import chiffre._
import chiffre.passes._
import chiffre.inject._

import scala.language.reflectiveCalls

class Counter extends Module with ChiffreInjectee {
  val io = IO(new Bundle {
    val in  = Input(UInt(4.W))
    val out = Output(UInt(4.W))
  })

  val rCounter = RegInit(0.U(4.W)) 
  rCounter := rCounter + 1.U
  io.out := rCounter

  // Add chiffre scanid and controller
  val scanId = "counter"
  isFaulty(rCounter, scanId, classOf[StuckAtInjectorWithId])
  val controller = Module(new ChiffreControllerExample(scanId))

}
