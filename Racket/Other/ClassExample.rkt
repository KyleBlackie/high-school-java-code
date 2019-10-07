#lang racket

(define animal%
  (class object%
    (super-new)
    (init-field size colour)
    (define/public (sound)
      (displayln "UNDESCRIPT ANIMAL SOUND"))
    ;getters
    (define/public (get-size) size)
    (define/public (get-colour) colour)
    ))

(define cat%
  (class animal%
    (super-new)
    (define (sound)
      (displayln "MEOW"))))



(define cat (new cat% [size 2] [colour "Green"]))
(display(send cat get-colour))
(display(send cat get-size))





;(send (new animal%) sound)

