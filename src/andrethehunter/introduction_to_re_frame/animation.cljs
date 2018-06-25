(ns andrethehunter.introduction-to-re-frame.animation
  (:require
    ["react-transition-group/CSSTransition" :as CSSTransition]
    ["react-transition-group/Transition" :as Transition]
    ["react-transition-group/TransitionGroup" :as TransitionGroup]
    [reagent.core :as r]))

(def ^:private css-transition (r/adapt-react-class CSSTransition))
(def ^:private transition (r/adapt-react-class Transition))
(def ^:private transition-group (r/adapt-react-class TransitionGroup))

(defn stack
  ([children]
   (stack {} children))
  ([opts children]
   (let [state (r/atom {:prev nil})]
     (fn stack [{:keys [:direction :enter-timeout :leave-timeout]
                 :or   {direction     "right"
                        enter-timeout 200
                        leave-timeout 200}
                 :as   opts}
                children]
       [transition-group
        (mapv
          (fn [child]
            [transition {:class-names "stack"}
             child])
          children)]))))
