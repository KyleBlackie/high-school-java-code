#lang racket

;arrays in racket are called vectors
;arraylists in racket are called lists

;define scores vector with a fixed size of 18
(define scores (make-vector 18))

;loop through vector and get user input for holes
(for ([i (in-range (vector-length scores))])
  (display (string-append "Hole " (number->string (+ i 1)) ":"))
  (vector-set! scores i (string->number(read-line)))
  )

;set maxScore and lowScore to first index value in scores vector
(define maxScore (vector-ref scores 0))
(define lowScore maxScore)

;find highest and lowest scores
(for ([i (in-range (vector-length scores))])
  (when (>  (vector-ref scores i) maxScore) (set! maxScore (vector-ref scores i)))
  (when (<  (vector-ref scores i) lowScore) (set! lowScore (vector-ref scores i)))
  )

;display highest and lowest scores
(display (string-append "The highest score was: " (number->string maxScore) "\nThe lowest score was: " (number->string lowScore)))



  