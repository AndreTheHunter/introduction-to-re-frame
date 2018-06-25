(ns todo.events
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-event-db ::initialise
  (fn [db] (assoc db :tasks [])))

(re-frame/reg-event-db ::add-task [(re-frame/path :tasks)]
  (fn [tasks [_ task-name]]
    (if (seq task-name)
      (conj tasks {:id   (random-uuid)
                   :name task-name})
      tasks)))

(re-frame/reg-event-db ::complete-task [(re-frame/path :tasks) re-frame/trim-v]
  (fn [tasks [id]]
    (mapv #(if (= (:id %) id)
             (assoc % :complete? true)
             %)
      tasks)))
