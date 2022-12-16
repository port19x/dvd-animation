(ns dvd-animation.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 60)
  (q/color-mode :hsb)
  ;128:80
  {:color (rand-int 256)
   :image (q/load-image "dvd-logo.png")
   :x 0
   :y 0
   :vx 4
   :vy 3})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (if (or (>= (+ (:x state) (:vx state)) (- 500 128))
                  (<= (+ (:x state) (:vx state)) 0)
                  (>= (+ (:y state) (:vy state)) (- 500 80))
                  (<= (+ (:y state) (:vy state)) 0)) (rand-int 256)
                                                     (:color state))
   :image (:image state)
   :x (+ (:x state) (:vx state))
   :y (+ (:y state) (:vy state))
   :vx (cond (>= (+ (:x state) (:vx state)) (- 500 128))  (* -1 (:vx state))
             (<= (+ (:x state) (:vx state)) 0)    (* -1 (:vx state))
             :else                                (:vx state))
   :vy (cond (>= (+ (:y state) (:vy state)) (- 500 80))  (* -1 (:vy state))
             (<= (+ (:y state) (:vy state)) 0)    (* -1 (:vy state))
             :else                                (:vy state))
   })

(defn draw-state [state]
  ; Clear the sketch by filling it with black color.
  (q/background 255)
  ; Set rectangle color.
  ;(q/fill (:color state) 255 255)
  (q/image (:image state) (:x state) (:y state)))

(q/defsketch dvd-animation
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
