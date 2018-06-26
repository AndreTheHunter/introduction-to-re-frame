(ns todo.sub
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub ::incomplete-tasks
  (fn [db]
    (remove :complete? (:tasks db))))

(re-frame/reg-sub ::complete-count
  (fn [db]
    (count (filter :complete? (:tasks db)))))
