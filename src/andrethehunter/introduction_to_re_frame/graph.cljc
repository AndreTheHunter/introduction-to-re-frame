(ns andrethehunter.introduction-to-re-frame.graph
  #?(:cljs (:require-macros andrethehunter.introduction-to-re-frame.graph))
  #?(:clj
     (:require
       [rhizome.viz :refer [graph->svg]])))

(defmacro graph-svg [g]
  (graph->svg (keys g) g
    :vertical? false
    :layout "circo"
    :options {"rank" "same;db;view;Reagent;DOM"}
    :node->descriptor (fn [n]
                        {:label (name n)})))
