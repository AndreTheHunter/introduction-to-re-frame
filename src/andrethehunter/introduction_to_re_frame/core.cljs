(ns andrethehunter.introduction-to-re-frame.core
  (:require
    [andrethehunter.introduction-to-re-frame.event :as event]
    [andrethehunter.introduction-to-re-frame.view :as view]
    [re-frame.core :as rf]
    [re-pressed.core :as rp]
    [reagent.core :as r]))

(when js/goog.DEBUG
  (defn ^:dev/before-load clear-console []
    (js/console.clear)))

(defn render []
  (r/render [view/main] (js/document.getElementById "slide-deck")))

(defn ^:dev/after-load reload []
  (rf/dispatch [::event/update])
  (render))

(defn ^:export init []
  (rf/dispatch-sync [::event/initialize])
  (rf/dispatch-sync [::rp/add-keyboard-event-listener "keyup"])
  (rf/dispatch [::rp/set-keyup-rules {:event-keys [[[::event/next]
                                                    [{:keyCode 39}] ;right-arrow
                                                    [{:keyCode 32}]] ;space
                                                   [[::event/prev]
                                                    [{:keyCode 37}]]]}]) ;left-arrow
  ;TODO add routing to update URL and history
  (render))
