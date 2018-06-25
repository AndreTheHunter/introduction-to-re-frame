(ns todo.views
  (:require [oops.core :refer [oget]]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [todo.events :as events]
            [todo.subs :as subs]))
(defn- input []
  (let [value (reagent/atom "")
        save (fn []
               (re-frame/dispatch [::events/add-task @value])
               (reset! value ""))]
    (fn [] [:input {:placeholder "Add a task..."
                    :value       @value
                    :on-change   #(reset! value (oget % ["target" "value"]))
                    :on-blur     save
                    :on-key-down #(when (= 13 (oget % "which")) (save))}])))
(defn- task [task]
  ^{:key (:id task)} [:li {:on-click #(re-frame/dispatch [::events/complete-task (:id task)])}
                      (:name task)])
(defn main []
  (let [tasks (re-frame/subscribe [::subs/incomplete-tasks])
        count (re-frame/subscribe [::subs/complete-count])]
    (fn [] [:div#example-todo
            [:p (str "Completed: " @count)]
            [input]
            [:ul (map task @tasks)]])))
