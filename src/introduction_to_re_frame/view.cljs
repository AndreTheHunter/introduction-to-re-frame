(ns introduction-to-re-frame.view
  (:require
    [introduction-to-re-frame.event :as event]
    [introduction-to-re-frame.sub :as sub]
    [re-frame.core :as rf]))

(defmulti render first)

(defmethod render :title [[_ [title & [sub-title]]]]
  [:div.centered.grid.align-center.justify-center
   [:div.title
    [:h1 title]
    (when sub-title
      [:h2 sub-title])]])

(defmethod render :heading [[_ heading]]
  [:h1 heading])

(defmethod render :points [[_ points]]
  (into [:ul] (map #(do [:li %]) points)))

(defmethod render :default [[_ v]] v)

(defn- ->slide [m]
  (map render m))

(defn main []
  (let [current-slide (rf/subscribe [::sub/current-slide])]
    (fn main []
      (let [current-slide @current-slide]
        (when current-slide
          (into
            [:div.slide {:on-click #(rf/dispatch [::event/next-slide])}]
            (->slide current-slide)))))))
