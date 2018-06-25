(ns andrethehunter.introduction-to-re-frame.view.live
  (:refer-clojure :exclude [eval])
  (:require
    [cljs.js :refer [empty-state eval js-eval]]
    [cljs.tools.reader :refer [read-string]]
    [oops.core :refer [oget]]
    [reagent.core :as r]))

(defn eval-str [s]
  (eval
    (empty-state)
    (read-string s)
    {:eval       js-eval
     :context    :expr}
    identity))

(defn view [src]
  (eval-str src))
