(ns andrethehunter.introduction-to-re-frame.db)

(defn current-slide [f]
  (fn [{:keys         [:slides]
        [slide point] :position
        :as           db}]
    (let [{:keys [:points] :as slide} (get slides slide)]
      (f))))

