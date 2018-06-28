(ns todo.event
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-event-db ::initialise
  (fn [db]
    (assoc db :tasks [])))

(re-frame/reg-event-db ::add-task
  [(re-frame/path :tasks)]
  (fn [tasks [event-id task]]
    (conj tasks (assoc task
                  :id (random-uuid)))))

(re-frame/reg-event-db ::complete-task
  [(re-frame/path :tasks) re-frame/trim-v]
  (fn [tasks [task]]
    (mapv #(if (= (:id %) (:id task))
             (assoc % :complete? true)
             %)
      tasks)))
