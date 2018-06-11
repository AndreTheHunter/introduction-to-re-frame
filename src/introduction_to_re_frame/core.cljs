(ns introduction-to-re-frame.core
  (:require
    [introduction-to-re-frame.slides :as slides]
    [re-frame.core :as rf]
    [reagent.core :as r]))

(rf/reg-event-db :initialize
  (constantly {:position 0
               :slides   [slides/title slides/about-me]}))

(rf/reg-event-db :next-slide
  (fn [{:keys [:position :slides] :as db}]
    (update db :position #(let [val (inc position)]
                            (if (< val (count slides))
                              val
                              position)))))

;(rf/reg-event-db :prev-slide
;  #(update % :position dec))

(rf/reg-sub :current-slide
  (fn [{:keys [:position :slides] :as db}]
    (get slides position)))

(defn root []
  (into [:section] @(rf/subscribe [:current-slide])))

(defn ^:dev/after-load render []
  (r/render [root] (js/document.getElementById "app")))

(defn ^:export init []
  (rf/dispatch-sync [:initialize])
  ;(rf/dispatch
  ;  [::rp/set-keydown-rules
  ;   {:event-keys []}])
  (render))
