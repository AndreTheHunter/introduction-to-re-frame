(ns introduction-to-re-frame.event
  (:require
    [introduction-to-re-frame.slides :as slides]
    [re-frame.core :as rf]))

(def ^:private interceptors [(when goog.DEBUG rf/debug) rf/trim-v])

(defn- reg-event [id f]
  (rf/reg-event-db id interceptors f))

(reg-event ::initialize
  (constantly {:position 0
               :slides   [slides/welcome slides/about-me]}))

(reg-event ::next-slide
  (fn [{:keys [:position :slides] :as db}]
    (let [max-position (- (count slides) 1)
          position (min (inc position) max-position)]
      (assoc db :position position))))

(reg-event ::prev-slide
  (fn [{:keys [:position :slides] :as db}]
    (let [min-position 0
          position (max (dec position) min-position)]
      (assoc db :position position))))

