#lang racket

;define a class called player
(define player%
  ;with the parent class object%
  (class object%
    (super-new)
    ;initialization / constructor arguments
    (init-field name age team)
    ;getters
    (define/public (get-age) age)
    (define/public (get-name) name)
    (define/public (get-team) team)
    ;setters
    (define/public (set-age new_age) (set! age new_age))
    (define/public (set-name new_name) (set! name new_name))
    (define/public (set-team new_team) (set! team new_team))

    ;toString
    (define/public (to-string) (displayln (string-append  "Player: { Name: " name ", Age: " (number->string age) ", Team: " team "} ")))
    ))

;create a player% object called player and give it the proper parameters
(define player (new player% [name "Kyle Blackie"] [age 16] [team "Oakville FC"]))
;test getters
(send player get-age)
(send player get-name)
(send player get-team)
(send player to-string)
;test setters
(send player set-age 17)
(send player set-name "Zoran Savic")
(send player set-team "Garth Webb")
(send player to-string)

