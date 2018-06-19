(ns introduction-to-re-frame.core
  (:require
    [introduction-to-re-frame.event :as event]
    [introduction-to-re-frame.view :as view]
    [re-frame.core :as rf]
    [re-pressed.core :as rp]
    [reagent.core :as r]))

(when js/goog.DEBUG
  (defn ^:dev/before-load clear-console []
    (js/console.clear)))

(defn ^:dev/after-load render []
  (r/render [view/main] (js/document.getElementById "app")))

(defn ^:export init []
  (rf/dispatch-sync [::event/initialize])
  (rf/dispatch-sync [::rp/add-keyboard-event-listener "keyup"])
  (rf/dispatch [::rp/set-keyup-rules {:event-keys [[[::event/next-slide]
                                                    [{:which 39}] ;right-arrow
                                                    [{:which 32}]] ;space
                                                   [[::event/prev-slide]
                                                    [{:which 37}]]]}]) ;left-arrow
  (render))
