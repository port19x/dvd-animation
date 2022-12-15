(ns dvd-animation.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 60)
  (q/color-mode :hsb)
  {:color 0
   :x 0
   :y 0
   :vx 2
   :vy 1})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :x (+ (:x state) (:vx state))
   :y (+ (:y state) (:vy state))
   :vx (:vx state)
   :vy (:vy state)
   })

(defn draw-state [state]
  ; Clear the sketch by filling it with black color.
  (q/background 0)
  ; Set rectangle color.
  (q/fill (:color state) 255 255)
  (q/rect (:x state) (:y state) 100 100))

(q/defsketch dvd-animation
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
