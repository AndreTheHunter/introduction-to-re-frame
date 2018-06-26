(ns todo.view
  (:require
    [oops.core :refer [oget]]
    [re-frame.core :as re-frame]
    [todo.component :refer [input task-list]]
    [todo.event :as events]
    [todo.sub :as subs]))

(defn main []
  (let [tasks (re-frame/subscribe [::subs/incomplete-tasks])
        count (re-frame/subscribe [::subs/complete-count])]
    (fn []
      [:div#example-todo
       [:p "Completed: " @count]
       [input {:placeholder "Add a task..."
               :on-save     (fn [task-name]
                              (when (seq task-name)
                                (re-frame/dispatch [::events/add-task {:name task-name}])))}]
       [task-list {:on-task-clicked (fn [task]
                                      (re-frame/dispatch [::events/complete-task task]))}
        @tasks]])))

