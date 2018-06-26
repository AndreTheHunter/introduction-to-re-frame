(ns example.reagent
  (:require [reagent.core :as reagent]
            [oops.core :refer [oget]]))

(defn hello []
  (reagent/with-let [value (reagent/atom "World")]
    [:div
     [:p (str "Hello, " @value "!")]
     [:input {:value     @value
              :on-change #(reset! value
                            (oget % ["target" "value"]))}]]))

