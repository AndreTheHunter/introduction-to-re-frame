(ns andrethehunter.introduction-to-re-frame.event
  (:require
    [andrethehunter.introduction-to-re-frame.slides :as slides]
    [clojure.string :as string]
    [oops.core :refer [oget oset! ocall]]
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

(defn- location-hash
  ([]
   (re-find #"(?<=#).+" (oget js/location "hash")))
  ([hash]
   (oset! js/location "hash" hash)))

(defn- hash->position [db]
  (let [position (let [hash (location-hash)]
                   (if (seq hash)
                     (mapv js/Number (string/split hash "-"))
                     [0 0]))]
    (assoc db
      :position position)))

(reg-event ::initialize
  (fn [db]
    (ocall js/window "addEventListener" "hashchange" (fn [] (rf/dispatch [::hash->position])) false)
    (-> db
        (assoc :slides slides/-order)
        hash->position)))

(reg-event ::update [:slides]
  (constantly slides/-order))

(defn- set-position [db position]
  (location-hash (string/join \- position))
  (assoc db
    :position position))

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
      (set-position db position))))

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
      (set-position db position))))

(reg-event ::hash->position hash->position)
