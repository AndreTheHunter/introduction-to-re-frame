(ns andrethehunter.introduction-to-re-frame.sub
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub ::current
  (fn [{:keys                           [:slides]
        [slide-position point-position] :position
        :as                             db}]
    (let [{:keys [:points] :as slide} (get slides slide-position)]
      (if points
        (assoc slide
          :points (subvec points 0 point-position))
        slide))))
