(ns dvd-animation.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 60)
  (q/color-mode :hsb)
  {:color (rand-int 256)
   :x 0
   :y 0
   :vx (/ (q/width) 125)
   :vy (/ (* (q/height) 3) 500)})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (if (or (>= (+ (:x state) (:vx state)) (* (q/width) 0.8))
                  (<= (+ (:x state) (:vx state)) 0)
                  (>= (+ (:y state) (:vy state)) (* (q/height) 0.8))
                  (<= (+ (:y state) (:vy state)) 0)) (rand-int 256)
                                                     (:color state))
   :x (+ (:x state) (:vx state))
   :y (+ (:y state) (:vy state))
   :vx (cond (>= (+ (:x state) (:vx state)) (* (q/width) 0.8))  (* -1 (:vx state))
             (<= (+ (:x state) (:vx state)) 0)    (* -1 (:vx state))
             :else                                (:vx state))
   :vy (cond (>= (+ (:y state) (:vy state)) (* (q/height) 0.8))  (* -1 (:vy state))
             (<= (+ (:y state) (:vy state)) 0)    (* -1 (:vy state))
             :else                                (:vy state))
   })

(defn draw-state [state]
  ; Clear the sketch by filling it with black color.
  (q/background 0)
  ; Set rectangle color.
  (q/fill (:color state) 255 255)
  (q/rect (:x state) (:y state) (* (q/width) 0.2) (* (q/height) 0.2)))

(q/defsketch dvd-animation
  :title "You spin my circle right round"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
