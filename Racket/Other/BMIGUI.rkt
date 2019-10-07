#lang racket/gui

(define frame (new frame% [label "BMI"]
                   [width 400]
                   [height 400]))
(define title (new message% [parent frame]
                   [label "Body Mass Index"]))

(define expl (new message%
                  [label "This program will calculate a persons BMI based off of the height and weight input into this program in either the metric or imperial systems\nWhat system do you want to use?\nMetric or Imperial?"]
                  [parent frame]
                  [auto-resize #t]
                  )) 
(message-box "What System Do You Want To Use?" "Metric or Imperial" #f '(yes-no))


(send frame show #t)