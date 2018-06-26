(ns todo.component
  (:require
    [oops.core :refer [oget]]
    [reagent.core :as reagent]))

(defn input [{:keys [:on-save :placeholder] :as opts} value]
  (let [state (reagent/atom (or value ""))
        save (fn []
               (on-save @state)
               (reset! state ""))]
    (fn input [opts value]
      [:input {:placeholder placeholder
               :value       @state
               :on-change   #(reset! state (oget % ["target" "value"]))
               :on-blur     save
               :on-key-down #(when (= 13 (oget % "which"))
                               (save))}])))

(defn task-list [{:keys [:on-task-clicked]} tasks]
  [:ul
   (for [task tasks]
     ^{:key (:id task)}
     [:li {:on-click (fn []
                       (on-task-clicked task))}
      (:name task)])])

