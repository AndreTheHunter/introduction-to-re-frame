(ns andrethehunter.introduction-to-re-frame.event
  (:require
    [andrethehunter.introduction-to-re-frame.slides :as slides]
    [re-frame.core :as rf]))

(def ^:private interceptors [(when goog.DEBUG rf/debug) rf/trim-v])

(defn- reg-event
  ([id handler]
   (reg-event id nil handler))
  ([id path handler]
   (let [interceptors (if (nil? path)
                        interceptors
                        (conj interceptors (rf/path path)))]
     (rf/reg-event-db id interceptors handler))))

(reg-event ::initialize
  (constantly {:position [0 0]
               :slides   slides/-order}))

(reg-event ::update [:slides]
  (constantly slides/-order))

(reg-event ::next
  (fn [{:keys                 [:slides]
        [slide-pos point-pos] :position
        :as                   db}]
    (let [{:keys [:points] :as slide} (get slides slide-pos)
          max-points (count points)
          max-slide (- (count slides) 1)
          next-point (inc point-pos)
          next-slide (min (inc slide-pos) max-slide)
          position (if (< max-points next-point)
                     [next-slide 0]
                     [slide-pos next-point])]
      (assoc db
        :position position))))

(reg-event ::prev
  (fn [{:keys                 [:slides]
        [slide-pos point-pos] :position
        :as                   db}]
    (let [{:keys [:points] :as slide} (get slides slide-pos)
          min-points 0
          min-slide 0
          prev-point (dec point-pos)
          prev-slide (max (dec slide-pos) min-slide)
          position (if (< prev-point min-points)
                     [prev-slide (count (:points (get slides prev-slide)))]
                     [slide-pos prev-point])]
      (assoc db
        :position position))))
