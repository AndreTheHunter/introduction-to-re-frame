(ns introduction-to-re-frame.core
  (:require
    [introduction-to-re-frame.event :as event]
    [introduction-to-re-frame.view :as view]
    [re-frame.core :as rf]
    [reagent.core :as r]))

(when js/goog.DEBUG
  (defn ^:dev/before-load clear-console []
    (js/console.clear)))

(defn ^:dev/after-load render []
  (r/render [view/main] (js/document.getElementById "app")))

(defn ^:export init []
  (rf/dispatch-sync [::event/initialize])
  ;(rf/dispatch
  ;  [::rp/set-keydown-rules
  ;   {:event-keys []}])
  (render))
