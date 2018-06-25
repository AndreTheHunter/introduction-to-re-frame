(ns todo.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub ::incomplete-tasks
  (fn [db]
    (filter #(not (:complete? %)) (:tasks db))))

(re-frame/reg-sub ::complete-count
  (fn [db]
    (count (filter :complete? (:tasks db)))))
