#lang racket	

;a method to get area of rectangle
(define(area l w)
  ( * l w)
  )

;method to get perimeter of rectangle
(define(per l w)
  ( + (* l 2) (* w 2))
  )

(define(welc)
  "This Program Will Calculate The Area And Perimeter of A Rectangle: Enter The Length And Then The Width of The Rectangle"
  )

;explain program
(welc)
(let
    (
  [l (string->number (read-line))]
  [w (string->number(read-line))]
  )
  (write "The Area Is: " )
  (write (area l w))

  (write " The Perimeter Is: ")
  (write(per l w))


  )