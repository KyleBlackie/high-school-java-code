#lang racket

;method to get ones string value
(define (ones ones)
  ;create a variable return 
  (define return "")
  ;use a switch to determine which number it is
  (case ones
  [(1) (set! return "One")]
  [(2) (set! return "Two")]
  [(3) (set! return "Three")]
  [(4) (set! return "Four")]
  [(5) (set! return "Five")]
  [(6) (set! return "Six")]
  [(7) (set! return "Seven")]
  [(8) (set! return "Eight")]
  [(9) (set! return "Nine")]
  [else (set! return "Unknown")])
  ;return the variable return
  return
  )

;method to get tens string value
(define (tens ten)
  (define return "")
  ;use switch for irregular numbers
  (case ten
  [(2) (set! return "Twenty ")]
  [(3) (set! return "Thirty ")]
  [(5) (set! return "Fifty ")]
  [(8) (set! return "Eighty ")]
  ;else get the number by calling the ones method then add "ty " to the end
  [else (set! return (string-append (ones ten) "ty "))])
  return)

;method to get teens value
(define (teens ten one)
  (define return "")
  (when (eq? ten 1)
  (case one
    [(0) (set! return "Ten ")]
    [(1) (set! return "Eleven ")]
    [(2) (set! return "Twelve ")]
    [(3) (set! return "Thirteen ")]
    [(5) (set! return "Fifteen ")]
    [(8) (set! return "Eighteen ")]
    [else (set! return (string-append (ones one) "teen "))]
    ))
  return)
  


;loop that gets input from user
(define number 0)
(let loop ()
  ;ask user for a number
  (display "Enter a number between 10 and 99")
  ;set a variable to the user's input
  (set! number (string->number(read-line)))
  ;check if input is a number
  (when (not(number? number)) (display "The input must be a number\n") (loop))
  ;check if the number is valid, if not loop to the top/ask again
  (when (or (< number 10) (> number 99)) (display "The number must be between 10 and 99.\n") (loop)))

;get the ones and tens column
(define one (remainder number 10))
(define ten (inexact->exact(exact-floor(/ number 10))))
;check if word is in teens
(define word (teens ten one))
;if not, word will be an empty string and the mehtods "tens" and "ones" will be called
(when (eq? word "") (set! word (tens ten))  (set! word(string-append word (ones one))))
;display the final number->word transformation
(display (string-append "The number entered is: " word))