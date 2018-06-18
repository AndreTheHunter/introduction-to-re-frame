(ns introduction-to-re-frame.sub
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub ::current-slide
  (fn [{:keys [:position :slides] :as db}]
    (get slides position)))

