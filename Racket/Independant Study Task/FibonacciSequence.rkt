#lang racket

(define a 1)

(define b 1)

(define c 0)

(display "Fibonacci Series\nThis program will print the first twenty terms of the Fibonacci Sequence.\n")

(display a)
(display ", ")
(display b)
(display ", ")
(for ([i (in-range 3 21)])
  (set! c (+ a b))
  (set! a b)
  (set! b c)
  (display c)
  (display ", ")
  )