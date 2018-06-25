(ns andrethehunter.introduction-to-re-frame.view
  (:require
    [andrethehunter.introduction-to-re-frame.event :as event]
    [andrethehunter.introduction-to-re-frame.sub :as sub]
    [andrethehunter.introduction-to-re-frame.util :refer [noop]]
    [andrethehunter.introduction-to-re-frame.view.highlight :as highlight]
    [oops.core :refer [ocall]]
    [re-frame.core :as rf]))

(declare render)

(defn- render-title [[title & [sub-title]]]
  ;TODO is centered still needed?
  [:div.centered.grid.align-center.justify-center
   [:div.title
    [:h1 title]
    (when sub-title
      [:h2 sub-title])]])

(defn- render-heading [heading]
  (let [[heading subheading] (if (string? heading) [heading nil] heading)]
    [:div.heading
     [:h1 heading]
     (when subheading
       [:h2 subheading])]))

(defn- render-point [point]
  (cond
    (string? point) point
    (and (vector? point) (-> point first keyword?)) (render [(first point) (second point)])
    (vector? point) (into [:span] (map render-point point))
    :else point))

(defn- render-points [points]
  (into [:ul]
    (map #(do [:li (render-point %)]) points)))

(defn- render-js [js]
  [highlight/javascript js])

(defn- render-clj [clj]
  [highlight/clojure clj])

(defn- render-link [v]
  ;TODO simplify URL (remove protocol and trailing slash
  (let [[text url] (if (string? v) [v v] v)]
    [:a {:href   url
         :target "_blank"}
     text]))

(defn- render-img [url]
  [:img {:src url}])

(defn- render-svg [s]
  [:div {:dangerouslySetInnerHTML {:__html s}}])

(defn- render-live [component]
  [:div.live {:on-click #(ocall % "stopPropagation")}
   [component]])

(defn- render [[k v]]
  (let [f (case k
            :title render-title
            :heading render-heading
            :points render-points
            :link render-link
            :code/js render-js
            :code/clj render-clj
            :img render-img
            :svg render-svg
            :live render-live
            identity)]
    (f v)))

(defn- render-slide [slide]
  [:div.slide {:on-click #(rf/dispatch [::event/next])}
   (for [[k v :as entry] slide]
     ^{:key k} [render entry])])
;TODO footer with title (and progress?)

(defn main []
  (let [slide (rf/subscribe [::sub/current])]
    (fn main []
      [render-slide @slide])))
