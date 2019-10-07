#lang racket

;method that will check the pH level of water to see if fish can survive in it
(define (phCheck ph)
  (let
      (
       [acidic 6.45]
       [basic 7.45]
       )
    (cond
      [(or (< ph 0) (> ph 14)) (error "Invalid Data")]
      [(< ph acidic) "TOO ACIDIC"]
      [(> ph basic) "TOO ALKALINE"]
      [else "NEUTRAL"]
      )
    )
  )

(phCheck 3)