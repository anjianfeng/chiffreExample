# chiffreExample

What's Chiffre?

>Chained Hierarchical Injection for Fault Resiliency Evaluation (Chiffre)

This provides a framework for automatically instrumenting a hardware design with run-time configurable fault injectors. 

See details on: https://github.com/IBM/chiffre

The project provides several simple examples to use Chiffre.

How to run:

Copy these example files into chiffre directory, and run sbt:

sbt:chiffre> testOnly examples.CounterTester

### before fault-inject:
```
sbt:chiffre> testOnly examples.CounterTester
[info] Compiling 1 Scala source to /raid1/home/soc/anjf/chiffre/chiffre/target/scala-2.12/classes ...
[info] Done compiling.
[info] [0.002] Elaborating design...
[info] [0.215] Done elaborating.
Total FIRRTL Compile Time: 522.1 ms
Total FIRRTL Compile Time: 42.7 ms
End of dependency graph
Circuit state created
[info] [0.001] SEED 1556343005885
test Counter Success: 1 tests passed in 9 cycles taking 0.019522 seconds
[info] [0.006] RAN 4 CYCLES PASSED
[info] CounterTester:
[info] Counter
[info] - should increase counter in firrtl
[info] ScalaTest
[info] Run completed in 2 seconds, 85 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[info] Passed: Total 1, Failed 0, Errors 0, Passed 1
[success] Total time: 6 s, completed Apr 27, 2019 1:30:07 PM

```

### after fault-inject:

```
[info] [0.001] Elaborating design...
[info] [0.155] Done elaborating.
[info] [0.000] Elaborating design...
[info] [0.032] Done elaborating.
Total FIRRTL Compile Time: 64.0 ms
Total FIRRTL Compile Time: 625.4 ms
Total FIRRTL Compile Time: 62.7 ms
End of dependency graph
Circuit state created
[info] [0.002] SEED 1556335783665
[info] StuckAtInjectorWithId enabled
[info]   - mask: 0xf
[info]   - value: 0xf
[info] StuckAtInjectorWithId enabled
[info]   - mask: 0xf
[info]   - value: 0xf
[info] [0.013] EXPECT AT 4   io_out got 15 expected 4 FAIL
test Counter Success: 0 tests passed in 9 cycles taking 0.034874 seconds
[info] [0.014] RAN 4 CYCLES FAILED FIRST AT CYCLE 4
[info] CounterTester:
[info] Counter
[info] - should increase counter in firrtl *** FAILED ***
[info]   false was not true (CounterTests.scala:24)
[info] ScalaTest
[info] Run completed in 1 second, 690 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 0, failed 1, canceled 0, ignored 0, pending 0
[info] *** 1 TEST FAILED ***
[error] Failed: Total 1, Failed 1, Errors 0, Passed 0
[error] Failed tests:
[error]         examples.CounterTester
[error] (Test / testOnly) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 3 s, completed Apr 27, 2019 11:29:45 AM

```
